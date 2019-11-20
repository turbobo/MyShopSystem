package shop.utils;
/**
 *  字符串工具类
 * @author 73556
 *
 */

public class StringUtils {
	//字符串为null或者是空字符串
	public static boolean isNullorBlank(String str){		
		//trim() 方法用于删除字符串的头尾空格。trim() 方法不会改变原始字符串。
		if(str!=null&&!"".equals(str.trim())){
			return true;  //两个条件都不为空
		}
		return false;
	}
	//将null改为空字符串
	public static String nullToBlank(Object str){
		if(str==null){
			str="";
		}
		return str.toString();
		
	}
	
}
