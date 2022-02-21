package com.group8.controller;

import cn.hutool.captcha.CircleCaptcha;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.UploadImg;
import com.group8.dto.UserQueryCondition;
import com.group8.entity.LgManageUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.ManageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/20 15:23 星期日
 * @apiNote
 */
@Api(tags = "官方用户管理")
@RestController
@RefreshScope
@RequestMapping("/mUser")
public class ManageUserController {


    @Autowired
    ManageUserService manageUserService;

    CircleCaptcha captcha;

    /**
     * 用户添加
     *
     * @param lgManageUser
     * @return 返回添加结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户添加", notes = "用户添加")
    public ResponseEntity<String> register(@RequestBody LgManageUser lgManageUser) {
        LgManageUser manageUser = manageUserService.checkUserName(lgManageUser.getUserName());
        if (manageUser == null) {
            int i = manageUserService.addManageUser(lgManageUser);
            if (i == 1) {
                return new ResponseEntity<>(200, "添加成功！", "");
            } else {
                return new ResponseEntity<>(500, "注册失败！", "");
            }
        } else {
            return new ResponseEntity<>(500, "用户名已存在！请重新输入！", "");
        }
    }

    /**
     * 用户名重复验证
     *
     * @param userName 待注册用户名
     * @return 返回验证结果
     */
    @PostMapping("/register/{userName}")
    @ApiOperation(value = "用户名验证", notes = "验证用户名是否重复")
    public ResponseEntity<String> checkUserName(@PathVariable("userName") String userName) {
        LgManageUser manageUser = manageUserService.checkUserName(userName);
        if (manageUser == null) {
            return new ResponseEntity<>(200, "用户名可用", "");
        } else {
            return new ResponseEntity<>(500, "用户名已存在！请重新输入！", "");
        }
    }

    /**
     * 用户登陆获取token
     *
     * @param lgManageUser 用户信息类
     * @return 登陆结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public ResponseEntity<String> login(@RequestBody LgManageUser lgManageUser) {
        String token = manageUserService.login(lgManageUser);
        if (token != null) {
            return new ResponseEntity<>(200, "登陆成功！", token);
        } else {
            return new ResponseEntity<>(500, "登陆失败！", "");
        }
    }

    /**
     * 根据token获取用户信息
     *
     * @param token 用户token
     * @return 用户信息
     */
    @PostMapping("/getInfo/{token}")
    @ApiOperation(value = "获取用户信息", notes = "根据token获取用户信息")
    public ResponseEntity<LgManageUser> getInfo(@PathVariable("token") String token) {
        LgManageUser manageUser = manageUserService.getInfo(token);
        if (manageUser != null) {
            return new ResponseEntity<>(200, "获取成功！", manageUser);
        } else {
            return new ResponseEntity<>(500, "获取失败！", null);
        }
    }

    /**
     * 用户登出
     *
     * @param token 用户信息token
     * @return 登出结果
     */
    @PostMapping("/logout/{token}")
    @ApiOperation(value = "用户登出", notes = "用户登出")
    public ResponseEntity<String> logout(@PathVariable("token") String token) {
        boolean flag = manageUserService.logout(token);
        if (flag) {
            return new ResponseEntity<>(200, "登出成功！", "");
        } else {
            return new ResponseEntity<>(500, "登出失败！", "");
        }
    }

    /**
     * 查询所有普通用户
     *
     * @return 用户集合
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "用户查询", notes = "根据条件查询用户并分页")
    public ResponseEntity<PageInfo<LgManageUser>> findByCondition(@RequestBody UserQueryCondition<LgManageUser> userQueryCondition) {
        PageHelper.startPage(userQueryCondition.getPage(), userQueryCondition.getLimit());
        List<LgManageUser> lgManageUserList = manageUserService.findByCondition(userQueryCondition.getUser());
        PageInfo<LgManageUser> pageInfo = new PageInfo<>(lgManageUserList);
        return new ResponseEntity<>(200, "查询成功！", pageInfo);
    }

    /**
     * 查询单个用户
     *
     * @return 用户对象
     */
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "用户查询", notes = "根据id查询用户")
    public ResponseEntity<LgManageUser> findById(@PathVariable("id") int id) {
        LgManageUser manageUser = manageUserService.findById(id);
        return new ResponseEntity<>(200, "查询成功！", manageUser);
    }

    @PostMapping("/updateHeadImg")
    @ApiOperation(value = "修改头像", notes = "根据id修改用户头像")
    public ResponseEntity<String> updateHeadImg(UploadImg uploadImg) {
        int i = manageUserService.updateHeadImg(uploadImg);
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
        int i = manageUserService.deleteById(id);
        if (i > 0) {
            return new ResponseEntity<>(200, "删除成功！", "");
        } else {
            return new ResponseEntity<>(500, "删除失败！", "");
        }
    }

}
