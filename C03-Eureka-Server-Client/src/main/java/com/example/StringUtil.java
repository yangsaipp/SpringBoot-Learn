package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StringUtil {
	public static StringBuilder fieldToString(Object obj) {
		return fieldToString(new StringBuilder(), obj);
    }
	
	public static StringBuilder fieldToString(StringBuilder sb, Object obj) {
        Field [] fields = obj.getClass().getDeclaredFields();
        sb.append(obj.getClass().getName())
        .append(";");
         
        for(Field f:fields){
            f.setAccessible(true);
            try {
				sb.append(f.getName())
				.append("=")
				.append(f.get(obj)).append(";");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            sb.append("<br/>");
        }
        return sb;
    }
	
	public static StringBuilder getToString(Object obj) {
		return getToString(new StringBuilder(), obj);
    }
	
	public static StringBuilder getToString(StringBuilder sb, Object obj) {
        Method [] methods = obj.getClass().getMethods();
        sb.append(obj.getClass().getName())
        .append(";");
         
        for(Method m:methods){
        	if(!m.getName().startsWith("get")) {
        		continue;
        	}
            m.setAccessible(true);
            try {
				sb.append(m.getName())
				.append("=")
				.append(m.invoke(obj)).append(";");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            sb.append("<br/>");
        }
        return sb;
    }
}
