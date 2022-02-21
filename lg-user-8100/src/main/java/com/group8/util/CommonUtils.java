package com.group8.util;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author QY
 * @create 2022-02-19 14:57
 */


public class CommonUtils {

    /**
     * 判断对象里是否包含某个属性
     */

    public static Boolean isExistField(String field,Object obj){
        if (ObjectUtil.isNull(obj) || StrUtil.isBlank(field)) {
            return null;
        }
        Object o = JSON.toJSON(obj);
        JSONObject jsonObj = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObj = (JSONObject) o;
        }
        return jsonObj.containsKey(field);
    }
}
