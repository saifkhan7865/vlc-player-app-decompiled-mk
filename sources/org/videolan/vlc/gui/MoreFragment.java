package org.videolan.vlc.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.Dialog;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KeyHelper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.Click;
import org.videolan.vlc.gui.helpers.ImageClick;
import org.videolan.vlc.gui.helpers.LongClick;
import org.videolan.vlc.gui.helpers.SimpleClick;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.network.IStreamsFragmentDelegate;
import org.videolan.vlc.gui.network.KeyboardListener;
import org.videolan.vlc.gui.network.MRLAdapter;
import org.videolan.vlc.gui.network.MrlAction;
import org.videolan.vlc.gui.network.StreamsFragmentDelegate;
import org.videolan.vlc.gui.preferences.PreferencesActivity;
import org.videolan.vlc.gui.view.TitleListView;
import org.videolan.vlc.interfaces.IHistory;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.interfaces.IRefreshable;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.IDialogManager;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.HistoryModel;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020!2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010&\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0012H\u0002J\b\u0010'\u001a\u00020(H\u0016J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0001J\b\u0010,\u001a\u00020-H\u0014J\b\u0010.\u001a\u00020-H\u0016J\b\u0010/\u001a\u00020!H\u0002J\u001c\u00100\u001a\u00020-2\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0016\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u0013J\u0012\u00107\u001a\u00020!2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u001c\u0010:\u001a\u00020-2\b\u00101\u001a\u0004\u0018\u0001022\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J&\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010B2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0019\u0010C\u001a\u00020!2\u0006\u00106\u001a\u00020\u00162\u0006\u0010D\u001a\u00020EH\u0001J\u0012\u0010F\u001a\u00020!2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0016\u0010G\u001a\u00020!2\u0006\u00106\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u0013J\u0018\u0010H\u001a\u00020-2\u0006\u00101\u001a\u0002022\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010I\u001a\u00020!2\u0006\u0010J\u001a\u000209H\u0016J\b\u0010K\u001a\u00020!H\u0016J\u001a\u0010L\u001a\u00020!2\u0006\u0010M\u001a\u00020>2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0011\u0010N\u001a\u00020!2\u0006\u0010O\u001a\u00020\u0013H\u0001J\b\u0010P\u001a\u00020!H\u0016J\b\u0010Q\u001a\u00020!H\u0002J!\u0010R\u001a\u00020!2\u0006\u0010S\u001a\u00020T2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010U\u001a\u00020VH\u0001J\u0011\u0010W\u001a\u00020!2\u0006\u00106\u001a\u00020\u0016H\u0001J\f\u0010X\u001a\u00020!*\u00020YH\u0002R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lorg/videolan/vlc/gui/MoreFragment;", "Lorg/videolan/vlc/gui/BaseFragment;", "Lorg/videolan/vlc/interfaces/IRefreshable;", "Lorg/videolan/vlc/interfaces/IHistory;", "Lorg/videolan/vlc/util/IDialogManager;", "Lorg/videolan/vlc/gui/network/IStreamsFragmentDelegate;", "()V", "aboutButton", "Landroid/widget/Button;", "dialogsDelegate", "Lorg/videolan/vlc/util/DialogDelegate;", "donationsButton", "Landroidx/cardview/widget/CardView;", "historyAdapter", "Lorg/videolan/vlc/gui/HistoryAdapter;", "historyEntry", "Lorg/videolan/vlc/gui/view/TitleListView;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "savedSelection", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "settingsButton", "streamsAdapter", "Lorg/videolan/vlc/gui/network/MRLAdapter;", "streamsEntry", "streamsViewModel", "Lorg/videolan/vlc/viewmodels/StreamsModel;", "viewModel", "Lorg/videolan/vlc/viewmodels/HistoryModel;", "clearHistory", "", "dialogCanceled", "dialog", "Lorg/videolan/libvlc/Dialog;", "fireDialog", "getMultiHelper", "getTitle", "", "getlistEventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/network/MrlAction;", "hasFAB", "", "isEmpty", "manageDonationVisibility", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onClick", "position", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroyActionMode", "onLongClick", "onPrepareActionMode", "onSaveInstanceState", "outState", "onStart", "onViewCreated", "view", "playMedia", "mw", "refresh", "restoreMultiSelectHelper", "setup", "fragment", "Landroidx/fragment/app/Fragment;", "keyboardListener", "Lorg/videolan/vlc/gui/network/KeyboardListener;", "showContext", "process", "Lorg/videolan/vlc/gui/helpers/Click;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoreFragment.kt */
public final class MoreFragment extends BaseFragment implements IRefreshable, IHistory, IDialogManager, IStreamsFragmentDelegate {
    private final /* synthetic */ StreamsFragmentDelegate $$delegate_0 = new StreamsFragmentDelegate();
    private Button aboutButton;
    private final DialogDelegate dialogsDelegate = new DialogDelegate();
    private CardView donationsButton;
    /* access modifiers changed from: private */
    public final HistoryAdapter historyAdapter = new HistoryAdapter(true, (IListEventsHandler) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public TitleListView historyEntry;
    private MultiSelectHelper<MediaWrapper> multiSelectHelper;
    private ArrayList<Integer> savedSelection = new ArrayList<>();
    private Button settingsButton;
    /* access modifiers changed from: private */
    public MRLAdapter streamsAdapter;
    /* access modifiers changed from: private */
    public TitleListView streamsEntry;
    private StreamsModel streamsViewModel;
    private HistoryModel viewModel;

    public void dialogCanceled(Dialog dialog) {
    }

    public SendChannel<MrlAction> getlistEventActor() {
        return this.$$delegate_0.getlistEventActor();
    }

    /* access modifiers changed from: protected */
    public boolean hasFAB() {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        this.$$delegate_0.onCtxAction(i, contextOption);
    }

    public void playMedia(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        this.$$delegate_0.playMedia(mediaWrapper);
    }

    public void setup(Fragment fragment, StreamsModel streamsModel, KeyboardListener keyboardListener) {
        Intrinsics.checkNotNullParameter(fragment, SecondaryActivity.KEY_FRAGMENT);
        Intrinsics.checkNotNullParameter(streamsModel, "viewModel");
        Intrinsics.checkNotNullParameter(keyboardListener, "keyboardListener");
        this.$$delegate_0.setup(fragment, streamsModel, keyboardListener);
    }

    public void showContext(int i) {
        this.$$delegate_0.showContext(i);
    }

    private final MultiSelectHelper<HistoryModel> getMultiHelper() {
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.historyAdapter.getMultiSelectHelper();
        if (multiSelectHelper2 instanceof MultiSelectHelper) {
            return multiSelectHelper2;
        }
        return null;
    }

    public void onCreate(Bundle bundle) {
        ArrayList<Integer> integerArrayList;
        super.onCreate(bundle);
        if (!(bundle == null || (integerArrayList = bundle.getIntegerArrayList("key_selection")) == null)) {
            this.savedSelection = integerArrayList;
        }
        this.dialogsDelegate.observeDialogs(this, this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.viewModel = (HistoryModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new HistoryModel.Factory(requireContext)).get(HistoryModel.class);
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
        this.streamsViewModel = (StreamsModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity2, (ViewModelProvider.Factory) new StreamsModel.Factory(requireContext2, true)).get(StreamsModel.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.more_fragment, viewGroup, false);
    }

    public String getTitle() {
        String string = getString(R.string.history);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.history_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.historyEntry = (TitleListView) findViewById;
        View findViewById2 = view.findViewById(R.id.settingsButton);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.settingsButton = (Button) findViewById2;
        View findViewById3 = view.findViewById(R.id.aboutButton);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.aboutButton = (Button) findViewById3;
        View findViewById4 = view.findViewById(R.id.donationsButton);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.donationsButton = (CardView) findViewById4;
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        TitleListView titleListView = null;
        if (!((SharedPreferences) settings.getInstance(requireActivity)).getBoolean(SettingsKt.PLAYBACK_HISTORY, true)) {
            TitleListView titleListView2 = this.historyEntry;
            if (titleListView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
                titleListView2 = null;
            }
            KotlinExtensionsKt.setGone(titleListView2);
        }
        HistoryModel historyModel = this.viewModel;
        if (historyModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            historyModel = null;
        }
        historyModel.getDataset().observe(getViewLifecycleOwner(), new MoreFragmentKt$sam$androidx_lifecycle_Observer$0(new MoreFragment$onViewCreated$1(this)));
        HistoryModel historyModel2 = this.viewModel;
        if (historyModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            historyModel2 = null;
        }
        historyModel2.getLoading().observe(getViewLifecycleOwner(), new MoreFragmentKt$sam$androidx_lifecycle_Observer$0(new MoreFragment$onViewCreated$2(this)));
        KextensionsKt.launchWhenStarted(FlowKt.onEach(this.historyAdapter.getEvents(), new MoreFragment$onViewCreated$3(this, (Continuation<? super MoreFragment$onViewCreated$3>) null)), LifecycleOwnerKt.getLifecycleScope(this));
        View findViewById5 = view.findViewById(R.id.streams_entry);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.streamsEntry = (TitleListView) findViewById5;
        Fragment fragment = this;
        StreamsModel streamsModel = this.streamsViewModel;
        if (streamsModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsViewModel");
            streamsModel = null;
        }
        setup(fragment, streamsModel, new MoreFragment$onViewCreated$4());
        MRLAdapter mRLAdapter = new MRLAdapter(getlistEventActor(), true);
        this.streamsAdapter = mRLAdapter;
        mRLAdapter.setOnDummyClickListener(new MoreFragment$onViewCreated$5(this));
        StreamsModel streamsModel2 = this.streamsViewModel;
        if (streamsModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsViewModel");
            streamsModel2 = null;
        }
        streamsModel2.getDataset().observe(getViewLifecycleOwner(), new MoreFragmentKt$sam$androidx_lifecycle_Observer$0(new MoreFragment$onViewCreated$6(this)));
        StreamsModel streamsModel3 = this.streamsViewModel;
        if (streamsModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsViewModel");
            streamsModel3 = null;
        }
        streamsModel3.getLoading().observe(getViewLifecycleOwner(), new MoreFragmentKt$sam$androidx_lifecycle_Observer$0(new MoreFragment$onViewCreated$7(this)));
        TitleListView titleListView3 = this.streamsEntry;
        if (titleListView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsEntry");
            titleListView3 = null;
        }
        KotlinExtensionsKt.setVisible(titleListView3.getActionButton());
        TitleListView titleListView4 = this.streamsEntry;
        if (titleListView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsEntry");
            titleListView4 = null;
        }
        titleListView4.setOnActionClickListener(new MoreFragment$onViewCreated$8(this));
        Button button = this.settingsButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settingsButton");
            button = null;
        }
        button.setOnClickListener(new MoreFragment$$ExternalSyntheticLambda0(this));
        Button button2 = this.aboutButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aboutButton");
            button2 = null;
        }
        button2.setOnClickListener(new MoreFragment$$ExternalSyntheticLambda1(this));
        manageDonationVisibility();
        CardView cardView = this.donationsButton;
        if (cardView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("donationsButton");
            cardView = null;
        }
        cardView.setOnClickListener(new MoreFragment$$ExternalSyntheticLambda2(this));
        TitleListView titleListView5 = this.historyEntry;
        if (titleListView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView5 = null;
        }
        titleListView5.getList().setAdapter(this.historyAdapter);
        TitleListView titleListView6 = this.historyEntry;
        if (titleListView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView6 = null;
        }
        titleListView6.getList().setNextFocusUpId(R.id.ml_menu_search);
        TitleListView titleListView7 = this.historyEntry;
        if (titleListView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView7 = null;
        }
        titleListView7.getList().setNextFocusLeftId(16908298);
        TitleListView titleListView8 = this.historyEntry;
        if (titleListView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView8 = null;
        }
        titleListView8.getList().setNextFocusRightId(16908298);
        TitleListView titleListView9 = this.historyEntry;
        if (titleListView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView9 = null;
        }
        titleListView9.getList().setNextFocusForwardId(16908298);
        TitleListView titleListView10 = this.streamsEntry;
        if (titleListView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsEntry");
            titleListView10 = null;
        }
        RecyclerView list = titleListView10.getList();
        MRLAdapter mRLAdapter2 = this.streamsAdapter;
        if (mRLAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsAdapter");
            mRLAdapter2 = null;
        }
        list.setAdapter(mRLAdapter2);
        TitleListView titleListView11 = this.historyEntry;
        if (titleListView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView11 = null;
        }
        titleListView11.setOnActionClickListener(new MoreFragment$onViewCreated$12(this));
        this.multiSelectHelper = this.historyAdapter.getMultiSelectHelper();
        TitleListView titleListView12 = this.historyEntry;
        if (titleListView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
            titleListView12 = null;
        }
        titleListView12.getList().requestFocus();
        TitleListView titleListView13 = this.historyEntry;
        if (titleListView13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
        } else {
            titleListView = titleListView13;
        }
        registerForContextMenu(titleListView.getList());
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(MoreFragment moreFragment, View view) {
        Intrinsics.checkNotNullParameter(moreFragment, "this$0");
        moreFragment.requireActivity().startActivityForResult(new Intent(moreFragment.requireActivity(), PreferencesActivity.class), 1);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(MoreFragment moreFragment, View view) {
        Intrinsics.checkNotNullParameter(moreFragment, "this$0");
        Intent intent = new Intent(moreFragment.requireActivity(), SecondaryActivity.class);
        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.ABOUT);
        moreFragment.requireActivity().startActivityForResult(intent, 3);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(MoreFragment moreFragment, View view) {
        Intrinsics.checkNotNullParameter(moreFragment, "this$0");
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = moreFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        uiTools.showDonations(requireActivity);
    }

    private final void manageDonationVisibility() {
        getActivity();
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStart() {
        /*
            r3 = this;
            super.onStart()
            org.videolan.vlc.viewmodels.HistoryModel r0 = r3.viewModel
            r1 = 0
            if (r0 != 0) goto L_0x000e
            java.lang.String r0 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x000e:
            r0.refresh()
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r2 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            if (r2 == 0) goto L_0x001c
            r1 = r0
            org.videolan.vlc.gui.ContentActivity r1 = (org.videolan.vlc.gui.ContentActivity) r1
        L_0x001c:
            if (r1 == 0) goto L_0x0022
            r0 = 0
            r1.setTabLayoutVisibility(r0)
        L_0x0022:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.MoreFragment.onStart():void");
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        MultiSelectHelper<HistoryModel> multiHelper = getMultiHelper();
        if (multiHelper != null) {
            bundle.putIntegerArrayList("key_selection", multiHelper.getSelectionMap());
        }
        super.onSaveInstanceState(bundle);
    }

    public void refresh() {
        HistoryModel historyModel = this.viewModel;
        if (historyModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            historyModel = null;
        }
        historyModel.refresh();
    }

    public boolean isEmpty() {
        return this.historyAdapter.isEmpty();
    }

    public void clearHistory() {
        HistoryModel historyModel = this.viewModel;
        if (historyModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            historyModel = null;
        }
        historyModel.clearHistory();
        Medialibrary.getInstance().clearHistory(0);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
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
        actionMode.setTitle((CharSequence) requireActivity().getString(R.string.selection_count, new Object[]{Integer.valueOf(selectionCount)}));
        MenuItem findItem = menu.findItem(R.id.action_history_info);
        if (selectionCount == 1) {
            z = true;
        }
        findItem.setVisible(z);
        menu.findItem(R.id.action_history_append).setVisible(PlaylistManager.Companion.hasMedia());
        return true;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (!KotlinExtensionsKt.isStarted(this)) {
            return false;
        }
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
        Integer num = null;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        List<MediaWrapper> selection = multiSelectHelper2.getSelection();
        if (!selection.isEmpty()) {
            if (menuItem != null) {
                num = Integer.valueOf(menuItem.getItemId());
            }
            int i = R.id.action_history_play;
            if (num != null && num.intValue() == i) {
                MediaUtils.openList$default(MediaUtils.INSTANCE, getActivity(), selection, 0, false, 8, (Object) null);
            } else {
                int i2 = R.id.action_history_append;
                if (num != null && num.intValue() == i2) {
                    MediaUtils.INSTANCE.appendMedia((Context) getActivity(), (List<? extends MediaWrapper>) selection);
                } else {
                    int i3 = R.id.action_history_info;
                    if (num != null && num.intValue() == i3) {
                        showInfoDialog(selection.get(0));
                    } else {
                        stopActionMode();
                        return false;
                    }
                }
            }
        }
        stopActionMode();
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        MultiSelectHelper<MediaWrapper> multiSelectHelper2 = null;
        setActionMode((ActionMode) null);
        MultiSelectHelper<MediaWrapper> multiSelectHelper3 = this.multiSelectHelper;
        if (multiSelectHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
        } else {
            multiSelectHelper2 = multiSelectHelper3;
        }
        multiSelectHelper2.clearSelection();
    }

    /* access modifiers changed from: private */
    public final void restoreMultiSelectHelper() {
        MultiSelectHelper<HistoryModel> multiHelper = getMultiHelper();
        if (multiHelper != null && this.savedSelection.size() > 0) {
            int size = this.savedSelection.size();
            boolean z = false;
            for (int i = 0; i < size; i++) {
                multiHelper.getSelectionMap().addAll(this.savedSelection);
                z = !this.savedSelection.isEmpty();
            }
            if (z) {
                startActionMode();
            }
            this.savedSelection.clear();
        }
    }

    /* access modifiers changed from: private */
    public final void process(Click click) {
        if (click.getPosition() >= 0) {
            HistoryModel historyModel = this.viewModel;
            if (historyModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                historyModel = null;
            }
            MediaWrapper mediaWrapper = (MediaWrapper) historyModel.getDataset().get(click.getPosition());
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
    }

    public final void onClick(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        if (!KeyHelper.INSTANCE.isShiftPressed() || getActionMode() != null) {
            HistoryModel historyModel = null;
            if (getActionMode() != null) {
                MultiSelectHelper<MediaWrapper> multiSelectHelper2 = this.multiSelectHelper;
                if (multiSelectHelper2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                    multiSelectHelper2 = null;
                }
                MultiSelectHelper.toggleSelection$default(multiSelectHelper2, i, false, 2, (Object) null);
                this.historyAdapter.notifyItemChanged(i, mediaWrapper);
                invalidateActionMode();
                return;
            }
            if (i != 0) {
                HistoryModel historyModel2 = this.viewModel;
                if (historyModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    historyModel = historyModel2;
                }
                historyModel.moveUp(mediaWrapper);
            }
            MediaUtils.INSTANCE.openMedia(requireContext(), mediaWrapper);
            return;
        }
        onLongClick(i, mediaWrapper);
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

    public void fireDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogActivity.Companion.setDialog(dialog);
        startActivity(new Intent(DialogActivity.KEY_DIALOG, (Uri) null, requireActivity(), DialogActivity.class));
    }
}
