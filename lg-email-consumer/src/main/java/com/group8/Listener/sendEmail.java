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
    public void sendEmail(LgNormalUser lgNormalUser) {
        String code = lgNormalUser.getActiveCode();
        String email = lgNormalUser.getUserEmail();
        String text = "<span style='font-size: 20px; font-weight: bold; margin: 30px; line-height: 80px;'>账户激活</span>" +
                "<img style='position: relative; top: -5px; left: -20px;' width='48px' height='48px' src='https://gitee.com/cdlycode/oss/raw/master/uPic/2022-02/18-113049.png' /><br>" +
                "<span style='margin-left: 30px; line-height: 30px;'>" + lgNormalUser.getUserName() + "您好！感谢您注册本网站...</br>" +
                "<span style='margin-left: 30px; line-height: 30px;'>请点击下面链接激活账号，30分钟有效，链接只能使用一次，请尽快激活！</span></br>" +
                "<span style='margin-left: 30px; line-height: 30px;'><a href='http://localhost:8100/nUser/activeUser?code=" + code + "'>点击这里</a></span></br></br></br></br>" +
                "<span style='float: right; margin-right: 30px;'>旅鸽在线</span><br>" +
                "<span style='float: right; margin-right: 30px;'>" + lgNormalUser.getCreatedTime().toString().split("\\.")[0] + "</span>";
        MailUtil.send(email, "旅鸽网站激活邮件", text, true);
    }

}
