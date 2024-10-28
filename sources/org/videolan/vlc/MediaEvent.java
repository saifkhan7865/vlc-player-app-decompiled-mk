package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/MediaEvent;", "", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getCtx", "()Landroid/content/Context;", "Lorg/videolan/vlc/Mount;", "Lorg/videolan/vlc/Unmount;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StoragesMonitor.kt */
abstract class MediaEvent {
    private final Context ctx;

    public /* synthetic */ MediaEvent(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private MediaEvent(Context context) {
        this.ctx = context;
    }

    public final Context getCtx() {
        return this.ctx;
    }
}
