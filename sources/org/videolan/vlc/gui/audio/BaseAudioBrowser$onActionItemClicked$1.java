package org.videolan.vlc.gui.audio;

import android.view.MenuItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1", f = "BaseAudioBrowser.kt", i = {}, l = {324, 325, 326}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseAudioBrowser.kt */
final class BaseAudioBrowser$onActionItemClicked$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MenuItem $item;
    final /* synthetic */ List<MediaLibraryItem> $list;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BaseAudioBrowser<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseAudioBrowser$onActionItemClicked$1(BaseAudioBrowser<T> baseAudioBrowser, MenuItem menuItem, List<? extends MediaLibraryItem> list, Continuation<? super BaseAudioBrowser$onActionItemClicked$1> continuation) {
        super(2, continuation);
        this.this$0 = baseAudioBrowser;
        this.$item = menuItem;
        this.$list = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseAudioBrowser$onActionItemClicked$1(this.this$0, this.$item, this.$list, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseAudioBrowser$onActionItemClicked$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0042
            if (r1 == r4) goto L_0x0033
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            java.lang.Object r0 = r9.L$1
            androidx.fragment.app.FragmentActivity r0 = (androidx.fragment.app.FragmentActivity) r0
            java.lang.Object r1 = r9.L$0
            org.videolan.vlc.gui.helpers.UiTools r1 = (org.videolan.vlc.gui.helpers.UiTools) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00d9
        L_0x001e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0026:
            java.lang.Object r0 = r9.L$1
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Object r1 = r9.L$0
            org.videolan.vlc.media.MediaUtils r1 = (org.videolan.vlc.media.MediaUtils) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ab
        L_0x0033:
            java.lang.Object r0 = r9.L$1
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Object r1 = r9.L$0
            org.videolan.vlc.media.MediaUtils r1 = (org.videolan.vlc.media.MediaUtils) r1
            kotlin.ResultKt.throwOnFailure(r10)
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x007a
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r10)
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.lifecycle.LifecycleOwner r10 = (androidx.lifecycle.LifecycleOwner) r10
            boolean r10 = org.videolan.tools.KotlinExtensionsKt.isStarted(r10)
            if (r10 == 0) goto L_0x0198
            android.view.MenuItem r10 = r9.$item
            int r10 = r10.getItemId()
            int r1 = org.videolan.vlc.R.id.action_mode_audio_play
            if (r10 != r1) goto L_0x0087
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.fragment.app.FragmentActivity r10 = r10.getActivity()
            android.content.Context r10 = (android.content.Context) r10
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r2 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r3 = r9.$list
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r9.L$0 = r1
            r9.L$1 = r10
            r9.label = r4
            java.lang.Object r2 = r2.getTracks(r3, r5)
            if (r2 != r0) goto L_0x0077
            return r0
        L_0x0077:
            r0 = r1
            r1 = r10
            r10 = r2
        L_0x007a:
            r2 = r10
            java.util.List r2 = (java.util.List) r2
            r5 = 8
            r6 = 0
            r3 = 0
            r4 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x0198
        L_0x0087:
            int r1 = org.videolan.vlc.R.id.action_mode_audio_append
            if (r10 != r1) goto L_0x00b2
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.fragment.app.FragmentActivity r10 = r10.getActivity()
            android.content.Context r10 = (android.content.Context) r10
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r2 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r4 = r9.$list
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r9.L$0 = r1
            r9.L$1 = r10
            r9.label = r3
            java.lang.Object r2 = r2.getTracks(r4, r5)
            if (r2 != r0) goto L_0x00a9
            return r0
        L_0x00a9:
            r0 = r10
            r10 = r2
        L_0x00ab:
            java.util.List r10 = (java.util.List) r10
            r1.appendMedia((android.content.Context) r0, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r10)
            goto L_0x0198
        L_0x00b2:
            int r1 = org.videolan.vlc.R.id.action_mode_audio_add_playlist
            java.lang.String r3 = "requireActivity(...)"
            if (r10 != r1) goto L_0x00e0
            org.videolan.vlc.gui.helpers.UiTools r1 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.fragment.app.FragmentActivity r10 = r10.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r3)
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r3 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r4 = r9.$list
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r9.L$0 = r1
            r9.L$1 = r10
            r9.label = r2
            java.lang.Object r2 = r3.getTracks(r4, r5)
            if (r2 != r0) goto L_0x00d7
            return r0
        L_0x00d7:
            r0 = r10
            r10 = r2
        L_0x00d9:
            java.util.List r10 = (java.util.List) r10
            r1.addToPlaylist(r0, r10)
            goto L_0x0198
        L_0x00e0:
            int r0 = org.videolan.vlc.R.id.action_mode_audio_info
            if (r10 != r0) goto L_0x00f3
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r9.$list
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r0)
            org.videolan.medialibrary.media.MediaLibraryItem r0 = (org.videolan.medialibrary.media.MediaLibraryItem) r0
            r10.showInfoDialog(r0)
            goto L_0x0198
        L_0x00f3:
            int r0 = org.videolan.vlc.R.id.action_mode_audio_share
            if (r10 != r0) goto L_0x010c
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.fragment.app.FragmentActivity r10 = r10.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r3)
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r9.$list
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.collections.List<org.videolan.medialibrary.interfaces.media.MediaWrapper>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            org.videolan.vlc.util.KextensionsKt.share((androidx.fragment.app.FragmentActivity) r10, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r0)
            goto L_0x0198
        L_0x010c:
            int r0 = org.videolan.vlc.R.id.action_mode_audio_set_song
            if (r10 != r0) goto L_0x012b
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.fragment.app.FragmentActivity r10 = r10.getActivity()
            if (r10 == 0) goto L_0x0198
            org.videolan.vlc.gui.helpers.AudioUtil r0 = org.videolan.vlc.gui.helpers.AudioUtil.INSTANCE
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r1 = r9.$list
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r1)
            java.lang.String r2 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            r0.setRingtone(r10, r1)
            goto L_0x0198
        L_0x012b:
            int r0 = org.videolan.vlc.R.id.action_mode_audio_delete
            if (r10 != r0) goto L_0x0137
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r9.$list
            r10.removeItems(r0)
            goto L_0x0198
        L_0x0137:
            int r0 = org.videolan.vlc.R.id.action_mode_go_to_folder
            r1 = 0
            if (r10 != r0) goto L_0x0153
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r10 = r9.$list
            java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)
            boolean r0 = r10 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r0 == 0) goto L_0x0149
            r1 = r10
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
        L_0x0149:
            if (r1 == 0) goto L_0x0198
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            org.videolan.vlc.util.KextensionsKt.showParentFolder(r10, r1)
            goto L_0x0198
        L_0x0153:
            int r0 = org.videolan.vlc.R.id.action_mode_favorite_add
            if (r10 != r0) goto L_0x0176
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.lifecycle.LifecycleOwner r10 = (androidx.lifecycle.LifecycleOwner) r10
            androidx.lifecycle.LifecycleCoroutineScope r10 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r10)
            r2 = r10
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1$2 r10 = new org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1$2
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r0 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r3 = r9.$list
            r10.<init>(r0, r3, r1)
            r5 = r10
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            goto L_0x0198
        L_0x0176:
            int r0 = org.videolan.vlc.R.id.action_mode_favorite_remove
            if (r10 != r0) goto L_0x0198
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r10 = r9.this$0
            androidx.lifecycle.LifecycleOwner r10 = (androidx.lifecycle.LifecycleOwner) r10
            androidx.lifecycle.LifecycleCoroutineScope r10 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r10)
            r2 = r10
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1$3 r10 = new org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1$3
            org.videolan.vlc.gui.audio.BaseAudioBrowser<T> r0 = r9.this$0
            java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r3 = r9.$list
            r10.<init>(r0, r3, r1)
            r5 = r10
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
        L_0x0198:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
