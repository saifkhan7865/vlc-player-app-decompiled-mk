package org.videolan.vlc.gui.network;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.ContextSheetKt;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/gui/network/StreamsFragmentDelegate;", "Lorg/videolan/vlc/gui/network/IStreamsFragmentDelegate;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "()V", "fragment", "Landroidx/fragment/app/Fragment;", "keyboardListener", "Lorg/videolan/vlc/gui/network/KeyboardListener;", "viewModel", "Lorg/videolan/vlc/viewmodels/StreamsModel;", "getlistEventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/network/MrlAction;", "onCtxAction", "", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "playMedia", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "renameStream", "setup", "showContext", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StreamsFragmentDelegate.kt */
public final class StreamsFragmentDelegate implements IStreamsFragmentDelegate, CtxActionReceiver {
    /* access modifiers changed from: private */
    public Fragment fragment;
    private KeyboardListener keyboardListener;
    /* access modifiers changed from: private */
    public StreamsModel viewModel;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: StreamsFragmentDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.util.ContextOption[] r0 = org.videolan.vlc.util.ContextOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_RENAME     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_APPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_COPY     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DELETE     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_SHORTCUT     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.network.StreamsFragmentDelegate.WhenMappings.<clinit>():void");
        }
    }

    public void setup(Fragment fragment2, StreamsModel streamsModel, KeyboardListener keyboardListener2) {
        Intrinsics.checkNotNullParameter(fragment2, SecondaryActivity.KEY_FRAGMENT);
        Intrinsics.checkNotNullParameter(streamsModel, "viewModel");
        Intrinsics.checkNotNullParameter(keyboardListener2, "keyboardListener");
        this.fragment = fragment2;
        this.viewModel = streamsModel;
        this.keyboardListener = keyboardListener2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: org.videolan.vlc.viewmodels.StreamsModel} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCtxAction(int r13, org.videolan.vlc.util.ContextOption r14) {
        /*
            r12 = this;
            java.lang.String r0 = "option"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            int[] r0 = org.videolan.vlc.gui.network.StreamsFragmentDelegate.WhenMappings.$EnumSwitchMapping$0
            int r14 = r14.ordinal()
            r14 = r0[r14]
            java.lang.String r0 = "requireActivity(...)"
            java.lang.String r1 = "viewModel"
            java.lang.String r2 = "fragment"
            r3 = 0
            switch(r14) {
                case 1: goto L_0x0165;
                case 2: goto L_0x0140;
                case 3: goto L_0x010d;
                case 4: goto L_0x00ae;
                case 5: goto L_0x0042;
                case 6: goto L_0x0019;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0168
        L_0x0019:
            androidx.fragment.app.Fragment r14 = r12.fragment
            if (r14 != 0) goto L_0x0021
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r14 = r3
        L_0x0021:
            androidx.fragment.app.FragmentActivity r14 = r14.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r0)
            androidx.lifecycle.LifecycleOwner r14 = (androidx.lifecycle.LifecycleOwner) r14
            androidx.lifecycle.LifecycleCoroutineScope r14 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r14)
            r4 = r14
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$3 r14 = new org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$3
            r14.<init>(r12, r13, r3)
            r7 = r14
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
            goto L_0x0168
        L_0x0042:
            org.videolan.vlc.viewmodels.StreamsModel r14 = r12.viewModel
            if (r14 != 0) goto L_0x004a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r14 = r3
        L_0x004a:
            org.videolan.tools.livedata.LiveDataset r14 = r14.getDataset()
            java.lang.Object r13 = r14.get(r13)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            org.videolan.vlc.viewmodels.StreamsModel r14 = r12.viewModel
            if (r14 != 0) goto L_0x005c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r14 = r3
        L_0x005c:
            r14.setDeletingMedia(r13)
            org.videolan.vlc.gui.helpers.UiTools r4 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.Fragment r13 = r12.fragment
            if (r13 != 0) goto L_0x0069
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r13 = r3
        L_0x0069:
            androidx.fragment.app.FragmentActivity r13 = r13.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            r5 = r13
            android.app.Activity r5 = (android.app.Activity) r5
            androidx.fragment.app.Fragment r13 = r12.fragment
            if (r13 != 0) goto L_0x007b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r13 = r3
        L_0x007b:
            androidx.fragment.app.FragmentActivity r13 = r13.requireActivity()
            int r14 = org.videolan.vlc.R.string.stream_deleted
            java.lang.String r6 = r13.getString(r14)
            java.lang.String r13 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r13)
            org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$1 r13 = new org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$1
            r13.<init>(r12)
            r8 = r13
            kotlin.jvm.functions.Function0 r8 = (kotlin.jvm.functions.Function0) r8
            org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$2 r13 = new org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$2
            r13.<init>(r12)
            r9 = r13
            kotlin.jvm.functions.Function0 r9 = (kotlin.jvm.functions.Function0) r9
            r10 = 4
            r11 = 0
            r7 = 0
            org.videolan.vlc.gui.helpers.UiTools.snackerWithCancel$default(r4, r5, r6, r7, r8, r9, r10, r11)
            org.videolan.vlc.viewmodels.StreamsModel r13 = r12.viewModel
            if (r13 != 0) goto L_0x00a8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00a9
        L_0x00a8:
            r3 = r13
        L_0x00a9:
            r3.refresh()
            goto L_0x0168
        L_0x00ae:
            org.videolan.vlc.viewmodels.StreamsModel r14 = r12.viewModel
            if (r14 != 0) goto L_0x00b6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r14 = r3
        L_0x00b6:
            org.videolan.tools.livedata.LiveDataset r14 = r14.getDataset()
            java.lang.Object r13 = r14.get(r13)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            androidx.fragment.app.Fragment r14 = r12.fragment
            if (r14 != 0) goto L_0x00c8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r14 = r3
        L_0x00c8:
            android.content.Context r14 = r14.requireContext()
            java.lang.String r0 = "requireContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r0)
            java.lang.String r0 = r13.getTitle()
            java.lang.String r1 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r13 = r13.getLocation()
            java.lang.String r1 = "getLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r1)
            org.videolan.tools.KotlinExtensionsKt.copy(r14, r0, r13)
            androidx.fragment.app.Fragment r13 = r12.fragment
            if (r13 != 0) goto L_0x00ee
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x00ef
        L_0x00ee:
            r3 = r13
        L_0x00ef:
            androidx.fragment.app.FragmentActivity r13 = r3.requireActivity()
            android.view.Window r13 = r13.getWindow()
            android.view.View r13 = r13.getDecorView()
            r14 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r13 = r13.findViewById(r14)
            int r14 = org.videolan.vlc.R.string.url_copied_to_clipboard
            r0 = 0
            com.google.android.material.snackbar.Snackbar r13 = com.google.android.material.snackbar.Snackbar.make((android.view.View) r13, (int) r14, (int) r0)
            r13.show()
            goto L_0x0168
        L_0x010d:
            org.videolan.vlc.viewmodels.StreamsModel r14 = r12.viewModel
            if (r14 != 0) goto L_0x0115
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r14 = r3
        L_0x0115:
            org.videolan.tools.livedata.LiveDataset r14 = r14.getDataset()
            java.lang.Object r13 = r14.get(r13)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            org.videolan.vlc.gui.helpers.UiTools r14 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.Fragment r1 = r12.fragment
            if (r1 != 0) goto L_0x0129
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x012a
        L_0x0129:
            r3 = r1
        L_0x012a:
            androidx.fragment.app.FragmentActivity r1 = r3.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r13 = r13.getTracks()
            java.lang.String r0 = "getTracks(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r0)
            java.lang.String r0 = "PLAYLIST_NEW_TRACKS"
            r14.addToPlaylist(r1, r13, r0)
            goto L_0x0168
        L_0x0140:
            org.videolan.vlc.viewmodels.StreamsModel r14 = r12.viewModel
            if (r14 != 0) goto L_0x0148
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r14 = r3
        L_0x0148:
            org.videolan.tools.livedata.LiveDataset r14 = r14.getDataset()
            java.lang.Object r13 = r14.get(r13)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            org.videolan.vlc.media.MediaUtils r14 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.Fragment r0 = r12.fragment
            if (r0 != 0) goto L_0x015c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x015d
        L_0x015c:
            r3 = r0
        L_0x015d:
            android.content.Context r0 = r3.requireContext()
            r14.appendMedia((android.content.Context) r0, (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13)
            goto L_0x0168
        L_0x0165:
            r12.renameStream(r13)
        L_0x0168:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.network.StreamsFragmentDelegate.onCtxAction(int, org.videolan.vlc.util.ContextOption):void");
    }

    public void showContext(int i) {
        FlagSet flagSet = new FlagSet(ContextOption.class);
        flagSet.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_ADD_SHORTCUT, ContextOption.CTX_ADD_TO_PLAYLIST, ContextOption.CTX_APPEND, ContextOption.CTX_COPY, ContextOption.CTX_DELETE, ContextOption.CTX_RENAME});
        StreamsModel streamsModel = this.viewModel;
        Fragment fragment2 = null;
        if (streamsModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel = null;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) streamsModel.getDataset().get(i);
        Fragment fragment3 = this.fragment;
        if (fragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
        } else {
            fragment2 = fragment3;
        }
        FragmentActivity requireActivity = fragment2.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        ContextSheetKt.showContext(requireActivity, this, i, mediaWrapper, flagSet);
    }

    public SendChannel<MrlAction> getlistEventActor() {
        Fragment fragment2 = this.fragment;
        if (fragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            fragment2 = null;
        }
        return ActorKt.actor$default(LifecycleOwnerKt.getLifecycleScope(fragment2), (CoroutineContext) null, 0, (CoroutineStart) null, (Function1) null, new StreamsFragmentDelegate$getlistEventActor$1(this, (Continuation<? super StreamsFragmentDelegate$getlistEventActor$1>) null), 15, (Object) null);
    }

    public void playMedia(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        mediaWrapper.setType(6);
        String scheme = mediaWrapper.getUri().getScheme();
        KeyboardListener keyboardListener2 = null;
        if (scheme == null || !StringsKt.startsWith$default(scheme, "rtsp", false, 2, (Object) null)) {
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            Fragment fragment2 = this.fragment;
            if (fragment2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
                fragment2 = null;
            }
            mediaUtils.openMedia(fragment2.getActivity(), mediaWrapper);
        } else {
            VideoPlayerActivity.Companion companion = VideoPlayerActivity.Companion;
            Fragment fragment3 = this.fragment;
            if (fragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
                fragment3 = null;
            }
            Context requireContext = fragment3.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            Uri uri = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            companion.start(requireContext, uri);
        }
        Fragment fragment4 = this.fragment;
        if (fragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
            fragment4 = null;
        }
        FragmentActivity activity = fragment4.getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
        KeyboardListener keyboardListener3 = this.keyboardListener;
        if (keyboardListener3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyboardListener");
        } else {
            keyboardListener2 = keyboardListener3;
        }
        keyboardListener2.hideKeyboard();
    }

    private final void renameStream(int i) {
        RenameDialog.Companion companion = RenameDialog.Companion;
        StreamsModel streamsModel = this.viewModel;
        Fragment fragment2 = null;
        if (streamsModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel = null;
        }
        RenameDialog newInstance$default = RenameDialog.Companion.newInstance$default(companion, (MediaLibraryItem) streamsModel.getDataset().get(i), false, 2, (Object) null);
        newInstance$default.setListener(new StreamsFragmentDelegate$renameStream$1(this));
        Fragment fragment3 = this.fragment;
        if (fragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
        } else {
            fragment2 = fragment3;
        }
        newInstance$default.show(fragment2.requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
    }
}
