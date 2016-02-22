package com.jmatrix.asynchttp.utils;

import io.netty.buffer.ByteBuf;

/**
 * @author jmatrix
 * @date 16/2/18
 */
public class ByteBuffUtils {
    public static byte[] byteBuf2Bytes(ByteBuf buf) {
        int readable = buf.readableBytes();
        int readerIndex = buf.readerIndex();
        if (buf.hasArray()) {
            byte[] array = buf.array();
            if (buf.arrayOffset() == 0 && readerIndex == 0 && array.length == readable) {
                return array;
            }
        }
        byte[] array = new byte[readable];
        buf.getBytes(readerIndex, array);
        return array;
    }
}
