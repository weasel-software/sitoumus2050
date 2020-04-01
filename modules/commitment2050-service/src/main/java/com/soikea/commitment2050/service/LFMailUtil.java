package com.soikea.commitment2050.service;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

public class LFMailUtil {
    /**
     *
     * Send mail using Liferay's MailServiceUtil. Before calling this, check that Liferay is configured properly.
     * @param body      body of the message
     * @param to        recipient, (if multiple, use comma to separate: example1@domain.fi, example2@domain.com )
     * @param from      from
     * @param subject   subject
     * @param html      if body contains html
     * @throws AddressException @see {@link AddressException}
     */
    public static void sendMail(String body, String to, String from, String subject, Boolean html) throws AddressException {
        MailMessage message = new MailMessage();
        if (StringUtils.isNotBlank(to) && to.contains(",")) {
            String[] mails = to.split(",");
            List<InternetAddress> mailto = new ArrayList();
            for ( String m : mails ) {
                m = m.trim();
                mailto.add(new InternetAddress(m));
            }
            message.setTo(mailto.toArray(new InternetAddress[mailto.size()]));
        } else {
            message.setTo(new InternetAddress(to));
        }
        message.setFrom(new InternetAddress(from));
        message.setSubject(subject);
        message.setBody(body);
        message.setHTMLFormat(html);

        MailServiceUtil.sendEmail(message);
    }
}
