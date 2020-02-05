package com.github.ralmnsk.srvlt.web.command;

import com.github.ralmnsk.srvlt.web.command.student.AllCoursesCommand;
import com.github.ralmnsk.srvlt.web.command.student.StudentCommand;
import com.github.ralmnsk.srvlt.web.command.tuitor.*;

public enum CommandEnum {
    ALLCOURSES {
        {
            this.command = new AllCoursesCommand();
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

    STUDENT{
        {
            this.command=new StudentCommand();
        }
    },

    TUITOR{
        {
            this.command=new TuitorCommand();
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
