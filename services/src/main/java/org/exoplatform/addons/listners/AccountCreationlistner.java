package org.exoplatform.addons.listners;

import org.exoplatform.addons.services.MailingService;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.listener.Asynchronous;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.idm.UpdateLoginTimeListener;
import org.exoplatform.services.organization.impl.mock.DummyOrganizationService;
import org.exoplatform.web.login.recovery.PasswordRecoveryServiceImpl;
@Asynchronous
public class AccountCreationlistner extends Listener<DummyOrganizationService.UserHandlerImpl, User> {


    private static final Log LOG = ExoLogger.getLogger(AccountCreationlistner.class);

    public static final String  USER_PROFILE = "UserProfile";

    private PortalContainer container;
    private OrganizationService organizationService;

    private MailingService mailingService;

    public AccountCreationlistner(PortalContainer container) {
        this.container = container;
    }
    @Override
    public void onEvent(Event<DummyOrganizationService.UserHandlerImpl, User> event) {
        if (organizationService == null) {
            organizationService = this.container.getComponentInstanceOfType(OrganizationService.class);
        }
        User user = event.getData();
        if (user == null) {
            LOG.info("User Not found");
        }
        else {
            this.mailingService.sendOnboardingEmail(user,null,null);
        }

    }
}
