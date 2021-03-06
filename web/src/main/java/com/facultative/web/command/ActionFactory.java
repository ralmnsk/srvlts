package com.facultative.web.command;

import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.COMMAND;

/**
 * The type Action factory.
 */
public class ActionFactory {
    /**
     * Define command action command.
     *
     * @param request the request
     * @return the action command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
            ActionCommand current = new IndexCommand();
//get command name
        String action = request.getParameter(COMMAND);
        if (request.getAttribute(COMMAND) != null){
            action = (String)request.getAttribute(COMMAND); //this is a command from RoleFilter
        }                                                   //that sets attribute COMMAND if user has
        if (action == null || action.isEmpty()) {           //a GUEST role
            return current;
        }

// get ActionCommand according to the action (parameter or attribute COMMAND)
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
            return new ErrorCommand();
        }
        return current;
    }
}
