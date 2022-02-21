package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.UserLoginForm;
import com.group8.dto.UserQueryCondition;
import com.group8.entity.LgBussinessUser;
import com.group8.entity.LgNormalUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.BusinessUserService;
import com.group8.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author QY
 * @create 2022-02-17 19:01
 */

@Api(tags = "商家用户管理")
@RestController
@RequestMapping("/bUser")
public class BusinessUserController {

    @Autowired
    BusinessUserService businessUserService;

    /**
     * 用户注册
     * @return 返回注册结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseEntity<String> register(@RequestBody LgBussinessUser lgBussinessUser) {
        int i = businessUserService.addBusinessUser(lgBussinessUser);
        if (i > 0) {
            return new ResponseEntity<>(200, "注册成功！", "请前往邮箱进行进一步验证！");
        } else {
            return new ResponseEntity<>(200, "注册失败！", "");
        }
    }

    /**
     * 用户名验证
     * @return
     */
    @PostMapping("/checkName/{username}")
    @ApiOperation(value = "用户名重复验证",notes = "用户名重复验证")
    public ResponseEntity<String> checkName(@PathVariable("username") String username){
        int i = businessUserService.checkName(username);
        if(i == 0){
            return new ResponseEntity<>(500,"用户名验证重复 已有此用户名存在");
        }else{
            return new ResponseEntity<>(200,"用户名可用");
        }
    }


    @PostMapping("/login")
    @ApiOperation(value = "用户登陆",notes = "用户登陆验证")
    public ResponseEntity<String> login(@RequestBody UserLoginForm userLoginForm){
        System.out.println(userLoginForm);
        LgBussinessUser lgBussinessUser = businessUserService.login(userLoginForm.getUserName(),userLoginForm.getPassword());
        if(lgBussinessUser != null){
            String token = JWTUtils.sign(lgBussinessUser.getBussName(), lgBussinessUser.getBussPassword());
            return new ResponseEntity<>(200,"登陆成功",token);
        }else{
            return new ResponseEntity<>(200,"用户名或密码错误",null);
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
    public ResponseEntity<LgBussinessUser> getInfo(@PathVariable("token") String token) {
        LgBussinessUser bussinessUser = businessUserService.getInfo(token);
        if (bussinessUser != null) {
            return new ResponseEntity<>(200, "获取成功！", bussinessUser);
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
        boolean flag = businessUserService.logout(token);
        if (flag) {
            return new ResponseEntity<>(200, "登出成功！", "");
        } else {
            return new ResponseEntity<>(500, "登出失败！", "");
        }
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "用户查询", notes = "查询所有用户并分页")
    public ResponseEntity<PageInfo<LgBussinessUser>> findAll(@RequestBody UserQueryCondition<LgBussinessUser> userQueryCondition){
        PageHelper.startPage(userQueryCondition.getPage(),userQueryCondition.getLimit());
        List<LgBussinessUser> list = businessUserService.findAll(userQueryCondition.getUser());
        System.out.println(list);
        PageInfo<LgBussinessUser> lgBussinessUserPageInfo = new PageInfo<>(list);
        return new ResponseEntity<PageInfo<LgBussinessUser>>(200, "查询成功！",lgBussinessUserPageInfo);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "用户查询", notes = "根据id查询用户")
    public ResponseEntity<LgBussinessUser> findById(@PathVariable("id") int id){
        LgBussinessUser lgBussinessUser = businessUserService.findById(id);
        return new ResponseEntity<>(200, "查询成功！", lgBussinessUser);
    }

    /**
     * 更新用户信息
     * @param lgBussinessUser 更新后的用户对象
     * @return 更新结果
     */
    @PostMapping("/update")
    @ApiOperation(value = "用户更新", notes = "根据id更新用户信息")
    public ResponseEntity<String> update(@RequestBody LgBussinessUser lgBussinessUser){
        int i = businessUserService.update(lgBussinessUser);
        if (i > 0) {
            return new ResponseEntity<>(200, "修改成功！", "");
        } else {
            return new ResponseEntity<>(200, "修改失败！", "");
        }
    }

    /**
     * 激活商铺用户
     */
    @RequestMapping("/activeUser")
    public ResponseEntity<String> checkActiveCode(String code) {
        boolean b = businessUserService.checkActiveCode(code);
        if (b) {
            return new ResponseEntity<>(200, "激活成功！", "");
        } else {
            return new ResponseEntity<>(200, "激活失败！", "");
        }
    }
}
