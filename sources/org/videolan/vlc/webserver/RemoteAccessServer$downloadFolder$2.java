package org.videolan.vlc.webserver;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$downloadFolder$2 extends Lambda implements Function0<String> {
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$downloadFolder$2(RemoteAccessServer remoteAccessServer) {
        super(0);
        this.this$0 = remoteAccessServer;
    }

    public final String invoke() {
        StringBuilder sb = new StringBuilder();
        File externalFilesDir = this.this$0.context.getExternalFilesDir((String) null);
        Intrinsics.checkNotNull(externalFilesDir);
        sb.append(externalFilesDir.getAbsolutePath());
        sb.append("/downloads");
        return sb.toString();
    }
}
