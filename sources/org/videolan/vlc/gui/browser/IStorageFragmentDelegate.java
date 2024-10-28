package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import androidx.collection.SimpleArrayMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J@\u0010\b\u001a\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\t0\u000bH&J\b\u0010\u0011\u001a\u00020\tH&J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H&J\b\u0010\u0016\u001a\u00020\tH&J\u001b\u0010\u0017\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H&¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001eH&R\u001e\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/gui/browser/IStorageFragmentDelegate;", "", "processingFolders", "Landroidx/collection/SimpleArrayMap;", "", "Landroid/widget/CheckBox;", "getProcessingFolders", "()Landroidx/collection/SimpleArrayMap;", "addBannedFoldersCallback", "", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "folder", "", "banned", "addRootsCallback", "checkBoxAction", "v", "Landroid/view/View;", "mrl", "removeRootsCallback", "withAdapters", "adapters", "", "Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;", "([Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;)V", "withContext", "context", "Landroid/content/Context;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageFragmentDelegate.kt */
public interface IStorageFragmentDelegate {
    void addBannedFoldersCallback(Function2<? super String, ? super Boolean, Unit> function2);

    void addRootsCallback();

    void checkBoxAction(View view, String str);

    SimpleArrayMap<String, CheckBox> getProcessingFolders();

    void removeRootsCallback();

    void withAdapters(StorageBrowserAdapter[] storageBrowserAdapterArr);

    void withContext(Context context);
}
