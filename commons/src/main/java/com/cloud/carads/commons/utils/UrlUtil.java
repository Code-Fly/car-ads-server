/**
 *
 */
package com.cloud.carads.commons.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author Barrie
 */
public class UrlUtil {
    public static String encode(String source, String charset) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decode(String source, String charset) {
        String result = source;
        try {
            result = java.net.URLDecoder.decode(source, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String join(String source, String param) {
        if (source.endsWith("/") && param.startsWith("/")) {
            return source + param.substring(1);
        } else if (!source.endsWith("/") && !param.startsWith("/")) {
            return source + "/" + param;
        } else {
            return source + param;
        }
    }
}
