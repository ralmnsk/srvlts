package com.facultative.web.command;

import com.facultative.service.messages.MessageManager;

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
        ActionCommand current = new EmptyCommand();
//get command name
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
// current = emptyCommand
            return current;
        }
// get object acording to the command
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
