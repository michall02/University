package pl.home.ui.security;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.ui.commons.UIComponentBuilder;

@SpringUI(path = LoginUI.NAME)
public class LoginUI extends UI {
    public static final String NAME = "/login";

    private final UIComponentBuilder loginFormFactoryBuilder;

    public LoginUI(@Qualifier("loginFormFactory") UIComponentBuilder loginFormFactoryBuilder) {
        this.loginFormFactoryBuilder = loginFormFactoryBuilder;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(loginFormFactoryBuilder.createComponent());
    }
}
