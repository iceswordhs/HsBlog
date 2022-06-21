package com.hs.service;

import com.hs.entity.JobBoard;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/6/21 21:47
 */
public interface KanbanService {
    List<JobBoard> getKanbanData();

    void saveBoards(List<JobBoard> boards);

    void clearAllData();
}
