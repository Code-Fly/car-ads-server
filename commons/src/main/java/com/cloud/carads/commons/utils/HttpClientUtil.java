package com.cloud.carads.commons.utils;

import com.cloud.wechat.commons.exception.ConnectionFailedException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2016/4/21.
 */
public class HttpClientUtil {

    public static String get(String url, String charset, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpGet.class, url, "", charset, null, cert);
    }

    public static String get(String url, Map<String, String> params, String charset, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpGet.class, url, params, charset, null, cert);
    }

    public static String post(String url, String param, String charset, String ContentType, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpPost.class, url, param, charset, ContentType, cert);
    }

    public static String post(String url, Map<String, String> params, String charset, String ContentType, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpPost.class, url, params, charset, ContentType, cert);
    }

    public static String put(String url, String param, String charset, String ContentType, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpPut.class, url, param, charset, ContentType, cert);
    }

    public static String put(String url, Map<String, String> params, String charset, String ContentType, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpPut.class, url, params, charset, ContentType, cert);
    }

    public static String delete(String url, String param, String charset, String ContentType, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpDelete.class, url, param, charset, ContentType, cert);
    }

    public static String delete(String url, Map<String, String> params, String charset, String ContentType, SSLCert cert) throws UnsupportedEncodingException, ConnectionFailedException {
        return HttpClientUtil.request(HttpDelete.class, url, params, charset, ContentType, cert);
    }


    public static String request(Class clazz, String url, Map<String, String> params, String charset, String contentType, SSLCert cert) throws ConnectionFailedException {
        if (clazz == HttpGet.class) {
            List<NameValuePair> valuePairs = new ArrayList(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
                valuePairs.add(nameValuePair);
            }
            String param = URLEncodedUtils.format(valuePairs, charset);

            return doRequest(url, param, charset, cert);
        } else {
            UrlEncodedFormEntity formEntity = null;
            try {
                if (null != params && !params.isEmpty()) {
                    List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
                        valuePairs.add(nameValuePair);
                    }

                    formEntity = new UrlEncodedFormEntity(valuePairs, charset);
                    if (null != contentType && !StringUtils.isEmpty(contentType)) {
                        formEntity.setContentType(contentType);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new ConnectionFailedException(e);
            }
            return doEnclosingRequest(clazz, url, formEntity, charset, cert);
        }
    }

    public static String request(Class clazz, String url, String params, String charset, String ContentType, SSLCert cert) throws ConnectionFailedException, UnsupportedEncodingException {
        if (clazz == HttpGet.class) {
            return doRequest(url, params, charset, cert);
        } else {
            StringEntity stringEntity = null;
            if (null != params && !StringUtils.isEmpty(params)) {
                stringEntity = new StringEntity(params, charset);
                if (null != ContentType && !StringUtils.isEmpty(ContentType)) {
                    stringEntity.setContentType(ContentType);
                }
            }
            return doEnclosingRequest(clazz, url, stringEntity, charset, cert);
        }
    }

    public static String upload(String url, Map<String, String> params, InputStream inputStream, String fileName, String charset, SSLCert cert) throws ConnectionFailedException, UnsupportedEncodingException {

        InputStreamBody isb = new InputStreamBody(inputStream, fileName);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", isb);

        if (null != params && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue()), Charset.forName(charset)));
            }
        }
        HttpEntity reqEntity = builder.build();
        return doEnclosingRequest(HttpPost.class, url, reqEntity, charset, cert);
    }

    public static String forward(Class clazz, String url, String params, String charset, String contentType, HttpServletResponse httpServletResponse) throws ConnectionFailedException {
        if (clazz == HttpGet.class) {
            return doForward(url, params, charset, null, httpServletResponse);
        } else {
            StringEntity sEntity = null;
            if (null != params && !StringUtils.isEmpty(params)) {
                sEntity = new StringEntity(params, charset);
                if (null != contentType && !StringUtils.isEmpty(contentType)) {
                    sEntity.setContentType(contentType);
                }
            }
            return doEnclosingForward(clazz, url, sEntity, charset, null, httpServletResponse);
        }
    }


    protected static String doEnclosingRequest(Class clazz, String url, HttpEntity sEntity, String charset, SSLCert cert) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {


            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", createSslFactory(cert))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            // 从上述SSLContext对象中得到SSLSocketFactory对象
            HttpEntityEnclosingRequestBase httpPost = (HttpEntityEnclosingRequestBase) clazz.newInstance(); // 设置响应头信息
            httpPost.setURI(URI.create(url));

            if (null != sEntity) {
                httpPost.setEntity(sEntity);
            }

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            respStr = EntityUtils.toString(response.getEntity(), charset);
            EntityUtils.consume(entity);
            httpPost.abort();
            response.close();
            httpclient.close();

        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;

    }

    protected static String doRequest(String url, String param, String charset, SSLCert cert) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", createSslFactory(cert))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();


            HttpRequestBase httpGet = null;

            if (null != param && !StringUtils.isEmpty(param)) {
                httpGet = new HttpGet(url + "?" + param);

            } else {
                httpGet = new HttpGet(url);
            }

            response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            respStr = EntityUtils.toString(entity, charset).trim();
            httpGet.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;
    }

    protected static String doForward(String url, String param, String charset, SSLCert cert, HttpServletResponse httpServletResponse) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        try {


            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", createSslFactory(cert))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            HttpGet httpGet = null;
            if (null != param && !StringUtils.isEmpty(param)) {
                httpGet = new HttpGet(url + "&" + param);

            } else {
                httpGet = new HttpGet(url);
            }

            response = httpclient.execute(httpGet);
            Header[] contentDisposition = response.getHeaders("Content-disposition");
            for (Header cd : contentDisposition) {
                httpServletResponse.setHeader("Content-disposition", cd.getValue());
            }
            Header[] contentType = response.getHeaders("Content-Type");
            for (Header ct : contentType) {
                httpServletResponse.setHeader("Content-Type", ct.getValue());
            }
            Header[] contentLength = response.getHeaders("Content-Length");
            for (Header cl : contentLength) {
                httpServletResponse.setHeader("Content-Length", cl.getValue());
            }
            entity = response.getEntity();

            InputStream inputStream = entity.getContent();
            OutputStream os = httpServletResponse.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
            //respStr = EntityUtils.toString(entity, charset).trim();
            httpGet.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return null;
    }

    protected static String doEnclosingForward(Class clazz, String url, HttpEntity sEntity, String charset, SSLCert cert, HttpServletResponse httpServletResponse) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        try {


            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", createSslFactory(cert))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            // 从上述SSLContext对象中得到SSLSocketFactory对象
            HttpEntityEnclosingRequestBase httpPost = (HttpEntityEnclosingRequestBase) clazz.newInstance(); // 设置响应头信息
            httpPost.setURI(URI.create(url));

            if (null != sEntity) {
                httpPost.setEntity(sEntity);
            }

            response = httpclient.execute(httpPost);

            Header[] contentDisposition = response.getHeaders("Content-disposition");
            for (Header cd : contentDisposition) {
                httpServletResponse.setHeader("Content-disposition", cd.getValue());
            }
            Header[] contentType = response.getHeaders("Content-Type");
            for (Header ct : contentType) {
                httpServletResponse.setHeader("Content-Type", ct.getValue());
            }
            Header[] contentLength = response.getHeaders("Content-Length");
            for (Header cl : contentLength) {
                httpServletResponse.setHeader("Content-Length", cl.getValue());
            }
            entity = response.getEntity();

            InputStream inputStream = entity.getContent();
            OutputStream os = httpServletResponse.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
            //respStr = EntityUtils.toString(entity, charset).trim();
            httpPost.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return null;
    }

    private static SSLConnectionSocketFactory createSslFactory(SSLCert cert) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, NoSuchProviderException {
        if (null != cert && null != cert.getPath() && null != cert.getPassword()) {
            // 证书
            char[] password = cert.getPassword().toCharArray();
            InputStream certStream = new FileInputStream(new File(cert.getPath()));
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            return new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());
        } else {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());

            return new SSLConnectionSocketFactory(sslContext);
        }
    }

    public static void main(String[] args) {

    }
}
