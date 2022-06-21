<template>
  <div class="board" >
    <p>
      <input class="board-title" v-model="board.title" />
    </p>
    <div class="notes">
      <div class="empty-state" v-show="!board.notes.length">this is a very empty screen</div>
      <note v-for="note in board.notes" :key="note.id" :content="note.text"
        :type="note.noteType" :position="note.position"
            :position-x="note.positionX" :position-y="note.positionY"
            :id="note.id" :note-size="note.noteSize" :note-size-h="note.noteSizeH" :note-size-w="note.noteSizeW"
        :font-size="note.fontSize" :votes="note.votes" :order="note.order" :active="note.id === activeDrag"
        @update="updateNote" @stop-drag="stopDrag" @start-drag="startDrag">
        </note>
    </div>
  </div>
</template>

<script>
import Note from './Note'
import Positioner from '@/utils/kanban/positioner.js'
import bus from '@/utils/kanban/bus.js'

export default {
  props: {
    board: {
      type: Object,
      required: true
    }
  },

  data: () => ({
    activeDrag: null
  }),

  beforeMount () {
    this.positioner = new Positioner()
    bus.$on('reset-active', () => {
      this.activeDrag = null
    })
  },

  watch: {
    'board.notes': function (notes) {
      this.positioner.setGrid(notes)
    }
  },

  methods: {
    startDrag (id) {
      var maxOrder = this.getMaxOrder()
      var note = this.getNoteById(id)

      // Set as active
      this.activeDrag = id

      // This note already is the top one, dont add 1
      if (note.order === maxOrder && maxOrder > 0) {
        return
      } else {
        this.updateNote(id, {order: maxOrder + 1})
      }
    },
    stopDrag (id) {
      this.updateNote(id, {active: false})
    },

    getNoteById (id) {
      return this.board.notes.find(function (note) {
        return id === note.id
      })
    },

    updateNote (id, update) {
      var note = this.getNoteById(id)
      if (note) {
        // The whole board is not "initial" anymore
        if (this.board.initial) { delete this.board.initial }

        // Update note properties
        Object.assign(note, update)
      } else {
        throw new Error('Where\'s the note!?')
      }
      return note
    },

    getMaxOrder () {
      return this.board.notes.reduce(function (prev, value) {
        if (typeof value.order === 'undefined') { return prev }
        return (prev > value.order ? prev : value.order)
      }, 0)
    },

    addNote (type) {
      var placeholderText
      var terciary
      switch (type) {
        case 'improvement':
          placeholderText = '近期需要提升的'
          terciary = 2
          break
        case 'neutral':
          placeholderText = '只是提醒一下'
          terciary = 1
          break
        case 'positive':
          placeholderText = '近期值得骄傲的'
          terciary = 0
          break
      }

      // Note default props
      var note = {
        text: placeholderText,
        // note_type: type,
        // 修改note_type为noteType
        noteType: type,
        position: this.positioner.getPositionforNew(terciary),
        positionX:this.positioner.getPositionforNew(terciary).x,
        positionY:this.positioner.getPositionforNew(terciary).y,
        noteSize: {w: 200, h: 150},
        noteSizeW: 200,
        noteSizeH: 150,
        fontSize: 1,
        votes: 0,
        order: this.getMaxOrder() + 1,
        id: Math.round(Math.random() * 100000)
      }

      this.board.notes.push(note)
    },

    reArrange () {
      // 将笔记赋给创建的positioner
      this.positioner.setState(this.board.notes)
      this.positioner.reArrange()
    }
  },

  components: {
    Note
  }
}
</script>
