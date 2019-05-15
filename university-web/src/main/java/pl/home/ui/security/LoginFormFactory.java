package pl.home.ui.security;


import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import pl.home.ui.commons.UIComponentBuilder;

@org.springframework.stereotype.Component
public class LoginFormFactory implements UIComponentBuilder {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    public LoginFormFactory(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    private class LoginForm extends VerticalLayout {

//        private VerticalLayout;
        private Panel panel;
        private TextField username;
        private PasswordField password;
        private Button loginBtn;
        private Button signupBtn;

        public LoginForm init(){
            setMargin(true);
            setHeight("100%");

            panel = new Panel("Login");
            panel.setWidthUndefined();

            loginBtn = new Button("Login");
            loginBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            signupBtn = new Button("Sign up");
            signupBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);

            username = new TextField();
            password = new PasswordField();

            return this;
        }

        public Component layout(){
            addComponent(panel);
            setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            FormLayout loginLayout = new FormLayout();
            loginLayout.addComponent(username);
            loginLayout.addComponent(password);
            loginLayout.addComponent(new HorizontalLayout(loginBtn, signupBtn));
            loginLayout.setSizeUndefined();
            loginLayout.setMargin(true);

            loginBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {

                }
            });

            signupBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {

                }
            });

            panel.setContent(loginLayout);

            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new LoginForm().init().layout();
    }
}
