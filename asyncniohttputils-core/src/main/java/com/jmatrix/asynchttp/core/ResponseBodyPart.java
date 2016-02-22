package com.jmatrix.asynchttp.core;

import com.jmatrix.asynchttp.utils.ByteBuffUtils;
import io.netty.buffer.ByteBuf;

/**
 * @author jmatrix
 * @date 16/2/19
 */
public class ResponseBodyPart {

    private byte[] bytes;

    private boolean last;

    public ResponseBodyPart(ByteBuf byteBuf, boolean last) {
        this.last = last;
        this.bytes = ByteBuffUtils.byteBuf2Bytes(byteBuf);
    }

    public ResponseBodyPart(ByteBuf byteBuf) {
        this(byteBuf, false);
    }

    public byte[] getResponsePart() {
        return bytes;
    }

    public ByteBuf getResponsePartByteBuf() {
        return null;
    }

    public boolean isLast() {
        return last;
    }
}
