package org.videolan.vlc.util;

import android.net.Uri;
import java.io.File;
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
import org.videolan.resources.AndroidDevices;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$scanAllowed$2", f = "Kextensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Kextensions.kt */
final class KextensionsKt$scanAllowed$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $this_scanAllowed;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KextensionsKt$scanAllowed$2(String str, Continuation<? super KextensionsKt$scanAllowed$2> continuation) {
        super(2, continuation);
        this.$this_scanAllowed = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new KextensionsKt$scanAllowed$2(this.$this_scanAllowed, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((KextensionsKt$scanAllowed$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String[] list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String path = Uri.parse(this.$this_scanAllowed).getPath();
            if (path == null) {
                return Boxing.boxBoolean(false);
            }
            File file = new File(path);
            if (!file.exists() || !file.canRead()) {
                return Boxing.boxBoolean(false);
            }
            if (AndroidDevices.INSTANCE.getWatchDevices() && (list = file.list()) != null) {
                for (String areEqual : list) {
                    if (Intrinsics.areEqual((Object) areEqual, (Object) ".nomedia")) {
                        return Boxing.boxBoolean(false);
                    }
                }
            }
            return Boxing.boxBoolean(true);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
