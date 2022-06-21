package com.hs.entity;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/6/19 0:32
 */
public class JobBoard {
    private int id;
    private boolean initial;
    private String title;
    private List<Note> notes;

    public JobBoard(){}

    public JobBoard(boolean initial,String title,List<Note> notes){
        this.initial=initial;
        this.title=title;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "JobBoard{" +
                "id=" + id +
                ", initial=" + initial +
                ", title='" + title + '\'' +
                ", notes=" + notes +
                '}';
    }
}
