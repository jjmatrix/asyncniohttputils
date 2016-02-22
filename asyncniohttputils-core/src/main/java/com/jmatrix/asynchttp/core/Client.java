package com.jmatrix.asynchttp.core;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public interface Client {

    public void close();

    public AsyncHttpResponse doGet(String reqUrl, AsyncHandler asyncHandler);

}
