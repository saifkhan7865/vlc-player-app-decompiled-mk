package org.videolan.vlc;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
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
import org.videolan.resources.AndroidDevices;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a:\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012*\u0012(\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00030\u0003 \u0005*\u0014\u0012\u000e\b\u0001\u0012\n \u0005*\u0004\u0018\u00010\u00030\u0003\u0018\u00010\u00040\u00040\u0001*\u00020\u0006H@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$updateStorages$2", f = "MediaParsingService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$updateStorages$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends List<? extends String>, ? extends String[]>>, Object> {
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$updateStorages$2(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$updateStorages$2> continuation) {
        super(2, continuation);
        this.this$0 = mediaParsingService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaParsingService$updateStorages$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends List<String>, String[]>> continuation) {
        return ((MediaParsingService$updateStorages$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<String> externalStorageDirectories = AndroidDevices.INSTANCE.getExternalStorageDirectories();
            Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
            if (access$getMedialibrary$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                access$getMedialibrary$p = null;
            }
            return new Pair(externalStorageDirectories, access$getMedialibrary$p.getDevices());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
