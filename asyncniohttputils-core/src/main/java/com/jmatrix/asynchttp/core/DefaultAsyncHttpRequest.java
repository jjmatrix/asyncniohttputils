package com.jmatrix.asynchttp.core;

import io.netty.handler.codec.http.HttpRequest;

import java.net.URI;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public class DefaultAsyncHttpRequest implements AsyncHttpRequest {

    private HttpRequest wrapRequest;

    private URI uri;

    public DefaultAsyncHttpRequest() {

    }

    public DefaultAsyncHttpRequest(HttpRequest wrapRequest, URI uri) {
        this.wrapRequest = wrapRequest;
        this.uri = uri;
    }

    @Override
    public String getHost() {
        return uri.getHost();
    }

    @Override
    public int getPort() {
        return uri.getPort();
    }

    @Override
    public HttpRequest unWrap() {
        return this.wrapRequest;
    }

    @Override
    public void wrap(HttpRequest httpRequest) {
        this.wrapRequest = httpRequest;
    }
}
