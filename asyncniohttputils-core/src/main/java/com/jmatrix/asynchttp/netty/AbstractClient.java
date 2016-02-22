package com.jmatrix.asynchttp.netty;

import com.jmatrix.asynchttp.core.AsyncHttpRequest;
import com.jmatrix.asynchttp.core.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public abstract class AbstractClient implements Client {

    private Logger logger = LoggerFactory.getLogger(AbstractClient.class);

    public AbstractClient() {
        try {
            doOpen();
        } catch (Exception e) {
            logger.error("init client error.", e);
        }
    }

    protected abstract void doOpen();


    protected abstract void connect(AsyncHttpRequest request);
}
