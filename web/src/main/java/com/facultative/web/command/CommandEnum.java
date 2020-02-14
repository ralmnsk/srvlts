package com.facultative.web.command;

import com.facultative.web.command.student.*;
import com.facultative.web.command.tutor.*;

public enum CommandEnum {
    INDEX {
        {
            this.command = new IndexCommand();
        }
    },
    ALLCOURSES {
        {
            this.command = new AllCoursesCommand();
        }
    },
    ADDMARK{
        {
            this.command = new AddMarkCommand();
        }
    },
    DOADDMARK{
        {
            this.command = new DoAddMarkCommand();
        }
    },
    DELMARK{
        {
            this.command = new DelMarkCommand();
        }
    },
    DODELMARK{
        {
            this.command = new DoDelMarkCommand();
        }
    },
    VIEWMARK{
        {
            this.command = new ViewMarkCommand();
        }
    },
    MARKS{
        {
            this.command = new MarksCommand();
        }
    },
    EDIT_MARK{
        {
            this.command = new EditMarkCommand();
        }
    },
    DO_EDIT_MARK{
        {
            this.command = new DoEditMarkCommand();
        }
    },

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    TOLOGIN{
        {
            this.command=new ToLoginCommand();
        }
    },
    TOREGISTER{
        {
            this.command=new ToRegisterCommand();
        }
    },
    REGISTER{
        {
            this.command=new RegisterCommand();
        }
    },
    REG_SUCCESS{
        {
            this.command=new RegSuccessCommand();
        }
    },

    STUDENT{
        {
            this.command=new StudentCommand();
        }
    },

    TUTOR{
        {
            this.command=new TutorCommand();
        }
    },

    CREATECOURSE{
        {
            this.command=new CreateCourseCommand();
        }
    },

    DOCREATECOURSES{
        {
            this.command=new DoCreateCourseCommand();
        }
    },

    VIEWCOURSE{
        {
            this.command=new ViewCourseCommand();
        }
    },
    EDITCOURSE{
        {
            this.command=new EditCourseCommand();
        }
    },

    UPDATECOURSE{
        {
            this.command=new UpdateCourseCommand();
        }
    },

    DELETECOURSE{
        {
            this.command=new DeleteCourseCommand();
        }
    },

    LANG{
        {
            this.command=new LanguageCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };


    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
