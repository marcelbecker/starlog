package de.anderscore.starlog.ui.components;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MBecker on 20.01.2017.
 */
public class UserLogin extends CustomComponent implements PresentableView<UserLogin.UserLoginPresenter> {

    private final TextField yourLogin;
    private final PasswordField yourPass;
    private final List<UserLoginPresenter> presenters = new ArrayList<>();

    public UserLogin() {

        Button signOn = new Button("Sign on", this::signOn);
        signOn.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        yourLogin = new TextField();
        yourLogin.setInputPrompt("Your Login");

        yourPass = new PasswordField();
        yourPass.setInputPrompt("Your Password");

        setCompositionRoot(new HorizontalLayout(yourLogin, yourPass, signOn));

    }

    private void signOn(Button.ClickEvent clickEvent) {
        presenters.forEach(p ->
                p.onSignOn(new Credentials(yourLogin.getValue(), yourPass.getValue())));
    }

    @Override
    public void addPresenter(UserLoginPresenter userLoginPresenter) {
        presenters.add(userLoginPresenter);
    }

    @Override
    public void removePresenter(UserLoginPresenter userLoginPresenter) {
        presenters.remove(userLoginPresenter);
    }


    public interface UserLoginPresenter {
        void onSignOn(Credentials credentials);
    }

    public static class Credentials {
        final String login;
        final String pass;

        public Credentials(String login, String pass) {
            this.login = login;
            this.pass = pass;
        }

        public String getLogin() {
            return login;
        }

        public String getPass() {
            return pass;
        }
    }
}
