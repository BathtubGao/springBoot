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
        this.httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore)// ??????Cookie
                .build();
    }

    /**
     * ????????????get??????
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {

        // ??????URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // ????????????map???????????????
        if (map != null) {
            // ????????????
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // ????????????
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 2 ??????httpGet????????????????????????url????????????
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpGet.addHeader("Accept-Encoding", "gzip,deflate");
        httpGet.setHeader("Accept", "application/json");
        // 3 ??????HttpClient??????httpGet????????????????????????????????????
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 4 ?????????????????????????????????httpResult?????????????????????????????????
        // ?????????
        // response.getStatusLine().getStatusCode();
        // ??????????????????????????????response.getEntity()????????????????????????????????????,???????????????????????????????????????
        // EntityUtils.toString(response.getEntity(), "UTF-8");
        HttpResult httpResult = null;
        // ??????????????????HttpResult
        if (response.getEntity() != null) {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
        }

        // ??????
        return httpResult;
    }

    public class HttpResult {
        // ??????????????????
        private int code;

        // ??????????????????
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
        String tableIndex = tableNum.substring(tableNum.indexOf("???") + 1, tableNum.indexOf("???"));
        Long dbIndex = Long.valueOf(tableNum.substring(0, tableNum.indexOf("???")));
        if (dbIndex > 8) {
            dbIndex = dbIndex + 2;
        }
        result.put("dbIndex", dbIndex);
        result.put("tableIndex", tableIndex);
        return result;
    }

}
