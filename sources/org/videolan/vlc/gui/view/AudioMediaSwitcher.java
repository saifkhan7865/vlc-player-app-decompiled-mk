package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002&'B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JJ\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\nH$J\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\nJ\u0006\u0010\u001f\u001a\u00020\u0012J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\bJ\u0018\u0010\"\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010$H@¢\u0006\u0002\u0010%R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006("}, d2 = {"Lorg/videolan/vlc/gui/view/AudioMediaSwitcher;", "Lorg/videolan/vlc/gui/view/FlingViewGroup;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "audioMediaSwitcherListener", "Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$AudioMediaSwitcherListener;", "hasPrevious", "", "previousPosition", "", "viewSwitchListener", "Lorg/videolan/vlc/gui/view/ViewSwitchListener;", "getViewSwitchListener", "()Lorg/videolan/vlc/gui/view/ViewSwitchListener;", "addMediaView", "", "inflater", "Landroid/view/LayoutInflater;", "title", "", "artist", "album", "cover", "Landroid/graphics/Bitmap;", "trackInfo", "hasChapters", "onChapterSwitching", "next", "onTextClicked", "setAudioMediaSwitcherListener", "l", "updateMedia", "service", "Lorg/videolan/vlc/PlaybackService;", "(Lorg/videolan/vlc/PlaybackService;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AudioMediaSwitcherListener", "EmptySwitcherListener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioMediaSwitcher.kt */
public abstract class AudioMediaSwitcher extends FlingViewGroup {
    /* access modifiers changed from: private */
    public AudioMediaSwitcherListener audioMediaSwitcherListener;
    /* access modifiers changed from: private */
    public boolean hasPrevious;
    /* access modifiers changed from: private */
    public int previousPosition;
    private final ViewSwitchListener viewSwitchListener = new AudioMediaSwitcher$viewSwitchListener$1(this);

    /* access modifiers changed from: protected */
    public abstract void addMediaView(LayoutInflater layoutInflater, String str, String str2, String str3, Bitmap bitmap, String str4, boolean z);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AudioMediaSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    public ViewSwitchListener getViewSwitchListener() {
        return this.viewSwitchListener;
    }

    public final void onTextClicked() {
        AudioMediaSwitcherListener audioMediaSwitcherListener2 = this.audioMediaSwitcherListener;
        if (audioMediaSwitcherListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            audioMediaSwitcherListener2 = null;
        }
        audioMediaSwitcherListener2.onTextClicked();
    }

    public final void onChapterSwitching(boolean z) {
        AudioMediaSwitcherListener audioMediaSwitcherListener2 = this.audioMediaSwitcherListener;
        if (audioMediaSwitcherListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            audioMediaSwitcherListener2 = null;
        }
        audioMediaSwitcherListener2.onChapterSwitching(z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00fe A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x011b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x013e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateMedia(org.videolan.vlc.PlaybackService r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            boolean r3 = r2 instanceof org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$1 r3 = (org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$1 r3 = new org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r5 == 0) goto L_0x00a7
            if (r5 == r9) goto L_0x009a
            if (r5 == r8) goto L_0x0082
            if (r5 == r7) goto L_0x0065
            if (r5 != r6) goto L_0x005d
            java.lang.Object r1 = r3.L$6
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r4 = r3.L$5
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r3.L$4
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            java.lang.Object r6 = r3.L$3
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            java.lang.Object r10 = r3.L$2
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            java.lang.Object r11 = r3.L$1
            org.videolan.vlc.PlaybackService r11 = (org.videolan.vlc.PlaybackService) r11
            java.lang.Object r3 = r3.L$0
            org.videolan.vlc.gui.view.AudioMediaSwitcher r3 = (org.videolan.vlc.gui.view.AudioMediaSwitcher) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r20 = r1
            r19 = r6
            r1 = r11
            r6 = r4
            r4 = r10
            goto L_0x0148
        L_0x005d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0065:
            java.lang.Object r1 = r3.L$5
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r5 = r3.L$4
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            java.lang.Object r10 = r3.L$3
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            java.lang.Object r11 = r3.L$2
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            java.lang.Object r12 = r3.L$1
            org.videolan.vlc.PlaybackService r12 = (org.videolan.vlc.PlaybackService) r12
            java.lang.Object r13 = r3.L$0
            org.videolan.vlc.gui.view.AudioMediaSwitcher r13 = (org.videolan.vlc.gui.view.AudioMediaSwitcher) r13
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0126
        L_0x0082:
            java.lang.Object r1 = r3.L$4
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            java.lang.Object r5 = r3.L$3
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            java.lang.Object r10 = r3.L$2
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            java.lang.Object r11 = r3.L$1
            org.videolan.vlc.PlaybackService r11 = (org.videolan.vlc.PlaybackService) r11
            java.lang.Object r12 = r3.L$0
            org.videolan.vlc.gui.view.AudioMediaSwitcher r12 = (org.videolan.vlc.gui.view.AudioMediaSwitcher) r12
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0105
        L_0x009a:
            java.lang.Object r1 = r3.L$1
            org.videolan.vlc.PlaybackService r1 = (org.videolan.vlc.PlaybackService) r1
            java.lang.Object r5 = r3.L$0
            org.videolan.vlc.gui.view.AudioMediaSwitcher r5 = (org.videolan.vlc.gui.view.AudioMediaSwitcher) r5
            kotlin.ResultKt.throwOnFailure(r2)
            r12 = r5
            goto L_0x00d7
        L_0x00a7:
            kotlin.ResultKt.throwOnFailure(r2)
            if (r1 != 0) goto L_0x00af
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x00af:
            java.lang.String r2 = r25.getCoverArt()
            java.lang.String r5 = r25.getPrevCoverArt()
            java.lang.String r10 = r25.getNextCoverArt()
            kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$2 r12 = new org.videolan.vlc.gui.view.AudioMediaSwitcher$updateMedia$2
            r13 = 0
            r12.<init>(r2, r5, r10, r13)
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r3.L$0 = r0
            r3.L$1 = r1
            r3.label = r9
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r11, r12, r3)
            if (r2 != r4) goto L_0x00d6
            return r4
        L_0x00d6:
            r12 = r0
        L_0x00d7:
            kotlin.Triple r2 = (kotlin.Triple) r2
            java.lang.Object r5 = r2.component1()
            r10 = r5
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            java.lang.Object r5 = r2.component2()
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            java.lang.Object r2 = r2.component3()
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            r3.L$0 = r12
            r3.L$1 = r1
            r3.L$2 = r10
            r3.L$3 = r5
            r3.L$4 = r2
            r3.label = r8
            java.lang.Object r11 = r1.trackInfo(r3)
            if (r11 != r4) goto L_0x00ff
            return r4
        L_0x00ff:
            r23 = r11
            r11 = r1
            r1 = r2
            r2 = r23
        L_0x0105:
            java.lang.String r2 = (java.lang.String) r2
            r3.L$0 = r12
            r3.L$1 = r11
            r3.L$2 = r10
            r3.L$3 = r5
            r3.L$4 = r1
            r3.L$5 = r2
            r3.label = r7
            java.lang.Object r13 = r11.prevTrackInfo(r3)
            if (r13 != r4) goto L_0x011c
            return r4
        L_0x011c:
            r23 = r5
            r5 = r1
            r1 = r2
            r2 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r23
        L_0x0126:
            java.lang.String r2 = (java.lang.String) r2
            r3.L$0 = r13
            r3.L$1 = r12
            r3.L$2 = r11
            r3.L$3 = r10
            r3.L$4 = r5
            r3.L$5 = r1
            r3.L$6 = r2
            r3.label = r6
            java.lang.Object r3 = r12.nextTrackInfo(r3)
            if (r3 != r4) goto L_0x013f
            return r4
        L_0x013f:
            r6 = r1
            r20 = r2
            r2 = r3
            r19 = r10
            r4 = r11
            r1 = r12
            r3 = r13
        L_0x0148:
            java.lang.String r2 = (java.lang.String) r2
            r3.removeAllViews()
            r13 = 0
            r3.hasPrevious = r13
            r3.previousPosition = r13
            android.content.Context r10 = r3.getContext()
            android.view.LayoutInflater r22 = android.view.LayoutInflater.from(r10)
            boolean r10 = r1.hasPrevious()
            if (r10 == 0) goto L_0x0179
            kotlin.jvm.internal.Intrinsics.checkNotNull(r22)
            java.lang.String r16 = r1.getTitlePrev()
            java.lang.String r17 = r1.getArtistPrev()
            java.lang.String r18 = r1.getAlbumPrev()
            r21 = 0
            r14 = r3
            r15 = r22
            r14.addMediaView(r15, r16, r17, r18, r19, r20, r21)
            r3.hasPrevious = r9
        L_0x0179:
            java.lang.String r10 = r1.getCurrentChapter()
            r11 = r10
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            if (r11 == 0) goto L_0x019a
            int r12 = r11.length()
            if (r12 != 0) goto L_0x0189
            goto L_0x019a
        L_0x0189:
            java.lang.String[] r7 = new java.lang.String[r7]
            r7[r13] = r10
            java.lang.String r10 = r1.getTitle()
            r7[r9] = r10
            java.lang.String r10 = r1.getArtist()
            r7[r8] = r10
            goto L_0x01ae
        L_0x019a:
            java.lang.String[] r7 = new java.lang.String[r7]
            java.lang.String r10 = r1.getTitle()
            r7[r13] = r10
            java.lang.String r10 = r1.getArtist()
            r7[r9] = r10
            java.lang.String r10 = r1.getAlbum()
            r7[r8] = r10
        L_0x01ae:
            r12 = r7[r13]
            r14 = r7[r9]
            r7 = r7[r8]
            boolean r8 = r1.hasMedia()
            if (r8 == 0) goto L_0x01d8
            kotlin.jvm.internal.Intrinsics.checkNotNull(r22)
            if (r11 == 0) goto L_0x01c8
            int r8 = r11.length()
            if (r8 != 0) goto L_0x01c6
            goto L_0x01c8
        L_0x01c6:
            r8 = 0
            goto L_0x01c9
        L_0x01c8:
            r8 = 1
        L_0x01c9:
            r17 = r8 ^ 1
            r10 = r3
            r11 = r22
            r8 = 0
            r13 = r14
            r14 = r7
            r15 = r4
            r16 = r6
            r10.addMediaView(r11, r12, r13, r14, r15, r16, r17)
            goto L_0x01d9
        L_0x01d8:
            r8 = 0
        L_0x01d9:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x01f9
            kotlin.jvm.internal.Intrinsics.checkNotNull(r22)
            java.lang.String r12 = r1.getTitleNext()
            java.lang.String r13 = r1.getArtistNext()
            java.lang.String r14 = r1.getAlbumNext()
            r17 = 0
            r10 = r3
            r11 = r22
            r15 = r5
            r16 = r2
            r10.addMediaView(r11, r12, r13, r14, r15, r16, r17)
        L_0x01f9:
            boolean r2 = r1.hasPrevious()
            if (r2 == 0) goto L_0x020b
            boolean r1 = r1.hasMedia()
            if (r1 == 0) goto L_0x020b
            r3.previousPosition = r9
            r3.scrollTo(r9)
            goto L_0x020e
        L_0x020b:
            r3.scrollTo(r8)
        L_0x020e:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.AudioMediaSwitcher.updateMedia(org.videolan.vlc.PlaybackService, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setAudioMediaSwitcherListener(AudioMediaSwitcherListener audioMediaSwitcherListener2) {
        Intrinsics.checkNotNullParameter(audioMediaSwitcherListener2, "l");
        this.audioMediaSwitcherListener = audioMediaSwitcherListener2;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$AudioMediaSwitcherListener;", "", "onChapterSwitching", "", "next", "", "onMediaSwitched", "position", "", "onMediaSwitching", "onTextClicked", "onTouchClick", "onTouchDown", "onTouchLongClick", "onTouchUp", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioMediaSwitcher.kt */
    public interface AudioMediaSwitcherListener {
        public static final int CURRENT_MEDIA = 2;
        public static final Companion Companion = Companion.$$INSTANCE;
        public static final int NEXT_MEDIA = 3;
        public static final int PREVIOUS_MEDIA = 1;

        void onChapterSwitching(boolean z);

        void onMediaSwitched(int i);

        void onMediaSwitching();

        void onTextClicked();

        void onTouchClick();

        void onTouchDown();

        void onTouchLongClick();

        void onTouchUp();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$AudioMediaSwitcherListener$Companion;", "", "()V", "CURRENT_MEDIA", "", "NEXT_MEDIA", "PREVIOUS_MEDIA", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: AudioMediaSwitcher.kt */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int CURRENT_MEDIA = 2;
            public static final int NEXT_MEDIA = 3;
            public static final int PREVIOUS_MEDIA = 1;

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$EmptySwitcherListener;", "Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$AudioMediaSwitcherListener;", "()V", "onChapterSwitching", "", "next", "", "onMediaSwitched", "position", "", "onMediaSwitching", "onTextClicked", "onTouchClick", "onTouchDown", "onTouchLongClick", "onTouchUp", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioMediaSwitcher.kt */
    public static final class EmptySwitcherListener implements AudioMediaSwitcherListener {
        public static final EmptySwitcherListener INSTANCE = new EmptySwitcherListener();

        public void onChapterSwitching(boolean z) {
        }

        public void onMediaSwitched(int i) {
        }

        public void onMediaSwitching() {
        }

        public void onTextClicked() {
        }

        public void onTouchClick() {
        }

        public void onTouchDown() {
        }

        public void onTouchLongClick() {
        }

        public void onTouchUp() {
        }

        private EmptySwitcherListener() {
        }
    }
}
