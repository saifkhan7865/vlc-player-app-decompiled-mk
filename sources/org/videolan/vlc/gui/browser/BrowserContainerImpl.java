package org.videolan.vlc.gui.browser;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ%\u0010 \u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010!\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"J%\u0010#\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010$J%\u0010%\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u0014\u0010&\u001a\u00020\u00182\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(H\u0016R\u001a\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\t\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\rR\u0014\u0010\b\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006)"}, d2 = {"Lorg/videolan/vlc/gui/browser/BrowserContainerImpl;", "T", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "scannedDirectory", "", "mrl", "", "isRootDirectory", "isNetwork", "isFile", "inCards", "(ZLjava/lang/String;ZZZZ)V", "getInCards", "()Z", "setInCards", "(Z)V", "getMrl", "()Ljava/lang/String;", "getScannedDirectory", "containerActivity", "", "getStorageDelegate", "Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "onClick", "", "v", "Landroid/view/View;", "position", "", "item", "(Landroid/view/View;ILjava/lang/Object;)V", "onCtxClick", "onImageClick", "onItemFocused", "(Landroid/view/View;Ljava/lang/Object;)V", "onLongClick", "(Landroid/view/View;ILjava/lang/Object;)Z", "onMainActionClick", "onUpdateFinished", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserContainer.kt */
public final class BrowserContainerImpl<T> implements BrowserContainer<T> {
    private boolean inCards;
    private final boolean isFile;
    private final boolean isNetwork;
    private final boolean isRootDirectory;
    private final String mrl;
    private final boolean scannedDirectory;

    public IStorageFragmentDelegate getStorageDelegate() {
        return null;
    }

    public void onClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onCtxClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onImageClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onItemFocused(View view, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public boolean onLongClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
        return false;
    }

    public void onMainActionClick(View view, int i, T t) {
        Intrinsics.checkNotNullParameter(view, "v");
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
    }

    public BrowserContainerImpl(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.scannedDirectory = z;
        this.mrl = str;
        this.isRootDirectory = z2;
        this.isNetwork = z3;
        this.isFile = z4;
        this.inCards = z5;
    }

    public boolean getScannedDirectory() {
        return this.scannedDirectory;
    }

    public String getMrl() {
        return this.mrl;
    }

    public boolean isRootDirectory() {
        return this.isRootDirectory;
    }

    public boolean isNetwork() {
        return this.isNetwork;
    }

    public boolean isFile() {
        return this.isFile;
    }

    public boolean getInCards() {
        return this.inCards;
    }

    public void setInCards(boolean z) {
        this.inCards = z;
    }

    public Void containerActivity() {
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }
}
