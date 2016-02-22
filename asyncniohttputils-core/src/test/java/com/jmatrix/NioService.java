package com.jmatrix;

import com.jmatrix.asynchttp.core.AsyncHttpResponse;
import com.jmatrix.asynchttp.netty.NettyClient;
import io.netty.handler.codec.http.*;

import java.net.URI;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public class NioService {

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        String reqUrl = "http://mo.api.vip.com/moapi/rule/startup/v2?api_key=23e7f28019e8407b98b84cd05b5aef2c&app_name=shop_iphone&app_version=5.3&client=iphone&format=json&height=1334&idfv=DBB7C916-9FDB-4FE0-8B14-A99CAFFCDC82&mobile_channel=ng00010v%3Aal80ssgp%3A37u8zn0w%3Ang00010p&mobile_platform=3&model=iPhone7%2C2&net=WIFI&province_id=105102&service_provider=46001&session_id=dae589a183ab9a1ad8351c6647f32a5331838d3b_shop_iphone_1442475401444&standby_id=ng00010v%3Aal80ssgp%3A37u8zn0w%3Ang00010p&timestamp=1442475402&ver=2.0&warehouse=VIP_BJ&width=750&mars_cid=6666667&user_id=103991712&tag=B&area_id=1051028";
        try {

            AsyncHttpResponse asyncHttpResponse = client.doGet(reqUrl, null);

            System.out.println("async content:" + asyncHttpResponse.getResponseAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("succ!");
    }

}
