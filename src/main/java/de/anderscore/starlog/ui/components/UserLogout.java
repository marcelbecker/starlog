package de.anderscore.starlog.ui.components;

import com.vaadin.ui.*;
import de.anderscore.starlog.ui.theme.StarlogTheme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MBecker on 20.01.2017.
 */
public class UserLogout extends CustomComponent implements PresentableView<UserLogout.UserLogoutPresenter> {

    private final List<UserLogoutPresenter> presenters = new ArrayList<>();
    private final Label username;

    public UserLogout() {

        Button signOut = new Button("Sign out", this::signOut);

        username = new Label();
        username.setWidth(250, Unit.PIXELS);
        username.addStyleName(StarlogTheme.TEXT_ALIGN_RIGHT);

        HorizontalLayout compositionRoot = new HorizontalLayout(username, signOut);
        compositionRoot.setComponentAlignment(username, Alignment.MIDDLE_LEFT);

        setCompositionRoot(compositionRoot);

    }

    public void setUsername(String username) {
        this.username.setValue(username);
    }

    private void signOut(Button.ClickEvent clickEvent) {
        presenters.forEach(p ->
                p.onSignOut(username.getValue()));
    }

    @Override
    public void addPresenter(UserLogoutPresenter userLogoutPresenter) {
        presenters.add(userLogoutPresenter);
    }

    @Override
    public void removePresenter(UserLogoutPresenter userLogoutPresenter) {
        presenters.remove(userLogoutPresenter);
    }


    public interface UserLogoutPresenter {
        void onSignOut(String username);
    }

}
