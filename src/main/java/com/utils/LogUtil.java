package com.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by dqf on 2015/7/24.
 */
public class LogUtil {
    private static Logger objLog;

    public LogUtil() {
    }

    private static Logger getLogger() {
        if(objLog == null) {
            objLog = LoggerFactory.getLogger(LogUtil.class);
        }

        return objLog;
    }

    public static void info(String message, Exception exception) {
        try {
            log("INFO", message, exception);
        } catch (Exception var3) {
            ;
        }

    }

    public static void info(Object message) {
        log("INFO", (Object)message);
    }

    public static void trace(String message) {
        try {
            log("TRACE", (Object)message);
        } catch (Exception var2) {
            ;
        }

    }

    public static void trace(String message, Exception exception) {
        try {
            log("TRACE", message, exception);
        } catch (Exception var3) {
            ;
        }

    }

    public static void error(String message, Exception exception) {
        try {
            log("ERROR", message, exception);
        } catch (Exception var3) {
            ;
        }

    }

    public static void error(String message) {
        try {
            log("ERROR", (Object)message);
        } catch (Exception var2) {
            ;
        }

    }

    public static void warning(String message, Exception exception) {
        try {
            log("WARN", message, exception);
        } catch (Exception var3) {
            ;
        }

    }

    public static void warning(String message) {
        try {
            log("WARN", (Object)message);
        } catch (Exception var2) {
            ;
        }

    }

    public static void debug(String message, Exception exception) {
        try {
            log("DEBUG", message, exception);
        } catch (Exception var3) {
            ;
        }

    }

    public static void debug(String message) {
        try {
            log("DEBUG", (Object)message);
        } catch (Exception var2) {
            ;
        }

    }

    public static void log(String level, Object msg) {
        log(level, msg, (Throwable)null);
    }

    public static void log(String level, Throwable e) {
        log(level, (Object)null, e);
    }

    public static void log(String level, Object msg, Throwable e) {
        try {
            StringBuilder ex = new StringBuilder();
            Throwable t = new Throwable();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            String input = sw.getBuffer().toString();
            StringReader sr = new StringReader(input);
            BufferedReader br = new BufferedReader(sr);

            for(int line = 0; line < 4; ++line) {
                br.readLine();
            }

            String var15 = br.readLine();
            int paren = var15.indexOf("at ");
            var15 = var15.substring(paren + 3);
            paren = var15.indexOf(40);
            String invokeInfo = var15.substring(0, paren);
            int period = invokeInfo.lastIndexOf(46);
            ex.append('[');
            ex.append(invokeInfo.substring(0, period));
            ex.append(':');
            ex.append(invokeInfo.substring(period + 1));
            ex.append("():");
            paren = var15.indexOf(58);
            period = var15.lastIndexOf(41);
            ex.append(var15.substring(paren + 1, period));
            ex.append(']');
            ex.append(" - ");
            ex.append(msg);
            if(StringUtils.equals(level, "TRACE")) {
                getLogger().trace(ex.toString(), e);
            } else if(StringUtils.equals(level, "DEBUG")) {
                getLogger().debug(ex.toString(), e);
            } else if(StringUtils.equals(level, "INFO")) {
                getLogger().info(ex.toString(), e);
            } else if(StringUtils.equals(level, "WARN")) {
                getLogger().warn(ex.toString(), e);
            } else if(StringUtils.equals(level, "ERROR")) {
                getLogger().error(ex.toString(), e);
            }
        } catch (Exception var14) {
            info(var14.getLocalizedMessage());
        }

    }
}
