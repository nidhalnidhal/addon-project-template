package org.exoplatform.addons.services;

import com.sun.mail.smtp.SMTPTransport;
import org.antlr.runtime.tree.BaseTreeAdaptor;
import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.resources.ResourceBundleService;
import org.exoplatform.web.WebAppController;
import org.exoplatform.web.login.recovery.PasswordRecoveryServiceImpl;
import org.exoplatform.web.security.security.RemindPasswordTokenService;
import sun.security.krb5.Credentials;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Future;

public class MailingService extends PasswordRecoveryServiceImpl {

    public MailingService(OrganizationService orgService, MailService mailService, ResourceBundleService bundleService, RemindPasswordTokenService remindPasswordTokenService, WebAppController controller) {
        super(orgService, mailService, bundleService, remindPasswordTokenService, controller);
    }

    private MailService mailService = new MailService() {
        @Override
        public Session getMailSession() {
            return null;
        }

        @Override
        public String getOutgoingMailServer() {
            return null;
        }

        @Override
        public void sendMessage(String s, String s1, String s2, String s3) throws Exception {

        }

        @Override
        public void sendMessage(Message message) throws Exception {

        }

        @Override
        public void sendMessage(MimeMessage mimeMessage) throws Exception {

        }

        @Override
        public Future<Boolean> sendMessageInFuture(String s, String s1, String s2, String s3) {
            return null;
        }

        @Override
        public Future<Boolean> sendMessageInFuture(Message message) {
            return null;
        }

        @Override
        public Future<Boolean> sendMessageInFuture(MimeMessage mimeMessage) {
            return null;
        }
    };

    public boolean sendOnboardingEmail(User user, Locale locale, StringBuilder url) {
        if (user == null) {
            throw new IllegalArgumentException("User or Locale must not be null");
        }

        ResourceBundleService bundleService = null;
        ResourceBundle bundle = bundleService.getResourceBundle(bundleService.getSharedResourceBundleNames(), locale);

        BaseTreeAdaptor remindPasswordTokenService = null;
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append(url);


        Message message = new Message();
        message.setTo(user.getEmail());
        message.setMimeType("text/html");

        try {
            mailService.sendMessage(message);
        } catch (Exception ex) {
            log.error("Failure to send onboarding email", ex);
            return false;
        }

        return true;
    }
}