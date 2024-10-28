package org.videolan.vlc.gui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.KeyHelper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.MediaBrowserFragment;
import org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.helpers.Click;
import org.videolan.vlc.gui.helpers.ImageClick;
import org.videolan.vlc.gui.helpers.LongClick;
import org.videolan.vlc.gui.helpers.SimpleClick;
import org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback;
import org.videolan.vlc.interfaces.IHistory;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.interfaces.IRefreshable;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.HistoryModel;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\tH\u0016J\u0016\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u0014J\u0012\u0010$\u001a\u00020\u00162\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0018\u0010'\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)2\u0006\u0010+\u001a\u00020,H\u0016J&\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010+\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u00102\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0016\u00103\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u0014J\u0018\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020#H\u0016J\u0010\u00107\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\tH\u0016J\u0018\u00108\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u00109\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010:\u001a\u00020\u0016H\u0016J\u0018\u0010;\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\u0016H\u0016J\u0010\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020@H\u0016J\u001a\u0010A\u001a\u00020\u00162\u0006\u0010B\u001a\u00020.2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010C\u001a\u00020\u0016H\u0016J\u0010\u0010D\u001a\u00020\u00162\u0006\u0010E\u001a\u00020\u001cH\u0016J\b\u0010F\u001a\u00020\u0016H\u0002J\f\u0010G\u001a\u00020\u0016*\u00020HH\u0002R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X.¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lorg/videolan/vlc/gui/HistoryFragment;", "Lorg/videolan/vlc/gui/browser/MediaBrowserFragment;", "Lorg/videolan/vlc/viewmodels/HistoryModel;", "Lorg/videolan/vlc/interfaces/IRefreshable;", "Lorg/videolan/vlc/interfaces/IHistory;", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$OnRefreshListener;", "Lorg/videolan/vlc/interfaces/IListEventsHandler;", "()V", "cleanMenuItem", "Landroid/view/MenuItem;", "empty", "Landroid/widget/TextView;", "historyAdapter", "Lorg/videolan/vlc/gui/HistoryAdapter;", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "list", "Landroidx/recyclerview/widget/RecyclerView;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "clear", "", "clearHistory", "getMultiHelper", "getTitle", "", "isEmpty", "", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "onClick", "position", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateOptionsMenu", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyActionMode", "onLongClick", "onMove", "oldPosition", "newPosition", "onOptionsItemSelected", "onPrepareActionMode", "onPrepareOptionsMenu", "onRefresh", "onRemove", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onStart", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onViewCreated", "view", "refresh", "setFabPlayVisibility", "enable", "updateEmptyView", "process", "Lorg/videolan/vlc/gui/helpers/Click;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HistoryFragment.kt */
public final class HistoryFragment extends MediaBrowserFragment<HistoryModel> implements IRefreshable, IHistory, SwipeRefreshLayout.OnRefreshListener, IListEventsHandler {
    /* access modifiers changed from: private */
    public MenuItem cleanMenuItem;
    private TextView empty;
    /* access modifiers changed from: private */
    public final HistoryAdapter historyAdapter;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView list;
    /* access modifiers changed from: private */
    public MultiSelectHelper<MediaWrapper> multiSelectHelper;

    public void clear() {
    }

    public void onMove(int i, int i2) {
    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public HistoryFragment() {
        HistoryAdapter historyAdapter2 = new HistoryAdapter(false, this, 1, (DefaultConstructorMarker) null);
        historyAdapter2.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        this.historyAdapter = historyAdapter2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        setViewModel((SortableModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new HistoryModel.Factory(requireContext)).get(HistoryModel.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.history_list, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.list);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.list = (RecyclerView) findViewById;
        View findViewById2 = view.findViewById(R.id.empty);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.empty = (TextView) findViewById2;
        ((HistoryModel) getViewModel()).getDataset().observe(getViewLifecycleOwner(), new HistoryFragmentKt$sam$androidx_lifecycle_Observer$0(new HistoryFragment$onViewCreated$1(this)));
        ((HistoryModel) getViewModel()).getLoading().observe(getViewLifecycleOwner(), new HistoryFragmentKt$sam$androidx_lifecycle_Observer$0(new HistoryFragment$onViewCreated$2(this)));
        this.historyAdapter.getUpdateEvt().observe(getViewLifecycleOwner(), new HistoryFragmentKt$sam$androidx_lifecycle_Observer$0(new HistoryFragment$onViewCreated$3(this)));
        RecyclerView recyclerView = null;
        KextensionsKt.launchWhenStarted(FlowKt.onEach(this.historyAdapter.getEvents(), new HistoryFragment$onViewCreated$4(this, (Continuation<? super HistoryFragment$onViewCreated$4>) null)), LifecycleOwnerKt.getLifecycleScope(this));
        RecyclerView recyclerView2 = this.list;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView recyclerView3 = this.list;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView3 = null;
        }
        recyclerView3.setAdapter(this.historyAdapter);
        RecyclerView recyclerView4 = this.list;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView4 = null;
        }
        recyclerView4.setNextFocusUpId(R.id.ml_menu_search);
        RecyclerView recyclerView5 = this.list;
        if (recyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView5 = null;
        }
        recyclerView5.setNextFocusLeftId(16908298);
        RecyclerView recyclerView6 = this.list;
        if (recyclerView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView6 = null;
        }
        recyclerView6.setNextFocusRightId(16908298);
        RecyclerView recyclerView7 = this.list;
        if (recyclerView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView7 = null;
        }
        recyclerView7.setNextFocusForwardId(16908298);
        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(new SwipeDragItemTouchHelperCallback(this.historyAdapter, false, false, 6, (DefaultConstructorMarker) null));
        this.itemTouchHelper = itemTouchHelper2;
        RecyclerView recyclerView8 = this.list;
        if (recyclerView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView8 = null;
        }
        itemTouchHelper2.attachToRecyclerView(recyclerView8);
        this.multiSelectHelper = this.historyAdapter.getMultiSelectHelper();
        RecyclerView recyclerView9 = this.list;
        if (recyclerView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            recyclerView9 = null;
        }
        recyclerView9.requestFocus();
        RecyclerView recyclerView10 = this.list;
        if (recyclerView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        } else {
            recyclerView = recyclerView10;
        }
        registerForContextMenu(recyclerView);
        getSwipeRefreshLayout().setOnRefreshListener(this);
    }

    public void onStart() {
        super.onStart();
        ((HistoryModel) getViewModel()).refresh();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        menuInflater.inflate(R.menu.fragment_option_history, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
        MenuItem findItem = menu.findItem(R.id.ml_menu_clean);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        this.cleanMenuItem = findItem;
        if (findItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cleanMenuItem");
            findItem = null;
        }
        findItem.setVisible(!isEmpty());
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuItem findItem = menu.findItem(R.id.ml_menu_clean);
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        findItem.setVisible(((SharedPreferences) settings.getInstance(requireActivity)).getBoolean(SettingsKt.PLAYBACK_HISTORY, true));
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.ml_menu_clean) {
            return super.onOptionsItemSelected(menuItem);
        }
        ConfirmDeleteDialog.Companion companion = ConfirmDeleteDialog.Companion;
        String string = getString(R.string.clear_playback_history);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(R.string.clear_history_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(R.string.clear_history);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        ConfirmDeleteDialog newInstance$default = ConfirmDeleteDialog.Companion.newInstance$default(companion, (ArrayList) null, string, string2, string3, 1, (Object) null);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        newInstance$default.show(activity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
        newInstance$default.setListener(new HistoryFragment$onOptionsItemSelected$1(this));
        return true;
    }

    public void setFabPlayVisibility(boolean z) {
        if (getFabPlay() != null) {
            FloatingActionButton fabPlay = getFabPlay();
            Intrinsics.checkNotNull(fabPlay);
            fabPlay.hide();
        }
    }

    public void refresh() {
        ((HistoryModel) getViewModel()).refresh();
    }

    public void onRefresh() {
        refresh();
    }

    public String getTitle() {
        String string = getString(R.string.history);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public MultiSelectHelper<HistoryModel> getMultiHelper() {
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.historyAdapter.getMultiSelectHelper();
        if (multiSelectHelper2 instanceof MultiSelectHelper) {
            return multiSelectHelper2;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final void updateEmptyView() {
        TextView textView = null;
        if (((HistoryModel) getViewModel()).isEmpty()) {
            getSwipeRefreshLayout().setVisibility(8);
            TextView textView2 = this.empty;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("empty");
            } else {
                textView = textView2;
            }
            textView.setVisibility(0);
            return;
        }
        TextView textView3 = this.empty;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("empty");
        } else {
            textView = textView3;
        }
        textView.setVisibility(8);
        getSwipeRefreshLayout().setVisibility(0);
    }

    public boolean isEmpty() {
        return this.historyAdapter.isEmpty();
    }

    public void clearHistory() {
        getMediaLibrary().clearHistory(0);
        ((HistoryModel) getViewModel()).clearHistory();
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        ((SharedPreferences) settings.getInstance(requireActivity)).edit().remove(Constants.KEY_AUDIO_LAST_PLAYLIST).remove("media_list").apply();
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        multiSelectHelper2.toggleActionMode(true, this.historyAdapter.getItemCount());
        actionMode.getMenuInflater().inflate(R.menu.action_mode_history, menu);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
        MultiSelectHelper<MediaWrapper> multiSelectHelper3 = null;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        int selectionCount = multiSelectHelper2.getSelectionCount();
        boolean z = false;
        if (selectionCount == 0) {
            stopActionMode();
            return false;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new HistoryFragment$onPrepareActionMode$1(this, actionMode, (Continuation<? super HistoryFragment$onPrepareActionMode$1>) null), 3, (Object) null);
        menu.findItem(R.id.action_history_info).setVisible(selectionCount == 1);
        menu.findItem(R.id.action_history_append).setVisible(PlaylistManager.Companion.hasMedia());
        MenuItem findItem = menu.findItem(R.id.action_go_to_folder);
        if (selectionCount == 1) {
            MultiSelectHelper<MediaWrapper> multiSelectHelper4 = this.multiSelectHelper;
            if (multiSelectHelper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            } else {
                multiSelectHelper3 = multiSelectHelper4;
            }
            if (KotlinExtensionsKt.retrieveParent(((MediaWrapper) CollectionsKt.first(multiSelectHelper3.getSelection())).getUri()) != null) {
                z = true;
            }
        }
        findItem.setVisible(z);
        return true;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (!KotlinExtensionsKt.isStarted(this)) {
            return false;
        }
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        List<MediaWrapper> selection = multiSelectHelper2.getSelection();
        if (!selection.isEmpty()) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.action_history_play) {
                MediaUtils.openList$default(MediaUtils.INSTANCE, getActivity(), selection, 0, false, 8, (Object) null);
            } else if (itemId == R.id.action_history_append) {
                MediaUtils.INSTANCE.appendMedia((Context) getActivity(), (List<? extends MediaWrapper>) selection);
            } else if (itemId == R.id.action_history_info) {
                showInfoDialog((MediaLibraryItem) CollectionsKt.first(selection));
            } else if (itemId == R.id.action_go_to_folder) {
                KextensionsKt.showParentFolder(this, (MediaWrapper) CollectionsKt.first(selection));
            } else {
                stopActionMode();
                return false;
            }
        }
        stopActionMode();
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
        MultiSelectHelper<MediaWrapper> multiSelectHelper3 = null;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        multiSelectHelper2.toggleActionMode(false, this.historyAdapter.getItemCount());
        setActionMode((ActionMode) null);
        MultiSelectHelper<MediaWrapper> multiSelectHelper4 = this.multiSelectHelper;
        if (multiSelectHelper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
        } else {
            multiSelectHelper3 = multiSelectHelper4;
        }
        multiSelectHelper3.clearSelection();
    }

    /* access modifiers changed from: private */
    public final void process(Click click) {
        MediaWrapper mediaWrapper = (MediaWrapper) ((HistoryModel) getViewModel()).getDataset().get(click.getPosition());
        if (click instanceof SimpleClick) {
            onClick(click.getPosition(), mediaWrapper);
        } else if (click instanceof LongClick) {
            onLongClick(click.getPosition(), mediaWrapper);
        } else if (!(click instanceof ImageClick)) {
        } else {
            if (getActionMode() != null) {
                onClick(click.getPosition(), mediaWrapper);
            } else {
                onLongClick(click.getPosition(), mediaWrapper);
            }
        }
    }

    public final void onClick(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        if (KeyHelper.INSTANCE.isShiftPressed() && getActionMode() == null) {
            onLongClick(i, mediaWrapper);
        } else if (getActionMode() != null) {
            MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
            if (multiSelectHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                multiSelectHelper2 = null;
            }
            MultiSelectHelper.toggleSelection$default(multiSelectHelper2, i, false, 2, (Object) null);
            this.historyAdapter.notifyItemChanged(i, mediaWrapper);
            invalidateActionMode();
        } else {
            if (i != 0) {
                ((HistoryModel) getViewModel()).moveUp(mediaWrapper);
            }
            MediaUtils.INSTANCE.openMedia(requireContext(), mediaWrapper);
        }
    }

    public final void onLongClick(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        multiSelectHelper2.toggleSelection(i, true);
        this.historyAdapter.notifyItemChanged(i, mediaWrapper);
        if (getActionMode() == null) {
            startActionMode();
        }
        invalidateActionMode();
    }

    public void onRemove(int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        ((HistoryModel) getViewModel()).removeFromHistory((MediaWrapper) mediaLibraryItem);
    }
}
