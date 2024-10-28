package org.videolan.resources.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"org/videolan/resources/util/ExtensionsKt$waitForML$2$1$listener$1", "Lorg/videolan/medialibrary/interfaces/Medialibrary$OnMedialibraryReadyListener;", "onMedialibraryIdle", "", "onMedialibraryReady", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 176)
/* compiled from: Extensions.kt */
public final class ExtensionsKt$waitForML$2$1$listener$1 implements Medialibrary.OnMedialibraryReadyListener {
    final /* synthetic */ CoroutineScope $$this$withContext;
    final /* synthetic */ CancellableContinuation<Function0<Unit>> $continuation;
    final /* synthetic */ Medialibrary $ml;

    public void onMedialibraryIdle() {
    }

    public ExtensionsKt$waitForML$2$1$listener$1(CancellableContinuation<? super Function0<Unit>> cancellableContinuation, CoroutineScope coroutineScope, Medialibrary medialibrary) {
        this.$continuation = cancellableContinuation;
        this.$$this$withContext = coroutineScope;
        this.$ml = medialibrary;
    }

    public void onMedialibraryReady() {
        if (!this.$continuation.isCompleted()) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this.$$this$withContext, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1(this.$continuation, this.$ml, this, (Continuation<? super ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1>) null), 1, (Object) null);
        }
    }
}
