package org.videolan.vlc;

import android.net.Uri;
import android.util.Log;
import java.util.List;
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
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$updateStorages$3", f = "MediaParsingService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$updateStorages$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<String> $missingDevices;
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$updateStorages$3(List<String> list, MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$updateStorages$3> continuation) {
        super(2, continuation);
        this.$missingDevices = list;
        this.this$0 = mediaParsingService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaParsingService$updateStorages$3(this.$missingDevices, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaParsingService$updateStorages$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            for (String next : this.$missingDevices) {
                Intrinsics.checkNotNull(next);
                Uri parse = Uri.parse(next);
                Log.i("MediaParsingService", "Storage management: storage missing: " + parse.getPath());
                Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
                if (access$getMedialibrary$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    access$getMedialibrary$p = null;
                }
                access$getMedialibrary$p.removeDevice(parse.getLastPathSegment(), parse.getPath());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
