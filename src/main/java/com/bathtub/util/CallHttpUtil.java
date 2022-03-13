package com.bathtub.util;

import net.sf.json.JSONObject;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class CallHttpUtil {

    private CloseableHttpClient httpClient;
    private HttpClientContext context;

    public CallHttpUtil() {
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder.<CookieSpecProvider>create().register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory()).register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory()).build();
        context.setCookieSpecRegistry(registry);


        CookieStore cookieStore = new BasicCookieStore();
        addCookie(cookieStore, "authId", "siA809CB2C0F4A3AA40A02D99E587AD984", "cnsuning.com", "/", "1969-12-31 " + "23:59:59.000Z");
        addCookie(cookieStore, "idsLoginUserIdLastTime", "11060524", "cnsuning.com", "/", "2018-11-14 16:34:53.594Z");
        addCookie(cookieStore, "secureToken", "1273F418016D9FDF34275126EF129119", "cnsuning.com", "/", "1969-12-31 " + "23:59:59.000Z");
        addCookie(cookieStore, "route", "7d0bd099bddc981ea86709ef451143cb", "http://lwms.cnsuning.com", "/", "1969-12-31" + " 23:59:59.000Z");
        addCookie(cookieStore, "TGC", "TGT21B6A23EAACE96AB7D0877C75BC8A02A136A5C9C", "sso.cnsuning.com", "/",
                "1969-12-31" + " 23:59:59.000Z");

        context.setCookieStore(cookieStore);
        this.httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore)// 设置Cookie
                .build();
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {

        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 判断参数map是否为非空
        if (map != null) {
            // 遍历参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 设置参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpGet.addHeader("Accept-Encoding", "gzip,deflate");
        httpGet.setHeader("Accept", "application/json");
        // 3 使用HttpClient执行httpGet，相当于按回车，发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 4 解析结果，封装返回对象httpResult，相当于显示相应的结果
        // 状态码
        // response.getStatusLine().getStatusCode();
        // 响应体，字符串，如果response.getEntity()为空，下面这个代码会报错,所以解析之前要做非空的判断
        // EntityUtils.toString(response.getEntity(), "UTF-8");
        HttpResult httpResult = null;
        // 解析数据封装HttpResult
        if (response.getEntity() != null) {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
        }

        // 返回
        return httpResult;
    }

    public class HttpResult {
        // 响应的状态码
        private int code;

        // 响应的响应体
        private String body;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public HttpResult(int statusCode, String string) {
            code = statusCode;
            body = string;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    protected void addCookie(CookieStore store, String name, String value, String domain, String path, String date) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        store.addCookie(cookie);
    }

    public Map<String, Object> getDbTableIndex(String warehouseNo, int num) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hashCode", warehouseNo);
        map.put("tableNum", num);
        HttpResult hr = null;
        try {
            hr = this.doGet("http://lswsadminpst.cnsuning.com/lsws-admin/jvm/TestHashTable/search.do", map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        JSONObject flowMap = JSONObject.fromObject(hr.getBody());
        String tableNum = flowMap.get("tableNum").toString();
        String tableIndex = tableNum.substring(tableNum.indexOf("库") + 1, tableNum.indexOf("表"));
        Long dbIndex = Long.valueOf(tableNum.substring(0, tableNum.indexOf("库")));
        if (dbIndex > 8) {
            dbIndex = dbIndex + 2;
        }
        result.put("dbIndex", dbIndex);
        result.put("tableIndex", tableIndex);
        return result;
    }

}
