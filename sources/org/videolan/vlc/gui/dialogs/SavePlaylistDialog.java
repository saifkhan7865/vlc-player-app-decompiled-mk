package org.videolan.vlc.gui.dialogs;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.AppScope;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.DependencyProvider;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogPlaylistBinding;
import org.videolan.vlc.gui.SimpleAdapter;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 U2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001UB\u0005¢\u0006\u0002\u0010\u0005J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u000201H\u0016J/\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002¢\u0006\u0002\u00103J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u00020\u0015H\u0016J\u0010\u00107\u001a\u00020/2\u0006\u00108\u001a\u000205H\u0016J\u0018\u00107\u001a\u00020/2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u000201H\u0016J\u0012\u0010;\u001a\u00020/2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J&\u0010>\u001a\u0004\u0018\u0001052\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\"\u0010C\u001a\u00020\u00152\u0006\u00108\u001a\u00020D2\u0006\u0010E\u001a\u0002012\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u0010\u0010H\u001a\u00020/2\u0006\u0010I\u001a\u00020=H\u0016J\u001a\u0010J\u001a\u00020/2\u0006\u0010K\u001a\u0002052\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u001b\u0010L\u001a\u00020/2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002¢\u0006\u0002\u0010#J#\u0010N\u001a\u00020/2\u0006\u0010O\u001a\u00020(2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002¢\u0006\u0002\u0010PJ\u0016\u0010Q\u001a\u00020/2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u000f0SH\u0002J\b\u0010T\u001a\u00020/H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0015@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX.¢\u0006\u0004\n\u0002\u0010\u001eR$\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R.\u0010&\u001a\u0016\u0012\u0004\u0012\u00020(\u0018\u00010'j\n\u0012\u0004\u0012\u00020(\u0018\u0001`)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006V"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SavePlaylistDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Landroid/view/View$OnClickListener;", "Landroid/widget/TextView$OnEditorActionListener;", "Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;", "()V", "adapter", "Lorg/videolan/vlc/gui/SimpleAdapter;", "alreadyAdding", "Ljava/util/concurrent/atomic/AtomicBoolean;", "binding", "Lorg/videolan/vlc/databinding/DialogPlaylistBinding;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "currentItem", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "value", "", "filesText", "setFilesText", "(Ljava/lang/String;)V", "", "isLoading", "setLoading", "(Z)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "newTracks", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "nonDuplicateTracks", "getNonDuplicateTracks", "()[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setNonDuplicateTracks", "([Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "playlistIterator", "", "selectedPlaylist", "Ljava/util/ArrayList;", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "Lkotlin/collections/ArrayList;", "getSelectedPlaylist", "()Ljava/util/ArrayList;", "setSelectedPlaylist", "(Ljava/util/ArrayList;)V", "addNewPlaylist", "", "getDefaultState", "", "currentTracks", "([Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onClick", "v", "item", "position", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onEditorAction", "Landroid/widget/TextView;", "actionId", "event", "Landroid/view/KeyEvent;", "onSaveInstanceState", "outState", "onViewCreated", "view", "processNextItem", "tracks", "savePlaylist", "playlist", "(Lorg/videolan/medialibrary/interfaces/media/Playlist;[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "saveToExistingPlaylists", "items", "", "updateEmptyView", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SavePlaylistDialog.kt */
public final class SavePlaylistDialog extends VLCBottomSheetDialogFragment implements View.OnClickListener, TextView.OnEditorActionListener, SimpleAdapter.ClickHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_DEFAULT_TITLE = "DEFAULT_TITLE";
    public static final String KEY_FOLDER = "PLAYLIST_FROM_FOLDER";
    public static final String KEY_NEW_TRACKS = "PLAYLIST_NEW_TRACKS";
    public static final String KEY_SUB_FOLDERS = "PLAYLIST_FOLDER_ADD_SUBFOLDERS";
    public static final String SELECTED_PLAYLIST = "SELECTED_PLAYLIST";
    public static final String TAG = "VLC/SavePlaylistDialog";
    /* access modifiers changed from: private */
    public SimpleAdapter adapter;
    /* access modifiers changed from: private */
    public final AtomicBoolean alreadyAdding = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public DialogPlaylistBinding binding;
    /* access modifiers changed from: private */
    public final CoroutineContextProvider coroutineContextProvider;
    private MediaLibraryItem currentItem;
    private String filesText = "";
    private boolean isLoading;
    /* access modifiers changed from: private */
    public Medialibrary medialibrary;
    /* access modifiers changed from: private */
    public MediaWrapper[] newTracks;
    private MediaWrapper[] nonDuplicateTracks;
    /* access modifiers changed from: private */
    public Iterator<? extends MediaLibraryItem> playlistIterator;
    private ArrayList<Playlist> selectedPlaylist;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public SavePlaylistDialog() {
        Companion companion = Companion;
        DependencyProvider dependencyProvider = companion;
        Function1 function1 = AnonymousClass1.INSTANCE;
        String name = CoroutineContextProvider.class.getName();
        if (dependencyProvider.getOverrideCreator() || !dependencyProvider.getCreatorMap().containsKey(name)) {
            dependencyProvider.getCreatorMap().put(name, function1);
        }
        if (dependencyProvider.getObjectMap().containsKey(name) && dependencyProvider.getOverrideCreator()) {
            dependencyProvider.getObjectMap().remove(name);
        }
        DependencyProvider dependencyProvider2 = companion;
        String name2 = CoroutineContextProvider.class.getName();
        if (!dependencyProvider2.getObjectMap().containsKey(name2)) {
            Map objectMap = dependencyProvider2.getObjectMap();
            Function1 function12 = (Function1) dependencyProvider2.getCreatorMap().get(name2);
            objectMap.put(name2, function12 != null ? function12.invoke(0) : null);
        }
        Object obj = dependencyProvider2.getObjectMap().get(name2);
        if (obj != null) {
            this.coroutineContextProvider = (CoroutineContextProvider) obj;
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type org.videolan.tools.CoroutineContextProvider");
    }

    public final ArrayList<Playlist> getSelectedPlaylist() {
        return this.selectedPlaylist;
    }

    public final void setSelectedPlaylist(ArrayList<Playlist> arrayList) {
        this.selectedPlaylist = arrayList;
    }

    public final MediaWrapper[] getNonDuplicateTracks() {
        return this.nonDuplicateTracks;
    }

    public final void setNonDuplicateTracks(MediaWrapper[] mediaWrapperArr) {
        this.nonDuplicateTracks = mediaWrapperArr;
    }

    /* access modifiers changed from: private */
    public final void setLoading(boolean z) {
        this.isLoading = z;
        DialogPlaylistBinding dialogPlaylistBinding = this.binding;
        if (dialogPlaylistBinding != null) {
            if (dialogPlaylistBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaylistBinding = null;
            }
            dialogPlaylistBinding.setIsLoading(Boolean.valueOf(z));
        }
    }

    /* access modifiers changed from: private */
    public final void setFilesText(String str) {
        this.filesText = str;
        DialogPlaylistBinding dialogPlaylistBinding = this.binding;
        if (dialogPlaylistBinding != null) {
            if (dialogPlaylistBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaylistBinding = null;
            }
            dialogPlaylistBinding.setFilesText(str);
        }
    }

    public View initialFocusedView() {
        DialogPlaylistBinding dialogPlaylistBinding = this.binding;
        DialogPlaylistBinding dialogPlaylistBinding2 = null;
        if (dialogPlaylistBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding = null;
        }
        View editText = dialogPlaylistBinding.dialogPlaylistName.getEditText();
        if (editText == null) {
            DialogPlaylistBinding dialogPlaylistBinding3 = this.binding;
            if (dialogPlaylistBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogPlaylistBinding2 = dialogPlaylistBinding3;
            }
            editText = dialogPlaylistBinding2.dialogPlaylistName;
            Intrinsics.checkNotNullExpressionValue(editText, "dialogPlaylistName");
        }
        return editText;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:7|8|(2:10|(1:12)(1:13))|14|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0081, code lost:
        setLoading(true);
        r2 = org.videolan.vlc.viewmodels.browser.BrowserModelKt.getBrowserModel$default(r0, 0, r4, false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x009c, code lost:
        if (requireArguments().getBoolean(KEY_SUB_FOLDERS, false) != false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x009e, code lost:
        androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r0).launchWhenStarted(new org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1(r0, r2, r4, (kotlin.coroutines.Continuation<? super org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1>) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00b0, code lost:
        r2.getDataset().observe(r0, new org.videolan.vlc.gui.dialogs.SavePlaylistDialogKt$sam$androidx_lifecycle_Observer$0(new org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$2(r0)));
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00ca, code lost:
        r2 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00cd, code lost:
        r2 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00cf, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r4 = requireArguments().getString(KEY_FOLDER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x007f, code lost:
        if (r4 != null) goto L_0x0081;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0075 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            super.onCreate(r18)
            r2 = r0
            androidx.lifecycle.LifecycleOwner r2 = (androidx.lifecycle.LifecycleOwner) r2
            androidx.lifecycle.LifecycleCoroutineScope r2 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r2)
            r3 = r2
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$1 r2 = new org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$1
            r9 = 0
            r2.<init>(r0, r9)
            r6 = r2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r3, r4, r5, r6, r7, r8)
            org.videolan.medialibrary.interfaces.Medialibrary r2 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String r3 = "getInstance(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r0.medialibrary = r2
            org.videolan.vlc.gui.SimpleAdapter r2 = new org.videolan.vlc.gui.SimpleAdapter
            r3 = r0
            org.videolan.vlc.gui.SimpleAdapter$ClickHandler r3 = (org.videolan.vlc.gui.SimpleAdapter.ClickHandler) r3
            r2.<init>(r3)
            r0.adapter = r2
            r2 = 1
            r3 = 0
            android.os.Bundle r4 = r17.requireArguments()     // Catch:{ Exception -> 0x0075 }
            java.lang.String r5 = "requireArguments(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ Exception -> 0x0075 }
            java.lang.String r5 = "PLAYLIST_NEW_TRACKS"
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0075 }
            r7 = 33
            if (r6 < r7) goto L_0x0052
            java.lang.Class<org.videolan.medialibrary.interfaces.media.MediaWrapper> r6 = org.videolan.medialibrary.interfaces.media.MediaWrapper.class
            java.lang.Object[] r4 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r4, (java.lang.String) r5, (java.lang.Class) r6)     // Catch:{ Exception -> 0x0075 }
            android.os.Parcelable[] r4 = (android.os.Parcelable[]) r4     // Catch:{ Exception -> 0x0075 }
            goto L_0x0056
        L_0x0052:
            android.os.Parcelable[] r4 = r4.getParcelableArray(r5)     // Catch:{ Exception -> 0x0075 }
        L_0x0056:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r4     // Catch:{ Exception -> 0x0075 }
            android.content.res.Resources r5 = r17.getResources()     // Catch:{ Exception -> 0x0075 }
            int r6 = org.videolan.vlc.R.plurals.media_quantity     // Catch:{ Exception -> 0x0075 }
            int r7 = r4.length     // Catch:{ Exception -> 0x0075 }
            int r8 = r4.length     // Catch:{ Exception -> 0x0075 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0075 }
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0075 }
            r10[r3] = r8     // Catch:{ Exception -> 0x0075 }
            java.lang.String r5 = r5.getQuantityString(r6, r7, r10)     // Catch:{ Exception -> 0x0075 }
            java.lang.String r6 = "getQuantityString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ Exception -> 0x0075 }
            r0.setFilesText(r5)     // Catch:{ Exception -> 0x0075 }
            goto L_0x00d0
        L_0x0075:
            android.os.Bundle r4 = r17.requireArguments()     // Catch:{ Exception -> 0x00cd }
            java.lang.String r5 = "PLAYLIST_FROM_FOLDER"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ Exception -> 0x00cd }
            if (r4 == 0) goto L_0x00ca
            r0.setLoading(r2)     // Catch:{ Exception -> 0x00cd }
            r10 = r0
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10     // Catch:{ Exception -> 0x00cd }
            r15 = 4
            r16 = 0
            r11 = 0
            r14 = 0
            r13 = r4
            org.videolan.vlc.viewmodels.browser.BrowserModel r2 = org.videolan.vlc.viewmodels.browser.BrowserModelKt.getBrowserModel$default(r10, r11, r13, r14, r15, r16)     // Catch:{ Exception -> 0x00cd }
            android.os.Bundle r5 = r17.requireArguments()     // Catch:{ Exception -> 0x00cd }
            java.lang.String r6 = "PLAYLIST_FOLDER_ADD_SUBFOLDERS"
            boolean r5 = r5.getBoolean(r6, r3)     // Catch:{ Exception -> 0x00cd }
            if (r5 == 0) goto L_0x00b0
            r5 = r0
            androidx.lifecycle.LifecycleOwner r5 = (androidx.lifecycle.LifecycleOwner) r5     // Catch:{ Exception -> 0x00cd }
            androidx.lifecycle.LifecycleCoroutineScope r5 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r5)     // Catch:{ Exception -> 0x00cd }
            org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1 r6 = new org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1     // Catch:{ Exception -> 0x00cd }
            r6.<init>(r0, r2, r4, r9)     // Catch:{ Exception -> 0x00cd }
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch:{ Exception -> 0x00cd }
            r5.launchWhenStarted(r6)     // Catch:{ Exception -> 0x00cd }
            goto L_0x00ca
        L_0x00b0:
            org.videolan.tools.livedata.LiveDataset r2 = r2.getDataset()     // Catch:{ Exception -> 0x00cd }
            r4 = r0
            androidx.lifecycle.LifecycleOwner r4 = (androidx.lifecycle.LifecycleOwner) r4     // Catch:{ Exception -> 0x00cd }
            org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$2 r5 = new org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$2     // Catch:{ Exception -> 0x00cd }
            r5.<init>(r0)     // Catch:{ Exception -> 0x00cd }
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch:{ Exception -> 0x00cd }
            org.videolan.vlc.gui.dialogs.SavePlaylistDialogKt$sam$androidx_lifecycle_Observer$0 r6 = new org.videolan.vlc.gui.dialogs.SavePlaylistDialogKt$sam$androidx_lifecycle_Observer$0     // Catch:{ Exception -> 0x00cd }
            r6.<init>(r5)     // Catch:{ Exception -> 0x00cd }
            androidx.lifecycle.Observer r6 = (androidx.lifecycle.Observer) r6     // Catch:{ Exception -> 0x00cd }
            r2.observe(r4, r6)     // Catch:{ Exception -> 0x00cd }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00cd }
        L_0x00ca:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r2 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r3]     // Catch:{ Exception -> 0x00cd }
            goto L_0x00cf
        L_0x00cd:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r2 = new org.videolan.medialibrary.interfaces.media.MediaWrapper[r3]
        L_0x00cf:
            r4 = r2
        L_0x00d0:
            r0.newTracks = r4
            if (r1 == 0) goto L_0x00da
            java.lang.String r2 = "SELECTED_PLAYLIST"
            java.io.Serializable r9 = r1.getSerializable(r2)
        L_0x00da:
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            r0.selectedPlaylist = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.SavePlaylistDialog.onCreate(android.os.Bundle):void");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogPlaylistBinding inflate = DialogPlaylistBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogPlaylistBinding dialogPlaylistBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.setIsLoading(Boolean.valueOf(this.isLoading));
        DialogPlaylistBinding dialogPlaylistBinding2 = this.binding;
        if (dialogPlaylistBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding2 = null;
        }
        dialogPlaylistBinding2.setFilesText(this.filesText);
        DialogPlaylistBinding dialogPlaylistBinding3 = this.binding;
        if (dialogPlaylistBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding3 = null;
        }
        EditText editText = dialogPlaylistBinding3.dialogPlaylistName.getEditText();
        if (editText != null) {
            String string = requireArguments().getString(KEY_DEFAULT_TITLE);
            if (string == null) {
                string = "";
            }
            editText.setText(string);
        }
        DialogPlaylistBinding dialogPlaylistBinding4 = this.binding;
        if (dialogPlaylistBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogPlaylistBinding = dialogPlaylistBinding4;
        }
        return dialogPlaylistBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        DialogPlaylistBinding dialogPlaylistBinding = this.binding;
        DialogPlaylistBinding dialogPlaylistBinding2 = null;
        if (dialogPlaylistBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding = null;
        }
        View.OnClickListener onClickListener = this;
        dialogPlaylistBinding.dialogPlaylistSave.setOnClickListener(onClickListener);
        DialogPlaylistBinding dialogPlaylistBinding3 = this.binding;
        if (dialogPlaylistBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding3 = null;
        }
        dialogPlaylistBinding3.dialogPlaylistCreate.setOnClickListener(onClickListener);
        DialogPlaylistBinding dialogPlaylistBinding4 = this.binding;
        if (dialogPlaylistBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding4 = null;
        }
        EditText editText = dialogPlaylistBinding4.dialogPlaylistName.getEditText();
        Intrinsics.checkNotNull(editText);
        editText.setOnEditorActionListener(this);
        DialogPlaylistBinding dialogPlaylistBinding5 = this.binding;
        if (dialogPlaylistBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding5 = null;
        }
        EditText editText2 = dialogPlaylistBinding5.dialogPlaylistName.getEditText();
        Intrinsics.checkNotNull(editText2);
        editText2.setOnKeyListener(new SavePlaylistDialog$$ExternalSyntheticLambda0(this));
        DialogPlaylistBinding dialogPlaylistBinding6 = this.binding;
        if (dialogPlaylistBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding6 = null;
        }
        dialogPlaylistBinding6.list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        DialogPlaylistBinding dialogPlaylistBinding7 = this.binding;
        if (dialogPlaylistBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding7 = null;
        }
        RecyclerView recyclerView = dialogPlaylistBinding7.list;
        SimpleAdapter simpleAdapter = this.adapter;
        if (simpleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            simpleAdapter = null;
        }
        recyclerView.setAdapter(simpleAdapter);
        SimpleAdapter simpleAdapter2 = this.adapter;
        if (simpleAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            simpleAdapter2 = null;
        }
        Medialibrary medialibrary2 = this.medialibrary;
        if (medialibrary2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            medialibrary2 = null;
        }
        Playlist[] playlists = medialibrary2.getPlaylists(Playlist.Type.All, false);
        Intrinsics.checkNotNull(playlists);
        for (Playlist playlist : playlists) {
            playlist.setDescription(getResources().getQuantityString(R.plurals.media_quantity, playlist.getTracksCount(), new Object[]{Integer.valueOf(playlist.getTracksCount())}));
        }
        Intrinsics.checkNotNullExpressionValue(playlists, "apply(...)");
        simpleAdapter2.submitList(CollectionsKt.listOf(Arrays.copyOf(playlists, playlists.length)));
        MediaWrapper[] mediaWrapperArr = this.newTracks;
        if (mediaWrapperArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newTracks");
            mediaWrapperArr = null;
        }
        if (!Tools.isArrayEmpty(mediaWrapperArr)) {
            DialogPlaylistBinding dialogPlaylistBinding8 = this.binding;
            if (dialogPlaylistBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaylistBinding8 = null;
            }
            dialogPlaylistBinding8.dialogPlaylistSave.setText(R.string.save);
        }
        updateEmptyView();
        getParentFragmentManager().setFragmentResultListener(DuplicationWarningDialog.REQUEST_KEY, getViewLifecycleOwner(), new SavePlaylistDialog$$ExternalSyntheticLambda1(this));
        DialogPlaylistBinding dialogPlaylistBinding9 = this.binding;
        if (dialogPlaylistBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding9 = null;
        }
        SwitchCompat switchCompat = dialogPlaylistBinding9.replaceSwitch;
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        switchCompat.setChecked(((SharedPreferences) settings.getInstance(requireActivity)).getBoolean(SettingsKt.PLAYLIST_REPLACE, false));
        DialogPlaylistBinding dialogPlaylistBinding10 = this.binding;
        if (dialogPlaylistBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding10 = null;
        }
        dialogPlaylistBinding10.replaceSwitch.setOnCheckedChangeListener(new SavePlaylistDialog$$ExternalSyntheticLambda2(this));
        ArrayList<Playlist> arrayList = this.selectedPlaylist;
        if (arrayList != null) {
            int i = 0;
            for (Object next : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Playlist playlist2 = (Playlist) next;
                SimpleAdapter simpleAdapter3 = this.adapter;
                if (simpleAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    simpleAdapter3 = null;
                }
                MultiSelectHelper.toggleSelection$default(simpleAdapter3.getMultiSelectHelper(), i, false, 2, (Object) null);
                i = i2;
            }
            if (arrayList.size() > 0) {
                DialogPlaylistBinding dialogPlaylistBinding11 = this.binding;
                if (dialogPlaylistBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogPlaylistBinding2 = dialogPlaylistBinding11;
                }
                dialogPlaylistBinding2.selectedPlaylistCount.setText(getResources().getString(R.string.selection_count, new Object[]{Integer.valueOf(arrayList.size())}));
            }
        }
    }

    /* access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$1(SavePlaylistDialog savePlaylistDialog, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(savePlaylistDialog, "this$0");
        if (i != 66) {
            return false;
        }
        savePlaylistDialog.addNewPlaylist();
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$4(SavePlaylistDialog savePlaylistDialog, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(savePlaylistDialog, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        int i = bundle.getInt(DuplicationWarningDialog.OPTION_KEY);
        if (i == 0) {
            MediaWrapper[] mediaWrapperArr = savePlaylistDialog.newTracks;
            if (mediaWrapperArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
                mediaWrapperArr = null;
            }
            savePlaylistDialog.processNextItem(mediaWrapperArr);
        } else if (i == 1) {
            MediaWrapper[] mediaWrapperArr2 = savePlaylistDialog.nonDuplicateTracks;
            Intrinsics.checkNotNull(mediaWrapperArr2);
            savePlaylistDialog.processNextItem(mediaWrapperArr2);
        }
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(SavePlaylistDialog savePlaylistDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(savePlaylistDialog, "this$0");
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = savePlaylistDialog.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), SettingsKt.PLAYLIST_REPLACE, Boolean.valueOf(z));
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        SimpleAdapter simpleAdapter = this.adapter;
        if (simpleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            simpleAdapter = null;
        }
        List<MediaLibraryItem> selection = simpleAdapter.getMultiSelectHelper().getSelection();
        Intrinsics.checkNotNull(selection, "null cannot be cast to non-null type java.util.ArrayList<org.videolan.medialibrary.interfaces.media.Playlist>{ kotlin.collections.TypeAliasesKt.ArrayList<org.videolan.medialibrary.interfaces.media.Playlist> }");
        ArrayList<Playlist> arrayList = (ArrayList) selection;
        this.selectedPlaylist = arrayList;
        bundle.putSerializable(SELECTED_PLAYLIST, arrayList);
    }

    private final void updateEmptyView() {
        DialogPlaylistBinding dialogPlaylistBinding = this.binding;
        SimpleAdapter simpleAdapter = null;
        if (dialogPlaylistBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding = null;
        }
        TextView textView = dialogPlaylistBinding.empty;
        SimpleAdapter simpleAdapter2 = this.adapter;
        if (simpleAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            simpleAdapter = simpleAdapter2;
        }
        textView.setVisibility(simpleAdapter.isEmpty() ? 0 : 8);
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.dialog_playlist_save) {
            SimpleAdapter simpleAdapter = this.adapter;
            SimpleAdapter simpleAdapter2 = null;
            if (simpleAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                simpleAdapter = null;
            }
            if (!simpleAdapter.getMultiSelectHelper().getSelection().isEmpty()) {
                SimpleAdapter simpleAdapter3 = this.adapter;
                if (simpleAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    simpleAdapter2 = simpleAdapter3;
                }
                List<MediaLibraryItem> selection = simpleAdapter2.getMultiSelectHelper().getSelection();
                this.playlistIterator = selection.iterator();
                saveToExistingPlaylists(selection);
            }
        } else if (id == R.id.dialog_playlist_create) {
            addNewPlaylist();
        }
    }

    /* access modifiers changed from: private */
    public final void processNextItem(MediaWrapper[] mediaWrapperArr) {
        Iterator<? extends MediaLibraryItem> it = this.playlistIterator;
        if (it != null) {
            Intrinsics.checkNotNull(it);
            if (it.hasNext()) {
                Iterator<? extends MediaLibraryItem> it2 = this.playlistIterator;
                Intrinsics.checkNotNull(it2);
                MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) it2.next();
                this.currentItem = mediaLibraryItem;
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
                savePlaylist((Playlist) mediaLibraryItem, mediaWrapperArr);
                return;
            }
        }
        this.playlistIterator = null;
        this.currentItem = null;
        dismiss();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(textView, "v");
        if (i != 4) {
            return false;
        }
        addNewPlaylist();
        return false;
    }

    private final void addNewPlaylist() {
        Editable text;
        String obj;
        if (!this.alreadyAdding.getAndSet(true)) {
            DialogPlaylistBinding dialogPlaylistBinding = this.binding;
            if (dialogPlaylistBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaylistBinding = null;
            }
            EditText editText = dialogPlaylistBinding.dialogPlaylistName.getEditText();
            if (editText != null && (text = editText.getText()) != null && (obj = text.toString()) != null) {
                CharSequence charSequence = obj;
                int length = charSequence.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
                    if (!z) {
                        if (!z2) {
                            z = true;
                        } else {
                            i++;
                        }
                    } else if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                }
                String obj2 = charSequence.subSequence(i, length + 1).toString();
                if (obj2 != null) {
                    UiTools uiTools = UiTools.INSTANCE;
                    DialogPlaylistBinding dialogPlaylistBinding2 = this.binding;
                    if (dialogPlaylistBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        dialogPlaylistBinding2 = null;
                    }
                    uiTools.setKeyboardVisibility(dialogPlaylistBinding2.dialogPlaylistName, false);
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new SavePlaylistDialog$addNewPlaylist$1(this, obj2, (Continuation<? super SavePlaylistDialog$addNewPlaylist$1>) null), 3, (Object) null);
                }
            }
        }
    }

    private final void savePlaylist(Playlist playlist, MediaWrapper[] mediaWrapperArr) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, this.coroutineContextProvider.getIO(), (CoroutineStart) null, new SavePlaylistDialog$savePlaylist$1(mediaWrapperArr, this, playlist, (Continuation<? super SavePlaylistDialog$savePlaylist$1>) null), 2, (Object) null);
    }

    private final void saveToExistingPlaylists(List<? extends MediaLibraryItem> list) {
        MediaWrapper[] mediaWrapperArr;
        String str;
        MediaWrapper[] mediaWrapperArr2 = this.newTracks;
        if (mediaWrapperArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newTracks");
            mediaWrapperArr2 = null;
        }
        int length = mediaWrapperArr2.length;
        ArrayList arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        for (Object next : list) {
            MediaWrapper[] tracks = ((MediaLibraryItem) next).getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
            MediaWrapper[] mediaWrapperArr3 = this.newTracks;
            if (mediaWrapperArr3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
                mediaWrapperArr3 = null;
            }
            this.nonDuplicateTracks = getNonDuplicateTracks(tracks, mediaWrapperArr3);
            MediaWrapper[] mediaWrapperArr4 = this.newTracks;
            if (mediaWrapperArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
                mediaWrapperArr4 = null;
            }
            int length2 = mediaWrapperArr4.length;
            MediaWrapper[] mediaWrapperArr5 = this.nonDuplicateTracks;
            Intrinsics.checkNotNull(mediaWrapperArr5);
            if (length2 - mediaWrapperArr5.length != 0) {
                arrayList2.add(next);
            }
        }
        Iterable iterable = (List) arrayList2;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        int i = 0;
        boolean z = false;
        for (Object next2 : iterable) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) next2;
            MediaWrapper[] tracks2 = mediaLibraryItem.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks2, "getTracks(...)");
            MediaWrapper[] mediaWrapperArr6 = this.newTracks;
            if (mediaWrapperArr6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
                mediaWrapperArr6 = null;
            }
            this.nonDuplicateTracks = getNonDuplicateTracks(tracks2, mediaWrapperArr6);
            MediaWrapper[] mediaWrapperArr7 = this.newTracks;
            if (mediaWrapperArr7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
                mediaWrapperArr7 = null;
            }
            int length3 = mediaWrapperArr7.length;
            MediaWrapper[] mediaWrapperArr8 = this.nonDuplicateTracks;
            Intrinsics.checkNotNull(mediaWrapperArr8);
            int length4 = length3 - mediaWrapperArr8.length;
            arrayList.add(mediaLibraryItem.getTitle());
            if (length4 < length) {
                int i3 = R.plurals.duplicate_three_options_secondary;
                if (length4 == 1) {
                    str = getResources().getQuantityString(i3, length4, new Object[]{mediaLibraryItem.getTitle()});
                    Intrinsics.checkNotNullExpressionValue(str, "getQuantityString(...)");
                } else {
                    str = getResources().getQuantityString(i3, length4, new Object[]{Integer.valueOf(length4), mediaLibraryItem.getTitle()});
                    Intrinsics.checkNotNullExpressionValue(str, "getQuantityString(...)");
                }
                z = true;
            } else {
                int i4 = R.plurals.duplicate_two_options_secondary;
                str = getResources().getQuantityString(i4, length4, new Object[]{mediaLibraryItem.getTitle()});
                Intrinsics.checkNotNullExpressionValue(str, "getQuantityString(...)");
            }
            arrayList3.add(str);
            i = i2;
        }
        List list2 = (List) arrayList3;
        if (!list2.isEmpty()) {
            DialogPlaylistBinding dialogPlaylistBinding = this.binding;
            if (dialogPlaylistBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaylistBinding = null;
            }
            if (!dialogPlaylistBinding.replaceSwitch.isChecked()) {
                DuplicationWarningDialog.Companion.newInstance(z, arrayList, new ArrayList(list2)).show(requireActivity().getSupportFragmentManager(), "duplicationWarningDialog");
                return;
            }
        }
        MediaWrapper[] mediaWrapperArr9 = this.newTracks;
        if (mediaWrapperArr9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newTracks");
            mediaWrapperArr = null;
        } else {
            mediaWrapperArr = mediaWrapperArr9;
        }
        processNextItem(mediaWrapperArr);
    }

    public void onClick(MediaLibraryItem mediaLibraryItem, int i) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        SimpleAdapter simpleAdapter = this.adapter;
        SimpleAdapter simpleAdapter2 = null;
        if (simpleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            simpleAdapter = null;
        }
        MultiSelectHelper.toggleSelection$default(simpleAdapter.getMultiSelectHelper(), i, false, 2, (Object) null);
        DialogPlaylistBinding dialogPlaylistBinding = this.binding;
        if (dialogPlaylistBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaylistBinding = null;
        }
        TextView textView = dialogPlaylistBinding.selectedPlaylistCount;
        Resources resources = getResources();
        int i2 = R.string.selection_count;
        SimpleAdapter simpleAdapter3 = this.adapter;
        if (simpleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            simpleAdapter2 = simpleAdapter3;
        }
        textView.setText(resources.getString(i2, new Object[]{Integer.valueOf(simpleAdapter2.getMultiSelectHelper().getSelection().size())}));
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SavePlaylistDialog$Companion;", "Lorg/videolan/tools/DependencyProvider;", "", "()V", "KEY_DEFAULT_TITLE", "", "KEY_FOLDER", "KEY_NEW_TRACKS", "KEY_SUB_FOLDERS", "SELECTED_PLAYLIST", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SavePlaylistDialog.kt */
    public static final class Companion extends DependencyProvider<Object> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final MediaWrapper[] getNonDuplicateTracks(MediaWrapper[] mediaWrapperArr, MediaWrapper[] mediaWrapperArr2) {
        Collection arrayList = new ArrayList();
        for (MediaWrapper mediaWrapper : mediaWrapperArr2) {
            int length = mediaWrapperArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    arrayList.add(mediaWrapper);
                    break;
                } else if (!(!mediaWrapperArr[i].equals(mediaWrapper))) {
                    break;
                } else {
                    i++;
                }
            }
        }
        return (MediaWrapper[]) ((List) arrayList).toArray(new MediaWrapper[0]);
    }
}
