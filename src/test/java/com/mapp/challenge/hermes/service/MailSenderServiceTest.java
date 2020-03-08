package com.mapp.challenge.hermes.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mapp.challenge.hermes.model.Mail;
import com.mapp.challenge.hermes.service.MailSenderService;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSenderServiceTest {

  private JavaMailSender mailer = mock(JavaMailSender.class);
  private Mail mail = mock(Mail.class);
  private MimeMessage mimeMessage = mock(MimeMessage.class);

  private MailSenderService mailSenderService = new MailSenderService(mailer);

  @Before
  public void setUp(){
    when(mail.getSender()).thenReturn("sender");
    when(mail.getRecipient()).thenReturn("recipient");
    when(mail.getSubject()).thenReturn("subject");
    when(mail.getBody()).thenReturn("body");
    when(mailer.createMimeMessage()).thenReturn(mimeMessage);
  }

  @Test
  public void shouldSendMail(){
    try {
      URI uri = new URI("https://google.com/robots.txt");
      when(mail.getAttachment()).thenReturn(uri);
      String result = mailSenderService.sendMail(mail);
      assertEquals("Successful", result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenURINotAbsolute() throws Exception {
      URI uri = new URI("///");
      when(mail.getAttachment()).thenReturn(uri);
      String result = mailSenderService.sendMail(mail);

  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForNullSender() throws Exception {
    URI uri = new URI("https://google.com/robots.txt");
    when(mail.getAttachment()).thenReturn(uri);
    when(mail.getSender()).thenReturn(null);
    String result = mailSenderService.sendMail(mail);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForNullRecipient() throws Exception {
    URI uri = new URI("https://google.com/robots.txt");
    when(mail.getAttachment()).thenReturn(uri);
    when(mail.getRecipient()).thenReturn(null);
    String result = mailSenderService.sendMail(mail);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForNullSubject() throws Exception {
    URI uri = new URI("https://google.com/robots.txt");
    when(mail.getAttachment()).thenReturn(uri);
    when(mail.getSubject()).thenReturn(null);
    String result = mailSenderService.sendMail(mail);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionForNullBody() throws Exception {
    URI uri = new URI("https://google.com/robots.txt");
    when(mail.getAttachment()).thenReturn(uri);
    when(mail.getBody()).thenReturn(null);
    String result = mailSenderService.sendMail(mail);
  }

  @Test(expected = UnknownHostException.class)
  public void shouldThrowExceptionForInvalidHost() throws Exception {
    URI uri = new URI("https://abc");
    when(mail.getAttachment()).thenReturn(uri);
    when(mail.getBody()).thenReturn(null);
    String result = mailSenderService.sendMail(mail);
  }

}
