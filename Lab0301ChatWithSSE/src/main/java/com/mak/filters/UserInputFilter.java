package com.mak.filters;

import com.mak.validation.GenderValidator;
import com.mak.validation.UserNameValidator;
import com.mak.validation.Validator;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserInputFilter implements Filter {
   private final Validator<String> USER_NAME_VALIDATOR = new UserNameValidator();
   private final Validator<String> GENDER_VALIDATOR = new GenderValidator();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String username = request.getParameter("userName");
        String gender = request.getParameter("gender");
        if (username != null && gender != null) {
            if (USER_NAME_VALIDATOR.isValid(username) && GENDER_VALIDATOR.isValid(gender)) {
                chain.doFilter(request, response);
            }
        } else {
            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

}
