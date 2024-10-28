package org.videolan.resources.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"org/videolan/resources/util/ExtensionsKt$await$2$1$observer$1", "Landroidx/lifecycle/Observer;", "onChanged", "", "value", "(Ljava/lang/Object;)V", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Extensions.kt */
public final class ExtensionsKt$await$2$1$observer$1 implements Observer<T> {
    final /* synthetic */ CancellableContinuation<T> $continuation;
    final /* synthetic */ LiveData<T> $this_await;

    ExtensionsKt$await$2$1$observer$1(LiveData<T> liveData, CancellableContinuation<? super T> cancellableContinuation) {
        this.$this_await = liveData;
        this.$continuation = cancellableContinuation;
    }

    public void onChanged(T t) {
        this.$this_await.removeObserver(this);
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m1774constructorimpl(t));
    }
}
