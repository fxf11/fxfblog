package com.fxf.blogbs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexMatchesUtils {

    /**
     * 判断是否含有空格
     * @param text
     * @return
     */
    public static boolean isBlank(String text) {


        if (text != null) {
            String pattern = "\\s";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(text);
            if (m.find()){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean isEmail(String email){
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        if (m.find()){
            return true;
        }else {
            return false;
        }
    }
}
