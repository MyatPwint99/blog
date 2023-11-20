package com.ojt.blog.web.controller;

import com.ojt.blog.web.form.PersonForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@RequestMapping("/mail")
public class MailController {
    @Autowired
    JavaMailSender javaMailSender;

    /***
     *<h2>Send Mail</h2>
     * <p>Pass Person Data To Service</p>
     *
     * @param personForm
     * @return String
     */
    @GetMapping("/send")
    public void sendMail(@ModelAttribute PersonForm personForm) throws MessagingException {
//        ****************************
//        Usage with simple mail message
//        ******************************
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setText("this is mail body");
//        mailMessage.setSubject("subject");
//        ****************************


//        ****************************
//        Usage with mimemessage directly
//        ****************************

        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom();
        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p><button> Register now </button>";
        message.setContent(htmlContent, "text/html; charset=utf-8");
//        ****************************


//        ****************************
//        Usage with helper
//        ****************************
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("noreply@baeldung.com");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(text);
//        var pathToAttachment = "/filepath";
//        FileSystemResource file
//                = new FileSystemResource(new File(pathToAttachment));
//        helper.addAttachment("Invoice", file);
//        *********************************
        javaMailSender.send(message);
        System.out.println("*******Mail is Sending*******");
    }

}
