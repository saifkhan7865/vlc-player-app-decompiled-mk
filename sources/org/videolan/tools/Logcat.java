package org.videolan.tools;

import io.ktor.http.ContentDisposition;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u000f\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/tools/Logcat;", "Ljava/lang/Runnable;", "()V", "mCallback", "Lorg/videolan/tools/Logcat$Callback;", "mProcess", "Ljava/lang/Process;", "mRun", "", "mThread", "Ljava/lang/Thread;", "run", "", "start", "callback", "stop", "Callback", "Companion", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Logcat.kt */
public final class Logcat implements Runnable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/UiTools/Logcat";
    private Callback mCallback;
    private Process mProcess;
    private boolean mRun;
    private Thread mThread;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/videolan/tools/Logcat$Callback;", "", "onLog", "", "log", "", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Logcat.kt */
    public interface Callback {
        void onLog(String str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r2 = new java.io.BufferedReader(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1 = r2.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        if (r1 == null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        r3 = r5.mCallback;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r3);
        r3.onLog(r1);
        r1 = r2.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        org.videolan.tools.CloseableUtils.INSTANCE.close(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        r4 = r1;
        r1 = r0;
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r5 = this;
            r0 = 3
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = "logcat"
            r2 = 0
            r0[r2] = r1
            java.lang.String r1 = "-v"
            r2 = 1
            r0[r2] = r1
            java.lang.String r1 = "time"
            r2 = 2
            r0[r2] = r1
            r1 = 0
            monitor-enter(r5)     // Catch:{ IOException -> 0x0087, all -> 0x0076 }
            boolean r2 = r5.mRun     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x0024
            monitor-exit(r5)     // Catch:{ IOException -> 0x0087, all -> 0x0076 }
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            r0.close(r1)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            r0.close(r1)
            return
        L_0x0024:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x0069 }
            java.lang.Process r0 = r2.exec(r0)     // Catch:{ all -> 0x0069 }
            r5.mProcess = r0     // Catch:{ all -> 0x0069 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ all -> 0x0069 }
            java.lang.Process r2 = r5.mProcess     // Catch:{ all -> 0x0069 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0069 }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ all -> 0x0069 }
            r0.<init>(r2)     // Catch:{ all -> 0x0069 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0067 }
            monitor-exit(r5)     // Catch:{ IOException -> 0x0073, all -> 0x006d }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0073, all -> 0x006d }
            r3 = r0
            java.io.Reader r3 = (java.io.Reader) r3     // Catch:{ IOException -> 0x0073, all -> 0x006d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0073, all -> 0x006d }
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x0074, all -> 0x0062 }
        L_0x004b:
            if (r1 == 0) goto L_0x005a
            org.videolan.tools.Logcat$Callback r3 = r5.mCallback     // Catch:{ IOException -> 0x0074, all -> 0x0062 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ IOException -> 0x0074, all -> 0x0062 }
            r3.onLog(r1)     // Catch:{ IOException -> 0x0074, all -> 0x0062 }
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x0074, all -> 0x0062 }
            goto L_0x004b
        L_0x005a:
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1.close(r0)
            goto L_0x008f
        L_0x0062:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0078
        L_0x0067:
            r2 = move-exception
            goto L_0x006b
        L_0x0069:
            r2 = move-exception
            r0 = r1
        L_0x006b:
            monitor-exit(r5)     // Catch:{ IOException -> 0x0073, all -> 0x006d }
            throw r2     // Catch:{ IOException -> 0x0073, all -> 0x006d }
        L_0x006d:
            r2 = move-exception
            r4 = r1
            r1 = r0
            r0 = r2
            r2 = r4
            goto L_0x0078
        L_0x0073:
            r2 = r1
        L_0x0074:
            r1 = r0
            goto L_0x0088
        L_0x0076:
            r0 = move-exception
            r2 = r1
        L_0x0078:
            org.videolan.tools.CloseableUtils r3 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r1 = (java.io.Closeable) r1
            r3.close(r1)
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r2 = (java.io.Closeable) r2
            r1.close(r2)
            throw r0
        L_0x0087:
            r2 = r1
        L_0x0088:
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r1 = (java.io.Closeable) r1
            r0.close(r1)
        L_0x008f:
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0.close(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.Logcat.run():void");
    }

    public final synchronized void start(Callback callback) {
        if (callback != null) {
            try {
                if (this.mThread == null && this.mProcess == null) {
                    this.mCallback = callback;
                    this.mRun = true;
                    Thread thread = new Thread(this);
                    this.mThread = thread;
                    Intrinsics.checkNotNull(thread);
                    thread.start();
                } else {
                    throw new IllegalStateException("logcat is already started");
                }
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IllegalArgumentException("callback should not be null");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:1|2|3|(1:5)|6|7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void stop() {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            r2.mRun = r0     // Catch:{ all -> 0x001f }
            java.lang.Process r0 = r2.mProcess     // Catch:{ all -> 0x001f }
            r1 = 0
            if (r0 == 0) goto L_0x0011
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x001f }
            r0.destroy()     // Catch:{ all -> 0x001f }
            r2.mProcess = r1     // Catch:{ all -> 0x001f }
        L_0x0011:
            java.lang.Thread r0 = r2.mThread     // Catch:{ InterruptedException -> 0x0019 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ InterruptedException -> 0x0019 }
            r0.join()     // Catch:{ InterruptedException -> 0x0019 }
        L_0x0019:
            r2.mThread = r1     // Catch:{ all -> 0x001f }
            r2.mCallback = r1     // Catch:{ all -> 0x001f }
            monitor-exit(r2)
            return
        L_0x001f:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.Logcat.stop():void");
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/videolan/tools/Logcat$Companion;", "", "()V", "TAG", "", "logcat", "getLogcat", "()Ljava/lang/String;", "writeLogcat", "", "filename", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Logcat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void writeLogcat(String str) throws IOException {
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.FileName);
            InputStreamReader inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec(new String[]{"logcat", "-v", RtspHeaders.Values.TIME, "-d"}).getInputStream());
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(str));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                try {
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        bufferedWriter.write(readLine);
                        bufferedWriter.newLine();
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    CloseableUtils.INSTANCE.close(bufferedWriter);
                    CloseableUtils.INSTANCE.close(outputStreamWriter);
                    CloseableUtils.INSTANCE.close(bufferedReader);
                    CloseableUtils.INSTANCE.close(inputStreamReader);
                    throw th;
                }
                CloseableUtils.INSTANCE.close(bufferedWriter);
                CloseableUtils.INSTANCE.close(outputStreamWriter);
                CloseableUtils.INSTANCE.close(bufferedReader);
                CloseableUtils.INSTANCE.close(inputStreamReader);
            } catch (FileNotFoundException unused2) {
            }
        }

        public final String getLogcat() throws IOException {
            InputStreamReader inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec(new String[]{"logcat", "-v", RtspHeaders.Values.TIME, "-d", "-t", "500"}).getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine + 10);
            }
            CloseableUtils.INSTANCE.close(bufferedReader);
            CloseableUtils.INSTANCE.close(inputStreamReader);
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return sb2;
        }
    }
}
