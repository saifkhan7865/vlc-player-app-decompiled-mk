package videolan.org.commontools;

import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\fH\u0017J\u0018\u0010\r\u001a\u00020\u00072\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\fH\u0016J\u0019\u0010\u000e\u001a\u00020\u00072\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00018\u0000H\u0017¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lvideolan/org/commontools/LiveEvent;", "T", "Landroidx/lifecycle/MutableLiveData;", "()V", "pending", "Ljava/util/concurrent/atomic/AtomicBoolean;", "clear", "", "observe", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "observeForever", "setValue", "t", "(Ljava/lang/Object;)V", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LiveEvent.kt */
public final class LiveEvent<T> extends MutableLiveData<T> {
    /* access modifiers changed from: private */
    public final AtomicBoolean pending = new AtomicBoolean(false);

    public void observe(LifecycleOwner lifecycleOwner, Observer<? super T> observer) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        Intrinsics.checkNotNullParameter(observer, "observer");
        if (hasActiveObservers()) {
            Log.w("VLC/LiveEvent", "Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(lifecycleOwner, new LiveEvent$$ExternalSyntheticLambda1(new LiveEvent$observe$1(this, observer)));
    }

    /* access modifiers changed from: private */
    public static final void observe$lambda$0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void observeForever$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    public void observeForever(Observer<? super T> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        super.observeForever(new LiveEvent$$ExternalSyntheticLambda0(new LiveEvent$observeForever$1(this, observer)));
    }

    public void setValue(T t) {
        this.pending.set(true);
        super.setValue(t);
    }

    public final void clear() {
        super.setValue(null);
    }
}
