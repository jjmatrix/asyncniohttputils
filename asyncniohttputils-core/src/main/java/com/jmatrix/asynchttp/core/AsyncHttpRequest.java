package com.jmatrix.asynchttp.core;

import io.netty.handler.codec.http.HttpRequest;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public interface AsyncHttpRequest {

    /**
     * get http host
     *
     * @return
     */
    String getHost();

    /**
     * get http port
     *
     * @return
     */
    int getPort();

    HttpRequest unWrap();

    void wrap(HttpRequest httpRequest);
}
