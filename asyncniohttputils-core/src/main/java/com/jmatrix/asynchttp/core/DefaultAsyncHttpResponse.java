package com.jmatrix.asynchttp.core;

import io.netty.handler.codec.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jmatrix
 * @date 16/1/29
 */
public class DefaultAsyncHttpResponse implements AsyncHttpResponse {

    private List<ResponseBodyPart> responseParts = new ArrayList<ResponseBodyPart>();

    private int responseBodyByteLength = 0;

    private HttpHeaders httpHeaders;

    public void addResponseBodyPart(ResponseBodyPart bodyPart) {
        responseParts.add(bodyPart);
        responseBodyByteLength += bodyPart.getResponsePart().length;
    }

    @Override
    public byte[] getResponseAsBytes() {
        byte[] bytes = new byte[responseBodyByteLength];
        int curIdx = 0;
        for (ResponseBodyPart bodyPart : responseParts) {
            System.arraycopy(bodyPart.getResponsePart(), 0, bytes, curIdx, bodyPart.getResponsePart().length);
            curIdx += bodyPart.getResponsePart().length;
        }
        return bytes;
    }

    @Override
    public String getResponseAsString() {
        return new String(getResponseAsBytes());
    }

    class AsyncHttpResponseBuilder {

    }

}
