package com.group8.dao;

import com.group8.entity.LgScenicspot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author acoffee
 * @create 2022-02-17 17:41
 */
@Mapper
@Repository
public interface ScenicDao {

    boolean uploadImg(@Param("scenicSpotUrl") String scenicSpotUrl,@Param("scenicId") int scenicId);

    LgScenicspot findScenic(int scenicId);

    boolean uploadStrategy(@Param("strategyUrl") String imgUrl, @Param("scenicId") int scenicId);

    boolean updateDownloadsNum(int id);

    boolean addScenicspot(LgScenicspot lgScenicspot);

    boolean updateScenicspot(LgScenicspot lgScenicspot);

    boolean deleteScenicspot(int scenicId);

    List<LgScenicspot> findAllScenicspot();

    List<LgScenicspot> findAllScenicspotByName(@Param("keyword") String keyword);

    List<LgScenicspot> findScenicByDownloadsNum();

    List<LgScenicspot> findLatestScenic();
}
