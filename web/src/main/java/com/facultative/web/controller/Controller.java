package com.facultative.web.controller;

import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.ActionFactory;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.service.messages.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.facultative.service.constants.Constants.*;

/**
 * The type Controller.
 */
@WebServlet(value = {"/controller","/"})
public class Controller extends HttpServlet {
    /**
     * Instantiates a new Controller.
     */
    public Controller() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);

        page = command.execute(req);
        if (page != null) {
            if(req.getParameter(LANG) != null){
                resp.sendRedirect((String)req.getSession().getAttribute(OLD_URL));
            }else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        } else {
            page = ConfigurationManager.getProperty("path.page.error");
            req.getSession().setAttribute(NULL_PAGE,
                    MessageManager.getProperty("message.nullpage"));
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
