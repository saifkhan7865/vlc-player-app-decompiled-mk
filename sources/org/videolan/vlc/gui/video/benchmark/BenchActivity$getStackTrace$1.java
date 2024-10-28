package org.videolan.vlc.gui.video.benchmark;

import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.benchmark.BenchActivity$getStackTrace$1", f = "BenchActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BenchActivity.kt */
final class BenchActivity$getStackTrace$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ BenchActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BenchActivity$getStackTrace$1(BenchActivity benchActivity, Continuation<? super BenchActivity$getStackTrace$1> continuation) {
        super(2, continuation);
        this.this$0 = benchActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BenchActivity$getStackTrace$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BenchActivity$getStackTrace$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.stacktraceFile != null) {
                try {
                    int myPid = Process.myPid();
                    Runtime runtime = Runtime.getRuntime();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtime.exec("logcat -d -v brief --pid=" + myPid).getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        sb.append(readLine);
                    }
                    String access$getStacktraceFile$p = this.this$0.stacktraceFile;
                    Intrinsics.checkNotNull(access$getStacktraceFile$p);
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(access$getStacktraceFile$p));
                    String sb2 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                    byte[] bytes = sb2.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                    bufferedReader.close();
                    new ProcessBuilder(new String[0]).command(new String[]{"logcat", "-c"}).redirectErrorStream(true).start();
                } catch (IOException e) {
                    Log.e("VLCBenchmark", e.toString());
                }
            } else {
                Log.e("VLCBenchmark", "getStackTrace: There was no stacktrace file provided");
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
