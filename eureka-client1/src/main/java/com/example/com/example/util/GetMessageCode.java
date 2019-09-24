package com.example.com.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * /**
 *
 * @author 黄炽鑫
 */
public class GetMessageCode {
    //https://api.miaodiyun.com/20150822/industrySMS/sendSMS
    private static final String QUERY_PATH = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    private static final String ACCOUNT_SID = "7e4bb734de6b42d6ad3451215498c222";
    private static final String AUTH_TOKEN = "777da84b6a4742c48e6498837e857781";

    /**
     * 根据实际号码发送手机验证码
     *
     * @param phone
     * @return 验证码
     */
    public static int getCode(String phone) {
//        int code[] = smsCode();
        int code = (int) ((Math.random() * 9 + 1) * 10000);
        String str = "【殇璃科技】您的验证码为" + code + "，请于25分钟内正确输入，如非本人操作，请忽略此短信。";
        String timestamp = getTimestamp();
        String sig = getMD5(ACCOUNT_SID, AUTH_TOKEN, timestamp);
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(QUERY_PATH);
            URLConnection connection = (HttpURLConnection) url.openConnection();
            //请求方式
            ((HttpURLConnection) connection).setRequestMethod("POST");
            //是否允许输入
            connection.setDoInput(true);
            //是否允许参数输出
            connection.setDoOutput(true);
            //设置连接响应时间
            connection.setConnectTimeout(5000);
            //设置参数读取参数
            connection.setReadTimeout(10000);
            //设置请求头
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            //提交请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            String args = getString(ACCOUNT_SID, str, phone, timestamp, sig, "json");
            out.write(args);
            out.flush();
            reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) connection).getInputStream(), "UTF-8"));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }
            System.out.println(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

//  accountSid=a14f6bfd43ce44c9b019de57f4e2de4b&smsContent=【秒嘀科技】您的验证码是345678，30分钟输入有效。
//    &to=13896543210&timestamp=20150821100312&sig=a14f6bfd43ue44c9b019du57f4e2ee4r&respDataType=JSON

    /**
     * 请求参数拼接
     *
     * @param accountSid
     * @param smsContent
     * @param to
     * @param timestamp
     * @param sig
     * @param respDataType
     * @return
     */
    public static String getString(String accountSid, String smsContent, String to, String timestamp, String sig, String respDataType) {
        return "accountSid=" + accountSid + "&smsContent=" + smsContent + " &to=" + to + "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + respDataType;

    }

    /**
     * 获取时间
     *
     * @return
     */
    public static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 签名
     *
     * @param sid
     * @param token
     * @param timestamp
     * @return
     */
    public static String getMD5(String sid, String token, String timestamp) {
        StringBuilder result = new StringBuilder();
        String source = sid + token + timestamp;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //加密
            byte[] bytes = digest.digest(source.getBytes());
            for (byte b : bytes) {
                //返回整数参数的字符串表示形式，作为16位中的无符号整数
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    result.append("0" + hex);
                } else {
                    result.append(hex);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}



