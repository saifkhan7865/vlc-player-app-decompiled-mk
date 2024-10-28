package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b(\b\u0002\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001,B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+¨\u0006-"}, d2 = {"Lorg/videolan/vlc/util/ContextOption;", "", "Lorg/videolan/vlc/util/Flag;", "(Ljava/lang/String;I)V", "toLong", "", "CTX_ADD_FOLDER_AND_SUB_PLAYLIST", "CTX_ADD_FOLDER_PLAYLIST", "CTX_ADD_GROUP", "CTX_ADD_SCANNED", "CTX_ADD_SHORTCUT", "CTX_ADD_TO_PLAYLIST", "CTX_APPEND", "CTX_BAN_FOLDER", "CTX_COPY", "CTX_CUSTOM_REMOVE", "CTX_DELETE", "CTX_DOWNLOAD_SUBTITLES", "CTX_FAV_ADD", "CTX_FAV_EDIT", "CTX_FAV_REMOVE", "CTX_FIND_METADATA", "CTX_GO_TO_FOLDER", "CTX_GROUP_SIMILAR", "CTX_INFORMATION", "CTX_ITEM_DL", "CTX_MARK_ALL_AS_PLAYED", "CTX_MARK_ALL_AS_UNPLAYED", "CTX_MARK_AS_PLAYED", "CTX_MARK_AS_UNPLAYED", "CTX_PLAY", "CTX_PLAY_ALL", "CTX_PLAY_AS_AUDIO", "CTX_PLAY_FROM_START", "CTX_PLAY_NEXT", "CTX_PLAY_SHUFFLE", "CTX_REMOVE_FROM_PLAYLIST", "CTX_REMOVE_GROUP", "CTX_RENAME", "CTX_RENAME_GROUP", "CTX_SET_RINGTONE", "CTX_SHARE", "CTX_STOP_AFTER_THIS", "CTX_UNGROUP", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextOption.kt */
public enum ContextOption implements Flag {
    CTX_ADD_FOLDER_AND_SUB_PLAYLIST,
    CTX_ADD_FOLDER_PLAYLIST,
    CTX_ADD_GROUP,
    CTX_ADD_SCANNED,
    CTX_ADD_SHORTCUT,
    CTX_ADD_TO_PLAYLIST,
    CTX_APPEND,
    CTX_BAN_FOLDER,
    CTX_COPY,
    CTX_CUSTOM_REMOVE,
    CTX_DELETE,
    CTX_DOWNLOAD_SUBTITLES,
    CTX_FAV_ADD,
    CTX_FAV_EDIT,
    CTX_FAV_REMOVE,
    CTX_FIND_METADATA,
    CTX_GO_TO_FOLDER,
    CTX_GROUP_SIMILAR,
    CTX_INFORMATION,
    CTX_ITEM_DL,
    CTX_MARK_ALL_AS_PLAYED,
    CTX_MARK_ALL_AS_UNPLAYED,
    CTX_MARK_AS_PLAYED,
    CTX_MARK_AS_UNPLAYED,
    CTX_PLAY,
    CTX_PLAY_ALL,
    CTX_PLAY_AS_AUDIO,
    CTX_PLAY_FROM_START,
    CTX_PLAY_NEXT,
    CTX_PLAY_SHUFFLE,
    CTX_REMOVE_FROM_PLAYLIST,
    CTX_REMOVE_GROUP,
    CTX_RENAME,
    CTX_RENAME_GROUP,
    CTX_SET_RINGTONE,
    CTX_SHARE,
    CTX_STOP_AFTER_THIS,
    CTX_UNGROUP;
    
    public static final Companion Companion = null;

    public static EnumEntries<ContextOption> getEntries() {
        return $ENTRIES;
    }

    static {
        ContextOption[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public long toLong() {
        return 1 << ordinal();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/util/ContextOption$Companion;", "", "()V", "createBaseFlags", "Lorg/videolan/vlc/util/FlagSet;", "Lorg/videolan/vlc/util/ContextOption;", "createCtxAudioFlags", "createCtxFolderFlags", "createCtxPlaylistAlbumFlags", "createCtxPlaylistItemFlags", "createCtxTrackFlags", "createCtxVideoFlags", "createCtxVideoGroupFlags", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContextOption.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final FlagSet<ContextOption> createBaseFlags() {
            FlagSet<ContextOption> flagSet = new FlagSet<>(ContextOption.class);
            flagSet.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_ADD_SHORTCUT, ContextOption.CTX_ADD_TO_PLAYLIST, ContextOption.CTX_APPEND});
            return flagSet;
        }

        public final FlagSet<ContextOption> createCtxVideoFlags() {
            FlagSet<ContextOption> createBaseFlags = createBaseFlags();
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_DELETE, ContextOption.CTX_DOWNLOAD_SUBTITLES, ContextOption.CTX_INFORMATION});
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_PLAY, ContextOption.CTX_PLAY_ALL, ContextOption.CTX_PLAY_AS_AUDIO, ContextOption.CTX_PLAY_NEXT});
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_SET_RINGTONE, ContextOption.CTX_SHARE});
            return createBaseFlags;
        }

        public final FlagSet<ContextOption> createCtxTrackFlags() {
            FlagSet<ContextOption> createBaseFlags = createBaseFlags();
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_DELETE, ContextOption.CTX_GO_TO_FOLDER, ContextOption.CTX_INFORMATION, ContextOption.CTX_PLAY_ALL, ContextOption.CTX_PLAY_NEXT});
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_SET_RINGTONE, ContextOption.CTX_SHARE});
            return createBaseFlags;
        }

        public final FlagSet<ContextOption> createCtxAudioFlags() {
            FlagSet<ContextOption> createBaseFlags = createBaseFlags();
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_INFORMATION, ContextOption.CTX_PLAY, ContextOption.CTX_PLAY_NEXT});
            return createBaseFlags;
        }

        public final FlagSet<ContextOption> createCtxPlaylistAlbumFlags() {
            FlagSet<ContextOption> createCtxAudioFlags = createCtxAudioFlags();
            createCtxAudioFlags.add(ContextOption.CTX_DELETE);
            return createCtxAudioFlags;
        }

        public final FlagSet<ContextOption> createCtxPlaylistItemFlags() {
            FlagSet<ContextOption> createBaseFlags = createBaseFlags();
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_DELETE, ContextOption.CTX_INFORMATION, ContextOption.CTX_PLAY_NEXT, ContextOption.CTX_SET_RINGTONE});
            return createBaseFlags;
        }

        public final FlagSet<ContextOption> createCtxVideoGroupFlags() {
            FlagSet<ContextOption> createBaseFlags = createBaseFlags();
            createBaseFlags.remove(ContextOption.CTX_ADD_SHORTCUT);
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_ADD_GROUP, ContextOption.CTX_MARK_ALL_AS_PLAYED, ContextOption.CTX_MARK_ALL_AS_UNPLAYED, ContextOption.CTX_PLAY_ALL, ContextOption.CTX_RENAME_GROUP, ContextOption.CTX_UNGROUP});
            return createBaseFlags;
        }

        public final FlagSet<ContextOption> createCtxFolderFlags() {
            FlagSet<ContextOption> createBaseFlags = createBaseFlags();
            createBaseFlags.remove(ContextOption.CTX_ADD_SHORTCUT);
            createBaseFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_BAN_FOLDER, ContextOption.CTX_MARK_ALL_AS_PLAYED, ContextOption.CTX_MARK_ALL_AS_UNPLAYED, ContextOption.CTX_PLAY_ALL});
            return createBaseFlags;
        }
    }
}
