package org.videolan.vlc.gui.preferences.search;

import android.app.Activity;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.tools.CloseableUtils;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.search.PreferenceParser$exportPreferences$2", f = "PreferenceParser.kt", i = {}, l = {241}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferenceParser.kt */
final class PreferenceParser$exportPreferences$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ File $dst;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceParser$exportPreferences$2(Activity activity, File file, Continuation<? super PreferenceParser$exportPreferences$2> continuation) {
        super(2, continuation);
        this.$activity = activity;
        this.$dst = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferenceParser$exportPreferences$2(this.$activity, this.$dst, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferenceParser$exportPreferences$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        CloseableUtils closeableUtils;
        Closeable closeable;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String access$getChangedPrefsJson = PreferenceParser.INSTANCE.getChangedPrefsJson(this.$activity);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.$dst));
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                try {
                    bufferedWriter.write(access$getChangedPrefsJson);
                    booleanRef.element = true;
                    CloseableUtils.INSTANCE.close(bufferedWriter);
                    closeableUtils = CloseableUtils.INSTANCE;
                    closeable = outputStreamWriter;
                } catch (IOException e) {
                    e.printStackTrace();
                    CloseableUtils.INSTANCE.close(bufferedWriter);
                    closeableUtils = CloseableUtils.INSTANCE;
                    closeable = outputStreamWriter;
                }
                closeableUtils.close(closeable);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                CloseableUtils.INSTANCE.close(bufferedWriter);
                CloseableUtils.INSTANCE.close(outputStreamWriter);
                throw th;
            }
            final Activity activity = this.$activity;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.preferences.search.PreferenceParser$exportPreferences$2$1", f = "PreferenceParser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.preferences.search.PreferenceParser$exportPreferences$2$1  reason: invalid class name */
    /* compiled from: PreferenceParser.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(booleanRef, activity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (booleanRef.element) {
                    Toast.makeText(activity, R.string.export_settings_success, 1).show();
                } else {
                    Toast.makeText(activity, R.string.export_settings_failure, 1).show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
