package com.mapp.challenge.hermes.model;

import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class MailTest {

  private Mail mail;

  @Before
  public void setUp(){
    try {
      URI uri = new URI("https://google.com/robots.txt");
      mail = new Mail();
      mail.setAttachment(uri);
      mail.setSender("sender");
      mail.setRecipient("recipient");
      mail.setSubject("subject");
      mail.setBody("body");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void shouldSerializeSuccessfully(){
    Mail mailObj = new Mail();
    byte[] result = mailObj.serialize("test-topic", mail);
    assertNotNull(result);
  }

  @Test
  public void shouldDeserializeSuccessfully(){
    Mail mailObj = new Mail();
    Mail result = mailObj.deserialize("test-topic", getMailByteArray());
    assertNotNull(result);
  }

  private byte[] getMailByteArray(){
    ObjectMapper mapper = new ObjectMapper();
    byte[] serializedObject=null;
    try {
      serializedObject = mapper.writeValueAsString(mail).getBytes();
    }catch (Exception e) {
    }
    return serializedObject;
  }

}
