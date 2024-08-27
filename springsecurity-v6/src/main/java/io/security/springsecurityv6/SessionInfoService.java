package io.security.springsecurityv6;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionInfoService {

    private final SessionRegistry sessionRegistry;

    public void sessionInfo() {
        for(Object principal : sessionRegistry.getAllPrincipals()) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(principal, false);

            for(SessionInformation sessionInformation : allSessions) {
                String sessionId = sessionInformation.getSessionId();
                Date lastRequest = sessionInformation.getLastRequest();

                System.out.println("principal = " + principal + " sessionId = " + sessionId + " lastRequest" + lastRequest);
            }
        }

    }
}
