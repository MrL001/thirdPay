package com.hntyy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	@SuppressWarnings("unused")
	private String charset = "UTF-8";
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	public GetHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
 
	/**
	 * 获得被装饰对象的引用和采用的字符编码
	 * 
	 * @param request
	 * @param charset
	 */
	public GetHttpServletRequestWrapper(HttpServletRequest request,
			String charset) {
		super(request);
		this.charset = charset;
	}
 
	/**
	 * 重写getParameter方法获得参数，对特殊字符进行过滤
	 */
	public String getParameter(String name) {
		String value = super.getParameter(name);
		value = value == null ? null : convert(value);
		return value;
	}
 
	/**
	 * 重写getParameterValues方法获得参数，对特殊字符进行过滤
	 */
	public String[] getParameterValues(String name){
		String[] values = super.getParameterValues(name);
		if(values!=null && values.length>0){
			values[0] = values[0] == null ? null : convert(values[0]);
		}
		return values;
	}
	
	/**
	 * 过滤规则
	 * @param target
	 * @return
	 */
	public String convert(String target) {
//		target =  StringEscapeUtils.escapeHtml(target);
//		target =  StringEscapeUtils.escapeJavaScript(target);
//		target = StringEscapeUtils.escapeSql(target);
//		target = target.replace("&", "&");
		
		/*target = target.replace(";", "*");
		target = target.replace("(", "*");
		target = target.replace(")", "*");
		target = target.replace("、", "*");	
		target = target.replace("<", "<");
		target = target.replace(">", ">");
		target = target.replace("'", "'");*/
//		target = target.replace("\"", """);
//		target = target.replace("alert", "a lert");
//		target = target.replace("script", "s cript");
//		target = target.replace("document", "d ocument");
//		target = target.replace("cookie", "c ookie");
		int index1 = target.indexOf("Optional(\"");
		int indexprelength="Optional(\"".length();
		int index3 = target.lastIndexOf("\")");
		//System.out.println("nvalue="+target);
		if(index1>-1){
			String nvalue = target.substring(index1+indexprelength, index3);

			return nvalue;
		}
		return target;
	}
}