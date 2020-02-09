package com.facultative.service.constants;

import java.util.HashSet;
import java.util.Set;

public class PermitAddCommandsForLang {
    private static Set<String> set=new HashSet<String>();
    static {
        set.add("docreatecourses");
        set.add("updatecourse");
        set.add("deletecourse");
        set.add("do_edit_mark");
        set.add("login");
        set.add("register");
        set.add("doaddmark");
        set.add("dodelmark");
    }

    public static boolean isPermit(String commandName){
        if(set.contains(commandName)){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(PermitAddCommandsForLang.isPermit("do"));
    }
}
