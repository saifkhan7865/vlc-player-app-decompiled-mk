package org.videolan.vlc.gui.helpers;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2", f = "AudioUtil.kt", i = {0}, l = {66, 105}, m = "invokeSuspend", n = {"newRingtone"}, s = {"L$0"})
/* compiled from: AudioUtil.kt */
final class AudioUtil$setRingtone$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $song;
    final /* synthetic */ FragmentActivity $this_setRingtone;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioUtil$setRingtone$2(MediaWrapper mediaWrapper, FragmentActivity fragmentActivity, Continuation<? super AudioUtil$setRingtone$2> continuation) {
        super(1, continuation);
        this.$song = mediaWrapper;
        this.$this_setRingtone = fragmentActivity;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AudioUtil$setRingtone$2(this.$song, this.$this_setRingtone, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((AudioUtil$setRingtone$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0164, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0168, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 0
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0028
            if (r1 == r4) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ Exception -> 0x0015 }
            goto L_0x018f
        L_0x0015:
            r12 = move-exception
            goto L_0x01be
        L_0x0018:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0020:
            java.lang.Object r1 = r11.L$0
            java.io.File r1 = (java.io.File) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0050
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r12)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = r11.$song
            android.net.Uri r12 = r12.getUri()
            java.io.File r1 = org.videolan.libvlc.util.AndroidUtil.UriToFile(r12)
            kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2$1 r6 = new org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2$1
            r6.<init>(r1, r3)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = r11
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r11.L$0 = r1
            r11.label = r4
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r6, r7)
            if (r12 != r0) goto L_0x0050
            return r0
        L_0x0050:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x0072
            androidx.fragment.app.FragmentActivity r12 = r11.$this_setRingtone
            android.content.Context r12 = r12.getApplicationContext()
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone
            int r1 = org.videolan.vlc.R.string.ringtone_error
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            android.widget.Toast r12 = android.widget.Toast.makeText(r12, r0, r5)
            r12.show()
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0072:
            android.webkit.MimeTypeMap r12 = android.webkit.MimeTypeMap.getSingleton()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r11.$song
            android.net.Uri r6 = r6.getUri()
            java.lang.String r6 = r6.getPath()
            java.lang.String r6 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r6)
            java.lang.String r12 = r12.getMimeTypeFromExtension(r6)
            r6 = 7
            kotlin.Pair[] r6 = new kotlin.Pair[r6]
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r11.$song
            java.lang.String r7 = r7.getTitle()
            java.lang.String r8 = "title"
            kotlin.Pair r7 = kotlin.TuplesKt.to(r8, r7)
            r6[r5] = r7
            java.lang.String r7 = "mime_type"
            kotlin.Pair r12 = kotlin.TuplesKt.to(r7, r12)
            r6[r4] = r12
            org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = r11.$song
            java.lang.String r12 = r12.getArtist()
            java.lang.String r7 = "artist"
            kotlin.Pair r12 = kotlin.TuplesKt.to(r7, r12)
            r6[r2] = r12
            java.lang.String r12 = "is_ringtone"
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            kotlin.Pair r12 = kotlin.TuplesKt.to(r12, r7)
            r7 = 3
            r6[r7] = r12
            java.lang.String r12 = "is_notification"
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            kotlin.Pair r12 = kotlin.TuplesKt.to(r12, r7)
            r7 = 4
            r6[r7] = r12
            java.lang.String r12 = "is_alarm"
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            kotlin.Pair r12 = kotlin.TuplesKt.to(r12, r7)
            r7 = 5
            r6[r7] = r12
            java.lang.String r12 = "is_music"
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            kotlin.Pair r12 = kotlin.TuplesKt.to(r12, r7)
            r7 = 6
            r6[r7] = r12
            android.content.ContentValues r12 = androidx.core.content.ContentValuesKt.contentValuesOf(r6)
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0015 }
            r7 = 29
            if (r6 < r7) goto L_0x0169
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone     // Catch:{ Exception -> 0x0015 }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x0015 }
            android.net.Uri r2 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI     // Catch:{ Exception -> 0x0015 }
            android.net.Uri r12 = r0.insert(r2, r12)     // Catch:{ Exception -> 0x0015 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)     // Catch:{ Exception -> 0x0015 }
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone     // Catch:{ Exception -> 0x0015 }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x0015 }
            java.io.OutputStream r0 = r0.openOutputStream(r12)     // Catch:{ Exception -> 0x0015 }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ Exception -> 0x0015 }
            r2 = r0
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x0162 }
            java.lang.Runtime r6 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x0162 }
            long r7 = r6.maxMemory()     // Catch:{ all -> 0x0162 }
            long r9 = r6.totalMemory()     // Catch:{ all -> 0x0162 }
            long r7 = r7 - r9
            long r9 = r6.freeMemory()     // Catch:{ all -> 0x0162 }
            long r7 = r7 - r9
            long r9 = r1.length()     // Catch:{ all -> 0x0162 }
            int r6 = (int) r9     // Catch:{ all -> 0x0162 }
            float r9 = (float) r6     // Catch:{ all -> 0x0162 }
            float r7 = (float) r7     // Catch:{ all -> 0x0162 }
            r8 = 1061997773(0x3f4ccccd, float:0.8)
            float r7 = r7 * r8
            int r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r7 > 0) goto L_0x015a
            byte[] r7 = new byte[r6]     // Catch:{ all -> 0x0162 }
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0162 }
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ all -> 0x0162 }
            r9.<init>(r1)     // Catch:{ all -> 0x0162 }
            java.io.InputStream r9 = (java.io.InputStream) r9     // Catch:{ all -> 0x0162 }
            r8.<init>(r9)     // Catch:{ all -> 0x0162 }
            r8.read(r7, r5, r6)     // Catch:{ all -> 0x0162 }
            r8.close()     // Catch:{ all -> 0x0162 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0162 }
            r2.write(r7)     // Catch:{ all -> 0x0162 }
            r2.close()     // Catch:{ all -> 0x0162 }
            r2.flush()     // Catch:{ all -> 0x0162 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0162 }
            kotlin.io.CloseableKt.closeFinally(r0, r3)     // Catch:{ Exception -> 0x0015 }
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone     // Catch:{ Exception -> 0x0015 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ Exception -> 0x0015 }
            android.media.RingtoneManager.setActualDefaultRingtoneUri(r0, r4, r12)     // Catch:{ Exception -> 0x0015 }
            goto L_0x019a
        L_0x015a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0162 }
            java.lang.String r1 = "Not enough memory"
            r12.<init>(r1)     // Catch:{ all -> 0x0162 }
            throw r12     // Catch:{ all -> 0x0162 }
        L_0x0162:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x0164 }
        L_0x0164:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r12)     // Catch:{ Exception -> 0x0015 }
            throw r1     // Catch:{ Exception -> 0x0015 }
        L_0x0169:
            java.lang.String r6 = "_data"
            java.lang.String r7 = r1.getAbsolutePath()     // Catch:{ Exception -> 0x0015 }
            r12.put(r6, r7)     // Catch:{ Exception -> 0x0015 }
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ Exception -> 0x0015 }
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6     // Catch:{ Exception -> 0x0015 }
            org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2$uri$1 r7 = new org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2$uri$1     // Catch:{ Exception -> 0x0015 }
            androidx.fragment.app.FragmentActivity r8 = r11.$this_setRingtone     // Catch:{ Exception -> 0x0015 }
            r7.<init>(r1, r8, r12, r3)     // Catch:{ Exception -> 0x0015 }
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch:{ Exception -> 0x0015 }
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12     // Catch:{ Exception -> 0x0015 }
            r11.L$0 = r3     // Catch:{ Exception -> 0x0015 }
            r11.label = r2     // Catch:{ Exception -> 0x0015 }
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r12)     // Catch:{ Exception -> 0x0015 }
            if (r12 != r0) goto L_0x018f
            return r0
        L_0x018f:
            android.net.Uri r12 = (android.net.Uri) r12     // Catch:{ Exception -> 0x0015 }
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone     // Catch:{ Exception -> 0x0015 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ Exception -> 0x0015 }
            android.media.RingtoneManager.setActualDefaultRingtoneUri(r0, r4, r12)     // Catch:{ Exception -> 0x0015 }
        L_0x019a:
            androidx.fragment.app.FragmentActivity r12 = r11.$this_setRingtone
            android.content.Context r12 = r12.getApplicationContext()
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone
            int r1 = org.videolan.vlc.R.string.ringtone_set
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r11.$song
            java.lang.String r2 = r2.getTitle()
            java.lang.Object[] r3 = new java.lang.Object[r4]
            r3[r5] = r2
            java.lang.String r0 = r0.getString(r1, r3)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            android.widget.Toast r12 = android.widget.Toast.makeText(r12, r0, r5)
            r12.show()
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x01be:
            java.lang.String r0 = "error setting ringtone"
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.String r1 = "VLC/AudioUtil"
            android.util.Log.e(r1, r0, r12)
            androidx.fragment.app.FragmentActivity r12 = r11.$this_setRingtone
            android.content.Context r12 = r12.getApplicationContext()
            androidx.fragment.app.FragmentActivity r0 = r11.$this_setRingtone
            int r1 = org.videolan.vlc.R.string.ringtone_error
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            android.widget.Toast r12 = android.widget.Toast.makeText(r12, r0, r5)
            r12.show()
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.AudioUtil$setRingtone$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
