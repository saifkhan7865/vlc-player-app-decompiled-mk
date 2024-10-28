package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.viewmodels.DisplaySettingsViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.DisplaySettingsDialog$onViewCreated$7$1", f = "DisplaySettingsDialog.kt", i = {}, l = {176}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DisplaySettingsDialog.kt */
final class DisplaySettingsDialog$onViewCreated$7$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DisplaySettingsDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DisplaySettingsDialog$onViewCreated$7$1(DisplaySettingsDialog displaySettingsDialog, Continuation<? super DisplaySettingsDialog$onViewCreated$7$1> continuation) {
        super(2, continuation);
        this.this$0 = displaySettingsDialog;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DisplaySettingsDialog$onViewCreated$7$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisplaySettingsDialog$onViewCreated$7$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DisplaySettingsViewModel access$getDisplaySettingsViewModel = this.this$0.getDisplaySettingsViewModel();
            Boolean access$getShowOnlyMultimediaFiles$p = this.this$0.showOnlyMultimediaFiles;
            Intrinsics.checkNotNull(access$getShowOnlyMultimediaFiles$p);
            this.label = 1;
            if (access$getDisplaySettingsViewModel.send(DisplaySettingsDialogKt.SHOW_ONLY_MULTIMEDIA_FILES, access$getShowOnlyMultimediaFiles$p, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}