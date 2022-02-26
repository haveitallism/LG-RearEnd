package com.group8.service;

import com.group8.dto.DownFile;
import com.group8.dto.UploadImg;
import com.group8.entity.LgScenicspot;

import java.net.MalformedURLException;
import java.util.List;

/**
 * @author acoffee
 * @create 2022-02-17 17:31
 */
public interface ScenicService {
    String uploadImg(UploadImg uploadImg);

    String downloadStrategy(DownFile file);

    String uploadStrategy(UploadImg uploadImg);

    boolean addScenicspot(LgScenicspot lgScenicspot);

    boolean updateScenicspot(LgScenicspot lgScenicspot);

    boolean deleteScenicspot(int scenicId);

    LgScenicspot findScenicspot(int scenicId);

    List<LgScenicspot> findAllScenicspot();

    List<LgScenicspot> findAllScenicspotByName(String keyword);

    List<LgScenicspot> findScenicByDownloadsNum();

    List<LgScenicspot> findLatestScenic();
}
