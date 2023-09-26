package com.firdose.ars.service.impl;

import com.firdose.ars.dto.EmailInfo;
import com.firdose.ars.dto.User;
import com.firdose.ars.exceptions.UserNotFoundException;
import com.firdose.ars.repository.UserRepository;
import com.firdose.ars.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    private static final Log log= LogFactory.getLog(UserServiceImp.class);

    @Autowired
    private JavaMailSender mailSender;

    private String mailFrom="shaikfirdose6652@gmail.com";

    EmailInfo email=new EmailInfo(mailFrom,"Your tickets booked successfully\n\n\nThanks for choosing our applicatoion","Email from Spring","D:\\Photos\\Wonderla\\IMG-20191106-WA0112.jpg");

    public void addUserBookings(User user1) throws Exception {
        log.info("User booked successfully "+user1.getUserName());
        userRepository.save(user1);

//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setFrom(mailfrom);
//		message.setTo(email.getToEmail());
//		message.setText(email.getBody());
//		message.setSubject(email.getSubject());
//
//		mailsender.send(message);
        try {
            MimeMessage mimemessage=mailSender.createMimeMessage();
            MimeMessageHelper mimemessagehelper;
            mimemessagehelper=new MimeMessageHelper(mimemessage, true);
            mimemessagehelper.setTo(email.getToEmail());
            mimemessagehelper.setText(email.getBody());
            mimemessagehelper.setSubject(email.getSubject());

            FileSystemResource file=new FileSystemResource(new File(email.getAttachment()));

            mimemessagehelper.addAttachment(file.getFilename(), file);

            mailSender.send(mimemessage);
        }
        catch(Exception e) {

        }

    }
    public User viewUserBookings(String username) throws UserNotFoundException {
        if(userRepository.existsById(username)) {
            log.debug("User booking found "+username);
            return userRepository.findById(username).get();
        }
        else {
            log.error("User not found "+username);
            throw new UserNotFoundException("Username not found");
        }
    }
    public User updateUserBookings(User user) {
        log.info("user updated successfully "+user.getUserName());
       return userRepository.save(user);

    }

    public boolean deleteUserBookings(String username) {
        if(userRepository.existsById(username)) {
            log.info("User bookings deleted "+username);
            userRepository.deleteById(username);
            return true;
        }
        else {
            return false;

        }

    }
    public List<User> viewAllBookings() {
        return userRepository.findAll();
    }
}