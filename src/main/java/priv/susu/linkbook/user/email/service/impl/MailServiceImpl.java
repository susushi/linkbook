package priv.susu.linkbook.user.email.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.email.MailConstant;
import priv.susu.linkbook.user.email.service.MailService;
import priv.susu.linkbook.user.service.UserService;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger EMAIL_LOG = LoggerFactory.getLogger("EMAIL_LOG");

	@Value("${linkbook.mail.from}")
	private String from = "";

	@Resource
	private JavaMailSender mailSender;

	@Resource
	private UserService userService;

	private static final String subject = "linkbook欢迎邮件";

	@Async
	@Override
	public void sendWelcomeMail(User toUser) {

		String toMail = toUser.getUsername();
		String mailContent = MailConstant.WELCOME_CONTENT;

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(from);
		smm.setTo(toMail);
		smm.setSubject(subject);
		smm.setText(mailContent);
		try {
			mailSender.send(smm);
		} catch (MailException e) {
			EMAIL_LOG.error("send verify mail failed. toUser: " + toUser + ". mailParam: " + smm.toString(), e);
		}
	}

}
