package com.github.ralmnsk.srvlt.web.command;

import com.github.ralmnsk.srvlt.web.command.student.StudentCommand;
import com.github.ralmnsk.srvlt.web.command.tuitor.CreateCourseCommand;
import com.github.ralmnsk.srvlt.web.command.tuitor.DoCreateCourseCommand;
import com.github.ralmnsk.srvlt.web.command.tuitor.TuitorCommand;

public enum CommandEnum {
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

//    UPDATECOURSE{
//        {
//            this.command=new UpdateCourseCommand();
//        }
//    },
//
//    DELETECOURSE{
//        {
//            this.command=new DeleteCourseCommand();
//        }
//    },

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
