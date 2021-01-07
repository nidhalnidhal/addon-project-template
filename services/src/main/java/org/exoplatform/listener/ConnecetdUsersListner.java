package org.exoplatform.listener;

import java.util.ArrayList;
import java.util.Calendar;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.component.ComponentRequestLifecycle;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.services.listener.Asynchronous;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.security.ConversationRegistry;
import org.exoplatform.services.security.ConversationState;

import java.util.Calendar;
import java.util.List;

public class ConnecetdUsersListner extends Listener<ConversationRegistry, ConversationState> {
    private static final Log LOG = ExoLogger.getLogger(ConnecetdUsersListner.class);

    public static final String  USER_PROFILE = "UserProfile";

    private PortalContainer container;

    private OrganizationService organizationService;

    private static List <User> liste = new ArrayList<User>();

    public ConnecetdUsersListner(PortalContainer container, OrganizationService organizationService) {
        this.container = container;
        this.organizationService = organizationService;
       // this.liste = liste;
    }

    public ConnecetdUsersListner() {
    }

    public static List<User> getListe() {
        return liste;
    }

    @Override
    public void onEvent(Event<ConversationRegistry, ConversationState> event) {
        LOG.info("User Connecetd");

        LOG.info("User Connecetd" + liste.size());

        if (organizationService == null) {
            organizationService = this.container.getComponentInstanceOfType(OrganizationService.class);
        }
        UserHandler userHandler = organizationService.getUserHandler();

        ConversationState state = event.getData();
        String userId = state.getIdentity().getUserId();
        boolean transactionOpened = false;
        if(organizationService instanceof ComponentRequestLifecycle) {
            RequestLifeCycle.begin((ComponentRequestLifecycle)organizationService);
            transactionOpened = true;
        }
        try {
            User user = (User) state.getAttribute(USER_PROFILE);

            if (user == null) {
                user = userHandler.findUserByName(userId);
                state.setAttribute(USER_PROFILE, user);
            }
            userHandler.saveUser(user, false);
            this.liste.add(user);

        } catch (Exception e) {
            LOG.error("Error while updating the last login time for user {}", userId, e);
        } finally {
            if(transactionOpened) {
                RequestLifeCycle.end();
            }
        }
        LOG.info("User Connecetd");
    }
}
