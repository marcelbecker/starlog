package de.anderscore.starlog.ui;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.anderscore.starlog.application.ApplicationContainer;

import java.io.IOException;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("starlog")
public class StarlogUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new MainLayout());
    }

    @WebServlet(urlPatterns = {"/starlog/*", "/VAADIN/*"},
            name = "StarlogUIServlet",
            asyncSupported = true,
            loadOnStartup = 0)
    @VaadinServletConfiguration(ui = StarlogUI.class, productionMode = false)
    public static class StarlogUIServlet extends VaadinServlet {
        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            super.init(servletConfig);
            ApplicationContainer.init();
        }

        @Override
        public void destroy() {
            super.destroy();
            ApplicationContainer.tearDown();
        }

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            super.service(request, response);
        }
    }
}
