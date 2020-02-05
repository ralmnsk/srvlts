package com.github.ralmnsk.srvlt.web.command;

import com.github.ralmnsk.srvlt.model.UserType;
import com.github.ralmnsk.srvlt.service.auth.LoginLogic;
import com.github.ralmnsk.srvlt.service.config.ConfigurationManager;
import com.github.ralmnsk.srvlt.service.messages.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
// извлечение из запроса логина и пароля
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
// проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass, request)) {
// определение пути к main.jsp
            UserType userType=UserType.valueOf((String)request.getSession().getAttribute("userRole"));
            if(userType.equals(UserType.STUDENT)){
                page = ConfigurationManager.getProperty("path.page.student");
            }
            if(userType.equals(UserType.TUITOR)){
                page = ConfigurationManager.getProperty("path.page.tuitor");
            }
        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
