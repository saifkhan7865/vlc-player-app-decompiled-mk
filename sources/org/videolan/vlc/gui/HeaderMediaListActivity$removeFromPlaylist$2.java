package org.videolan.vlc.gui;

import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2", f = "HeaderMediaListActivity.kt", i = {}, l = {562, 564}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$removeFromPlaylist$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Integer> $indexes;
    final /* synthetic */ HashMap<Integer, Long> $itemsRemoved;
    final /* synthetic */ List<MediaWrapper> $list;
    final /* synthetic */ Playlist $playlist;
    int label;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$removeFromPlaylist$2(HeaderMediaListActivity headerMediaListActivity, List<Integer> list, List<? extends MediaWrapper> list2, Playlist playlist, HashMap<Integer, Long> hashMap, Continuation<? super HeaderMediaListActivity$removeFromPlaylist$2> continuation) {
        super(2, continuation);
        this.this$0 = headerMediaListActivity;
        this.$indexes = list;
        this.$list = list2;
        this.$playlist = playlist;
        this.$itemsRemoved = hashMap;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$removeFromPlaylist$2(this.this$0, this.$indexes, this.$list, this.$playlist, this.$itemsRemoved, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeaderMediaListActivity$removeFromPlaylist$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 == r4) goto L_0x001b
            if (r1 != r3) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0073
        L_0x0013:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x003d
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.CoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13
            org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$tracks$1 r1 = new org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$tracks$1
            org.videolan.medialibrary.interfaces.media.Playlist r5 = r12.$playlist
            r1.<init>(r5, r2)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r5 = r12
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r12.label = r4
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r13, r1, r5)
            if (r13 != r0) goto L_0x003d
            return r0
        L_0x003d:
            r10 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r10 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r10
            org.videolan.vlc.gui.HeaderMediaListActivity r13 = r12.this$0
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel r13 = r13.viewModel
            if (r13 != 0) goto L_0x004e
            java.lang.String r13 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r13)
            goto L_0x004f
        L_0x004e:
            r2 = r13
        L_0x004f:
            org.videolan.medialibrary.media.MediaLibraryItem r7 = r2.getPlaylist()
            if (r7 == 0) goto L_0x0073
            java.util.List<java.lang.Integer> r6 = r12.$indexes
            java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r8 = r12.$list
            java.util.HashMap<java.lang.Integer, java.lang.Long> r9 = r12.$itemsRemoved
            kotlinx.coroutines.CoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13
            org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$1$1 r1 = new org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$1$1
            r11 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r12.label = r3
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r13, r1, r12)
            if (r13 != r0) goto L_0x0073
            return r0
        L_0x0073:
            java.util.List<java.lang.Integer> r13 = r12.$indexes
            int r13 = r13.size()
            if (r13 <= r4) goto L_0x0084
            org.videolan.vlc.gui.HeaderMediaListActivity r13 = r12.this$0
            int r0 = org.videolan.vlc.R.string.removed_from_playlist_anonymous
            java.lang.String r13 = r13.getString(r0)
            goto L_0x009d
        L_0x0084:
            org.videolan.vlc.gui.HeaderMediaListActivity r13 = r12.this$0
            int r0 = org.videolan.vlc.R.string.remove_playlist_item
            java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r1 = r12.$list
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r1)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            java.lang.String r1 = r1.getTitle()
            java.lang.Object[] r2 = new java.lang.Object[r4]
            r3 = 0
            r2[r3] = r1
            java.lang.String r13 = r13.getString(r0, r2)
        L_0x009d:
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            org.videolan.vlc.gui.helpers.UiTools r0 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.HeaderMediaListActivity r13 = r12.this$0
            r1 = r13
            android.app.Activity r1 = (android.app.Activity) r1
            org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$2 r13 = new org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$2
            org.videolan.vlc.gui.HeaderMediaListActivity r3 = r12.this$0
            r13.<init>(r3)
            r4 = r13
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$3 r13 = new org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2$3
            java.util.HashMap<java.lang.Integer, java.lang.Long> r3 = r12.$itemsRemoved
            org.videolan.medialibrary.interfaces.media.Playlist r5 = r12.$playlist
            org.videolan.vlc.gui.HeaderMediaListActivity r6 = r12.this$0
            r13.<init>(r3, r5, r6)
            r5 = r13
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            r6 = 4
            r7 = 0
            r3 = 0
            org.videolan.vlc.gui.helpers.UiTools.snackerWithCancel$default(r0, r1, r2, r3, r4, r5, r6, r7)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
