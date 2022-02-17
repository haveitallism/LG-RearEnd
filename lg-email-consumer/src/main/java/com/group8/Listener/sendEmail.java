package com.group8.Listener;

import cn.hutool.extra.mail.MailUtil;
import com.group8.entity.LgNormalUser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author QY
 * @create 2022-02-17 14:53
 */

@Component
public class sendEmail {

    @RabbitListener(queues = "LgMail")
    public void sendEmail(LgNormalUser lgNormalUser){
        System.out.println(lgNormalUser);
        String code = lgNormalUser.getActiveCode();
        String email = lgNormalUser.getUserEmail();
        //String message = "<a href = http://localhost:8100/nUser/activeUser?code="+ code +">点击此链接进行激活❥</a> ";
        String text = "<a href='http://localhost:8100/nUser/activeUser?code="+ code + "'>点击此链接进行激活</a>";
        MailUtil.send("454476569@qq.com","旅鸽网站激活邮件",text,false);
    }

}
