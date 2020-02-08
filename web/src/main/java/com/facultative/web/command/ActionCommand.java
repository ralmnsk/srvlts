package com.facultative.web.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Action command.
 */
public interface ActionCommand {
    /**
     * Execute string.
     *
     * @param request the request
     * @return the string
     */
    String execute(HttpServletRequest request);
}
