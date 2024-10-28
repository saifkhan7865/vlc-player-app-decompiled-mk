package org.videolan.mobile.app.delegates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.AppScope;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\f\u0010\n\u001a\u00020\u0005*\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lorg/videolan/mobile/app/delegates/IndexersDelegate;", "Landroid/content/BroadcastReceiver;", "Lorg/videolan/mobile/app/delegates/IIndexersDelegate;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "setupIndexers", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IIndexersDelegate.kt */
public final class IndexersDelegate extends BroadcastReceiver implements IIndexersDelegate {
    public void setupIndexers(Context context) {
        ExtensionsKt.registerReceiverCompat(context, this, new IntentFilter(Constants.ACTION_CONTENT_INDEXING), false);
    }

    public void onReceive(Context context, Intent intent) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new IndexersDelegate$onReceive$1((Continuation<? super IndexersDelegate$onReceive$1>) null), 3, (Object) null);
    }
}
