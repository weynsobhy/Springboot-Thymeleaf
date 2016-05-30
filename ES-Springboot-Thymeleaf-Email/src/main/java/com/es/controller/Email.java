package com.es.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.es.AppConfig;

@Controller
@RequestMapping("")
public class Email {
	
	@RequestMapping(value = "/email/send", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void SimpleMailMessage(@RequestParam("file") MultipartFile files)
			throws IOException, MessagingException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		// attachment mulai
		String fileName = null;

		JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true);
		if (files != null) {
		fileName = files.getOriginalFilename();
		String extension = FilenameUtils.getExtension(fileName);
		byte[] bytes = files.getBytes();
		BufferedOutputStream buffStream = new BufferedOutputStream(
				new FileOutputStream(new File("/home/sibos/Desktop/SkripsiDokumen/email/" + fileName)));
		buffStream.write(bytes);
		buffStream.close();
//		FileSystemResource file = new FileSystemResource(
//				new File("directory file folder" + fileName));
		FileSystemResource file = new FileSystemResource(
				new File("/home/user/Desktop/Dokumen/email/" + fileName));
		mailMsg.addAttachment(fileName, file);
		}
		mailMsg.setFrom("email@gmail");
		mailMsg.setTo("email@gmail");
		mailMsg.setSubject("Email ini berisi file");
		mailMsg.setText("Assalamualaikum");
		mailSender.send(mimeMessage);
		System.out.println("---Alhamdulillah---");
		// attachment selesai

	}

}
