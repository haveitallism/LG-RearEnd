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

import java.io.IOException;
import java.util.Random;

/**
 * @author acoffee
 * @create 2022-02-17 15:42
 */
public class QiuniuUtils {

    public static String uploadPicture(UploadImg uploadImg)  {
        String qiniuUrl = "r7futd8xx.hn-bkt.clouddn.com";
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "GMucqD1bf4zKiZSoHafMWR6nf9h0R1us1BxFRBxn";
        String secretKey = "9olNhZCBABDA9fl5WmPjeniPkdCZ-gnTK439CKCl";
        String bucket = "group8";
        String key = getRandomCharacterAndNumber(10) + ".jpg";//生成随机文件名
        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);
        String responseUrl = "";
        try{
            byte[] localFile = new byte[0];
            try {
                localFile = uploadImg.getFile().getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Response response = uploadManager.put(localFile,key,uptoken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            responseUrl = "http://"+responseUrl + qiniuUrl +"/"+ putRet.key;

        }catch (QiniuException e){
            Response r = e.response;
        }
        return responseUrl;
    }


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
}
