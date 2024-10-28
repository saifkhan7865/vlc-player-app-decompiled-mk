package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$updateStorages$isNew$1", f = "MediaParsingService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$updateStorages$isNew$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $device;
    final /* synthetic */ String $uuid;
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$updateStorages$isNew$1(MediaParsingService mediaParsingService, String str, String str2, Continuation<? super MediaParsingService$updateStorages$isNew$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaParsingService;
        this.$uuid = str;
        this.$device = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaParsingService$updateStorages$isNew$1(this.this$0, this.$uuid, this.$device, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MediaParsingService$updateStorages$isNew$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
            Medialibrary medialibrary = null;
            if (access$getMedialibrary$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                access$getMedialibrary$p = null;
            }
            boolean z = !access$getMedialibrary$p.isDeviceKnown(this.$uuid, this.$device, true);
            Medialibrary access$getMedialibrary$p2 = this.this$0.medialibrary;
            if (access$getMedialibrary$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            } else {
                medialibrary = access$getMedialibrary$p2;
            }
            medialibrary.addDevice(this.$uuid, this.$device, true);
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
