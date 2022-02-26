package com.group8.service.Impl;

import com.group8.dao.ScenicDao;
import com.group8.dto.DownFile;
import com.group8.entity.LgScenicspot;
import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author acoffee
 * @create 2022-02-17 17:40
 */
@Service
public class ScenicServiceImpl implements ScenicService {

    @Autowired
    ScenicDao scenicDao;

    @Override
    public String uploadImg(UploadImg uploadImg) {
        String imgUrl = QiniuUtils.uploadFile(uploadImg);
//        boolean flag = scenicDao.uploadImg(imgUrl, uploadImg.getId());
        return imgUrl;
    }

    @Override
    public String uploadStrategy(UploadImg uploadImg) {
        String fileUrl = QiniuUtils.uploadFile(uploadImg);
//        boolean flag = scenicDao.uploadStrategy(fileUrl, uploadImg.getId());
        return fileUrl;
    }

    @Override
    public boolean addScenicspot(LgScenicspot lgScenicspot) {
        //加入当前时间
        long nowTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(nowTime);
        lgScenicspot.setCreatedTime(timestamp);
        boolean flag = scenicDao.addScenicspot(lgScenicspot);
        return flag;
    }


    @Override
    public boolean updateScenicspot(LgScenicspot lgScenicspot) {
        long nowTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(nowTime);
        lgScenicspot.setUpdatedTime(timestamp);
        boolean flag = scenicDao.updateScenicspot(lgScenicspot);
        return flag;
    }

    @Override
    public boolean deleteScenicspot(int scenicId) {
        boolean flag = scenicDao.deleteScenicspot(scenicId);
        return flag;
    }

    @Override
    public LgScenicspot findScenicspot(int scenicId) {
        LgScenicspot scenic = scenicDao.findScenic(scenicId);
        return scenic;
    }

    @Override
    public List<LgScenicspot> findAllScenicspot() {
        List<LgScenicspot> scenicspotList = scenicDao.findAllScenicspot();
        return scenicspotList;
    }

    @Override
    public List<LgScenicspot> findAllScenicspotByName(String keyword) {
        List<LgScenicspot> scenicspotList = scenicDao.findAllScenicspotByName(keyword);
        return scenicspotList;
    }

    @Override
    public List<LgScenicspot> findScenicByDownloadsNum() {
        return scenicDao.findScenicByDownloadsNum();
    }

    @Override
    public List<LgScenicspot> findLatestScenic() {
        return scenicDao.findLatestScenic();
    }

    @Override
    public String downloadStrategy(DownFile file) {
        LgScenicspot lgScenicspot = scenicDao.findScenic(file.getId());
        Boolean isFlag = QiniuUtils.downLoadFile(lgScenicspot.getStrategyUrl(), file.getSavePath() + lgScenicspot.getScenicName() + ".pdf");
        if (isFlag == true){
            scenicDao.updateDownloadsNum(file.getId());
        }
        return lgScenicspot.getScenicName() + ".pdf";
    }
}
