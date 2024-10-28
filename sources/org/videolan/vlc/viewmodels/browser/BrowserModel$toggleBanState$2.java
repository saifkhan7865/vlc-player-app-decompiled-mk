package org.videolan.vlc.viewmodels.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.BrowserModel$toggleBanState$2", f = "BrowserModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserModel.kt */
final class BrowserModel$toggleBanState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserModel$toggleBanState$2(String str, Continuation<? super BrowserModel$toggleBanState$2> continuation) {
        super(2, continuation);
        this.$path = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserModel$toggleBanState$2(this.$path, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserModel$toggleBanState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String[] bannedFolders = Medialibrary.getInstance().bannedFolders();
            MedialibraryUtils medialibraryUtils = MedialibraryUtils.INSTANCE;
            String str = this.$path;
            Intrinsics.checkNotNull(bannedFolders);
            if (medialibraryUtils.isStrictlyBanned(str, (List<String>) ArraysKt.toList((T[]) bannedFolders))) {
                Medialibrary.getInstance().unbanFolder(this.$path);
            } else if (!MedialibraryUtils.INSTANCE.isBanned(this.$path, (List<String>) ArraysKt.toList((T[]) bannedFolders))) {
                Medialibrary.getInstance().banFolder(this.$path);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
