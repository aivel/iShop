package root.security;

import java.security.Principal;

/**
 * Created by Max on 10/16/2014.
 */
public class GroupPrincipalImpl implements Principal {
    private String name;

    public GroupPrincipalImpl(final String name) {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
