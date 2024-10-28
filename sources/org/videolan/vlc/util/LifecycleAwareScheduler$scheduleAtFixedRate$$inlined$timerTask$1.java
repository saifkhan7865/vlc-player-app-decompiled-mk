package org.videolan.vlc.util;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Timer.kt */
public final class LifecycleAwareScheduler$scheduleAtFixedRate$$inlined$timerTask$1 extends TimerTask {
    final /* synthetic */ Bundle $data$inlined;
    final /* synthetic */ String $id$inlined;
    final /* synthetic */ LifecycleAwareScheduler this$0;

    public LifecycleAwareScheduler$scheduleAtFixedRate$$inlined$timerTask$1(LifecycleAwareScheduler lifecycleAwareScheduler, String str, Bundle bundle) {
        this.this$0 = lifecycleAwareScheduler;
        this.$id$inlined = str;
        this.$data$inlined = bundle;
    }

    public void run() {
        TimerTask timerTask = this;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.callback), Dispatchers.getMain(), (CoroutineStart) null, new LifecycleAwareScheduler$scheduleAtFixedRate$1$1(this.this$0, this.$id$inlined, this.$data$inlined, (Continuation<? super LifecycleAwareScheduler$scheduleAtFixedRate$1$1>) null), 2, (Object) null);
    }
}
