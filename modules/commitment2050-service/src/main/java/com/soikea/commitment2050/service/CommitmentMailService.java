package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.exception.PortalException;

import javax.mail.internet.AddressException;

public interface CommitmentMailService {
    /**
     * Send email template
     *
     * Email template (messages body content) is fetched from Liferay's cms by url title.
     *
     * @param mailto email recipient
     * @param templateUrlTitle template article url title (Liferay creates url title automatically from articles name, lowercase, spaces are replaced with "-").
     * @param language should be for example fi_FI, en_US..
     * @throws PortalException @see {@link PortalException}
     * @throws AddressException @see {@link AddressException}
     *
     */
    void sendEmail(String mailto, String templateUrlTitle, String language) throws PortalException, AddressException;

    void sendEmail(String to, String subject, String body, boolean html);

    void sendCommitmentApprovedEmails();
}
