package com.utils;

import com.entity.sys.Sms;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ResourceBundle;

/**
 * Created by dqf on 2015/8/24.
 */
public class SMSUtil {
    public static Logger logger = LoggerFactory.getLogger(SMSUtil.class);
    public static boolean SENDOPEN = false;
    public static String uri = "http://222.73.117.158/msg/";
    public static String accountId = "Gcflk888";
    public static String password = "Gcflk888";
    public static String title = "【玖达信息】";
    public static boolean needstatus = true;
    public static String product = "349312826";
    public static String extno = null;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        uri = bundle.getString("sms_uri");
        accountId = bundle.getString("sms_accountId");
        password = bundle.getString("sms_password");
        title = bundle.getString("sms_title");
        needstatus = Boolean.parseBoolean(bundle.getString("sms_needstatus"));
        product = bundle.getString("sms_product");
        extno = bundle.getString("sms_extno");
    }

    public static String httpSend(Sms sms) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod();
        String photoNumber = sms.getPhone();
        if (CommonUtil.isMobile(photoNumber)) {
            try {
                URI base = new URI(uri, false);
                method.setURI(new URI(base, "HttpBatchSendSM", false));
                method.setQueryString(new NameValuePair[]{new NameValuePair("account", accountId), new NameValuePair("pswd", password), new NameValuePair("mobile", sms.getPhone()), new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", sms.getMsg()), new NameValuePair("product", product), new NameValuePair("extno", extno)});
                int result = client.executeMethod(method);
                if (result != 200) {
                    throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
                } else {
                    InputStream in = method.getResponseBodyAsStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    boolean len = false;

                    int len1;
                    while ((len1 = in.read(buffer)) != -1) {
                        baos.write(buffer, 0, len1);
                    }

                    String var10 = URLDecoder.decode(baos.toString(), "UTF-8");
                    return var10;
                }
            } finally {
                method.releaseConnection();
            }
        } else {
            return null;
        }
    }

    private SMSUtil() {
    }

    public static String getSmsText() {
        return "";
    }
}
