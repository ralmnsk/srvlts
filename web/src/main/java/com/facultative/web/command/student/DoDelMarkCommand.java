package com.facultative.web.command.student;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;
import com.facultative.service.config.ConfigurationManager;
import com.facultative.web.command.ActionCommand;
import com.facultative.web.command.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.facultative.service.constants.Constants.*;

public class DoDelMarkCommand implements ActionCommand {

    private IMarkService<Mark> markService = MarkServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long markId = (Long)request.getSession().getAttribute(MARK_ID);
        markService.delete(markId);

        return "/controller?command=viewmark";
    }
}
