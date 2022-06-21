package com.hs.repository;

import com.hs.entity.JobBoard;
import com.hs.entity.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/6/19 0:40
 */
@Mapper
public interface JobBoardMapper {
    List<JobBoard> getBoardJob();

    void addBoardJob(JobBoard board);

    void addNote(Note note);

    void addJobBoardNote(Integer boardId,Integer noteId);

    void clearBoardTable();
    void clearNotesTable();
    void clearBoardNoteTable();
}
