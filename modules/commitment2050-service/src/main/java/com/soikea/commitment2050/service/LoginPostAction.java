package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;

import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.soikea.commitment2050.service.Constants.*;

/**
 * Custom redirects after login
 */
@Component(
        immediate = true, property = "key=login.events.post",
        service = LifecycleAction.class
)
public class LoginPostAction implements LifecycleAction {
    private static Logger log = LoggerFactory.getLogger(LoginPostAction.class.getName());

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
        HttpServletRequest request = lifecycleEvent.getRequest();
        HttpSession session = request.getSession();
        LastPath lastPath = (LastPath) session.getAttribute(WebKeys.LAST_PATH);
        if (log.isDebugEnabled()) {
            log.debug("Login post action: LAST_PATH:" + (lastPath != null ? lastPath.getPath() : null));
        }
        try {
            if ( request.getParameterMap().containsKey(AUTOLOGIN_PARAMETER_FROM_REGISTRATION) ) {
                //last redirect is made with js... some problems with liferay password change behaviour, which
                // shows change passoword forms and breaks normal landing page behaviour...
                sendTranslatedRedirect(lifecycleEvent, SMARTWAYS_REGISTRATION_SUCCESSFULL_PAGE);
            }
            else if (lastPath.getPath().contains(SMARTWAYS_LOGIN_PAGE)) {
                sendTranslatedRedirect(lifecycleEvent, SMARTWAYS_LANDING_PAGE_PATH);
            } else {
                sendTranslatedRedirect(lifecycleEvent, DEFAULT_LANDING_PAGE_PATH);
        }
        } catch (IOException e) {
            log.error("Redirect failed");
        }
    }

    private void sendTranslatedRedirect(LifecycleEvent lifecycleEvent, String location) throws IOException {
        String i18nPath = (String) lifecycleEvent.getRequest().getAttribute("I18N_PATH");
        if (i18nPath == null) i18nPath = "";
        lifecycleEvent.getResponse().sendRedirect(i18nPath + location);
    }

}