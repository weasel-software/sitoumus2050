package com.soikea.commitment2050.service;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import java.util.Date;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.soikea.commitment2050.model.City;
import com.soikea.commitment2050.model.Commitment;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;

import static com.soikea.commitment2050.service.Constants.*;

@Component(immediate = true, service = CommitmentMailService.class)
public class CommitmentMailServiceImpl implements CommitmentMailService {

    @Reference
    CommitmentService commitmentService;

    @Reference
    UserService userService;

    private static Logger log = LoggerFactory.getLogger(CommitmentMailServiceImpl.class.getName());

    @Override
    public void sendEmail(String mailto, String templateUrlTitle, String language) throws PortalException, AddressException {
        JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticleByUrlTitle(SITOUMUS2050_GROUP_ID, templateUrlTitle, WorkflowConstants.STATUS_APPROVED);
        JSONObject json = LFArticleXMLToJsonTransformer.lfSimpleFlatArticleContentXMLToJSON(article.getContent());
        String subject = json.getString("Subject_" + language);
        String body = JournalArticleLocalServiceUtil.getArticleContent(SITOUMUS2050_GROUP_ID, article.getArticleId(), article.getVersion(), null, article.getDDMTemplateKey(), language, null, null);
        if ( log.isDebugEnabled() ) {
            log.debug("Sending email, subject" + subject + " body: " + body);
        }
        sendEmail(mailto, subject, body, true);
    }

    @Override
    public void sendEmail(String to, String subject, String body, boolean html) {

        try {
            if("true".equals(PropsUtil.get("use.liferay.mail"))) {
                LFMailUtil.sendMail(body, to, Constants.MAIL_SENDER, subject, true);
            } else {
                Session session = MailSessionWrapper.getInstance();
                // create a message
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(Constants.MAIL_SENDER));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                // If the desired charset is known, you can use
                // setText(text, charset)
                msg.setText(body);

                Transport.send(msg);
            }

        } catch (MessagingException mex) {
            throw new RuntimeException(mex);
        }
    }

    @Override
    public void sendCommitmentApprovedEmails() {
        int commitmentsCount = 0;
        List<Commitment> commitments;
        commitmentsCount = JournalArticleLocalServiceUtil.getNotInTrashArticlesCount(COMMITMENTS_GROUP_ID, COMMITMENTS_FOLDER_ID);

        for (int i = 0; i < commitmentsCount; i = i + 50) {
            try {
                commitments = commitmentService.getRangeOfLatestApprovedCommitments(i, i + 50);
                if (commitments != null) {
                    commitments.forEach((commitment) -> {
                        String emailAddress = "";
                        try {
                            emailAddress = userService.getUser(commitment.getCreatedByUserId()).getEmailAddress();
                        } catch (PortalException e){
                            log.debug("getting email address failed: ", e);
                        }
                        if((commitment.getVersion() == 1.0) && (within24Hours(commitment.getUpdated())) && !emailAddress.equals("")){
                         try {
                             sendEmail(emailAddress, EMAIL_COMMITMENT_APPROVED_URL_TITLE, "fi_FI");
                         } catch (Exception e) {
                             log.debug("Failed to send commitment accepted mails to commitment owners: ", e);
                         }
                     }
                    });
                }

            } catch (Exception e) {
                log.error("ERROR: " + e.getMessage());
            }
        }
    }

    public boolean within24Hours(Date aDate) {
        final long DAY = 24 * 60 * 60 * 1000;
        return aDate.getTime() > System.currentTimeMillis() - DAY;
    }

}
