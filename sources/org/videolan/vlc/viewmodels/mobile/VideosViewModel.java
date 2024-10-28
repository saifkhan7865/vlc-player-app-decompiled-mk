package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.providers.medialibrary.FoldersProvider;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.VideoGroupsProvider;
import org.videolan.vlc.providers.medialibrary.VideosProvider;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001NB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u001d\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0000¢\u0006\u0002\b#J\u0015\u0010$\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0000¢\u0006\u0002\b%J\u001b\u0010&\u001a\u00020\u001e2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(H\u0000¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020+2\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¢\u0006\u0002\b,J\u001e\u0010-\u001a\u0004\u0018\u00010\t2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0(H@¢\u0006\u0002\u00100J\u0016\u00101\u001a\u0002022\u0006\u00103\u001a\u00020/H@¢\u0006\u0002\u00104J\u0010\u00105\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013H\u0002J\u0016\u00106\u001a\u00020+2\u0006\u00103\u001a\u00020\u0014H@¢\u0006\u0002\u00107J\u0016\u00108\u001a\u0002092\u0006\u00103\u001a\u00020\u0014H@¢\u0006\u0002\u00107J\u0015\u0010:\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0000¢\u0006\u0002\b;J!\u0010<\u001a\u00020+2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010!\u001a\u00020\"H\u0000¢\u0006\u0002\b=J\u001f\u0010>\u001a\u00020+2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u00103\u001a\u00020/H\u0000¢\u0006\u0002\b?J\u001b\u0010@\u001a\u00020\u001e2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(H\u0000¢\u0006\u0002\bAJ;\u0010B\u001a\u00020+2\b\u0010\u0002\u001a\u0004\u0018\u00010 2\u0006\u0010C\u001a\u00020/2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010D\u001a\u0002022\b\b\u0002\u0010E\u001a\u000202H\u0000¢\u0006\u0002\bFJ\u0014\u0010G\u001a\u00020\u001e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0(J\u000e\u0010G\u001a\u00020\u001e2\u0006\u00103\u001a\u00020/J\u0016\u0010H\u001a\u00020\u001e2\u0006\u0010I\u001a\u00020\t2\u0006\u0010J\u001a\u00020KJ\u0014\u0010L\u001a\u00020\u001e2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00140(J\u000e\u0010L\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R.\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00132\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00130\u0019X\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001b¨\u0006O"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/VideosViewModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "context", "Landroid/content/Context;", "type", "Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "folder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "group", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;Lorg/videolan/medialibrary/interfaces/media/Folder;Lorg/videolan/medialibrary/interfaces/media/VideoGroup;)V", "getFolder", "()Lorg/videolan/medialibrary/interfaces/media/Folder;", "getGroup", "()Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "<set-?>", "groupingType", "getGroupingType", "()Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "provider", "getProvider", "()Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "providers", "", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "addItemToPlaylist", "Lkotlinx/coroutines/Job;", "activity", "Landroidx/fragment/app/FragmentActivity;", "position", "", "addItemToPlaylist$vlc_android_release", "append", "append$vlc_android_release", "appendFoldersSelection", "selection", "", "appendFoldersSelection$vlc_android_release", "changeGroupingType", "", "changeGroupingType$vlc_android_release", "createGroup", "medias", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "groupSimilar", "", "media", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadProvider", "markAsPlayed", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsUnplayed", "", "play", "play$vlc_android_release", "playAll", "playAll$vlc_android_release", "playAudio", "playAudio$vlc_android_release", "playFoldersSelection", "playFoldersSelection$vlc_android_release", "playVideo", "mw", "fromStart", "forceAll", "playVideo$vlc_android_release", "removeFromGroup", "renameGroup", "videoGroup", "newName", "", "ungroup", "groups", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideosViewModel.kt */
public final class VideosViewModel extends MedialibraryViewModel {
    private final Folder folder;
    private final VideoGroup group;
    private VideoGroupingType groupingType;
    private MedialibraryProvider<? extends MediaLibraryItem> provider;
    private final MedialibraryProvider<? extends MediaLibraryItem>[] providers;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideosViewModel.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType[] r0 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType r1 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType r1 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.FOLDER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType r1 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.NAME     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.mobile.VideosViewModel.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideosViewModel(Context context, VideoGroupingType videoGroupingType, Folder folder2, VideoGroup videoGroup) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(videoGroupingType, "type");
        this.folder = folder2;
        this.group = videoGroup;
        this.groupingType = videoGroupingType;
        MedialibraryProvider<? extends MediaLibraryItem> loadProvider = loadProvider();
        this.provider = loadProvider;
        this.providers = new MedialibraryProvider[]{loadProvider};
        watchMedia();
        watchMediaGroups();
        watchFolders();
    }

    public final Folder getFolder() {
        return this.folder;
    }

    public final VideoGroup getGroup() {
        return this.group;
    }

    public final VideoGroupingType getGroupingType() {
        return this.groupingType;
    }

    public final MedialibraryProvider<? extends MediaLibraryItem> getProvider() {
        return this.provider;
    }

    private final MedialibraryProvider<? extends MediaLibraryItem> loadProvider() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.groupingType.ordinal()];
        if (i == 1) {
            return new VideosProvider(this.folder, this.group, getContext(), this);
        }
        if (i == 2) {
            return new FoldersProvider(getContext(), this, Folder.TYPE_FOLDER_VIDEO);
        }
        if (i == 3) {
            return new VideoGroupsProvider(getContext(), this);
        }
        throw new NoWhenBranchMatchedException();
    }

    public MedialibraryProvider<? extends MediaLibraryItem>[] getProviders() {
        return this.providers;
    }

    public final void changeGroupingType$vlc_android_release(VideoGroupingType videoGroupingType) {
        Intrinsics.checkNotNullParameter(videoGroupingType, "type");
        if (this.groupingType != videoGroupingType) {
            this.groupingType = videoGroupingType;
            this.provider = loadProvider();
            getProviders()[0] = this.provider;
            refresh();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ%\u0010\u0011\u001a\u0002H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/VideosViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "groupingType", "Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "folder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "group", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;Lorg/videolan/medialibrary/interfaces/media/Folder;Lorg/videolan/medialibrary/interfaces/media/VideoGroup;)V", "getContext", "()Landroid/content/Context;", "getFolder", "()Lorg/videolan/medialibrary/interfaces/media/Folder;", "getGroup", "()Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideosViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final Folder folder;
        private final VideoGroup group;
        private final VideoGroupingType groupingType;

        public Factory(Context context2, VideoGroupingType videoGroupingType, Folder folder2, VideoGroup videoGroup) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(videoGroupingType, "groupingType");
            this.context = context2;
            this.groupingType = videoGroupingType;
            this.folder = folder2;
            this.group = videoGroup;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Factory(Context context2, VideoGroupingType videoGroupingType, Folder folder2, VideoGroup videoGroup, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(context2, videoGroupingType, (i & 4) != 0 ? null : folder2, (i & 8) != 0 ? null : videoGroup);
        }

        public final Context getContext() {
            return this.context;
        }

        public final Folder getFolder() {
            return this.folder;
        }

        public final VideoGroup getGroup() {
            return this.group;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new VideosViewModel(applicationContext, this.groupingType, this.folder, this.group);
        }
    }

    public final Job play$vlc_android_release(int i) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$play$1(this, i, (Continuation<? super VideosViewModel$play$1>) null), 3, (Object) null);
    }

    public final Job append$vlc_android_release(int i) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$append$1(this, i, (Continuation<? super VideosViewModel$append$1>) null), 3, (Object) null);
    }

    public final Job playFoldersSelection$vlc_android_release(List<? extends Folder> list) {
        Intrinsics.checkNotNullParameter(list, "selection");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$playFoldersSelection$1(list, this, (Continuation<? super VideosViewModel$playFoldersSelection$1>) null), 3, (Object) null);
    }

    public final Job addItemToPlaylist$vlc_android_release(FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$addItemToPlaylist$1(this, i, fragmentActivity, (Continuation<? super VideosViewModel$addItemToPlaylist$1>) null), 3, (Object) null);
    }

    public final Job appendFoldersSelection$vlc_android_release(List<? extends Folder> list) {
        Intrinsics.checkNotNullParameter(list, "selection");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$appendFoldersSelection$1(list, this, (Continuation<? super VideosViewModel$appendFoldersSelection$1>) null), 3, (Object) null);
    }

    public static /* synthetic */ void playVideo$vlc_android_release$default(VideosViewModel videosViewModel, FragmentActivity fragmentActivity, MediaWrapper mediaWrapper, int i, boolean z, boolean z2, int i2, Object obj) {
        videosViewModel.playVideo$vlc_android_release(fragmentActivity, mediaWrapper, i, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2);
    }

    public final void playVideo$vlc_android_release(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        if (fragmentActivity != null) {
            if (!mediaWrapper.isPresent()) {
                UiTools.INSTANCE.snackerMissing(fragmentActivity);
                return;
            }
            mediaWrapper.removeFlags(8);
            PlaylistManager.Companion.setPlayingAsAudio(false);
            SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(fragmentActivity);
            if (z || (!sharedPreferences.getBoolean(SettingsKt.FORCE_PLAY_ALL_VIDEO, Settings.INSTANCE.getTvUI()) && !z2)) {
                if (z) {
                    mediaWrapper.addFlags(32);
                }
                MediaUtils.INSTANCE.openMedia(fragmentActivity, mediaWrapper);
                return;
            }
            MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider = this.provider;
            if (medialibraryProvider instanceof VideosProvider) {
                MediaUtils.INSTANCE.playAll(fragmentActivity, medialibraryProvider, i, false);
            } else if (medialibraryProvider instanceof FoldersProvider) {
                MediaUtils.INSTANCE.playAllTracks((Context) fragmentActivity, (FoldersProvider) medialibraryProvider, i, false);
            } else if (medialibraryProvider instanceof VideoGroupsProvider) {
                MediaUtils.INSTANCE.playAllTracks((Context) fragmentActivity, (VideoGroupsProvider) medialibraryProvider, mediaWrapper, false);
            }
        }
    }

    public static /* synthetic */ void playAll$vlc_android_release$default(VideosViewModel videosViewModel, FragmentActivity fragmentActivity, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        videosViewModel.playAll$vlc_android_release(fragmentActivity, i);
    }

    public final void playAll$vlc_android_release(FragmentActivity fragmentActivity, int i) {
        if (fragmentActivity != null && KotlinExtensionsKt.isStarted(fragmentActivity)) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[this.groupingType.ordinal()];
            if (i2 == 1) {
                MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider = this.provider;
                Intrinsics.checkNotNull(medialibraryProvider, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.VideosProvider");
                MediaUtils.INSTANCE.playAll(fragmentActivity, (VideosProvider) medialibraryProvider, i, false);
            } else if (i2 == 2) {
                MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider2 = this.provider;
                Intrinsics.checkNotNull(medialibraryProvider2, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.FoldersProvider");
                MediaUtils.INSTANCE.playAllTracks((Context) fragmentActivity, (FoldersProvider) medialibraryProvider2, i, false);
            } else if (i2 == 3) {
                MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider3 = this.provider;
                Intrinsics.checkNotNull(medialibraryProvider3, "null cannot be cast to non-null type org.videolan.vlc.providers.medialibrary.VideoGroupsProvider");
                MediaUtils.INSTANCE.playAllTracks((Context) fragmentActivity, (VideoGroupsProvider) medialibraryProvider3, (MediaWrapper) null, false);
            }
        }
    }

    public final void playAudio$vlc_android_release(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        if (fragmentActivity != null) {
            mediaWrapper.addFlags(8);
            PlaylistManager.Companion.setPlayingAsAudio(true);
            MediaUtils.INSTANCE.openMedia(fragmentActivity, mediaWrapper);
        }
    }

    public final Job renameGroup(VideoGroup videoGroup, String str) {
        Intrinsics.checkNotNullParameter(videoGroup, "videoGroup");
        Intrinsics.checkNotNullParameter(str, "newName");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$renameGroup$1(videoGroup, str, (Continuation<? super VideosViewModel$renameGroup$1>) null), 3, (Object) null);
    }

    public final Job removeFromGroup(List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(list, "medias");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$removeFromGroup$1(list, this, (Continuation<? super VideosViewModel$removeFromGroup$1>) null), 3, (Object) null);
    }

    public final Job removeFromGroup(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$removeFromGroup$2(this, mediaWrapper, (Continuation<? super VideosViewModel$removeFromGroup$2>) null), 3, (Object) null);
    }

    public final Job ungroup(List<? extends MediaLibraryItem> list) {
        Intrinsics.checkNotNullParameter(list, "groups");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$ungroup$1(list, (Continuation<? super VideosViewModel$ungroup$1>) null), 3, (Object) null);
    }

    public final Job ungroup(VideoGroup videoGroup) {
        Intrinsics.checkNotNullParameter(videoGroup, "group");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideosViewModel$ungroup$2(videoGroup, (Continuation<? super VideosViewModel$ungroup$2>) null), 3, (Object) null);
    }

    public final Object createGroup(List<? extends MediaWrapper> list, Continuation<? super VideoGroup> continuation) {
        if (list.size() < 2) {
            return null;
        }
        return BuildersKt.withContext(Dispatchers.getIO(), new VideosViewModel$createGroup$2(this, list, (Continuation<? super VideosViewModel$createGroup$2>) null), continuation);
    }

    public final Object groupSimilar(MediaWrapper mediaWrapper, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new VideosViewModel$groupSimilar$2(this, mediaWrapper, (Continuation<? super VideosViewModel$groupSimilar$2>) null), continuation);
    }

    public final Object markAsPlayed(MediaLibraryItem mediaLibraryItem, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new VideosViewModel$markAsPlayed$2(mediaLibraryItem, (Continuation<? super VideosViewModel$markAsPlayed$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final Object markAsUnplayed(MediaLibraryItem mediaLibraryItem, Continuation<Object> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new VideosViewModel$markAsUnplayed$2(mediaLibraryItem, (Continuation<? super VideosViewModel$markAsUnplayed$2>) null), continuation);
    }
}
