package com.opslab.bean;

import lombok.Data;

import java.util.Map;

/**
 * 封装通用的客户端请求
 */
@Data
public class ClientInfo {
    //唯一标识
    private String kid;
    //用户标识
    private String userId;
    //请求发送时间
    private Long reqTime;
    //请求响应时间
    private Long respTime;
    //请求端ip
    private String clientIp;
    //客户端os系统
    private String platform;
    //客户端os系统版本
    private String osVersion;
    //客户端类型名称及版本
    private String appInfo;
    //客户端指纹信息
    private String userAgents;
    //http referer信息
    private String referer;
    /*手机IMEI设备Id*/
    private String driverId;
    //请求端
    private String uri;
    //请求参数
    private Map<String,Object> params;
}
