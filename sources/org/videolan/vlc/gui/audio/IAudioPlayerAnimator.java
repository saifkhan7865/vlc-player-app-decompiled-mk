package org.videolan.vlc.gui.audio;

import androidx.window.layout.FoldingFeature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.videolan.vlc.databinding.AudioPlayerBinding;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\tH&J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH&J\b\u0010\u0013\u001a\u00020\u000bH&J\u000e\u0010\u0014\u001a\u00020\u000bH¦@¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u000b*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/gui/audio/IAudioPlayerAnimator;", "", "foldingFeature", "Landroidx/window/layout/FoldingFeature;", "getFoldingFeature", "()Landroidx/window/layout/FoldingFeature;", "setFoldingFeature", "(Landroidx/window/layout/FoldingFeature;)V", "isShowingCover", "", "manageHinge", "", "manageSearchVisibilities", "filter", "onSlide", "slideOffset", "", "showCover", "value", "switchShowCover", "updateBackground", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupAnimator", "Lorg/videolan/vlc/gui/audio/AudioPlayer;", "binding", "Lorg/videolan/vlc/databinding/AudioPlayerBinding;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerAnimator.kt */
public interface IAudioPlayerAnimator {
    FoldingFeature getFoldingFeature();

    boolean isShowingCover();

    void manageHinge();

    void manageSearchVisibilities(boolean z);

    void onSlide(float f);

    void setFoldingFeature(FoldingFeature foldingFeature);

    void setupAnimator(AudioPlayer audioPlayer, AudioPlayerBinding audioPlayerBinding);

    void showCover(boolean z);

    void switchShowCover();

    Object updateBackground(Continuation<? super Unit> continuation);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioPlayerAnimator.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void manageSearchVisibilities$default(IAudioPlayerAnimator iAudioPlayerAnimator, boolean z, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    z = false;
                }
                iAudioPlayerAnimator.manageSearchVisibilities(z);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: manageSearchVisibilities");
        }
    }
}
