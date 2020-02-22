package com.facultative.web.command.saver;

import com.facultative.model.Person;
import com.facultative.model.UserType;
import javax.servlet.http.HttpServletRequest;
import static com.facultative.service.constants.Constants.*;
import static com.facultative.service.constants.Constants.OLD_COMMAND;

public class Saver {    //saves oldCommand in Session

    private HttpServletRequest req;

    public Saver(HttpServletRequest req){
        this.req = req;
    }

    public void save(){
        if(req.getParameter(COMMAND) !=null){
            String oldCommand = INDEX;
            String action = req.getParameter(COMMAND);
            switch (action){
                case DO_ADD_MARK: oldCommand = VIEW_MARK; break;  //oldCommand - command before language command
                case DEL_MARK: oldCommand = VIEW_MARK; break;
                case DELETE_COURSE: oldCommand = VIEW_COURSE; break;
                case DO_CREATE_COURSES: oldCommand = VIEW_COURSE; break;
                case DO_EDIT_MARK: oldCommand = MARKS; break;
                case DO_DEL_MARK: oldCommand = VIEW_MARK; break;
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
