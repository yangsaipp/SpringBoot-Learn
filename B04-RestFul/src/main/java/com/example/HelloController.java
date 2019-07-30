package com.example;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Component("/helloContro")
public class HelloController extends AbstractController {
	private static final String CONTENT_TYPE = "application/xml; charset=UTF-8";
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType(CONTENT_TYPE);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
		String result = "sss";
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<root>\n");
		out.println("<result>"+result+"</result>\n");
		out.println("</root>\n");
		
		out.flush();
		out.close();
		return null;
	}

}
