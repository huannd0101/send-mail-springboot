package com.example.mailsender.controller;

import com.example.mailsender.utils.Constant;
import com.example.mailsender.utils.FormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@RestController
public class SendMailController {

    @Autowired
    private JavaMailSender javaMailSender;

    //@ResponseBody
    @GetMapping("/send")
    public String demoSendMail() {
        //tạo MailMessage
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(Constant.MAIL_TO);
        simpleMailMessage.setSubject("This is message");
        simpleMailMessage.setText("This is content mail");

        //Send mail
        javaMailSender.send(simpleMailMessage);

        return "Send mail successfully";
    }


    @GetMapping("/send-att")
    public String demoSendMaiWithAttachment() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(Constant.MAIL_TO);
        helper.setSubject("This is subject");
        helper.setText("This is text");

        //demo thôi nhé :v
        //Trên thực tế thì sẽ lấy file từ form ở ví dụ dưới cùng
        String path1 = "C:\\Users\\Nguyen Dinh Huan\\OneDrive\\Desktop\\Admin\\500.html";
        String path2 = "C:\\Users\\Nguyen Dinh Huan\\OneDrive\\Desktop\\Admin\\404.html";

        FileSystemResource file1 = new FileSystemResource(new File(path1));
        FileSystemResource file2 = new FileSystemResource(new File(path2));

        helper.addAttachment(file1.getFilename(), file1);
        helper.addAttachment(file2.getFilename(), file2);

        javaMailSender.send(message);

        return "Send mail successfully";
    }

    @GetMapping("/send-html")
    public String sendMailHTML() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = "<h3 style='color:red;'>Content width html</h3>"
                +"<img style='width:300px' src='https://res.cloudinary.com/dlqdesqni/image/upload/v1631257854/i7ujvuudc9pctszm48tn.jpg'>";

        message.setContent(htmlMsg, "text/html");
        helper.setSubject("This is subject");
        helper.setTo(Constant.MAIL_TO);

        javaMailSender.send(message);

        return "send mail successfully";
    }

    //send mail from form html
    @GetMapping("/")
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("formRequest",new FormRequest());
        return mav;
    }

    @PostMapping("/send-form")
    public String sendMailForm(@ModelAttribute("formRequest") FormRequest formRequest) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(formRequest.getSubject());
        helper.setTo(formRequest.getMailTo());
        helper.setText(formRequest.getContent());

        for(MultipartFile file : formRequest.getFiles()){
            helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
        }

        javaMailSender.send(message);

        return "send mail successfully";
    }

}
