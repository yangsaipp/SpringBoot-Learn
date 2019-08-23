package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * 请求地址：http://127.0.0.1:8080/demo.do
 * @author yangsai
 *
 */
@Component("/demo.do")
public class DemoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1429382551388733590L;
	
	private static final String CONTENT_TYPE = "application/xml; charset=UTF-8";


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	
}