package org.videolan.vlc.util;

import android.content.Context;
import android.content.res.AssetManager;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.FileUtils$copyHrtfs$1", f = "FileUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileUtils.kt */
final class FileUtils$copyHrtfs$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $force;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileUtils$copyHrtfs$1(Context context, boolean z, Continuation<? super FileUtils$copyHrtfs$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$force = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileUtils$copyHrtfs$1(this.$context, this.$force, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileUtils$copyHrtfs$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            AssetManager assets = this.$context.getAssets();
            FileUtils fileUtils = FileUtils.INSTANCE;
            Intrinsics.checkNotNull(assets);
            fileUtils.copyAssetFolder(assets, "hrtfs", this.$context.getDir("vlc", 0).getAbsolutePath() + "/.share/hrtfs", this.$force);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
