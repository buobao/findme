package com.utils;

import com.bean.BaseEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dqf on 2015/7/14.
 */
public class CommonUtil {
    private static Pattern referer_pattern = Pattern.compile("@([^@^\\s^:]{1,})([\\s\\:\\,\\;]{0,1})");

    public CommonUtil(){}

    private static boolean match(String paramString1, String paramString2){
        boolean bool;
        if (paramString1 != null && paramString1.trim().length() != 0){
            if (paramString2 != null && paramString2.trim().length() != 0){
                bool = Pattern.compile(paramString1).matcher(paramString2).matches();
            }else{
                bool = false;
            }
        }else {
            bool = false;
        }
        return bool;
    }

    public static boolean isMobile(String str){
        if (StringUtils.isEmpty(str)){
            return false;
        }else{
            return Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$").matcher(str).matches();
        }
    }

    public static boolean isEmail(String email){
        if (StringUtils.isEmpty(email)){
            return false;
        }else {
            boolean tag = true;
            String pattern1 = "[a-z0-9A-Z_.-]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\\\.){1,3}[a-zA-z\\\\-]{1,}";
            Pattern pattern = Pattern.compile("[a-z0-9A-Z_.-]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\\\.){1,3}[a-zA-z\\\\-]{1,}");
            Matcher mat = pattern.matcher(email);
            if (!mat.find()){
                tag = false;
            }
            return tag;
        }
    }

    @Deprecated
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,8) + uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
    }

    @Deprecated
    public static String getRandomString(int length){
        if (length <=0){
            return "";
        }else{
            char[] randomChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
            Random random = new Random();
            StringBuffer stringBuffer = new StringBuffer();

            for (int i=0;i<length;++i){
                stringBuffer.append(randomChar[Math.abs(random.nextInt())%randomChar.length]);
            }

            return stringBuffer.toString();
        }
    }

    public static List<String> splitString(String str, int length){
        ArrayList list = new ArrayList();

        for (int i=0;i<str.length();i+=length){
            int endIndex = i + length;
            if (endIndex <= str.length()){
                list.add(str.substring(i,i+length));
            }else{
                list.add(str.substring(i,str.length()-1));
            }
        }
        return list;
    }

    public static String toString(List<String> list, String separator){
        StringBuffer stringBuffer = new StringBuffer();
        Iterator i$ = list.iterator();

        while (i$.hasNext()){
            String str = (String)i$.next();
            stringBuffer.append(separator + str);
        }
        stringBuffer.deleteCharAt(0);
        return stringBuffer.toString();
    }

    public List<Object> listCompare(List<Object> list_pid, List<Object> list_id){
        if (list_pid.size() != list_id.size()){
            return list_pid.size() >= list_id.size()?list_pid:list_id;
        }
        ArrayList list = new ArrayList();
        return list;
    }

    public static String toString(String[] obj, String tag){
        String str = "";
        if (obj != null && obj.length>0){
            for (int i=0;i<obj.length;++i){
                str += obj[i];
                if (i<obj.length-1){
                    str += tag;
                }
            }
        }
        return str;
    }

    public static String getAliasPath(String[] str, int index, int begin){
        String result = "";
        if (index < 0){
            return result;
        }else{
            if (index > str.length){
                index = str.length - 1;
            }

            for (int i=begin;i<=index;++i){
                if (i==begin){
                    result = str[i];
                }else{
                    result = result + "."+str[i];
                }
            }
            return result;
        }
    }

    public static String getAliasPath(String[] str,int index){return getAliasPath(str, index, 0);}

    public static Object getData(String field, String data){
        Object result = "%" + data + "%";
        if (StringUtils.endsWith(field, "Sex")){
            result = BaseEnum.SexEnum.valueOf(data);
        }

        if (StringUtils.endsWith(field, "If")){

        }

        if (StringUtils.endsWith(field, "State")){
            result = BaseEnum.StateEnum.valueOf(data);
        }else if (StringUtils.endsWith(field, "Integer")){
            result = Integer.valueOf(data);
        }

        return result;
    }

    public static String getPath(String[] str, int index) {
        return getPath(str, index, 0);
    }

    public static String getPath(String[] str, int index, int begin) {
        String result = "";
        if(index < 0) {
            return result;
        } else {
            if(index > str.length) {
                index = str.length - 1;
            }

            for(int i = begin; i <= index; ++i) {
                if(i == begin) {
                    result = str[i];
                } else if(i == index) {
                    result = result + "." + str[i];
                } else {
                    result = result + "_" + str[i];
                }
            }

            return result;
        }
    }

    public static String getAlias(String[] str, int index) {
        return getAlias(str, index, 0);
    }

    public static String getAlias(String[] str, int index, int begin) {
        String result = "";
        if(index < 0) {
            return result;
        } else {
            if(index > str.length) {
                index = str.length - 1;
            }

            for(int i = begin; i <= index; ++i) {
                if(i == begin) {
                    result = str[i];
                } else {
                    result = result + "_" + str[i];
                }
            }

            return result;
        }
    }

    public static List<String> getReferers(String msg) {
        ArrayList referers = new ArrayList();
        Matcher matchr = referer_pattern.matcher(msg);

        while(matchr.find()) {
            String origion_str = matchr.group();
            String str = origion_str.substring(1, origion_str.length()).trim();
            referers.add(str);
        }

        return referers;
    }
}

















































