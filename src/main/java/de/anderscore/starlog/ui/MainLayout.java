package de.anderscore.starlog.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.*;
import de.anderscore.starlog.ui.components.UserLogin;
import de.anderscore.starlog.ui.components.UserLogout;

import java.util.logging.Logger;

import static com.vaadin.ui.Alignment.TOP_RIGHT;
import static de.anderscore.starlog.ui.theme.StarlogTheme.BACKGROUND_DARK;
import static java.util.logging.Logger.getLogger;


/**
 * Created by MBecker on 20.01.2017.
 */
public class MainLayout extends VerticalLayout implements ViewDisplay {

    private static final Logger logger = getLogger(MainLayout.class.getName());
    private final UserLogout userLogout;


    private CustomComponent contentPart;
    private final UserLogin userLogin;
    private final HorizontalLayout topBar;


    public MainLayout() {

        userLogin = new UserLogin();
        userLogin.addPresenter(this::onUserLogin);
        userLogin.setSizeUndefined();

        userLogout = new UserLogout();
        userLogout.addPresenter(this::onUserLogout);
        userLogout.setSizeUndefined();

        topBar = new HorizontalLayout();
        topBar.addStyleName(BACKGROUND_DARK);
        topBar.setMargin(true);
        topBar.setWidth(100, Unit.PERCENTAGE);
        topBar.addComponent(new Label("StarLog"));
        topBar.addComponent(userLogin);
        topBar.setComponentAlignment(userLogin, TOP_RIGHT);

        addComponent(topBar);
        contentPart = new CustomComponent();
        addComponent(contentPart);
    }

    @Override
    public void showView(View view) {
        CustomComponent newComponent = new CustomComponent((Component) view);
        replaceComponent(contentPart, newComponent);
        contentPart = newComponent;
    }

    private void onUserLogin(UserLogin.Credentials credentials) {
        if ("abc".equals(credentials.getLogin()) && "mm".equals(credentials.getPass())) {
            topBar.replaceComponent(userLogin, userLogout);
            topBar.setComponentAlignment(userLogout, TOP_RIGHT);
            userLogout.setUsername(credentials.getLogin());
        }
    }

    private void onUserLogout(String username) {
        topBar.replaceComponent(userLogout, userLogin);
        topBar.setComponentAlignment(userLogin, TOP_RIGHT);
    }
}
