package com.jmatrix.asynchttp.netty;

import com.jmatrix.asynchttp.core.ResponseBodyPart;
import com.jmatrix.asynchttp.core.ResponseResultFuture;
import com.jmatrix.asynchttp.core.ResultFuture;
import com.jmatrix.asynchttp.utils.ChannelManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public class NettyHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(NettyHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ResultFuture resultFuture = ChannelManager.attr(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResultFuture resultFuture = ChannelManager.attr(ctx.channel());
        if (resultFuture != null && resultFuture instanceof ResponseResultFuture) {
            if (msg instanceof HttpResponse) {
                HttpResponse response = (HttpResponse) msg;
                ((ResponseResultFuture) resultFuture).getAsyncHandler().onReceiveHeaders(response.headers());
            }
            if (msg instanceof HttpContent) {
                HttpContent httpContent = (HttpContent) msg;
                if (msg instanceof LastHttpContent) {
                    ((ResponseResultFuture) resultFuture).receiveResponseBody(new ResponseBodyPart(httpContent.content(), true));
                    ((ResponseResultFuture) resultFuture).isDone();
                } else
                    ((ResponseResultFuture) resultFuture).receiveResponseBody(new ResponseBodyPart(httpContent.content()));
            }
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
