package com.group8.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.group8.dto.UserCollects;
import com.group8.dto.UserLoginForm;
import com.group8.entity.*;
import com.group8.service.NormalUserService;
import com.group8.util.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/17 10:49 星期四
 * @apiNote 普通用户Controller
 */
@Api(tags = "普通用户管理") //
@RestController
@RequestMapping("/nUser")
public class NormalUserController {

    @Autowired
    NormalUserService normalUserService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    CircleCaptcha captcha;


    /**
     * 用户注册
     *
     * @param lgNormalUser
     * @return 返回注册结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseEntity<String> register(@RequestBody LgNormalUser lgNormalUser) {
        int i = normalUserService.addNormalUser(lgNormalUser);
        if (i == 1) {
            return new ResponseEntity<>(200, "注册成功！请前往邮箱进行进一步验证！", "");
        } else if (i == -1) {
            return new ResponseEntity<>(500, "用户名已存在！请重新输入！", "");
        } else {
            return new ResponseEntity<>(500, "注册失败！", "");
        }
    }

    /**
     * 用户登陆
     *
     * @param userLoginForm 登陆信息类
     * @return 登陆结果
     */
    @GetMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public ResponseEntity<String> login(@RequestBody UserLoginForm userLoginForm) {
        String token = normalUserService.login(userLoginForm);
        if (token != null) {

            return new ResponseEntity<>(200, "登陆成功！", token);
        } else {
            return new ResponseEntity<>(500, "登陆失败！", "");
        }
    }

    /**
     * 查询所有普通用户
     *
     * @return 用户集合
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "用户查询", notes = "查询所有用户并分页")
    public ResponseEntity<List<LgNormalUser>> findAll() {
        List<LgNormalUser> lgNormalUserList = normalUserService.findAll();
        return new ResponseEntity<>(200, "查询成功！", lgNormalUserList);
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "用户查询", notes = "根据id查询用户")
    public ResponseEntity<LgNormalUser> findById(@PathVariable("id") int id){
        LgNormalUser lgNormalUser = normalUserService.findById(id);
        return new ResponseEntity<>(200, "查询成功！", lgNormalUser);
    }

    /**
     * 更新用户信息
     *
     * @param lgNormalUser 更新后的用户对象
     * @return 更新结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "用户更新", notes = "根据id更新用户信息")
    public ResponseEntity<String> update(@RequestBody LgNormalUser lgNormalUser){
        int i = normalUserService.update(lgNormalUser);
        if (i > 0) {
            return new ResponseEntity<>(200, "修改成功！", "");
        } else {
            return new ResponseEntity<>(500, "修改失败！", "");
        }
    }

    /**
     * 删除用户，用于注销用户的账户，删除用户信息
     *
     * @param id 用户id
     * @return 删除结果
     */
    @PostMapping("/deleteById/{id}")
    @ApiOperation(value = "用户删除", notes = "根据id删除用户")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        int i = normalUserService.deleteById(id);
        if (i > 0) {
            return new ResponseEntity<>(200, "删除成功！", "");
        } else {
            return new ResponseEntity<>(500, "删除失败！", "");
        }
    }

    //@PostMapping("/collect")
    //@ApiOperation(value = "用户更新", notes = "根据id更新用户信息")

    @RequestMapping("/activeUser")
    public ResponseEntity<String> checkActiveCode(String code) {
        boolean b = normalUserService.checkActiveCode(code);
        if (b) {
            return new ResponseEntity<>(200, "验证成功！", "");
        } else {
            return new ResponseEntity<>(200, "验证失败！", "");
        }
    }



    @GetMapping("/test")
    public ResponseEntity<String> test() {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("/Users/Comme_moi/Desktop/circle.png");
        return null;
    }


    @GetMapping("/test1/{code}")
    public ResponseEntity<String> test1(@PathVariable("code") String code) {
        //验证图形验证码的有效性，返回boolean值
        boolean verify = captcha.verify(code);
        if (verify) {
            return new ResponseEntity<>(200, "true", "true！");
        } else {
            return new ResponseEntity<>(500, "false", "false！");
        }
    }

    /**
     * 用户收藏(团游，景点，游记）
     */
    @GetMapping("/collect")
    public ResponseEntity<String> collectTravelNotes(@RequestBody Object collect){
        String collectJson = JSONObject.toJSONString(collect);
        //如果不包含userId
        if(Boolean.FALSE.equals(CommonUtils.isExistField("userId",collect))){
            return new ResponseEntity<>(500,"用户未登录");
        }
        //如果包含游记字段
        if (Boolean.TRUE.equals(CommonUtils.isExistField("notesId", collect))){
            int i = normalUserService.addTravelCollect(JSON.parseObject(collectJson, LgNormalUserTravelnotesCollect.class));
            if (i > 0) {
                return new ResponseEntity<>(200, "收藏游记成功！", "");
            } else {
                return new ResponseEntity<>(500, "收藏游记失败！", "");
            }
        }
        //如果包含团游字段
        if(Boolean.TRUE.equals(CommonUtils.isExistField("groupId", collect))){
            int i = normalUserService.addGroupCollect(JSON.parseObject(collectJson,LgNormalUserGroupCollect.class));
            if (i > 0) {
                return new ResponseEntity<>(200, "收藏团游成功！", "");
            } else {
                return new ResponseEntity<>(500, "收藏团游失败！", "");
            }
        }
        //如果包含景点
        if(Boolean.TRUE.equals(CommonUtils.isExistField("scenicId", collect))){
            int i = normalUserService.addScenicCollect(JSON.parseObject(collectJson,LgNormalUserScenicspotCollect.class));
            if (i > 0) {
                return new ResponseEntity<>(200, "收藏景点成功！", "");
            } else {
                return new ResponseEntity<>(500, "收藏景点失败！", "");
            }
        }
        return new ResponseEntity<>(500, "收藏失败！", "");
    }


    /**
     * 查询所有的收藏内容
     * 根据不同的分类 放入Redis的ZSet里面 统一设置  -key:"collect"  -value:"项目名字"  -score:时间
     */
    @GetMapping("/showAllCollects/{userId}")
    public ResponseEntity<List<UserCollects>> showAllCollects(@PathVariable("userId") int userId){
        List<UserCollects> list = normalUserService.showAllCollects(userId);
        if(!ObjectUtil.isNull(list)){
            return new ResponseEntity<>(200,"查询所有收藏成功");
        }else {
            return new ResponseEntity<>(500,"查询所有收藏失败");
        }
    }

}
