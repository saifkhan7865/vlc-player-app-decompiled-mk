package org.videolan.vlc.gui.helpers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.fragment.app.FragmentActivity;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/net/Uri;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2$uri$1", f = "AudioUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioUtil.kt */
final class AudioUtil$setRingtone$2$uri$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Uri>, Object> {
    final /* synthetic */ File $newRingtone;
    final /* synthetic */ FragmentActivity $this_setRingtone;
    final /* synthetic */ ContentValues $values;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioUtil$setRingtone$2$uri$1(File file, FragmentActivity fragmentActivity, ContentValues contentValues, Continuation<? super AudioUtil$setRingtone$2$uri$1> continuation) {
        super(2, continuation);
        this.$newRingtone = file;
        this.$this_setRingtone = fragmentActivity;
        this.$values = contentValues;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioUtil$setRingtone$2$uri$1(this.$newRingtone, this.$this_setRingtone, this.$values, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Uri> continuation) {
        return ((AudioUtil$setRingtone$2$uri$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Uri contentUriForPath = MediaStore.Audio.Media.getContentUriForPath(this.$newRingtone.getAbsolutePath());
            if (contentUriForPath == null) {
                return null;
            }
            FragmentActivity fragmentActivity = this.$this_setRingtone;
            File file = this.$newRingtone;
            ContentValues contentValues = this.$values;
            ContentResolver contentResolver = fragmentActivity.getContentResolver();
            contentResolver.delete(contentUriForPath, "_data=\"" + file.getAbsolutePath() + '\"', (String[]) null);
            return fragmentActivity.getContentResolver().insert(contentUriForPath, contentValues);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
