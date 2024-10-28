package org.videolan.medialibrary;

import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleEvent<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleEvent";
    /* access modifiers changed from: private */
    public final AtomicBoolean mPending = new AtomicBoolean(false);

    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }
        super.observe(lifecycleOwner, new Observer<T>() {
            public void onChanged(T t) {
                if (SingleEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public void observeForever(final Observer<? super T> observer) {
        super.observeForever(new Observer<T>() {
            public void onChanged(T t) {
                if (SingleEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public void setValue(T t) {
        this.mPending.set(true);
        super.setValue(t);
    }

    public void clear() {
        super.setValue(null);
    }
}
