package com.facultative.tag;

import com.facultative.model.Person;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static com.facultative.service.constants.Constants.SPACE;

public class MyTag extends TagSupport {

    private String head;
    private int rows;

    public void setHead (String head) {
        this. head = head;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            if( pageContext.getSession().getAttribute("person") != null){
                JspWriter out = pageContext.getOut();
                Person person = (Person)pageContext.getSession().getAttribute("person");
                out.write(person.getSurname() + SPACE + person.getName());
            }

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
