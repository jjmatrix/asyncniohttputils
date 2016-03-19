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

    public static void testPost() {
        NettyClient client = new NettyClient();
        String reqUrl = "http://mo.api.vip.com/moapi/operation/switch/v1";
        String reqBody = "api_key=23e7f28019e8407b98b84cd05b5aef2c&app_name=shop_iphone&app_version=5" +
                ".16&format=json&mars_cid=2133dff826d71395e45d2d7368673827a3abb095&mobile_channel=ng00010v%3Aal80ssgp%3A37u8zn0w" +
                "%3Ang00010p&mobile_platform=3&page_id=page_te_loading_14576929579781&province_id=104104&session_id" +
                "=2133dff826d71395e45d2d7368673827a3abb095_shop_iphone_1457692957806&standby_id=ng00010v%3Aal80ssgp%3A37u8zn0w%3Ang00010p" +
                "&switch_code=BORUI_SDK_SWITCH%2COXO_AREA_SWITCH%2Cgift_switch%2Ccan_choice_return_mode%2CORDER_RETURN_MONEY_NEW" +
                "%2Cindex_category_search%2CCOUPONS_ACTIVATE%2CCOUPON_JUMP%2CGOODS_KEYWORD_SEARCH%2Chalf_vis%2Cbrand_op_switch" +
                "%2CBROWSING_HISTORY_SWITCH%2CIS_SHOW_GOODS_RETURN%2CCOUPONS_GET_USE%2Cenable_beauty_fav%2CH5_PAYMENT_SUCCESS_SWITCH" +
                "%2CCONNECTION_SAMPLE_SWITCH%2CTEMP_Ping%2CNATIVE_CART_SWITCH%2CTEMP_History%2CCOUPON_EXPIRE_ALERT_SWITCH%2CCART_RED" +
                "%2Clogin_switch_for_qq%2CORDER_DELIVERY_CHECK_48HOURS_SWITCH%2Ccheckout_check_4level_address_switch" +
                "%2Cuser_center_switch_for_neworold%2Clogin_switch_for_modify_password%2Clogin_switch_for_modify_password_cancel_app" +
                "%2CUSERCENTER_NOTICE_SWITCH%2CNEW_CUSTOMER_INDIVIDUATION%2CPASSFORGET_SERVICE_SWITCH%2CIF_SHOW_BIG_PICTURE" +
                "%2CLOGISTICS_STATUTE%2CCLASSIFY_TEST%2CAPP_classify_switch%2CBRANDSEARCH_SWITCH%2CREAL_AUTH_PASSPORT_SWITCH" +
                "%2CCROSS_WAREHOUSE%2CWALLET_USE_PASSPORT%2CCOMMAND_ENVELOPE_CART%2CCOUPONS_GET_USE_DETAILS%2CANOTHER_PAY_SWITCH" +
                "%2CSHORT_PASSWORD_SWITCH%2CACS_ORDER_STATUS_TIPS%2CCART_COUPONS_NUMBER%2COXO_SWITCH%2Ctop_safety_tips_switch" +
                "%2Crisk_user_safety_tips_switch%2CREAL_AUTH_SWITCH%2CCOIN_SWITCH%2CWALLET_SWITCH%2CORDER_Virtual%2CCARD_E" +
                "%2CAD_PAYSUCCESS_TJYJ%2CVIPCLUB_SWITCH%2CWITHDRAWCASH_RECORD_SWITCH%2CBATCH_LOG_SWITCH%2CROYALSERVICE_SWITCH" +
                "%2CCUICU_SWITCH%2CUSERCENTER_ADS_SWITCH%2Creach_pmsact%2CAPP_EXCEPTION%2CFAPIAO_SWITCH%2Cis_ShareImg_With_WXSDK" +
                "%2Creturn_goods%2CREGISTER_NOSECONDPASS_SWITCH%2CAPI_LOG_TRACEROUTE_SWITCH%2CPAYMENT_PASSWORD_SWITCH" +
                "%2Cpaysuccess_op_switch%2Clogidetail_op_switch%2Creco_coupon_cart%2Creco_coupon_checkout%2C3RDLOGIN_NEW_SWITCH" +
                "%2C3RDLOGIN_OLD_SWITCH%2C3RDLOGIN_MAIL_SWITCH%2CIF_DISPLAY_VIDEO%2CWALLET_DETAILS_SWITCH&timestamp=1457692958&user_id" +
                "=149749807&ver=2.0&warehouse=VIP_NH&tag=A1&api_sign=9258ec02cdd06b0347851479d66e6b712f3c6b8f";
        AsyncHttpResponse asyncHttpResponse = client.doPost(reqUrl, reqBody, null);
        System.out.println("async content:" + asyncHttpResponse.getResponseAsString());
    }

    public static void testGet() {
        NettyClient client = new NettyClient();
        String reqUrl = "http://mo.api.vip.com:8081/moapi/rule/startup/v2?api_key=23e7f28019e8407b98b84cd05b5aef2c&app_name=shop_iphone" +
                "&app_version=5.3&client=iphone&format=json&height=1334&idfv=DBB7C916-9FDB-4FE0-8B14-A99CAFFCDC82&mobile_channel=ng00010v" +
                "%3Aal80ssgp%3A37u8zn0w%3Ang00010p&mobile_platform=3&model=iPhone7%2C2&net=WIFI&province_id=105102&service_provider=46001" +
                "&session_id=dae589a183ab9a1ad8351c6647f32a5331838d3b_shop_iphone_1442475401444&standby_id=ng00010v%3Aal80ssgp%3A37u8zn0w" +
                "%3Ang00010p&timestamp=1442475402&ver=2" +
                ".0&warehouse=VIP_BJ&width=750&mars_cid=6666667&user_id=103991712&tag=B&area_id=1051028";
        try {

            AsyncHttpResponse asyncHttpResponse = client.doGet(reqUrl, null);
            client.doGet(reqUrl, null);
            client.doGet(reqUrl, null);
            System.out.println("async content:" + asyncHttpResponse.getResponseAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testPost();
        System.out.println("succ!");
    }

}
