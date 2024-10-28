package org.videolan.vlc.gui.preferences;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesAdvanced$onPreferenceTreeClick$7$copied$1", f = "PreferencesAdvanced.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$7$copied$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ File $dst;
    int label;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$7$copied$1(PreferencesAdvanced preferencesAdvanced, File file, Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$7$copied$1> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAdvanced;
        this.$dst = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onPreferenceTreeClick$7$copied$1(this.this$0, this.$dst, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((PreferencesAdvanced$onPreferenceTreeClick$7$copied$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String[] strArr;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            String parent = this.this$0.requireContext().getDir("db", 0).getParent();
            Intrinsics.checkNotNull(parent);
            sb.append(parent);
            sb.append("/databases");
            File[] listFiles = new File(sb.toString()).listFiles();
            if (listFiles != null) {
                Collection arrayList = new ArrayList(listFiles.length);
                for (File path : listFiles) {
                    arrayList.add(path.getPath());
                }
                strArr = (String[]) ((List) arrayList).toArray(new String[0]);
            } else {
                strArr = null;
            }
            if (strArr != null) {
                FileUtils fileUtils = FileUtils.INSTANCE;
                String path2 = this.$dst.getPath();
                Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                z = fileUtils.zip(strArr, path2);
            }
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
