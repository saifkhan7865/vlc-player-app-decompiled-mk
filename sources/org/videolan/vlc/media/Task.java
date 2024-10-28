package org.videolan.vlc.media;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00121\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR>\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/media/Task;", "Lorg/videolan/vlc/media/Action;", "service", "Lorg/videolan/vlc/PlaybackService;", "task", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lkotlin/coroutines/Continuation;", "", "", "(Lorg/videolan/vlc/PlaybackService;Lkotlin/jvm/functions/Function2;)V", "getService", "()Lorg/videolan/vlc/PlaybackService;", "getTask", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaUtils.kt */
final class Task extends Action {
    private final PlaybackService service;
    private final Function2<PlaybackService, Continuation<? super Unit>, Object> task;

    public Task(PlaybackService playbackService, Function2<? super PlaybackService, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkNotNullParameter(function2, "task");
        this.service = playbackService;
        this.task = function2;
    }

    public final PlaybackService getService() {
        return this.service;
    }

    public final Function2<PlaybackService, Continuation<? super Unit>, Object> getTask() {
        return this.task;
    }
}
