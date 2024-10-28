package io.netty.handler.codec.http2;

import io.netty.channel.ChannelId;

final class Http2StreamChannelId implements ChannelId {
    private static final long serialVersionUID = -6642338822166867585L;
    private final int id;
    private final ChannelId parentId;

    Http2StreamChannelId(ChannelId channelId, int i) {
        this.parentId = channelId;
        this.id = i;
    }

    public String asShortText() {
        return this.parentId.asShortText() + '/' + this.id;
    }

    public String asLongText() {
        return this.parentId.asLongText() + '/' + this.id;
    }

    public int compareTo(ChannelId channelId) {
        if (!(channelId instanceof Http2StreamChannelId)) {
            return this.parentId.compareTo(channelId);
        }
        Http2StreamChannelId http2StreamChannelId = (Http2StreamChannelId) channelId;
        int compareTo = this.parentId.compareTo(http2StreamChannelId.parentId);
        return compareTo == 0 ? this.id - http2StreamChannelId.id : compareTo;
    }

    public int hashCode() {
        return (this.id * 31) + this.parentId.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Http2StreamChannelId)) {
            return false;
        }
        Http2StreamChannelId http2StreamChannelId = (Http2StreamChannelId) obj;
        if (this.id != http2StreamChannelId.id || !this.parentId.equals(http2StreamChannelId.parentId)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return asShortText();
    }
}
