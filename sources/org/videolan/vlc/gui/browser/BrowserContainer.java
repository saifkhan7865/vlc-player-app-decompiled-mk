package org.videolan.vlc.gui.browser;

import android.app.Activity;
import kotlin.Metadata;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\b\u0010\u0012\u001a\u00020\u0013H&J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&R\u0018\u0010\u0003\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006R\u0012\u0010\n\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0006¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/browser/BrowserContainer;", "T", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "inCards", "", "getInCards", "()Z", "setInCards", "(Z)V", "isFile", "isNetwork", "isRootDirectory", "mrl", "", "getMrl", "()Ljava/lang/String;", "scannedDirectory", "getScannedDirectory", "containerActivity", "Landroid/app/Activity;", "getStorageDelegate", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserContainer.kt */
public interface BrowserContainer<T> extends IEventsHandler<T> {
    Activity containerActivity();

    boolean getInCards();

    String getMrl();

    boolean getScannedDirectory();

    IStorageFragmentDelegate getStorageDelegate();

    boolean isFile();

    boolean isNetwork();

    boolean isRootDirectory();

    void setInCards(boolean z);
}
