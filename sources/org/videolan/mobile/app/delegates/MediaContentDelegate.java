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
import org.videolan.tools.AppScope;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\f\u0010\n\u001a\u00020\u0005*\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lorg/videolan/mobile/app/delegates/MediaContentDelegate;", "Landroid/content/BroadcastReceiver;", "Lorg/videolan/mobile/app/delegates/IMediaContentDelegate;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "setupContentResolvers", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IMediaContentDelegate.kt */
public final class MediaContentDelegate extends BroadcastReceiver implements IMediaContentDelegate {
    public void setupContentResolvers(Context context) {
        KotlinExtensionsKt.getLocalBroadcastManager(context).registerReceiver(this, new IntentFilter(Constants.ACTION_OPEN_CONTENT));
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (context != null && intent != null && (stringExtra = intent.getStringExtra(Constants.EXTRA_CONTENT_ID)) != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new MediaContentDelegate$onReceive$1(context, stringExtra, (Continuation<? super MediaContentDelegate$onReceive$1>) null), 3, (Object) null);
        }
    }
}
