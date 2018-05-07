/**
 *
 */
package com.cloud.carads.commons.utils;

import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @author Barrie
 */
public class ConfigUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    public static final String PATH_SEPARATOR = File.separator;

    public static String getJson(String fileName) {
        String path = getServerPath() + "WEB-INF/classes/" + fileName;
        String tempString = null;
        String lastStr = "";

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, CharEncoding.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            while ((tempString = reader.readLine()) != null) {
                lastStr += tempString;
            }
        } catch (IOException e) {
            logger.error("获取配置失败", e);
        }
        return lastStr;
    }

    public static String getProperty(String fileName, String keyname) {
        Properties p = new Properties();
        String result = "";
        try {
            String path = getServerPath() + "WEB-INF/classes/" + fileName;
            p.load(new InputStreamReader(new FileInputStream(path), "UTF-8"));
//            String path = "/api/" + fileName;
//            p.load(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), "UTF-8"));

            result = p.getProperty(keyname);
        } catch (Exception e) {
            logger.error("获取配置失败", e);
        }
        return result;
    }

    public static void setProperty(String fileName, String keyname, String keyvalue) {
        Properties p = new Properties();
        try {
            String path = getServerPath() + "WEB-INF/classes/" + fileName;
            p.load(new FileInputStream(path));
            OutputStream fos = new FileOutputStream(path);
            p.setProperty(keyname, keyvalue);
            p.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            logger.error("添加配置失败", e);
        }
    }

    public static void setProperty(String fileName, Map<String, String> map) {
        Properties p = new Properties();
        try {
            String path = getServerPath() + "WEB-INF/classes/" + fileName;
            p.load(new FileInputStream(path));
            OutputStream fos = new FileOutputStream(path);
            p.putAll(map);
            p.store(fos, "Update '" + map + "'");
        } catch (IOException e) {
            logger.error("更新配置失败", e);
        }
    }

    public static String getServerPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource(PATH_SEPARATOR).getPath();

        if (path.startsWith("file:/")) {
            path = "/" + path.substring(6, path.indexOf("/" + "WEB-INF")) + "/";
        } else {
            path = "/" + path.substring(1, path.indexOf("/" + "WEB-INF")) + "/";
        }

        return path;
    }

    /**
     * 获取请求的URL
     *
     * @param request
     * @return
     */
    public static String getServerUrl(HttpServletRequest request, boolean isFullPath) {
        String path = request.getContextPath();

        if (isFullPath) {
            int port = request.getServerPort();
            String basePath = null;
            if (80 == port) {
                basePath = request.getScheme() + "://" + request.getServerName() + path;
            } else {
                basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            }
            return basePath;
        } else {
            return path;
        }
    }

    /**
     * 获取请求的IP
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = null;

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
