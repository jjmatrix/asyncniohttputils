package com.jmatrix.asynchttp.utils;

import com.jmatrix.asynchttp.core.ResultFuture;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author jmatrix
 * @date 16/2/19
 */
public class ChannelManager {

    private final static AttributeKey<ResultFuture> futureKey = AttributeKey.valueOf("futureKey");

    public static ResultFuture attr(Channel channel) {
        return channel.attr(futureKey).get();
    }

    public static void setAttr(Channel channel, ResultFuture<?> future) {
        channel.attr(futureKey).set(future);
    }

}
