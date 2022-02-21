package com.group8.dao;

import com.group8.entity.LgTravelnotes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author acoffee
 * @create 2022-02-21 16:06
 */
@Mapper
@Repository
public interface TravelNotesDao {

    LgTravelnotes findTravelNotesById(@Param("notesId") int notesId);
}
