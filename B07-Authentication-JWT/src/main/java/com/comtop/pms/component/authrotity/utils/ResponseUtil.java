package com.comtop.pms.component.authrotity.utils;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.comtop.pms.component.authrotity.config.AuthrotityConstant;
import com.google.gson.Gson;


/**
 * 
 * 响应工具类
 * @author lihuan
 *
 */
public class ResponseUtil {
	/**
	 * 请求返回数据处理
	 * @param res
	 * @return 响应体
	 */
	public static ResponseEntity<String> general(CommonResponse res){
		Gson gson = new Gson();
		return new ResponseEntity<String>(gson.toJson(res), HttpStatus.OK);
	}
	
	/**
	 * 成功请求
	 * @param data
	 * @return 响应体
	 */
	public static ResponseEntity<String> success(Object data){
		CommonResponse res = new CommonResponse();
		res.setCode(AuthrotityConstant.RESCODE_SUCCESS);
		res.setData(data);
		return general(res);
	}
	
	/**
	 * @return 响应体
	 */
	public static ResponseEntity<String> success(){
		CommonResponse res = new CommonResponse();
		res.setCode(AuthrotityConstant.RESCODE_SUCCESS);
		return general(res);
	}
	
	/**
	 * @param msg
	 * @return 响应体
	 */
	public static ResponseEntity<String> success(String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(AuthrotityConstant.RESCODE_SUCCESS_MSG);
		res.setMsg(msg);
		return general(res);
	}
	
	/**
	 * 请求抛出异常
	 * @param msg
	 * @return 响应体
	 */
	public static ResponseEntity<String> exception(String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(AuthrotityConstant.RESCODE_EXCEPTION);
		res.setMsg(msg);
		return general(res);
	}
	
	/**
	 * @return 响应体
	 */
	public static ResponseEntity<String> unKonwException(){
		CommonResponse res = new CommonResponse();
		res.setCode(AuthrotityConstant.RESCODE_EXCEPTION);
		res.setMsg("请稍后再试");
		return general(res);
	}
	
	/**
	 * 自定义
	 * @param code
	 * @param msg
	 * @return 响应体
	 */
	public static ResponseEntity<String> custom(Integer code, String msg){
		CommonResponse res = new CommonResponse();
		res.setCode(code);
		res.setMsg(msg);
		return general(res);
	}
}
