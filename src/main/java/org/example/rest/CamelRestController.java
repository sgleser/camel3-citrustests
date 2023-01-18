package org.example.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CamelRestController {

    @RequestMapping("/api/**")
    public void camelServlet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getRequestURI();
            String camelPrefix = (path != null && path.startsWith("/")) ? "/camel" : "/camel/";
            request.getServletContext().getRequestDispatcher(camelPrefix + path).forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
