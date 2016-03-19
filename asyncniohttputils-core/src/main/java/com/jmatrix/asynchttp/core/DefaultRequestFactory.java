package com.jmatrix.asynchttp.core;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * @author jmatrix
 * @date 16/2/19
 */
public class DefaultRequestFactory implements RequestFactory {

    private static Logger logger = LoggerFactory.getLogger(DefaultRequestFactory.class);

    public static AsyncHttpRequest newAsyncHttpRequest(String reqUrl, HttpMethod httpMethod) {
        AsyncHttpRequest asyncHttpRequest = null;
        try {
            URI uri = new URI(reqUrl);
            HttpRequest httpRequest = new DefaultHttpRequest(HttpVersion.HTTP_1_1, httpMethod, reqUrl);
            httpRequest.headers().set(HttpHeaders.Names.HOST, uri.getHost());
            httpRequest.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

            if (httpMethod.equals(HttpMethod.POST)) {
                httpRequest.headers().set(HttpHeaders.Names.TRANSFER_ENCODING, HttpHeaders.Values.CHUNKED);
            }

            asyncHttpRequest = new DefaultAsyncHttpRequest(httpRequest, uri);

        } catch (Exception e) {
            logger.error("create httprequest error.", e);
        }
        return asyncHttpRequest;
    }


    public static ResultFuture newResultFuture(AsyncHandler asyncHandler) {
        return new ResponseResultFuture(asyncHandler);
    }

}
