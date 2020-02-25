package com.facultative.web.command;

import com.facultative.model.UserType;
import com.facultative.service.config.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.USER_ROLE;

public class ErrorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        if( request.getSession().getAttribute(USER_ROLE) != null){
            UserType userType = UserType.valueOf((String)request.getSession().getAttribute(USER_ROLE));
            switch (userType){
                case STUDENT:
                    return ConfigurationManager.getProperty("path.page.student");
                case TUTOR:
                    return ConfigurationManager.getProperty("path.page.tutor");
            }
        }
        return ConfigurationManager.getProperty("path.page.error");
    }
}
