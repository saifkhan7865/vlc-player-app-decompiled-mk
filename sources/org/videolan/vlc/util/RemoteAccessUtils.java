package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/util/RemoteAccessUtils;", "", "()V", "otpFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getOtpFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessUtils.kt */
public final class RemoteAccessUtils {
    public static final RemoteAccessUtils INSTANCE = new RemoteAccessUtils();
    private static final MutableStateFlow<String> otpFlow = StateFlowKt.MutableStateFlow(null);

    private RemoteAccessUtils() {
    }

    public final MutableStateFlow<String> getOtpFlow() {
        return otpFlow;
    }
}
