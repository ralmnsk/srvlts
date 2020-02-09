package com.facultative.service.constants;

public class Constants {
    //Web
        //session
    public static final String USER_ID="userId";
    public static final String USER_ROLE="userRole";
    public static final String MARK="mark";
    public static final String MARK_ID="markid";
    public static final String COURSE="course";
    public static final String PAGE_COURSE_TUTOR_NUMBER="pageCourseNumber";
    public static final String PAGE_MARK_STUDENT_NUMBER="pageMarkStudentNumber";
    public static final String PAGE_MARK_TUTOR_NUMBER="pageMarkNumber";
    public static final String PAGE_ALL_COURSES_NUMBER="pageAllCoursesNumber";
    public static final String PAGES_COUNT="pagesCount";
    public static final String EDIT_COURSE="editCourse";
        //request
    public static final String ADD_MARK="addmark";
    public static final String COURSE_ID="courseid";
    public static final String COMMAND="command";
    public static final String CREATE_COURSE="createcourse";
    public static final String DEL_MARK="delmark";
    public static final String EDIT_MARK="edit_mark";
    public static final String EDIT_ID="editid";
    public static final String LOGIN="login";
    public static final String LIST_JSP="list";
    public static final String MARKS_VIEW="marks_view";
    public static final String NAME="name";
    public static final String NEXT="next";
    public static final String NULL_PAGE="nullPage";
    public static final String OLD_URL="oldUrl";
    public static final String PROCESS_FLAG="processFlag";
    public static final String PARAMETER_MOVE="move";
    public static final String PASSWORD="password";
    public static final String PREVIOUS="previous";
    public static final String SELECT_TYPE="selectType";
    public static final String SURNAME="surname";
    public static final String REVIEW="review";
    public static final String VIEW_COURSE="viewcourse";
    public static final String VIEW_MARK="viewmark";
    //internationalization, messages
    public static final String CONFIGURATION_MANAGER_FILE_BASE_NAME="config";
    public static final String MESSAGE_MANAGER_FILE_BASE_NAME="messages";
    public static final String LANG="lang";
    public static final String LANG_EN="en";
    public static final String LANG_RU="ru";
    public static final String OLD_COMMAND="oldCommand";
    public static final String OLD_PAGE="oldPage";
    //DaoPerson
    public static final String SQL_QUERY_PERSON_GET_BY_LOGIN="select * from mydb.user where login=?";
    public static final String SQL_QUERY_PERSON_SAVE="insert into mydb.user (surname, name, login, password, role) values (?, ?, ?, ?, ?)";
    public static final String SQL_QUERY_PERSON_GET="select * from mydb.user where id=?";
    public static final String SQL_QUERY_PERSON_UPDATE="update mydb.user set surname=?, name=?, login=?, password=?, role=? where id=?";
    public static final String SQL_QUERY_PERSON_DELETE="delete from mydb.user where id=?";

    //DaoCourse
    public static final String SQL_QUERY_COURSE_SAVE="insert into mydb.course (name_course, id_tutor) values (?, ?)";
    public static final String SQL_QUERY_COURSE_GET="SELECT id_course,name_course,id_tutor,surname,name,login,password,role FROM mydb.course join mydb.user on id_tutor=id where id_course=?";
    public static final String SQL_QUERY_COURSE_UPDATE="update mydb.course set name_course=?, id_tutor=? where id_course=?";
    public static final String SQL_QUERY_COURSE_DELETE="delete from mydb.course where id_course=?";
    public static final String SQL_QUERY_COURSE_BY_TUTOR_PARAM_ID="SELECT id_course,name_course,id_tutor,surname,name,login,password,role FROM mydb.course join mydb.user on id_tutor=id where id_tutor=? limit ?,?";
    public static final String SQL_QUERY_COURSE_ALL_NO_PARAM="SELECT id_course,name_course,id_tutor,surname,name,login,password,role FROM mydb.course join mydb.user on id_tutor=id limit ?,?";
    public static final String SQL_QUERY_COURSE_COUNT_BY_TUTOR_ID="SELECT count(*) FROM mydb.course where id_tutor=?";
    public static final String SQL_QUERY_COURSE_COUNT="SELECT count(*) FROM mydb.course";
    public static final long IN_COURSE_ALL_NO_TUTOR_ID=0L;
    public static final int NO_NUMBER=0;

    //DaoMark
    public static final String SQL_QUERY_MARK_SAVE="INSERT INTO mydb.mark (id_course, id_student) VALUES (?, ?)";
    public static final String SQL_QUERY_MARK_GET="SELECT id_mark, mark, review, mydb.course.id_course,name_course,id_tutor, mydb.user.surname, mydb.user.name,id_student " +
            "            FROM mydb.mark join mydb.course on mydb.mark.id_course=mydb.course.id_course " +
            "            join mydb.user on mydb.user.id=mydb.course.id_tutor " +
            "            where id_mark=?";
    public static final String SQL_QUERY_MARK_UPDATE="UPDATE mydb.mark SET mark = ?, review = ? WHERE (id_mark = ?)";
    public static final String SQL_QUERY_MARK_DELETE="delete from mydb.mark where id_mark=?";
    public static final String SQL_QUERY_MARK_ALL_BY_TUTOR_ID="SELECT mydb.course.id_course, name_course, mydb.course.id_tutor, " +
            "id_mark,mark,review,id_student,surname,name " +
            " FROM mydb.course join mydb.mark on mydb.course.id_course=mydb.mark.id_course " +
            "join mydb.user on mydb.user.id=mydb.mark.id_student " +
            "where id_tutor=? limit ?,?";
    public static final String SQL_QUERY_MARK_ALL_BY_STUDENT_ID="SELECT mydb.course.id_course, name_course, mydb.course.id_tutor, " +
            "surname,name, id_mark,mark,review,id_student" +
            " FROM mydb.course join mydb.mark on mydb.course.id_course=mydb.mark.id_course " +
            "join mydb.user on mydb.user.id=mydb.course.id_tutor " +
            "where id_student=? limit ?,?";
    public static final String SQL_QUERY_MARK_BY_TUTOR_ID="SELECT count(*) FROM mydb.course join mydb.mark on mydb.course.id_course=mydb.mark.id_course " +
            "            join mydb.user on mydb.user.id=mydb.mark.id_student " +
            "            where id_tutor=?";
    public static final String SQL_QUERY_MARK_BY_STUDENT_ID="SELECT count(*) FROM mydb.mark where id_student=?";
    public static final int ITEMS_ON_PAGE=10;
}
