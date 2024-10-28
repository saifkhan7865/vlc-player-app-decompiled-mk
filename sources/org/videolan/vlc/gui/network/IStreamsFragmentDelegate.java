package org.videolan.vlc.gui.network;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J \u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/network/IStreamsFragmentDelegate;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "getlistEventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/network/MrlAction;", "playMedia", "", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setup", "fragment", "Landroidx/fragment/app/Fragment;", "viewModel", "Lorg/videolan/vlc/viewmodels/StreamsModel;", "keyboardListener", "Lorg/videolan/vlc/gui/network/KeyboardListener;", "showContext", "position", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StreamsFragmentDelegate.kt */
public interface IStreamsFragmentDelegate extends CtxActionReceiver {
    SendChannel<MrlAction> getlistEventActor();

    void playMedia(MediaWrapper mediaWrapper);

    void setup(Fragment fragment, StreamsModel streamsModel, KeyboardListener keyboardListener);

    void showContext(int i);
}
