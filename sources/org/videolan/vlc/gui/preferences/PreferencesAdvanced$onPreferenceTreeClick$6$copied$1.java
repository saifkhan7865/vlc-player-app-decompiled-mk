package org.videolan.vlc.gui.preferences;

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
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$6$copied$1", f = "PreferencesAdvanced.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$6$copied$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ File $dst;
    int label;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$6$copied$1(PreferencesAdvanced preferencesAdvanced, File file, Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$6$copied$1> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAdvanced;
        this.$dst = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onPreferenceTreeClick$6$copied$1(this.this$0, this.$dst, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((PreferencesAdvanced$onPreferenceTreeClick$6$copied$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(FileUtils.INSTANCE.copyFile(new File(this.this$0.requireContext().getDir("db", 0).toString() + Medialibrary.VLC_MEDIA_DB_NAME), this.$dst));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
