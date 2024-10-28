package org.videolan.vlc.gui.preferences.search;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
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
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.search.PreferenceParser$restoreSettings$2", f = "PreferenceParser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferenceParser.kt */
final class PreferenceParser$restoreSettings$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Uri $file;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceParser$restoreSettings$2(Uri uri, Activity activity, Continuation<? super PreferenceParser$restoreSettings$2> continuation) {
        super(2, continuation);
        this.$file = uri;
        this.$activity = activity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferenceParser$restoreSettings$2(this.$file, this.$activity, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferenceParser$restoreSettings$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String path = this.$file.getPath();
            if (path == null) {
                return null;
            }
            Activity activity = this.$activity;
            Map map = (Map) new Moshi.Builder().build().adapter((Type) Types.newParameterizedType(Map.class, String.class, Object.class)).fromJson(FileUtils.INSTANCE.getStringFromFile(path));
            SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(activity);
            ArrayList<PreferenceItem> parsePreferences = PreferenceParser.INSTANCE.parsePreferences((Context) activity, true);
            if (map == null) {
                return null;
            }
            for (Map.Entry entry : map.entrySet()) {
                for (PreferenceItem preferenceItem : parsePreferences) {
                    if (Intrinsics.areEqual((Object) preferenceItem.getKey(), entry.getKey()) && !Intrinsics.areEqual((Object) preferenceItem.getKey(), (Object) "custom_libvlc_options")) {
                        Log.i("PrefParser", "Restored: " + ((String) entry.getKey()) + " -> " + entry.getValue());
                        String str = (String) entry.getKey();
                        if (entry.getValue() instanceof Double) {
                            Object value = entry.getValue();
                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Double");
                            obj2 = Boxing.boxInt((int) ((Double) value).doubleValue());
                        } else {
                            obj2 = entry.getValue();
                        }
                        SettingsKt.putSingle(sharedPreferences, str, obj2);
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
