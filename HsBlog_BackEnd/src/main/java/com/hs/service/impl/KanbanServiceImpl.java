package com.hs.service.impl;

import com.hs.entity.JobBoard;
import com.hs.entity.Note;
import com.hs.repository.JobBoardMapper;
import com.hs.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/6/21 21:48
 */
@Service
public class KanbanServiceImpl implements KanbanService {
    @Autowired
    JobBoardMapper mapper;

    @Override
    public List<JobBoard> getKanbanData() {
        List<JobBoard> boardJob = mapper.getBoardJob();
        return boardJob;
    }

    @Transactional
    @Override
    public void saveBoards(List<JobBoard> boards) {
        clearAllData();
        for (JobBoard board : boards) {
            mapper.addBoardJob(board);
            List<Note> notes = board.getNotes();
            for (Note note : notes) {
                mapper.addNote(note);
                mapper.addJobBoardNote(board.getId(), note.getId());
            }
        }
    }

    @Override
    public void clearAllData() {
        mapper.clearBoardTable();
        mapper.clearNotesTable();
        mapper.clearBoardNoteTable();
    }
}
