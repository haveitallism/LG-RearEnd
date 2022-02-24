package com.group8.controller;

import com.group8.entity.LgScenicspot;
import com.group8.entity.LgTravelnotes;
import com.group8.entity.ResponseEntity;
import com.group8.service.TravelNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author acoffee
 * @create 2022-02-21 15:59
 */
@RestController
@RequestMapping("/travelnotes")
public class TravelNotesController {

    @Autowired
    TravelNotesService travelNotesService;


    //根据id查询游记
    @RequestMapping("/findTravelNotesById/{notesId}")
    public ResponseEntity<LgTravelnotes> findTravelNotesById(@PathVariable int notesId){
        LgTravelnotes travelnotesInfo = travelNotesService.findTravelNotesById(notesId);
        if (travelnotesInfo != null) {
            return new ResponseEntity<LgTravelnotes>(200, "查询成功！", travelnotesInfo);
        } else {
            return new ResponseEntity<LgTravelnotes>(500,"查询失败！",null);
        }
    }

    //根据点赞量查询游记并降序排列
    @RequestMapping("/findTravelNotesByPraiseNum")
    public ResponseEntity<List<LgTravelnotes>> findTravelNotesByPraiseNum(){
        List<LgTravelnotes> lgTravelnotesList = travelNotesService.findTravelNotesByPraiseNum();
        if (lgTravelnotesList != null) {
            return new ResponseEntity<List<LgTravelnotes>>(200, "查询成功！", lgTravelnotesList);
        } else {
            return new ResponseEntity<List<LgTravelnotes>>(500,"查询失败！",null);
        }
    }

    //根据发布时间查询游记并降序排列
    @RequestMapping("/findLatestTravelNotes")
    public ResponseEntity<List<LgTravelnotes>> findLatestTravelNotes(){
        List<LgTravelnotes> lgTravelnotesList = travelNotesService.findLatestTravelNotes();
        if (lgTravelnotesList != null) {
            return new ResponseEntity<List<LgTravelnotes>>(200, "查询成功！", lgTravelnotesList);
        } else {
            return new ResponseEntity<List<LgTravelnotes>>(500,"查询失败！",null);
        }
    }
}
