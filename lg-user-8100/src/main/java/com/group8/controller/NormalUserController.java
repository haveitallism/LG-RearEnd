package com.group8.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.group8.dto.UserLoginForm;
import com.group8.entity.LgNormalUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.NormalUserService;
import com.group8.utils.JWTUtils;
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
     * @param lgNormalUser
     * @return 返回注册结果
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseEntity<String> register(@RequestBody LgNormalUser lgNormalUser) {
        int i = normalUserService.addNormalUser(lgNormalUser);
        if (i > 0) {
            return new ResponseEntity<>(200, "注册成功！", "请前往邮箱进行进一步验证！");
        } else {
            return new ResponseEntity<>(500, "注册失败！", "");
        }
    }

    /**
     * 用户登陆
     * @param userLoginForm 登陆信息类
     * @return 登陆结果
     */
    @GetMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public ResponseEntity<String> login(@RequestBody UserLoginForm userLoginForm) {
        LgNormalUser lgNormalUser = normalUserService.findByUsernameAndPwd(userLoginForm.getUserName(),
                                                                           userLoginForm.getPassword());
        if(lgNormalUser != null){
            String token = JWTUtils.sign(userLoginForm.getUserName(), userLoginForm.getPassword());
            return new ResponseEntity<>(200, "登陆成功！", token);
        }else{
            return new ResponseEntity<>(500, "登陆失败！", "");
        }
    }

    /**
     * 查询所有普通用户
     * @return 用户集合
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "用户查询", notes = "查询所有用户并分页")
    public ResponseEntity<List<LgNormalUser>> findAll(){
        List<LgNormalUser> lgNormalUserList = normalUserService.findAll();
        return new ResponseEntity<>(200, "查询成功！", lgNormalUserList);
    }

    /**
     * 根据id查询用户
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

    //@PostMapping("/collect")
    //@ApiOperation(value = "用户更新", notes = "根据id更新用户信息")


    @ApiOperation(value = "邮件发送", notes = "将邮件业务由消息队列中传出")
    @RequestMapping("/sendEmail")
    public String sendEmail(){
        LgNormalUser user = new LgNormalUser();
        user.setUserEmail("shqyshqy123@163.com");
        user.setActiveCode("12345");
        rabbitTemplate.convertAndSend("LG-mail-exchange","LgMail",user);
        return "发送邮件成功";
    }

    @ApiOperation(value = "用户激活", notes = "将用户状态码更改为1")
    @RequestMapping("/activeUser")
    public String checkActiveCode(String code){
        boolean b = normalUserService.checkActiveCode(code);
        if(b){
            return "验证成功";
        }else {
            return "验证失败";
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
        if (verify){
            return new ResponseEntity<>(200, "true", "true！");
        }else {
            return new ResponseEntity<>(500, "false", "false！");
        }
    }

}
