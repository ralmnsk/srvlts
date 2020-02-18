package com.facultative.web.controller;

import com.facultative.model.Person;
import com.facultative.model.UserType;
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

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);

        String page = command.execute(req);
        if (page != null) {
            if(req.getParameter(LANG) != null){                         //if language was pressed
                resp.sendRedirect(req.getContextPath() + page); //send to last page
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
        //Language
        if(req.getParameter(COMMAND) !=null){
            String oldCommand = INDEX;
            String action = req.getParameter(COMMAND);
            switch (action){
                case DO_ADD_MARK: oldCommand = VIEW_MARK; break;
                case DEL_MARK: oldCommand = VIEW_MARK; break;
                case DELETE_COURSE: oldCommand = VIEW_COURSE; break;
                case DO_CREATE_COURSES: oldCommand = VIEW_COURSE; break;
                case DO_EDIT_MARK: oldCommand = MARKS; break;
                case UPDATE_COURSE: oldCommand = VIEW_COURSE; break;
                case LOGIN:
                    if(req.getSession().getAttribute(PERSON) !=null){
                        Person person = (Person)req.getSession().getAttribute(PERSON);
                        if (person.getRole() == UserType.STUDENT){
                            oldCommand = STUDENT;
                        } else if (person.getRole() == UserType.TUTOR){
                            oldCommand = TUTOR;
                        } else {
                            oldCommand = LOGIN;
                        }
                    }
                    break;
                case REGISTER:
                         oldCommand = TO_REGISTER;
                    break;
                default: oldCommand = action; break;
            }
            req.getSession().setAttribute(OLD_COMMAND,oldCommand);
        }
    }
}
