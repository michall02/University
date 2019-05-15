package pl.home.ui.security;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path = LoginUI.NAME)
public class LoginUI extends UI {
    public static final String NAME = "/login";

    private final  LoginFormFactory loginFormFactory;

    public LoginUI(LoginFormFactory loginFormFactory) {
        this.loginFormFactory = loginFormFactory;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(loginFormFactory.createComponent());
    }
}
