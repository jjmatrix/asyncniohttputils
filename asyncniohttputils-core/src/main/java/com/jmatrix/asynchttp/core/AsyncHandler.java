package com.jmatrix.asynchttp.core;

import io.netty.handler.codec.http.HttpHeaders;

/**
 * @author jmatrix
 * @date 16/2/19
 */
public interface AsyncHandler {

    default void onComplete(AsyncHttpResponse asyncHttpResponse) {

    }

    default boolean onReceiveHeaders(HttpHeaders httpHeaders) {
        return true;
    }

    default boolean onReceiveResponseBodyPart(ResponseBodyPart responseBodyPart) {
        return true;
    }

}
