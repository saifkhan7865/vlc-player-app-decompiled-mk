package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

public class DecoratingHttp2ConnectionEncoder extends DecoratingHttp2FrameWriter implements Http2ConnectionEncoder, Http2SettingsReceivedConsumer {
    private final Http2ConnectionEncoder delegate;

    public DecoratingHttp2ConnectionEncoder(Http2ConnectionEncoder http2ConnectionEncoder) {
        super(http2ConnectionEncoder);
        this.delegate = (Http2ConnectionEncoder) ObjectUtil.checkNotNull(http2ConnectionEncoder, "delegate");
    }

    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.delegate.lifecycleManager(http2LifecycleManager);
    }

    public Http2Connection connection() {
        return this.delegate.connection();
    }

    public Http2RemoteFlowController flowController() {
        return this.delegate.flowController();
    }

    public Http2FrameWriter frameWriter() {
        return this.delegate.frameWriter();
    }

    public Http2Settings pollSentSettings() {
        return this.delegate.pollSentSettings();
    }

    public void remoteSettings(Http2Settings http2Settings) throws Http2Exception {
        this.delegate.remoteSettings(http2Settings);
    }

    public void consumeReceivedSettings(Http2Settings http2Settings) {
        Http2ConnectionEncoder http2ConnectionEncoder = this.delegate;
        if (http2ConnectionEncoder instanceof Http2SettingsReceivedConsumer) {
            ((Http2SettingsReceivedConsumer) http2ConnectionEncoder).consumeReceivedSettings(http2Settings);
            return;
        }
        throw new IllegalStateException("delegate " + this.delegate + " is not an instance of " + Http2SettingsReceivedConsumer.class);
    }
}
