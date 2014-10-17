package root.security;

import javax.security.auth.callback.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Max on 10/16/2014.
 */
public class CallBackHandlerImpl implements CallbackHandler {
    private final String username;
    private final String password;
    private final HttpServletRequest request;

    public CallBackHandlerImpl(final String username, final String password, final HttpServletRequest request) {
        this.username = username;
        this.password = password;
        this.request = request;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback: callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback)callback).setName(username);
            } else
            if (callback instanceof PasswordCallback) {
                ((PasswordCallback)callback).setPassword(password.toCharArray());
            }
        }
    }
}
