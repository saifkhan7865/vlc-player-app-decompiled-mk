package org.videolan.tools;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\b\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0001¢\u0006\u0002\u0010\u000eR\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/videolan/tools/SingletonHolder;", "T", "A", "", "creator", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)V", "instance", "getInstance", "()Ljava/lang/Object;", "setInstance", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "arg", "(Ljava/lang/Object;)Ljava/lang/Object;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SingletonHolder.kt */
public class SingletonHolder<T, A> {
    private Function1<? super A, ? extends T> creator;
    private volatile T instance;

    public SingletonHolder(Function1<? super A, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(function1, "creator");
        this.creator = function1;
    }

    public final T getInstance() {
        return this.instance;
    }

    public final void setInstance(T t) {
        this.instance = t;
    }

    public final T getInstance(A a) {
        T t = this.instance;
        if (t == null) {
            synchronized (this) {
                t = this.instance;
                if (t == null) {
                    Function1 function1 = this.creator;
                    Intrinsics.checkNotNull(function1);
                    T invoke = function1.invoke(a);
                    this.instance = invoke;
                    this.creator = null;
                    t = invoke;
                }
            }
        }
        return t;
    }
}
