package org.videolan.vlc.gui.dialogs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.core.view.ViewGroupKt;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogDisplaySettingsBinding;
import org.videolan.vlc.databinding.SortDisplaySettingBinding;
import org.videolan.vlc.viewmodels.DisplaySettingsViewModel;
import org.videolan.vlc.viewmodels.mobile.VideoGroupingType;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 92\u00020\u0001:\u00029:B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\bH\u0016J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J$\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001a\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J@\u0010,\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020-2\u0006\u0010.\u001a\u00020\b2\b\b\u0001\u0010/\u001a\u00020\u00062\b\b\u0001\u00100\u001a\u00020\u00062\b\b\u0001\u00101\u001a\u00020\u00062\b\b\u0001\u00102\u001a\u00020\u0006H\u0002J\b\u00103\u001a\u00020\"H\u0002J\b\u00104\u001a\u00020\"H\u0002J\b\u00105\u001a\u00020\"H\u0002J\b\u00106\u001a\u00020\"H\u0002J\b\u00107\u001a\u00020\"H\u0002J\b\u00108\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0012\u0010\u0010\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0018j\b\u0012\u0004\u0012\u00020\u0006`\u0019X.¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogDisplaySettingsBinding;", "currentSort", "", "currentSortDesc", "", "displayInCards", "displaySettingsViewModel", "Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "getDisplaySettingsViewModel", "()Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "displaySettingsViewModel$delegate", "Lkotlin/Lazy;", "onlyFavs", "Ljava/lang/Boolean;", "showAllArtists", "showHiddenFiles", "showOnlyMultimediaFiles", "showVideoGroups", "", "sorts", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getDefaultState", "getSortTag", "sort", "desc", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "setupSortViews", "Lorg/videolan/vlc/databinding/SortDisplaySettingBinding;", "isCurrentSort", "titleString", "ascString", "descString", "iconDrawable", "updateDisplayMode", "updateShowAllArtists", "updateShowAllFiles", "updateShowHiddenFiles", "updateShowOnlyFavs", "updateSorts", "Companion", "VideoGroup", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DisplaySettingsDialog.kt */
public final class DisplaySettingsDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private DialogDisplaySettingsBinding binding;
    private int currentSort = -1;
    private boolean currentSortDesc;
    /* access modifiers changed from: private */
    public boolean displayInCards;
    private final Lazy displaySettingsViewModel$delegate;
    /* access modifiers changed from: private */
    public Boolean onlyFavs;
    /* access modifiers changed from: private */
    public Boolean showAllArtists;
    /* access modifiers changed from: private */
    public Boolean showHiddenFiles;
    /* access modifiers changed from: private */
    public Boolean showOnlyMultimediaFiles;
    /* access modifiers changed from: private */
    public String showVideoGroups;
    private ArrayList<Integer> sorts;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public DisplaySettingsDialog() {
        Fragment fragment = this;
        this.displaySettingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(DisplaySettingsViewModel.class), new DisplaySettingsDialog$special$$inlined$activityViewModels$default$1(fragment), new DisplaySettingsDialog$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new DisplaySettingsDialog$special$$inlined$activityViewModels$default$3(fragment));
    }

    /* access modifiers changed from: private */
    public final DisplaySettingsViewModel getDisplaySettingsViewModel() {
        return (DisplaySettingsViewModel) this.displaySettingsViewModel$delegate.getValue();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jk\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog;", "displayInCards", "", "showAllArtists", "onlyFavs", "sorts", "", "", "currentSort", "currentSortDesc", "videoGroup", "", "showOnlyMultimediaFiles", "showHiddenFiles", "(ZLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;IZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DisplaySettingsDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ DisplaySettingsDialog newInstance$default(Companion companion, boolean z, Boolean bool, Boolean bool2, List list, int i, boolean z2, String str, Boolean bool3, Boolean bool4, int i2, Object obj) {
            int i3 = i2;
            return companion.newInstance(z, (i3 & 2) != 0 ? null : bool, bool2, list, i, z2, (i3 & 64) != 0 ? null : str, (i3 & 128) != 0 ? null : bool3, (i3 & 256) != 0 ? null : bool4);
        }

        public final DisplaySettingsDialog newInstance(boolean z, Boolean bool, Boolean bool2, List<Integer> list, int i, boolean z2, String str, Boolean bool3, Boolean bool4) {
            Intrinsics.checkNotNullParameter(list, DisplaySettingsDialogKt.SORTS);
            DisplaySettingsDialog displaySettingsDialog = new DisplaySettingsDialog();
            displaySettingsDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(DisplaySettingsDialogKt.DISPLAY_IN_CARDS, Boolean.valueOf(z)), TuplesKt.to(DisplaySettingsDialogKt.SORTS, list), TuplesKt.to(DisplaySettingsDialogKt.CURRENT_SORT, Integer.valueOf(i)), TuplesKt.to(DisplaySettingsDialogKt.CURRENT_SORT_DESC, Boolean.valueOf(z2)), TuplesKt.to(DisplaySettingsDialogKt.VIDEO_GROUPING, str)));
            if (bool2 != null) {
                Bundle arguments = displaySettingsDialog.getArguments();
                Intrinsics.checkNotNull(arguments);
                arguments.putBoolean(DisplaySettingsDialogKt.ONLY_FAVS, bool2.booleanValue());
            }
            if (bool != null) {
                Bundle arguments2 = displaySettingsDialog.getArguments();
                Intrinsics.checkNotNull(arguments2);
                arguments2.putBoolean(DisplaySettingsDialogKt.SHOW_ALL_ARTISTS, bool.booleanValue());
            }
            if (bool3 != null) {
                Bundle arguments3 = displaySettingsDialog.getArguments();
                Intrinsics.checkNotNull(arguments3);
                arguments3.putBoolean(DisplaySettingsDialogKt.SHOW_ONLY_MULTIMEDIA_FILES, bool3.booleanValue());
            }
            if (bool4 != null) {
                Bundle arguments4 = displaySettingsDialog.getArguments();
                Intrinsics.checkNotNull(arguments4);
                arguments4.putBoolean(DisplaySettingsDialogKt.SHOW_HIDDEN_FILES, bool4.booleanValue());
            }
            return displaySettingsDialog;
        }
    }

    public View initialFocusedView() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = this.binding;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        TextView textView = dialogDisplaySettingsBinding.title;
        Intrinsics.checkNotNullExpressionValue(textView, "title");
        return textView;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003a, code lost:
        r9 = getArguments();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r9) {
        /*
            r8 = this;
            r0 = r8
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            androidx.lifecycle.LifecycleCoroutineScope r0 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r0)
            r1 = r0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            org.videolan.vlc.gui.dialogs.DisplaySettingsDialog$onCreate$1 r0 = new org.videolan.vlc.gui.dialogs.DisplaySettingsDialog$onCreate$1
            r7 = 0
            r0.<init>(r8, r7)
            r4 = r0
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 3
            r6 = 0
            r2 = 0
            r3 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
            super.onCreate(r9)
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x0101
            java.lang.String r0 = "display_in_cards"
            boolean r9 = r9.getBoolean(r0)
            r8.displayInCards = r9
            android.os.Bundle r9 = r8.getArguments()
            r0 = 1
            if (r9 == 0) goto L_0x0049
            java.lang.String r1 = "only_favs"
            boolean r9 = r9.containsKey(r1)
            if (r9 != r0) goto L_0x0049
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x0049
            boolean r9 = r9.getBoolean(r1)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            goto L_0x004a
        L_0x0049:
            r9 = r7
        L_0x004a:
            r8.onlyFavs = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x0059
            java.lang.String r1 = "sorts"
            java.util.ArrayList r9 = r9.getIntegerArrayList(r1)
            goto L_0x005a
        L_0x0059:
            r9 = r7
        L_0x005a:
            if (r9 == 0) goto L_0x00f9
            r8.sorts = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00f1
            java.lang.String r1 = "current_sort"
            int r9 = r9.getInt(r1)
            r8.currentSort = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00e9
            java.lang.String r1 = "current_sort_desc"
            boolean r9 = r9.getBoolean(r1)
            r8.currentSortDesc = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x0097
            java.lang.String r1 = "show_all_artists"
            boolean r9 = r9.containsKey(r1)
            if (r9 != r0) goto L_0x0097
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x0097
            boolean r9 = r9.getBoolean(r1)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            goto L_0x0098
        L_0x0097:
            r9 = r7
        L_0x0098:
            r8.showAllArtists = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00b7
            java.lang.String r1 = "show_only_multimedia_files"
            boolean r9 = r9.containsKey(r1)
            if (r9 != r0) goto L_0x00b7
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00b7
            boolean r9 = r9.getBoolean(r1)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            goto L_0x00b8
        L_0x00b7:
            r9 = r7
        L_0x00b8:
            r8.showOnlyMultimediaFiles = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00d7
            java.lang.String r1 = "show_hidden_files"
            boolean r9 = r9.containsKey(r1)
            if (r9 != r0) goto L_0x00d7
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00d7
            boolean r9 = r9.getBoolean(r1)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            goto L_0x00d8
        L_0x00d7:
            r9 = r7
        L_0x00d8:
            r8.showHiddenFiles = r9
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x00e6
            java.lang.String r0 = "show_video_groups"
            java.lang.String r7 = r9.getString(r0, r7)
        L_0x00e6:
            r8.showVideoGroups = r7
            return
        L_0x00e9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Current sort desc should be provided"
            r9.<init>(r0)
            throw r9
        L_0x00f1:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Current sort should be provided"
            r9.<init>(r0)
            throw r9
        L_0x00f9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Sorts should be provided"
            r9.<init>(r0)
            throw r9
        L_0x0101:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Display in list should be provided"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.DisplaySettingsDialog.onCreate(android.os.Bundle):void");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogDisplaySettingsBinding inflate = DialogDisplaySettingsBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        updateDisplayMode();
        updateShowAllArtists();
        updateShowOnlyFavs();
        updateShowAllFiles();
        updateShowHiddenFiles();
        updateSorts();
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = this.binding;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = null;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        dialogDisplaySettingsBinding.displayModeGroup.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda0(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding3 = null;
        }
        dialogDisplaySettingsBinding3.showAllArtistGroup.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda3(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding4 = this.binding;
        if (dialogDisplaySettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding4 = null;
        }
        dialogDisplaySettingsBinding4.showAllArtistCheckbox.setOnCheckedChangeListener(new DisplaySettingsDialog$$ExternalSyntheticLambda4(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding5 = this.binding;
        if (dialogDisplaySettingsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding5 = null;
        }
        dialogDisplaySettingsBinding5.showHiddenFilesGroup.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda5(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding6 = this.binding;
        if (dialogDisplaySettingsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding6 = null;
        }
        dialogDisplaySettingsBinding6.showHiddenFilesCheckbox.setOnCheckedChangeListener(new DisplaySettingsDialog$$ExternalSyntheticLambda6(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding7 = this.binding;
        if (dialogDisplaySettingsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding7 = null;
        }
        dialogDisplaySettingsBinding7.showAllFilesGroup.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda7(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding8 = this.binding;
        if (dialogDisplaySettingsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding8 = null;
        }
        dialogDisplaySettingsBinding8.showAllFilesCheckbox.setOnCheckedChangeListener(new DisplaySettingsDialog$$ExternalSyntheticLambda8(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding9 = this.binding;
        if (dialogDisplaySettingsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding9 = null;
        }
        dialogDisplaySettingsBinding9.onlyFavsGroup.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda9(this));
        if (this.onlyFavs == null) {
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding10 = this.binding;
            if (dialogDisplaySettingsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding10 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding10.onlyFavsCheckbox);
        }
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding11 = this.binding;
        if (dialogDisplaySettingsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding11 = null;
        }
        dialogDisplaySettingsBinding11.onlyFavsCheckbox.setOnCheckedChangeListener(new DisplaySettingsDialog$$ExternalSyntheticLambda10(this));
        if (this.showVideoGroups == null) {
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding12 = this.binding;
            if (dialogDisplaySettingsBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding12 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding12.videoGroupsGroup);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding13 = this.binding;
            if (dialogDisplaySettingsBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding13 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding13.videoGroupSpinner);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding14 = this.binding;
            if (dialogDisplaySettingsBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding14 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding14.videoGroupText);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding15 = this.binding;
            if (dialogDisplaySettingsBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding15 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding15.videoGroupImage);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireActivity(), 17367048, VideoGroup.values());
        arrayAdapter.setDropDownViewResource(17367049);
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding16 = this.binding;
        if (dialogDisplaySettingsBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding16 = null;
        }
        dialogDisplaySettingsBinding16.videoGroupSpinner.setAdapter(arrayAdapter);
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding17 = this.binding;
        if (dialogDisplaySettingsBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding17 = null;
        }
        dialogDisplaySettingsBinding17.videoGroupsGroup.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda11(this));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding18 = this.binding;
        if (dialogDisplaySettingsBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding18 = null;
        }
        dialogDisplaySettingsBinding18.videoGroupSpinner.setSelection(ArraysKt.indexOf((T[]) VideoGroup.values(), VideoGroup.Companion.findByValue(this.showVideoGroups)));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding19 = this.binding;
        if (dialogDisplaySettingsBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding2 = dialogDisplaySettingsBinding19;
        }
        dialogDisplaySettingsBinding2.videoGroupSpinner.setOnItemSelectedListener(new DisplaySettingsDialog$onViewCreated$11(arrayAdapter, this));
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(DisplaySettingsDialog displaySettingsDialog, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.displayInCards = !displaySettingsDialog.displayInCards;
        displaySettingsDialog.updateDisplayMode();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$onViewCreated$1$1(displaySettingsDialog, (Continuation<? super DisplaySettingsDialog$onViewCreated$1$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(DisplaySettingsDialog displaySettingsDialog, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = displaySettingsDialog.binding;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = null;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.showAllArtistCheckbox;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = displaySettingsDialog.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding2 = dialogDisplaySettingsBinding3;
        }
        checkBox.setChecked(!dialogDisplaySettingsBinding2.showAllArtistCheckbox.isChecked());
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(DisplaySettingsDialog displaySettingsDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.showAllArtists = Boolean.valueOf(z);
        displaySettingsDialog.updateShowAllArtists();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$onViewCreated$3$1(displaySettingsDialog, (Continuation<? super DisplaySettingsDialog$onViewCreated$3$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(DisplaySettingsDialog displaySettingsDialog, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = displaySettingsDialog.binding;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = null;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.showHiddenFilesCheckbox;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = displaySettingsDialog.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding2 = dialogDisplaySettingsBinding3;
        }
        checkBox.setChecked(!dialogDisplaySettingsBinding2.showHiddenFilesCheckbox.isChecked());
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$4(DisplaySettingsDialog displaySettingsDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.showHiddenFiles = Boolean.valueOf(z);
        displaySettingsDialog.updateShowHiddenFiles();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$onViewCreated$5$1(displaySettingsDialog, (Continuation<? super DisplaySettingsDialog$onViewCreated$5$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(DisplaySettingsDialog displaySettingsDialog, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = displaySettingsDialog.binding;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = null;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.showAllFilesCheckbox;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = displaySettingsDialog.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding2 = dialogDisplaySettingsBinding3;
        }
        checkBox.setChecked(!dialogDisplaySettingsBinding2.showAllFilesCheckbox.isChecked());
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(DisplaySettingsDialog displaySettingsDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.showOnlyMultimediaFiles = Boolean.valueOf(z);
        displaySettingsDialog.updateShowAllFiles();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$onViewCreated$7$1(displaySettingsDialog, (Continuation<? super DisplaySettingsDialog$onViewCreated$7$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(DisplaySettingsDialog displaySettingsDialog, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = displaySettingsDialog.binding;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = null;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.onlyFavsCheckbox;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = displaySettingsDialog.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding2 = dialogDisplaySettingsBinding3;
        }
        checkBox.setChecked(!dialogDisplaySettingsBinding2.onlyFavsCheckbox.isChecked());
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$8(DisplaySettingsDialog displaySettingsDialog, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.onlyFavs = Boolean.valueOf(z);
        displaySettingsDialog.updateShowAllArtists();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$onViewCreated$9$1(displaySettingsDialog, (Continuation<? super DisplaySettingsDialog$onViewCreated$9$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(DisplaySettingsDialog displaySettingsDialog, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = displaySettingsDialog.binding;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        dialogDisplaySettingsBinding.videoGroupSpinner.performClick();
    }

    private final void updateDisplayMode() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = this.binding;
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = null;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        dialogDisplaySettingsBinding.displayInListText.setText(getString(!this.displayInCards ? R.string.display_in_grid : R.string.display_in_list));
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding2 = dialogDisplaySettingsBinding3;
        }
        dialogDisplaySettingsBinding2.displayInListImage.setImageDrawable(ContextCompat.getDrawable(requireActivity(), !this.displayInCards ? R.drawable.ic_view_grid : R.drawable.ic_view_list));
    }

    private final void updateShowAllArtists() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = null;
        if (this.showAllArtists == null) {
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = this.binding;
            if (dialogDisplaySettingsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding2 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding2.showAllArtistGroup);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
            if (dialogDisplaySettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogDisplaySettingsBinding = dialogDisplaySettingsBinding3;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding.allArtistsImage);
            return;
        }
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding4 = this.binding;
        if (dialogDisplaySettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding = dialogDisplaySettingsBinding4;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.showAllArtistCheckbox;
        Boolean bool = this.showAllArtists;
        Intrinsics.checkNotNull(bool);
        checkBox.setChecked(bool.booleanValue());
    }

    private final void updateShowAllFiles() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = null;
        if (this.showOnlyMultimediaFiles == null) {
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = this.binding;
            if (dialogDisplaySettingsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding2 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding2.showAllFilesGroup);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
            if (dialogDisplaySettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogDisplaySettingsBinding = dialogDisplaySettingsBinding3;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding.allFilesImage);
            return;
        }
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding4 = this.binding;
        if (dialogDisplaySettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding = dialogDisplaySettingsBinding4;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.showAllFilesCheckbox;
        Boolean bool = this.showOnlyMultimediaFiles;
        Intrinsics.checkNotNull(bool);
        checkBox.setChecked(bool.booleanValue());
    }

    private final void updateShowHiddenFiles() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = null;
        if (this.showHiddenFiles == null) {
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = this.binding;
            if (dialogDisplaySettingsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding2 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding2.showHiddenFilesGroup);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
            if (dialogDisplaySettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogDisplaySettingsBinding = dialogDisplaySettingsBinding3;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding.hiddenFilesImage);
            return;
        }
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding4 = this.binding;
        if (dialogDisplaySettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding = dialogDisplaySettingsBinding4;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.showHiddenFilesCheckbox;
        Boolean bool = this.showHiddenFiles;
        Intrinsics.checkNotNull(bool);
        checkBox.setChecked(bool.booleanValue());
    }

    private final void updateShowOnlyFavs() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = null;
        if (this.onlyFavs == null) {
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = this.binding;
            if (dialogDisplaySettingsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDisplaySettingsBinding2 = null;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding2.onlyFavsGroup);
            DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
            if (dialogDisplaySettingsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogDisplaySettingsBinding = dialogDisplaySettingsBinding3;
            }
            KotlinExtensionsKt.setGone(dialogDisplaySettingsBinding.onlyFavsImage);
            return;
        }
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding4 = this.binding;
        if (dialogDisplaySettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDisplaySettingsBinding = dialogDisplaySettingsBinding4;
        }
        CheckBox checkBox = dialogDisplaySettingsBinding.onlyFavsCheckbox;
        Boolean bool = this.onlyFavs;
        Intrinsics.checkNotNull(bool);
        checkBox.setChecked(bool.booleanValue());
    }

    private final void updateSorts() {
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding = this.binding;
        if (dialogDisplaySettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding = null;
        }
        if (dialogDisplaySettingsBinding.sortsContainer.getChildCount() == 0) {
            ArrayList<Integer> arrayList = this.sorts;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException(DisplaySettingsDialogKt.SORTS);
                arrayList = null;
            }
            for (Number intValue : arrayList) {
                int intValue2 = intValue.intValue();
                LayoutInflater from = LayoutInflater.from(requireActivity());
                int i = R.layout.sort_display_setting;
                DialogDisplaySettingsBinding dialogDisplaySettingsBinding2 = this.binding;
                if (dialogDisplaySettingsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    dialogDisplaySettingsBinding2 = null;
                }
                ViewDataBinding inflate = DataBindingUtil.inflate(from, i, dialogDisplaySettingsBinding2.sortsContainer, true);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                SortDisplaySettingBinding sortDisplaySettingBinding = (SortDisplaySettingBinding) inflate;
                sortDisplaySettingBinding.sortAsc.setTag(R.id.sort, getSortTag(intValue2, false));
                sortDisplaySettingBinding.sortDesc.setTag(R.id.sort, getSortTag(intValue2, true));
                sortDisplaySettingBinding.sortAsc.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda1(this, intValue2));
                sortDisplaySettingBinding.sortDesc.setOnClickListener(new DisplaySettingsDialog$$ExternalSyntheticLambda2(this, intValue2));
                int i2 = this.currentSort;
                boolean z = intValue2 == i2 || (i2 == 0 && intValue2 == 1);
                if (intValue2 == 1) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_name, R.string.sort_alpha_asc, R.string.sort_alpha_desc, R.drawable.ic_sort_alpha);
                } else if (intValue2 == 2) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_length, R.string.sortby_length_asc, R.string.sortby_length_desc, R.drawable.ic_sort_length);
                } else if (intValue2 == 3) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_date_insertion, R.string.sort_date_asc, R.string.sort_date_desc, R.drawable.ic_medialibrary_date);
                } else if (intValue2 == 4) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_date_last_modified, R.string.sort_date_asc, R.string.sort_date_desc, R.drawable.ic_medialibrary_scan);
                } else if (intValue2 == 5) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_date_release, R.string.sort_date_asc, R.string.sort_date_desc, R.drawable.ic_sort_date);
                } else if (intValue2 == 7) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_artist_name, R.string.sort_alpha_asc, R.string.sort_alpha_desc, R.drawable.ic_sort_artist);
                } else if (intValue2 == 15) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_number, R.string.sortby_number_asc, R.string.sortby_number_desc, R.drawable.ic_sort_number);
                } else if (intValue2 == 9) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_album_name, R.string.sort_alpha_asc, R.string.sort_alpha_desc, R.drawable.ic_sort_album);
                } else if (intValue2 == 10) {
                    setupSortViews(sortDisplaySettingBinding, z, R.string.sortby_filename, R.string.sort_alpha_asc, R.string.sort_alpha_desc, R.drawable.ic_sort_filename);
                } else {
                    throw new IllegalStateException("Unsupported sort: " + intValue2);
                }
            }
        }
        DialogDisplaySettingsBinding dialogDisplaySettingsBinding3 = this.binding;
        if (dialogDisplaySettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDisplaySettingsBinding3 = null;
        }
        LinearLayout linearLayout = dialogDisplaySettingsBinding3.sortsContainer;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "sortsContainer");
        for (View next : ViewGroupKt.getChildren(linearLayout)) {
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type android.view.ViewGroup");
            for (View next2 : ViewGroupKt.getChildren((ViewGroup) next)) {
                if (next2.getTag(R.id.sort) != null) {
                    boolean z2 = Intrinsics.areEqual(next2.getTag(R.id.sort), (Object) getSortTag(this.currentSort, this.currentSortDesc)) || (this.currentSort == 0 && Intrinsics.areEqual(next2.getTag(R.id.sort), (Object) getSortTag(1, this.currentSortDesc)));
                    next2.setSelected(z2);
                    Intrinsics.checkNotNull(next2, "null cannot be cast to non-null type android.widget.TextView");
                    ((TextView) next2).setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, z2 ? ContextCompat.getDrawable(requireActivity(), R.drawable.ic_check_large) : null, (Drawable) null);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void updateSorts$lambda$12$lambda$10(DisplaySettingsDialog displaySettingsDialog, int i, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.currentSort = i;
        displaySettingsDialog.currentSortDesc = false;
        displaySettingsDialog.updateSorts();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$updateSorts$1$1$1(displaySettingsDialog, i, (Continuation<? super DisplaySettingsDialog$updateSorts$1$1$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void updateSorts$lambda$12$lambda$11(DisplaySettingsDialog displaySettingsDialog, int i, View view) {
        Intrinsics.checkNotNullParameter(displaySettingsDialog, "this$0");
        displaySettingsDialog.currentSort = i;
        displaySettingsDialog.currentSortDesc = true;
        displaySettingsDialog.updateSorts();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(displaySettingsDialog), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$updateSorts$1$2$1(displaySettingsDialog, i, (Continuation<? super DisplaySettingsDialog$updateSorts$1$2$1>) null), 3, (Object) null);
    }

    private final void setupSortViews(SortDisplaySettingBinding sortDisplaySettingBinding, boolean z, int i, int i2, int i3, int i4) {
        sortDisplaySettingBinding.sortTitle.setText(getString(i));
        sortDisplaySettingBinding.sortAsc.setText(getString(i2));
        sortDisplaySettingBinding.sortDesc.setText(getString(i3));
        sortDisplaySettingBinding.sortIcon.setImageDrawable(ContextCompat.getDrawable(requireActivity(), i4));
        boolean z2 = true;
        sortDisplaySettingBinding.sortAsc.setSelected(z && !this.currentSortDesc);
        TextView textView = sortDisplaySettingBinding.sortDesc;
        if (!z || !this.currentSortDesc) {
            z2 = false;
        }
        textView.setSelected(z2);
    }

    private final String getSortTag(int i, boolean z) {
        StringBuilder sb;
        String str;
        if (z) {
            sb = new StringBuilder();
            sb.append(i);
            str = "_desc";
        } else {
            sb = new StringBuilder();
            sb.append(i);
            str = "_asc";
        }
        sb.append(str);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000ej\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog$VideoGroup;", "", "value", "", "title", "", "type", "Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "(Ljava/lang/String;ILjava/lang/String;ILorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;)V", "getTitle", "()I", "getType", "()Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "getValue", "()Ljava/lang/String;", "toString", "GROUP_BY_NAME", "GROUP_BY_FOLDER", "NO_GROUP", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DisplaySettingsDialog.kt */
    public enum VideoGroup {
        GROUP_BY_NAME(Constants.GROUP_VIDEOS_NAME, R.string.video_min_group_length_name, VideoGroupingType.NAME),
        GROUP_BY_FOLDER(Constants.GROUP_VIDEOS_FOLDER, R.string.video_min_group_length_folder, VideoGroupingType.FOLDER),
        NO_GROUP(Constants.GROUP_VIDEOS_NONE, R.string.video_min_group_length_disable, VideoGroupingType.NONE);
        
        public static final Companion Companion = null;
        private final int title;
        private final VideoGroupingType type;
        private final String value;

        public static EnumEntries<VideoGroup> getEntries() {
            return $ENTRIES;
        }

        private VideoGroup(String str, int i, VideoGroupingType videoGroupingType) {
            this.value = str;
            this.title = i;
            this.type = videoGroupingType;
        }

        public final int getTitle() {
            return this.title;
        }

        public final VideoGroupingType getType() {
            return this.type;
        }

        public final String getValue() {
            return this.value;
        }

        static {
            VideoGroup[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        public String toString() {
            String string = AppContextProvider.INSTANCE.getAppContext().getString(this.title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog$VideoGroup$Companion;", "", "()V", "findByValue", "Lorg/videolan/vlc/gui/dialogs/DisplaySettingsDialog$VideoGroup;", "value", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: DisplaySettingsDialog.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final VideoGroup findByValue(String str) {
                for (VideoGroup videoGroup : VideoGroup.values()) {
                    if (Intrinsics.areEqual((Object) str, (Object) videoGroup.getValue())) {
                        return videoGroup;
                    }
                }
                return VideoGroup.GROUP_BY_NAME;
            }
        }
    }
}
