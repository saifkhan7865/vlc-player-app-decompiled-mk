package org.videolan.tools;

import android.os.Looper;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u001a\u0012\u0010\u0006\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¨\u0006\u0007"}, d2 = {"runBackground", "", "runnable", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "runIO", "runOnMainThread", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Workers.kt */
public final class WorkersKt {
    public static final void runBackground(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        if (!Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
            runnable.run();
        } else {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new WorkersKt$runBackground$1(runnable, (Continuation<? super WorkersKt$runBackground$1>) null), 2, (Object) null);
        }
    }

    public static final void runOnMainThread(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new WorkersKt$runOnMainThread$1(runnable, (Continuation<? super WorkersKt$runOnMainThread$1>) null), 3, (Object) null);
    }

    public static final void runIO(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new WorkersKt$runIO$1(runnable, (Continuation<? super WorkersKt$runIO$1>) null), 2, (Object) null);
    }
}
