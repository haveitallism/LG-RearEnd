package com.group8.utils;

import com.google.gson.Gson;
import com.group8.dto.UploadImg;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * 工具类：文件的上传与下载
 * @author acoffee
 * @create 2022-02-17 15:42
 */
public class QiniuUtils {

    public static String uploadFile(UploadImg uploadImg)  {
        String qiniuUrl = "r7futd8xx.hn-bkt.clouddn.com";
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "GMucqD1bf4zKiZSoHafMWR6nf9h0R1us1BxFRBxn";
        String secretKey = "9olNhZCBABDA9fl5WmPjeniPkdCZ-gnTK439CKCl";
        String bucket = "group8";
        String key = null;
        if (uploadImg.getFile().getOriginalFilename().endsWith(".jpg") || uploadImg.getFile().getOriginalFilename().endsWith(".jpeg")){
            key = getRandomCharacterAndNumber(10) + ".jpg";//生成随机文件名
        }else{
            key = getRandomCharacterAndNumber(10) + ".pdf";//生成随机文件名
        }
        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);
        //为后面拼接做准备
        String responseUrl = "";
        //使用流
        try{
            byte[] localFile = new byte[0];
            try {
                localFile = uploadImg.getFile().getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Response response = uploadManager.put(localFile,key,uptoken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //图片URl
            responseUrl = "http://"+responseUrl + qiniuUrl +"/"+ putRet.key;

        }catch (QiniuException e){
            Response r = e.response;
        }
        return responseUrl;
    }


    //随机生成字符串的方法，使传上去的每个文件名都不一样
    public static String getRandomCharacterAndNumber(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
                // int choice = 97; // 指定字符串为小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    public static Boolean downLoadFile(String fileUrl,String filePath) {
        BufferedInputStream bis = null;
        FileOutputStream fis= null;
        try {
            URL url = new URL(fileUrl);
            bis = new BufferedInputStream(url.openStream());
            fis = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
