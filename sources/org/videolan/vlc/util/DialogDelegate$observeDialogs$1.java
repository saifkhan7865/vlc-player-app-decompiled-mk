package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/util/DialogEvt;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DialogDelegates.kt */
final class DialogDelegate$observeDialogs$1 extends Lambda implements Function1<DialogEvt, Unit> {
    final /* synthetic */ IDialogManager $manager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DialogDelegate$observeDialogs$1(IDialogManager iDialogManager) {
        super(1);
        this.$manager = iDialogManager;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DialogEvt) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(DialogEvt dialogEvt) {
        Intrinsics.checkNotNullParameter(dialogEvt, "it");
        if (dialogEvt instanceof Show) {
            this.$manager.fireDialog(((Show) dialogEvt).getDialog());
        } else if (dialogEvt instanceof Cancel) {
            this.$manager.dialogCanceled(((Cancel) dialogEvt).getDialog());
        }
    }
}
