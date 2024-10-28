package okhttp3.internal.http2;

import kotlin.Metadata;
import okio.Buffer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "okhttp3/internal/Util$execute$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: Util.kt */
public final class Http2Connection$pushDataLater$$inlined$execute$1 implements Runnable {
    final /* synthetic */ Buffer $buffer$inlined;
    final /* synthetic */ int $byteCount$inlined;
    final /* synthetic */ boolean $inFinished$inlined;
    final /* synthetic */ String $name;
    final /* synthetic */ int $streamId$inlined;
    final /* synthetic */ Http2Connection this$0;

    public Http2Connection$pushDataLater$$inlined$execute$1(String str, Http2Connection http2Connection, int i, Buffer buffer, int i2, boolean z) {
        this.$name = str;
        this.this$0 = http2Connection;
        this.$streamId$inlined = i;
        this.$buffer$inlined = buffer;
        this.$byteCount$inlined = i2;
        this.$inFinished$inlined = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        r1.setName(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            java.lang.String r0 = r7.$name
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.String r2 = "currentThread"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r2 = r1.getName()
            r1.setName(r0)
            okhttp3.internal.http2.Http2Connection r0 = r7.this$0     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            okhttp3.internal.http2.PushObserver r0 = r0.pushObserver     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            int r3 = r7.$streamId$inlined     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            okio.Buffer r4 = r7.$buffer$inlined     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            okio.BufferedSource r4 = (okio.BufferedSource) r4     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            int r5 = r7.$byteCount$inlined     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            boolean r6 = r7.$inFinished$inlined     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            boolean r0 = r0.onData(r3, r4, r5, r6)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            if (r0 == 0) goto L_0x0035
            okhttp3.internal.http2.Http2Connection r3 = r7.this$0     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            okhttp3.internal.http2.Http2Writer r3 = r3.getWriter()     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            int r4 = r7.$streamId$inlined     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            okhttp3.internal.http2.ErrorCode r5 = okhttp3.internal.http2.ErrorCode.CANCEL     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r3.rstStream(r4, r5)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
        L_0x0035:
            if (r0 != 0) goto L_0x003b
            boolean r0 = r7.$inFinished$inlined     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            if (r0 == 0) goto L_0x0057
        L_0x003b:
            okhttp3.internal.http2.Http2Connection r0 = r7.this$0     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            monitor-enter(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            okhttp3.internal.http2.Http2Connection r3 = r7.this$0     // Catch:{ all -> 0x004f }
            java.util.Set r3 = r3.currentPushRequests     // Catch:{ all -> 0x004f }
            int r4 = r7.$streamId$inlined     // Catch:{ all -> 0x004f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x004f }
            r3.remove(r4)     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            goto L_0x0057
        L_0x004f:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            throw r3     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
        L_0x0052:
            r0 = move-exception
            r1.setName(r2)
            throw r0
        L_0x0057:
            r1.setName(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection$pushDataLater$$inlined$execute$1.run():void");
    }
}
