/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.example.ex1;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2018年3月2日 杨赛
 */
public class MyHttpResponse extends HttpServletResponseWrapper {

	/**
	 * 构造函数
	 * @param response
	 */
	public MyHttpResponse(HttpServletResponse response) {
		super(response);
	}

	/**
	 * 
	 * @see javax.servlet.http.HttpServletResponseWrapper#sendRedirect(java.lang.String)
	 */
	@Override
	public void sendRedirect(String location) throws IOException {
		// super.sendRedirect(location);
		this.setStatus(401);
		writeValueToResp(this);
	}

	/**
	 * 将Object序列化后写入响应头中 
	 * @param resp HttpServletResponse
	 * @param obj Object
	 */
	private void writeValueToResp(HttpServletResponse resp) {
		OutputStreamWriter out = null;
		resp.setContentType("application/json;charset=UTF-8");
		try {
			out = new OutputStreamWriter(resp.getOutputStream(),"UTF-8");
			mapper.writeValue(out, "22");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/** */
	private ObjectMapper mapper = new ObjectMapper();
}
