package org.videolan.vlc.gui;

import android.net.Uri;
import java.io.File;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoModel$checkFile$1$itemFile$1", f = "InfoActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: InfoActivity.kt */
final class InfoModel$checkFile$1$itemFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoModel$checkFile$1$itemFile$1(MediaWrapper mediaWrapper, Continuation<? super InfoModel$checkFile$1$itemFile$1> continuation) {
        super(2, continuation);
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new InfoModel$checkFile$1$itemFile$1(this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
        return ((InfoModel$checkFile$1$itemFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String location = this.$mw.getLocation();
            Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
            String substring = location.substring(5);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return new File(Uri.decode(substring));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
