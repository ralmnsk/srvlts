package com.facultative.web.command;

import com.facultative.model.UserType;
import com.facultative.service.auth.LoginLogic;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;

public class LoginCommand implements ActionCommand {

    private LoginLogic loginLogic = new LoginLogic();

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);

        if (loginLogic.checkLogin(login, pass, request)) {

            UserType userType=UserType.valueOf((String)request.getSession().getAttribute(USER_ROLE));
            if(userType.equals(UserType.STUDENT)){
                page = ConfigurationManager.getProperty("path.page.student");
            } else if(userType.equals(UserType.TUTOR)){
                page = ConfigurationManager.getProperty("path.page.tutor");
            } else{
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.role.error"));
                page = ConfigurationManager.getProperty("path.page.login");
            }

        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
