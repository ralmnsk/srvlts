package com.facultative.web.filter;

import com.facultative.model.UserType;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static com.facultative.service.constants.Constants.*;

@WebFilter({"/*"})
public class RoleFilter implements Filter {
    private Set<String> commandSet = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        commandSet.add(LOGIN);               // commands that can be accessed by guests
        commandSet.add(TO_LOGIN);              //without redirection
        commandSet.add(INDEX);
        commandSet.add(REGISTER);
        commandSet.add(TO_REGISTER);
        commandSet.add(LANG);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        authorization(request, response);
        chain.doFilter(request, response);
    }

    //sets command 'index' as attribute of the request if user is a guest
    //command is executed in Controller
    private boolean authorization(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String action = INDEX;
        if (req.getParameter(COMMAND) != null){ //get command from parameter!
            action = req.getParameter(COMMAND);
        }

        UserType clientType = UserType.GUEST;
        if (session.getAttribute(USER_ROLE) != null){
            clientType = UserType.valueOf((String)session.getAttribute(USER_ROLE));
        }

        if (clientType == UserType.GUEST){
            if (!commandSet.contains(action)){
                request.setAttribute(COMMAND,INDEX); // if action of guest aren't in commandSet
            }                                          // then set index command to
        }                                              // pass there

        return true;
    }

    @Override
    public void destroy() {

    }
}