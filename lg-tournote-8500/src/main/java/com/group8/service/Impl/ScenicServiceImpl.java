package com.group8.service.Impl;

import com.group8.dao.ScenicDao;
import com.group8.dto.DownFile;
import com.group8.entity.LgScenicspot;
import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        boolean flag = scenicDao.uploadImg(imgUrl, uploadImg.getId());
        return imgUrl;
    }

    @Override
    public String uploadStrategy(UploadImg uploadImg) {
        String imgUrl = QiniuUtils.uploadFile(uploadImg);
        boolean flag = scenicDao.uploadStrategy(imgUrl, uploadImg.getId());
        return uploadImg.getFile().getOriginalFilename();
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
