package com.opslab.bean;

import java.util.Map;

/**
 * http请求信息
 */
public class HttpRequestInfo {
    //请求标识如userid
    private String bskid;
    //http请求来源
    private String referer;
    //http请求端的信息
    private String clientIp;
    //浏览器指纹
    private String userAgents;
    //请求地址
    private String uri;
    //请求参数
    private Map<String, Object> params;


    public HttpRequestInfo() {
    }

    public HttpRequestInfo(String bskid, String referer, String clientIp, String userAgents, String uri, Map<String, Object> params) {
        this.bskid = bskid;
        this.referer = referer;
        this.clientIp = clientIp;
        this.userAgents = userAgents;
        this.uri = uri;
        this.params = params;
    }

    public String getBskid() {
        return bskid;
    }

    public void setBskid(String bskid) {
        this.bskid = bskid;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUserAgents() {
        return userAgents;
    }

    public void setUserAgents(String userAgents) {
        this.userAgents = userAgents;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
