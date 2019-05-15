package pl.home.ui.security;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.ui.commons.UIComponentBuilder;

@SpringUI(path = SignupUI.NAME)
public class SignupUI extends UI {
    public static final String NAME = "/signup";

    private final UIComponentBuilder signupFormFactoryBuilder;

    public SignupUI(@Qualifier("signupFormFactory") UIComponentBuilder signupFormFactoryBuilder) {
        this.signupFormFactoryBuilder = signupFormFactoryBuilder;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(signupFormFactoryBuilder.createComponent());
    }
}
