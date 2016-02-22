package com.jmatrix.asynchttp.core;

/**
 * @author jmatrix
 * @date 16/1/29
 */
public interface AsyncHttpResponse {

    void addResponseBodyPart(ResponseBodyPart bodyPart);

    byte[] getResponseAsBytes();

    String getResponseAsString();

}
