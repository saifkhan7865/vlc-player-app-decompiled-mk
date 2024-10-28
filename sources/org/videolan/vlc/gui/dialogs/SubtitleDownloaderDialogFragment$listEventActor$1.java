package org.videolan.vlc.gui.dialogs;

import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ActorScope;
import org.videolan.vlc.viewmodels.SubtitlesModel;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/gui/dialogs/SubtitleEvent;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1", f = "SubtitleDownloaderDialogFragment.kt", i = {0, 1}, l = {57, 59}, m = "invokeSuspend", n = {"$this$actor", "$this$actor"}, s = {"L$0", "L$0"})
/* compiled from: SubtitleDownloaderDialogFragment.kt */
final class SubtitleDownloaderDialogFragment$listEventActor$1 extends SuspendLambda implements Function2<ActorScope<SubtitleEvent>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SubtitleDownloaderDialogFragment this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SubtitleDownloaderDialogFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                org.videolan.vlc.gui.dialogs.State[] r0 = org.videolan.vlc.gui.dialogs.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.dialogs.State r1 = org.videolan.vlc.gui.dialogs.State.NotDownloaded     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.dialogs.State r1 = org.videolan.vlc.gui.dialogs.State.Downloaded     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitleDownloaderDialogFragment$listEventActor$1(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, Continuation<? super SubtitleDownloaderDialogFragment$listEventActor$1> continuation) {
        super(2, continuation);
        this.this$0 = subtitleDownloaderDialogFragment;
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$2(DialogInterface dialogInterface, int i) {
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SubtitleDownloaderDialogFragment$listEventActor$1 subtitleDownloaderDialogFragment$listEventActor$1 = new SubtitleDownloaderDialogFragment$listEventActor$1(this.this$0, continuation);
        subtitleDownloaderDialogFragment$listEventActor$1.L$0 = obj;
        return subtitleDownloaderDialogFragment$listEventActor$1;
    }

    public final Object invoke(ActorScope<SubtitleEvent> actorScope, Continuation<? super Unit> continuation) {
        return ((SubtitleDownloaderDialogFragment$listEventActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 == r3) goto L_0x0023
            if (r1 != r2) goto L_0x001b
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r11.L$0
            kotlinx.coroutines.channels.ActorScope r4 = (kotlinx.coroutines.channels.ActorScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0143
        L_0x001b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0023:
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r11.L$0
            kotlinx.coroutines.channels.ActorScope r4 = (kotlinx.coroutines.channels.ActorScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0051
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlinx.coroutines.channels.ActorScope r12 = (kotlinx.coroutines.channels.ActorScope) r12
            kotlinx.coroutines.channels.Channel r1 = r12.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x003e:
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r11.L$0 = r12
            r11.L$1 = r1
            r11.label = r3
            java.lang.Object r4 = r1.hasNext(r4)
            if (r4 != r0) goto L_0x004e
            return r0
        L_0x004e:
            r10 = r4
            r4 = r12
            r12 = r10
        L_0x0051:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x0146
            java.lang.Object r12 = r1.next()
            org.videolan.vlc.gui.dialogs.SubtitleEvent r12 = (org.videolan.vlc.gui.dialogs.SubtitleEvent) r12
            r5 = r4
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            boolean r5 = kotlinx.coroutines.CoroutineScopeKt.isActive(r5)
            r6 = 0
            if (r5 == 0) goto L_0x013a
            boolean r5 = r12 instanceof org.videolan.vlc.gui.dialogs.SubtitleClick
            if (r5 == 0) goto L_0x00c6
            r5 = r12
            org.videolan.vlc.gui.dialogs.SubtitleClick r5 = (org.videolan.vlc.gui.dialogs.SubtitleClick) r5
            org.videolan.vlc.gui.dialogs.SubtitleItem r6 = r5.getItem()
            org.videolan.vlc.gui.dialogs.State r6 = r6.getState()
            int[] r7 = org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1.WhenMappings.$EnumSwitchMapping$0
            int r6 = r6.ordinal()
            r6 = r7[r6]
            java.lang.String r7 = "requireActivity(...)"
            if (r6 == r3) goto L_0x00a7
            if (r6 == r2) goto L_0x0089
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0089:
            org.videolan.vlc.gui.helpers.UiTools r5 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r6 = r11.this$0
            androidx.fragment.app.FragmentActivity r6 = r6.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            android.content.Context r6 = (android.content.Context) r6
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r7 = r11.this$0
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1$$ExternalSyntheticLambda0 r8 = new org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1$$ExternalSyntheticLambda0
            r8.<init>(r12, r7)
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1$$ExternalSyntheticLambda1 r12 = new org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1$$ExternalSyntheticLambda1
            r12.<init>()
            r5.deleteSubtitleDialog(r6, r8, r12)
            goto L_0x0143
        L_0x00a7:
            org.videolan.vlc.util.VLCDownloadManager r12 = org.videolan.vlc.util.VLCDownloadManager.INSTANCE
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r6 = r11.this$0
            androidx.fragment.app.FragmentActivity r6 = r6.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            org.videolan.vlc.gui.dialogs.SubtitleItem r5 = r5.getItem()
            r7 = r11
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r11.L$0 = r4
            r11.L$1 = r1
            r11.label = r2
            java.lang.Object r12 = r12.download(r6, r5, r7)
            if (r12 != r0) goto L_0x0143
            return r0
        L_0x00c6:
            boolean r5 = r12 instanceof org.videolan.vlc.gui.dialogs.SubtitleLongClick
            if (r5 == 0) goto L_0x0143
            org.videolan.vlc.gui.dialogs.SubtitleLongClick r12 = (org.videolan.vlc.gui.dialogs.SubtitleLongClick) r12
            org.videolan.vlc.gui.dialogs.SubtitleItem r12 = r12.getItem()
            org.videolan.vlc.gui.dialogs.State r12 = r12.getState()
            int[] r5 = org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1.WhenMappings.$EnumSwitchMapping$0
            int r12 = r12.ordinal()
            r12 = r5[r12]
            if (r12 == r3) goto L_0x00e6
            if (r12 == r2) goto L_0x00e3
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x00e3:
            int r12 = org.videolan.vlc.R.string.delete_the_selected
            goto L_0x00e8
        L_0x00e6:
            int r12 = org.videolan.vlc.R.string.download_the_selected
        L_0x00e8:
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r5 = r11.this$0
            android.widget.Toast r5 = r5.toast
            java.lang.String r7 = "toast"
            if (r5 == 0) goto L_0x0101
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r5 = r11.this$0
            android.widget.Toast r5 = r5.toast
            if (r5 != 0) goto L_0x00fe
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r5 = r6
        L_0x00fe:
            r5.cancel()
        L_0x0101:
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r5 = r11.this$0
            androidx.fragment.app.FragmentActivity r8 = r5.getActivity()
            android.content.Context r8 = (android.content.Context) r8
            r9 = 0
            android.widget.Toast r12 = android.widget.Toast.makeText(r8, r12, r9)
            java.lang.String r8 = "makeText(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r8)
            r5.toast = r12
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r12 = r11.this$0
            android.widget.Toast r12 = r12.toast
            if (r12 != 0) goto L_0x0122
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r12 = r6
        L_0x0122:
            r5 = 48
            r8 = 100
            r12.setGravity(r5, r9, r8)
            org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment r12 = r11.this$0
            android.widget.Toast r12 = r12.toast
            if (r12 != 0) goto L_0x0135
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x0136
        L_0x0135:
            r6 = r12
        L_0x0136:
            r6.show()
            goto L_0x0143
        L_0x013a:
            kotlinx.coroutines.channels.Channel r12 = r4.getChannel()
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r12, r6, r3, r6)
        L_0x0143:
            r12 = r4
            goto L_0x003e
        L_0x0146:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment$listEventActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(SubtitleEvent subtitleEvent, SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, DialogInterface dialogInterface, int i) {
        SubtitleClick subtitleClick = (SubtitleClick) subtitleEvent;
        String path = subtitleClick.getItem().getMediaUri().getPath();
        if (path != null) {
            SubtitlesModel access$getViewModel$p = subtitleDownloaderDialogFragment.viewModel;
            if (access$getViewModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                access$getViewModel$p = null;
            }
            access$getViewModel$p.deleteSubtitle(path, subtitleClick.getItem().getIdSubtitle());
        }
    }
}
