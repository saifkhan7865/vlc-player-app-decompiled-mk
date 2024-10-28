package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.AppScope;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.DependencyProvider;
import org.videolan.vlc.databinding.DialogAddToGroupBinding;
import org.videolan.vlc.gui.SimpleAdapter;
import org.videolan.vlc.viewmodels.mobile.VideoGroupingType;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 72\u00020\u00012\u00020\u0002:\u00017B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u000bH\u0016J\u0018\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020#H\u0016J\u0012\u0010+\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J&\u0010.\u001a\u0004\u0018\u00010%2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u001a\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020%2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0012\u00105\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX.¢\u0006\u0004\n\u0002\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AddToGroupDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Lorg/videolan/vlc/gui/SimpleAdapter$ClickHandler;", "()V", "adapter", "Lorg/videolan/vlc/gui/SimpleAdapter;", "binding", "Lorg/videolan/vlc/databinding/DialogAddToGroupBinding;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "forbidNewGroup", "", "value", "isLoading", "setLoading", "(Z)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "newGroupListener", "Lkotlin/Function0;", "", "getNewGroupListener", "()Lkotlin/jvm/functions/Function0;", "setNewGroupListener", "(Lkotlin/jvm/functions/Function0;)V", "newTrack", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "viewModel", "Lorg/videolan/vlc/viewmodels/mobile/VideosViewModel;", "addToGroup", "videoGroup", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onClick", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "position", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "updateEmptyView", "empty", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AddToGroupDialog.kt */
public final class AddToGroupDialog extends VLCBottomSheetDialogFragment implements SimpleAdapter.ClickHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String FORBID_NEW_GROUP = "FORBID_NEW_GROUP";
    public static final String KEY_TRACKS = "ADD_TO_GROUP_TRACKS";
    public static final String TAG = "VLC/SavePlaylistDialog";
    /* access modifiers changed from: private */
    public SimpleAdapter adapter;
    private DialogAddToGroupBinding binding;
    private final CoroutineContextProvider coroutineContextProvider;
    /* access modifiers changed from: private */
    public boolean forbidNewGroup = true;
    private boolean isLoading;
    /* access modifiers changed from: private */
    public Medialibrary medialibrary;
    public Function0<Unit> newGroupListener;
    /* access modifiers changed from: private */
    public MediaWrapper[] newTrack;
    private VideosViewModel viewModel;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public AddToGroupDialog() {
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

    public final Function0<Unit> getNewGroupListener() {
        Function0<Unit> function0 = this.newGroupListener;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("newGroupListener");
        return null;
    }

    public final void setNewGroupListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.newGroupListener = function0;
    }

    private final void setLoading(boolean z) {
        this.isLoading = z;
        DialogAddToGroupBinding dialogAddToGroupBinding = this.binding;
        if (dialogAddToGroupBinding != null) {
            if (dialogAddToGroupBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogAddToGroupBinding = null;
            }
            dialogAddToGroupBinding.setIsLoading(Boolean.valueOf(z));
        }
    }

    public View initialFocusedView() {
        DialogAddToGroupBinding dialogAddToGroupBinding = this.binding;
        if (dialogAddToGroupBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAddToGroupBinding = null;
        }
        RecyclerView recyclerView = dialogAddToGroupBinding.list;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "list");
        return recyclerView;
    }

    public void onCreate(Bundle bundle) {
        MediaWrapper[] mediaWrapperArr;
        boolean z;
        Parcelable[] parcelableArr;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new AddToGroupDialog$onCreate$1(this, (Continuation<? super AddToGroupDialog$onCreate$1>) null), 3, (Object) null);
        super.onCreate(bundle);
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
        this.adapter = new SimpleAdapter(this);
        try {
            Bundle requireArguments = requireArguments();
            Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments(...)");
            if (Build.VERSION.SDK_INT >= 33) {
                parcelableArr = (Parcelable[]) AppUtils$$ExternalSyntheticApiModelOutline0.m(requireArguments, KEY_TRACKS, MediaWrapper.class);
            } else {
                parcelableArr = requireArguments.getParcelableArray(KEY_TRACKS);
            }
            mediaWrapperArr = (MediaWrapper[]) parcelableArr;
        } catch (Exception unused2) {
            mediaWrapperArr = new MediaWrapper[0];
        }
        this.newTrack = mediaWrapperArr;
        try {
            z = requireArguments().getBoolean(FORBID_NEW_GROUP);
        } catch (Exception unused3) {
            z = true;
        }
        this.forbidNewGroup = z;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogAddToGroupBinding inflate = DialogAddToGroupBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogAddToGroupBinding dialogAddToGroupBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.setIsLoading(Boolean.valueOf(this.isLoading));
        DialogAddToGroupBinding dialogAddToGroupBinding2 = this.binding;
        if (dialogAddToGroupBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogAddToGroupBinding = dialogAddToGroupBinding2;
        }
        return dialogAddToGroupBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        DialogAddToGroupBinding dialogAddToGroupBinding = this.binding;
        VideosViewModel videosViewModel = null;
        if (dialogAddToGroupBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAddToGroupBinding = null;
        }
        dialogAddToGroupBinding.list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        DialogAddToGroupBinding dialogAddToGroupBinding2 = this.binding;
        if (dialogAddToGroupBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAddToGroupBinding2 = null;
        }
        RecyclerView recyclerView = dialogAddToGroupBinding2.list;
        SimpleAdapter simpleAdapter = this.adapter;
        if (simpleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            simpleAdapter = null;
        }
        recyclerView.setAdapter(simpleAdapter);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.viewModel = (VideosViewModel) new VideosViewModel.Factory(requireContext, VideoGroupingType.NAME, (Folder) null, (VideoGroup) null).create(VideosViewModel.class);
        updateEmptyView$default(this, false, 1, (Object) null);
        VideosViewModel videosViewModel2 = this.viewModel;
        if (videosViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            videosViewModel = videosViewModel2;
        }
        videosViewModel.getProvider().getPagedList().observe(getViewLifecycleOwner(), new AddToGroupDialog$sam$androidx_lifecycle_Observer$0(new AddToGroupDialog$onViewCreated$1(this)));
    }

    static /* synthetic */ void updateEmptyView$default(AddToGroupDialog addToGroupDialog, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        addToGroupDialog.updateEmptyView(z);
    }

    /* access modifiers changed from: private */
    public final void updateEmptyView(boolean z) {
        DialogAddToGroupBinding dialogAddToGroupBinding = this.binding;
        if (dialogAddToGroupBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAddToGroupBinding = null;
        }
        dialogAddToGroupBinding.empty.setVisibility(z ? 0 : 8);
    }

    private final void addToGroup(VideoGroup videoGroup) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, this.coroutineContextProvider.getIO(), (CoroutineStart) null, new AddToGroupDialog$addToGroup$1(this, videoGroup, (Continuation<? super AddToGroupDialog$addToGroup$1>) null), 2, (Object) null);
        dismiss();
    }

    public void onClick(MediaLibraryItem mediaLibraryItem, int i) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (mediaLibraryItem instanceof DummyItem) {
            getNewGroupListener().invoke();
            dismiss();
            return;
        }
        addToGroup((VideoGroup) mediaLibraryItem);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/AddToGroupDialog$Companion;", "Lorg/videolan/tools/DependencyProvider;", "", "()V", "FORBID_NEW_GROUP", "", "KEY_TRACKS", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AddToGroupDialog.kt */
    public static final class Companion extends DependencyProvider<Object> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
