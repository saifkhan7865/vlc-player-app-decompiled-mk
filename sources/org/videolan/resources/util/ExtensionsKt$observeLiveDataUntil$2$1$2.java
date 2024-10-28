package org.videolan.resources.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: Extensions.kt */
public final class ExtensionsKt$observeLiveDataUntil$2$1$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ LiveData<T> $data;
    final /* synthetic */ Ref.ObjectRef<Observer<T>> $observers;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$observeLiveDataUntil$2$1$2(Ref.ObjectRef<Observer<T>> objectRef, LiveData<T> liveData) {
        super(1);
        this.$observers = objectRef;
        this.$data = liveData;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.$data.removeObserver((Observer) this.$observers.element);
    }
}
