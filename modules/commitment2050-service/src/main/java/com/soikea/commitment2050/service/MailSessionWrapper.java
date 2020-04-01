package com.soikea.commitment2050.service;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailSessionWrapper {

  private static Session instance = null;

  public static synchronized Session getInstance() {
    if(instance == null) {
      Properties props = System.getProperties();

      instance = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(props.get("mail.smtp.user").toString(), props.get("mail.smtp.password").toString());
        }
      });

    }
    return instance;
  }

}
