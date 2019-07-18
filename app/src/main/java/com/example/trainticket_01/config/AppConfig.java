package com.example.trainticket_01.config;

/**
 * Created by maomao on 2019/4/3.
 *
 * 设置APP参数类
 */

public class AppConfig
{
    // 域名
    public static final String DOMAIN = "http://192.168.199.191:5000";
    // 服务器地址
    public static final String SERVER_URL = DOMAIN + "/api/v1.0/";
    // 登录
    public static final String LOGIN = SERVER_URL + "login/";
    // 注册
    public static final String REGISTER = SERVER_URL + "register/";


    // 全部A
    public static final String TRAIN_TYPE_A = "";
    // 高铁G
    public static final String TRAIN_TYPE_G = "高铁";
    // 特快T
    public static final String TRAIN_TYPE_T = "特快";
    // 快车K
    public static final String TRAIN_TYPE_K = "快车";
    // 查询车票
    public static final String QUERY_TICKET = SERVER_URL + "get_ticket/";

    // 座次席别
    public static final String SEAT_TYPE_ZY = "一等座";
    public static final String SEAT_TYPE_ZE = "二等座";
    public static final String SEAT_TYPE_RW = "软卧";
    public static final String SEAT_TYPE_YW = "硬卧";
    public static final String SEAT_TYPE_YZ = "硬座";
    public static final String SEAT_TYPE_WZ = "无座";
    // 提交订单
    public static final String ORDER_TICKET = SERVER_URL + "post_order/";
    // 支付车票
    public static final String PAY_TICKET = SERVER_URL + "pay_order/";
    // 退票
    public static final String RETURN_TICKET = SERVER_URL + "return_ticket/";

    // 订单状态
    public static final int ORDER_NOW = 0;
    public static final int ORDER_OLD = 1;
    public static final int ORDER_UNPAID = 2;
    // 查询订单
    public static final String QUERY_ORDER = SERVER_URL + "get_orders/";



    // 修改信息
    public static final String MODIFY_INFO = SERVER_URL + "change_info/";
    // 修改密码
    public static final String MODIFY_PASSWORD = SERVER_URL + "change_password/";

}
