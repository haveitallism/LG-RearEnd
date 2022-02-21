package com.group8.service.Impl;

import com.group8.dao.ScenicDao;
import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.utils.QiuniuUtils;
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
        String imgUrl = QiuniuUtils.uploadPicture(uploadImg);
        boolean flag = scenicDao.uploadImg(imgUrl, uploadImg.getId());
        return imgUrl;
    }
}
