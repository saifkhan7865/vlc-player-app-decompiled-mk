package org.videolan.vlc.util;

import android.support.v4.media.session.PlaybackStateCompat;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001a\b\u0002\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001dB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001c¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/util/PlaybackAction;", "", "Lorg/videolan/vlc/util/Flag;", "capability", "", "(Ljava/lang/String;IJ)V", "toLong", "ACTION_STOP", "ACTION_PAUSE", "ACTION_PLAY", "ACTION_REWIND", "ACTION_SKIP_TO_PREVIOUS", "ACTION_SKIP_TO_NEXT", "ACTION_FAST_FORWARD", "ACTION_SET_RATING", "ACTION_SEEK_TO", "ACTION_PLAY_PAUSE", "ACTION_PLAY_FROM_MEDIA_ID", "ACTION_PLAY_FROM_SEARCH", "ACTION_SKIP_TO_QUEUE_ITEM", "ACTION_PLAY_FROM_URI", "ACTION_PREPARE", "ACTION_PREPARE_FROM_MEDIA_ID", "ACTION_PREPARE_FROM_SEARCH", "ACTION_PREPARE_FROM_URI", "ACTION_SET_REPEAT_MODE", "ACTION_SET_CAPTIONING_ENABLED", "ACTION_SET_SHUFFLE_MODE", "ACTION_SET_PLAYBACK_SPEED", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackAction.kt */
public enum PlaybackAction implements Flag {
    ACTION_STOP(1),
    ACTION_PAUSE(2),
    ACTION_PLAY(4),
    ACTION_REWIND(8),
    ACTION_SKIP_TO_PREVIOUS(16),
    ACTION_SKIP_TO_NEXT(32),
    ACTION_FAST_FORWARD(64),
    ACTION_SET_RATING(128),
    ACTION_SEEK_TO(256),
    ACTION_PLAY_PAUSE(512),
    ACTION_PLAY_FROM_MEDIA_ID(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID),
    ACTION_PLAY_FROM_SEARCH(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH),
    ACTION_SKIP_TO_QUEUE_ITEM(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM),
    ACTION_PLAY_FROM_URI(8192),
    ACTION_PREPARE(16384),
    ACTION_PREPARE_FROM_MEDIA_ID(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID),
    ACTION_PREPARE_FROM_SEARCH(65536),
    ACTION_PREPARE_FROM_URI(PlaybackStateCompat.ACTION_PREPARE_FROM_URI),
    ACTION_SET_REPEAT_MODE(PlaybackStateCompat.ACTION_SET_REPEAT_MODE),
    ACTION_SET_CAPTIONING_ENABLED(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED),
    ACTION_SET_SHUFFLE_MODE(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE),
    ACTION_SET_PLAYBACK_SPEED(PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED);
    
    public static final Companion Companion = null;
    private final long capability;

    public static EnumEntries<PlaybackAction> getEntries() {
        return $ENTRIES;
    }

    private PlaybackAction(long j) {
        this.capability = j;
    }

    static {
        PlaybackAction[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public long toLong() {
        return this.capability;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/PlaybackAction$Companion;", "", "()V", "createActivePlaybackActions", "Lorg/videolan/vlc/util/FlagSet;", "Lorg/videolan/vlc/util/PlaybackAction;", "createBaseActions", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaybackAction.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FlagSet<PlaybackAction> createBaseActions() {
            FlagSet<PlaybackAction> flagSet = new FlagSet<>(PlaybackAction.class);
            flagSet.addAll((Enum[]) new PlaybackAction[]{PlaybackAction.ACTION_PLAY_PAUSE, PlaybackAction.ACTION_PLAY_FROM_MEDIA_ID, PlaybackAction.ACTION_PLAY_FROM_SEARCH, PlaybackAction.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackAction.ACTION_PLAY_FROM_URI});
            return flagSet;
        }

        public final FlagSet<PlaybackAction> createActivePlaybackActions() {
            FlagSet<PlaybackAction> createBaseActions = createBaseActions();
            createBaseActions.addAll((Enum[]) new PlaybackAction[]{PlaybackAction.ACTION_REWIND, PlaybackAction.ACTION_FAST_FORWARD, PlaybackAction.ACTION_SEEK_TO, PlaybackAction.ACTION_SET_PLAYBACK_SPEED});
            return createBaseActions;
        }
    }
}
