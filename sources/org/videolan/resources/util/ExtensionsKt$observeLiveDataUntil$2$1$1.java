package org.videolan.resources.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "t", "onChanged", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: Extensions.kt */
public final class ExtensionsKt$observeLiveDataUntil$2$1$1<T> implements Observer {
    final /* synthetic */ Function1<T, Boolean> $block;
    final /* synthetic */ LiveData<T> $data;
    final /* synthetic */ Ref.BooleanRef $init;
    final /* synthetic */ Ref.ObjectRef<Observer<T>> $observers;
    final /* synthetic */ T $oldData;
    final /* synthetic */ CancellableContinuation<T> $suspend;

    public ExtensionsKt$observeLiveDataUntil$2$1$1(T t, Ref.BooleanRef booleanRef, Function1<? super T, Boolean> function1, CancellableContinuation<? super T> cancellableContinuation, Ref.ObjectRef<Observer<T>> objectRef, LiveData<T> liveData) {
        this.$oldData = t;
        this.$init = booleanRef;
        this.$block = function1;
        this.$suspend = cancellableContinuation;
        this.$observers = objectRef;
        this.$data = liveData;
    }

    public final void onChanged(T t) {
        if (!Intrinsics.areEqual((Object) this.$oldData, (Object) t) || !this.$init.element) {
            this.$init.element = true;
            if (!this.$block.invoke(t).booleanValue() && !this.$suspend.isCancelled()) {
                Result.Companion companion = Result.Companion;
                this.$suspend.resumeWith(Result.m1774constructorimpl(t));
                Observer observer = (Observer) this.$observers.element;
                if (observer != null) {
                    this.$data.removeObserver(observer);
                }
            }
        }
    }
}
