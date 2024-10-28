package org.videolan.vlc.gui.browser;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.interfaces.Filterable;
import org.videolan.vlc.viewmodels.DisplaySettingsViewModel;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;
import org.videolan.vlc.viewmodels.MedialibraryViewModelKt;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\n\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\"H\u0016J\u0010\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020(H\u0016J\n\u0010)\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010*\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010+H&J\u0006\u0010,\u001a\u00020\"J\u0012\u0010-\u001a\u00020$2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00100\u001a\u00020$2\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020$H\u0016J\u0018\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020(2\u0006\u00106\u001a\u000207H\u0016J\u0010\u00108\u001a\u00020\"2\u0006\u00101\u001a\u000209H\u0016J\b\u0010:\u001a\u00020$H\u0016J\u0010\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020$H&J\b\u0010?\u001a\u00020$H\u0016J\u0010\u0010@\u001a\u00020$2\u0006\u0010A\u001a\u00020/H\u0016J\b\u0010B\u001a\u00020$H\u0016J\b\u0010C\u001a\u00020$H\u0016J\u001a\u0010D\u001a\u00020$2\u0006\u0010E\u001a\u00020\u00172\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u0010F\u001a\u00020$H\u0002J\u0010\u0010G\u001a\u00020\"2\u0006\u00101\u001a\u000202H\u0014J\u0016\u0010H\u001a\u00020$2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002020JH\u0014J\b\u0010K\u001a\u00020$H\u0016J\u0006\u0010L\u001a\u00020$J\b\u0010M\u001a\u00020$H\u0014J\u0010\u0010N\u001a\u00020$2\u0006\u0010O\u001a\u00020\"H\u0016J\u0010\u0010P\u001a\u00020$2\u0006\u0010Q\u001a\u00020\u0014H\u0016J\u0012\u0010R\u001a\u00020$2\b\b\u0002\u0010S\u001a\u00020\u0014H\u0016R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R&\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u0000@TX.¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006T"}, d2 = {"Lorg/videolan/vlc/gui/browser/MediaBrowserFragment;", "T", "Lorg/videolan/vlc/viewmodels/SortableModel;", "Lorg/videolan/vlc/gui/BaseFragment;", "Lorg/videolan/vlc/interfaces/Filterable;", "()V", "displaySettingsViewModel", "Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "getDisplaySettingsViewModel", "()Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "displaySettingsViewModel$delegate", "Lkotlin/Lazy;", "mediaLibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMediaLibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "setMediaLibrary", "(Lorg/videolan/medialibrary/interfaces/Medialibrary;)V", "savedSelection", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "searchButtonView", "Landroid/view/View;", "transition", "Landroidx/transition/ChangeBounds;", "<set-?>", "viewModel", "getViewModel", "()Lorg/videolan/vlc/viewmodels/SortableModel;", "setViewModel", "(Lorg/videolan/vlc/viewmodels/SortableModel;)V", "Lorg/videolan/vlc/viewmodels/SortableModel;", "allowedToExpand", "", "clear", "", "enableSearchOption", "filter", "query", "", "getFilterQuery", "getMultiHelper", "Lorg/videolan/tools/MultiSelectHelper;", "inSearchMode", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDeleteFailed", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onDestroy", "onDisplaySettingChanged", "key", "value", "", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onPause", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onRefresh", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "onViewCreated", "view", "releaseBreadCrumb", "removeItem", "removeItems", "items", "", "restoreList", "restoreMultiSelectHelper", "setBreadcrumb", "setSearchVisibility", "visible", "sortBy", "sort", "sortMenuTitles", "index", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserFragment.kt */
public abstract class MediaBrowserFragment<T extends SortableModel> extends BaseFragment implements Filterable {
    private final Lazy displaySettingsViewModel$delegate;
    public Medialibrary mediaLibrary;
    private ArrayList<Integer> savedSelection = new ArrayList<>();
    private View searchButtonView;
    private final ChangeBounds transition;
    protected T viewModel;

    public boolean allowedToExpand() {
        return true;
    }

    public void clear() {
    }

    public boolean enableSearchOption() {
        return true;
    }

    public abstract MultiSelectHelper<T> getMultiHelper();

    public void onDisplaySettingChanged(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(obj, "value");
    }

    public abstract void onRefresh();

    public MediaBrowserFragment() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new AccelerateDecelerateInterpolator());
        changeBounds.setDuration(300);
        this.transition = changeBounds;
        Fragment fragment = this;
        this.displaySettingsViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(DisplaySettingsViewModel.class), new MediaBrowserFragment$special$$inlined$activityViewModels$default$1(fragment), new MediaBrowserFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new MediaBrowserFragment$special$$inlined$activityViewModels$default$3(fragment));
    }

    public final Medialibrary getMediaLibrary() {
        Medialibrary medialibrary = this.mediaLibrary;
        if (medialibrary != null) {
            return medialibrary;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaLibrary");
        return null;
    }

    public final void setMediaLibrary(Medialibrary medialibrary) {
        Intrinsics.checkNotNullParameter(medialibrary, "<set-?>");
        this.mediaLibrary = medialibrary;
    }

    /* access modifiers changed from: private */
    public final DisplaySettingsViewModel getDisplaySettingsViewModel() {
        return (DisplaySettingsViewModel) this.displaySettingsViewModel$delegate.getValue();
    }

    public T getViewModel() {
        T t = this.viewModel;
        if (t != null) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    /* access modifiers changed from: protected */
    public void setViewModel(T t) {
        Intrinsics.checkNotNullParameter(t, "<set-?>");
        this.viewModel = t;
    }

    public void onCreate(Bundle bundle) {
        ArrayList<Integer> integerArrayList;
        super.onCreate(bundle);
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        setMediaLibrary(instance);
        if (bundle != null && (integerArrayList = bundle.getIntegerArrayList("key_selection")) != null) {
            this.savedSelection = integerArrayList;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.searchButton);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.searchButtonView = findViewById;
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new MediaBrowserFragment$onViewCreated$1(this, (Continuation<? super MediaBrowserFragment$onViewCreated$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void setBreadcrumb() {
        FragmentActivity activity = getActivity();
        RecyclerView recyclerView = activity != null ? (RecyclerView) activity.findViewById(R.id.ariane) : null;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
    }

    private final void releaseBreadCrumb() {
        FragmentActivity activity = getActivity();
        RecyclerView recyclerView = activity != null ? (RecyclerView) activity.findViewById(R.id.ariane) : null;
        if (recyclerView != null) {
            recyclerView.setAdapter((RecyclerView.Adapter) null);
        }
    }

    public void onStart() {
        super.onStart();
        setBreadcrumb();
    }

    public void onStop() {
        super.onStop();
        releaseBreadCrumb();
    }

    public void onResume() {
        super.onResume();
        SortableModel viewModel2 = getViewModel();
        MedialibraryViewModel medialibraryViewModel = viewModel2 instanceof MedialibraryViewModel ? (MedialibraryViewModel) viewModel2 : null;
        if (medialibraryViewModel != null) {
            medialibraryViewModel.resume();
        }
    }

    public void onPause() {
        super.onPause();
        SortableModel viewModel2 = getViewModel();
        MedialibraryViewModel medialibraryViewModel = viewModel2 instanceof MedialibraryViewModel ? (MedialibraryViewModel) viewModel2 : null;
        if (medialibraryViewModel != null) {
            medialibraryViewModel.pause();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.savedSelection.clear();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        MultiSelectHelper multiHelper = getMultiHelper();
        if (multiHelper != null) {
            bundle.putIntegerArrayList("key_selection", multiHelper.getSelectionMap());
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void removeItems(List<? extends MediaLibraryItem> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        if (list.size() == 1) {
            removeItem((MediaLibraryItem) list.get(0));
            return;
        }
        ConfirmDeleteDialog newInstance$default = ConfirmDeleteDialog.Companion.newInstance$default(ConfirmDeleteDialog.Companion, new ArrayList(list), (String) null, (String) null, (String) null, 14, (Object) null);
        newInstance$default.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ConfirmDeleteDialog.class).getSimpleName());
        newInstance$default.setListener(new MediaBrowserFragment$removeItems$1(list, this));
    }

    /* access modifiers changed from: protected */
    public boolean removeItem(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        ConfirmDeleteDialog newInstance$default = ConfirmDeleteDialog.Companion.newInstance$default(ConfirmDeleteDialog.Companion, CollectionsKt.arrayListOf(mediaLibraryItem), (String) null, (String) null, (String) null, 14, (Object) null);
        newInstance$default.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ConfirmDeleteDialog.class).getSimpleName());
        newInstance$default.setListener(new MediaBrowserFragment$removeItem$1(this, mediaLibraryItem));
        return true;
    }

    /* access modifiers changed from: private */
    public final void onDeleteFailed(MediaLibraryItem mediaLibraryItem) {
        if (isAdded()) {
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            String string = getString(R.string.msg_delete_failed, mediaLibraryItem.getTitle());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            uiTools.snacker(requireActivity, string);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        SortableModel viewModel2 = getViewModel();
        MedialibraryViewModel medialibraryViewModel = viewModel2 instanceof MedialibraryViewModel ? (MedialibraryViewModel) viewModel2 : null;
        if (medialibraryViewModel != null) {
            MedialibraryViewModelKt.prepareOptionsMenu(medialibraryViewModel, menu);
        }
        sortMenuTitles$default(this, 0, 1, (Object) null);
    }

    public static /* synthetic */ void sortMenuTitles$default(MediaBrowserFragment mediaBrowserFragment, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                i = 0;
            }
            mediaBrowserFragment.sortMenuTitles(i);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sortMenuTitles");
    }

    public void sortMenuTitles(int i) {
        Menu menu = getMenu();
        if (menu != null) {
            SortableModel viewModel2 = getViewModel();
            MedialibraryViewModel medialibraryViewModel = viewModel2 instanceof MedialibraryViewModel ? (MedialibraryViewModel) viewModel2 : null;
            if (medialibraryViewModel != null) {
                MedialibraryViewModelKt.sortMenuTitles(medialibraryViewModel, menu, i);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.ml_menu_sortby_name) {
            sortBy(1);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_filename) {
            sortBy(10);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_length) {
            sortBy(2);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_date) {
            sortBy(5);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_last_modified) {
            sortBy(4);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_insertion_date) {
            sortBy(3);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_artist_name) {
            sortBy(7);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_album_name) {
            sortBy(9);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_media_number) {
            sortBy(15);
            return true;
        } else if (itemId != R.id.ml_menu_sortby_number) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            sortBy(6);
            return super.onOptionsItemSelected(menuItem);
        }
    }

    public void sortBy(int i) {
        getViewModel().sort(i);
    }

    public final void restoreMultiSelectHelper() {
        MultiSelectHelper multiHelper = getMultiHelper();
        if (multiHelper != null) {
            if (this.savedSelection.size() > 0) {
                int size = this.savedSelection.size();
                boolean z = false;
                for (int i = 0; i < size; i++) {
                    multiHelper.getSelectionMap().add(this.savedSelection.get(i));
                    z = !this.savedSelection.isEmpty();
                }
                if (z) {
                    startActionMode();
                }
                this.savedSelection.clear();
            }
            if (getActionMode() != null) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), (CoroutineStart) null, new MediaBrowserFragment$restoreMultiSelectHelper$1$1(this, multiHelper, (Continuation<? super MediaBrowserFragment$restoreMultiSelectHelper$1$1>) null), 2, (Object) null);
            }
        }
    }

    public void filter(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        getViewModel().filter(str);
    }

    public void restoreList() {
        getViewModel().restore();
    }

    public String getFilterQuery() {
        return getViewModel().getFilterQuery();
    }

    public void setSearchVisibility(boolean z) {
        View view = this.searchButtonView;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchButtonView");
            view = null;
        }
        int i = 0;
        if ((view.getVisibility() == 0) != z) {
            View view3 = this.searchButtonView;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchButtonView");
                view3 = null;
            }
            if (view3.getParent() instanceof ConstraintLayout) {
                View view4 = this.searchButtonView;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("searchButtonView");
                } else {
                    view2 = view4;
                }
                ViewParent parent = view2.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                ConstraintLayout constraintLayout = (ConstraintLayout) parent;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                int i2 = R.id.searchButton;
                if (!z) {
                    i = 8;
                }
                constraintSet.setVisibility(i2, i);
                this.transition.excludeChildren((Class<?>) RecyclerView.class, true);
                TransitionManager.beginDelayedTransition(constraintLayout, this.transition);
                constraintSet.applyTo(constraintLayout);
                return;
            }
            View view5 = this.searchButtonView;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchButtonView");
            } else {
                view2 = view5;
            }
            if (!z) {
                i = 8;
            }
            view2.setVisibility(i);
        }
    }

    public final boolean inSearchMode() {
        View view = this.searchButtonView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchButtonView");
            view = null;
        }
        return view.getVisibility() == 0;
    }
}
