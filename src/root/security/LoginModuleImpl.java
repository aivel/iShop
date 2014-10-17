package root.security;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Max on 10/16/2014.
 */
public class LoginModuleImpl implements LoginModule {
    private CallbackHandler callbackHandler = null;
    private Subject subject;
    private List<String> userGroups;
    private UserPrincipalImpl userPrincipal;
    private GroupPrincipalImpl groupPrincipal;

    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private HttpServletRequest request = null;
    private boolean auth = false;

    private String uname;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username: ");
        callbacks[1] = new PasswordCallback("Password: ", false);

        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        final String username = ((NameCallback)callbacks[0]).getName();
        final String password = new String(((PasswordCallback)callbacks[1]).getPassword());
        final HttpSession session;

//        if (request != null)
//            session = request.getSession(true);
//        else
//            session = null;

        if (password.equals(PASSWORD) && username.equals(USERNAME)) {
            System.out.println("auth successful!");
            auth = true;
        } else {
            auth = false;
            throw new FailedLoginException("Failed to login");
        }

        uname = username;

        userGroups = new ArrayList<>();
        userGroups.add("user");

        return auth;
    }

    @Override
    public boolean commit() throws LoginException {
        userPrincipal = new UserPrincipalImpl(uname);
        subject.getPrincipals().add(userPrincipal);

        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName: userGroups) {
                groupPrincipal = new GroupPrincipalImpl(groupName);
                subject.getPrincipals().add(groupPrincipal);
            }
        }

        return auth;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(groupPrincipal);

        return false;
    }
}
