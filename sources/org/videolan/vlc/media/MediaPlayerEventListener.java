package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.videolan.libvlc.MediaPlayer;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/media/MediaPlayerEventListener;", "", "onEvent", "", "event", "Lorg/videolan/libvlc/MediaPlayer$Event;", "(Lorg/videolan/libvlc/MediaPlayer$Event;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerController.kt */
public interface MediaPlayerEventListener {
    Object onEvent(MediaPlayer.Event event, Continuation<? super Unit> continuation);
}
