package pl.home.ui.security;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import pl.home.services.security.RegisterUserService;
import pl.home.ui.commons.UIComponentBuilder;

@org.springframework.stereotype.Component
public class SignupFormFactory implements UIComponentBuilder {

    private final RegisterUserService registerUserService;

    public SignupFormFactory(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    private class SignupForm extends VerticalLayout{
        private Panel panel;
        private TextField username;
        private PasswordField password;
        private PasswordField passwordAgain;
        private Button signupBtn;

        public SignupForm init(){
            setMargin(true);
            setHeight("100%");

            panel = new Panel("Sign up");
            panel.setSizeUndefined();

            signupBtn = new Button("Sign up");
            signupBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            username = new TextField("username");
            password = new PasswordField("password");
            passwordAgain = new PasswordField("password again");

            signupBtn.addClickListener(event -> {
                if(passwordAgain.getValue().equals(password.getValue())){
                    registerUserService.save(username.getValue(),password.getValue());
                    Notification.show("Success","Account has create", Notification.Type.HUMANIZED_MESSAGE);
                    UI.getCurrent().getPage().setLocation("/login");
                }else{
                    Notification.show("Error","Your password are different", Notification.Type.ERROR_MESSAGE);
                }
            });

            return this;
        }

        public Component layout(){
            addComponent(panel);
            setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            FormLayout signupLayout = new FormLayout();
            signupLayout.addComponent(username);
            signupLayout.addComponent(password);
            signupLayout.addComponent(passwordAgain);
            signupLayout.addComponent(signupBtn);
            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);


            panel.setContent(signupLayout);

            return this;
        }
    }


    @Override
    public Component createComponent() {
        return new SignupForm().init().layout();
    }
}
