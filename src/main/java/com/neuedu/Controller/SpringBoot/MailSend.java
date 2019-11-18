//package com.neuedu.Controller.SpringBoot;
//
//        import com.neuedu.common.ServerResponse;
//        import org.slf4j.Logger;
//        import org.slf4j.LoggerFactory;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.mail.SimpleMailMessage;
//        import org.springframework.mail.javamail.JavaMailSender;
//        import org.springframework.stereotype.Controller;
//        import org.springframework.web.bind.annotation.RequestMapping;
//        import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/mail")
//public class MailSend {
//    /* *
//     * @Description  http://localhost:8888/sendMail
//     * @author dalaoyang
//     * @email yangyang@dalaoyang.cn
//     * @method 发送文本邮件
//     * @date
//     * @param
//     * @return
//     */
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @RequestMapping("/sendMail")
//    public ServerResponse sendMail() {
//        String sender="love_zyy_hui@163.com";
//        String receiver="1161107215@qq.com";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(sender);
//        message.setTo(receiver);
//        message.setSubject("大老nihao1");
//        message.setText("你好你好你好！");
//        try {
//            javaMailSender.send(message);
//            logger.info("简单邮件已经发送。");
//        } catch (Exception e) {
//            logger.error("发送简单邮件时发生异常！", e);
//        }
//        return ServerResponse.createServerResponseBySucces(null ,"发送成功");
//    }
//}
