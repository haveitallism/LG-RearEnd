package com.group8.service;

import com.group8.entity.LgTravelnotes;

import java.util.List;

/**
 * @author acoffee
 * @create 2022-02-21 16:01
 */
public interface TravelNotesService {
    LgTravelnotes findTravelNotesById(int notesId);

    List<LgTravelnotes> findTravelNotesByPraiseNum();

    boolean addTravelNotes(LgTravelnotes travelnotes);

    List<LgTravelnotes> findLatestTravelNotes();

    List<LgTravelnotes> searchByKeyword(String keyword);
}
