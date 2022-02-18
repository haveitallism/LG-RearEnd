package com.group8.controller;

import com.group8.dto.UserLoginForm;
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
        int i = businessUserService.addNormalUser(lgBussinessUser);
        if (i > 0) {
            return new ResponseEntity<>(200, "注册成功！", "请前往邮箱进行进一步验证！");
        } else {
            return new ResponseEntity<>(500, "注册失败！", "");
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆",notes = "用户登陆验证")
    public ResponseEntity<String> login(@RequestBody UserLoginForm userLoginForm){
        System.out.println(userLoginForm);
        LgBussinessUser lgBussinessUser = businessUserService.login(userLoginForm.getUserName(),userLoginForm.getPassword());
        if(lgBussinessUser != null){
            String token = JWTUtils.sign(userLoginForm.getUserName(), userLoginForm.getPassword());
            return new ResponseEntity<>(200,"登陆成功",token);
        }else{
            return new ResponseEntity<>(200,"用户名或密码错误",null);
        }
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "用户查询", notes = "查询所有用户并分页")
    public ResponseEntity<List<LgBussinessUser>> findAll(){
        List<LgBussinessUser> lgBussinessUserList = businessUserService.findAll();
        return new ResponseEntity<>(200, "查询成功！", lgBussinessUserList);
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

}
