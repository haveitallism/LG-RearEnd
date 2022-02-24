package com.group8.service.Impl;

import com.group8.dao.TravelNotesDao;
import com.group8.entity.LgTravelnotes;
import com.group8.service.TravelNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author acoffee
 * @create 2022-02-21 16:01
 */
@Service
public class TravelNotesServiceImpl implements TravelNotesService {

    @Autowired
    TravelNotesDao travelNotesDao;

    @Override
    public LgTravelnotes findTravelNotesById(int notesId) {
        LgTravelnotes notesInfo = travelNotesDao.findTravelNotesById(notesId);
        return notesInfo;
    }

    @Override
    public List<LgTravelnotes> findTravelNotesByPraiseNum() {
        List<LgTravelnotes> lgTravelnotesList =  travelNotesDao.findTravelNotesByPraiseNum();
        return lgTravelnotesList;
    }

    @Override
    public List<LgTravelnotes> findLatestTravelNotes() {
        return travelNotesDao.findLatestTravelNotes();
    }
}
