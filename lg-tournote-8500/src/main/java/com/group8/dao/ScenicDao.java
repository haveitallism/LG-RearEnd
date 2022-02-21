package com.group8.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author acoffee
 * @create 2022-02-17 17:41
 */
@Mapper
@Repository
public interface ScenicDao {

    boolean uploadImg(@Param("scenicSpotUrl") String scenicSpotUrl,@Param("scenicId") int scenicId);
}
