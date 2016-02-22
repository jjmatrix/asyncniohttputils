package com.jmatrix.asynchttp.netty;

import com.jmatrix.asynchttp.constants.CommonConstants;
import com.jmatrix.asynchttp.core.*;
import com.jmatrix.asynchttp.utils.ChannelManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jmatrix
 * @date 16/1/22
 */
public class NettyClient extends AbstractClient {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private Bootstrap clientBootstrap;

    private EventLoopGroup clientServiceGroup;

    @Override
    protected void doOpen() {
        clientBootstrap = new Bootstrap();
        clientServiceGroup = new NioEventLoopGroup(0, new NamedThreadFactory("asynchttp-work"));
        clientBootstrap.group(clientServiceGroup);
        clientBootstrap.channel(NioSocketChannel.class);
        clientBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("log", new LoggingHandler(LogLevel.INFO));
                ch.pipeline().addLast("codec", new HttpClientCodec());
                ch.pipeline().addLast("chunk", new ChunkedWriteHandler());
                ch.pipeline().addLast(new NettyHandler());
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("shutdown LoopGroup.");
            close();
        }));
    }

    @Override
    protected void connect(AsyncHttpRequest request) {

    }

    @Override
    public AsyncHttpResponse doGet(String reqUrl, AsyncHandler asyncHandler) {
        try {
            AsyncHttpRequest asyncHttpRequest = DefaultRequestFactory.newAsyncHttpRequest(reqUrl);
            Channel channel = clientBootstrap.connect(asyncHttpRequest.getHost(), asyncHttpRequest.getPort() > 0 ? asyncHttpRequest.getPort() : CommonConstants.DEFAULT_PORT).sync().channel();
            ResultFuture<AsyncHttpResponse> resultFuture = DefaultRequestFactory.newResultFuture(asyncHandler);
            ChannelManager.setAttr(channel, resultFuture);
            channel.writeAndFlush(asyncHttpRequest.unWrap());
            return resultFuture.get();
        } catch (Exception e) {
            logger.error("do get request error.", e);
        }
        return null;
    }

    public void doPost(AsyncHttpRequest request, AsyncHttpResponse response) {

    }

    @Override
    public void close() {
        if (!clientServiceGroup.isShutdown())
            clientServiceGroup.shutdownGracefully();
    }

}
