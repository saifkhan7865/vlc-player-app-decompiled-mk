package org.videolan.vlc.gui.network;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.MrlPanelBinding;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.interfaces.BrowserFragmentInterface;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007B\u0005¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0001J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00152\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u001c\u0010\"\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J&\u0010%\u001a\u0004\u0018\u00010\u001e2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0019\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0001J\u0012\u0010/\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\"\u00100\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u0002012\u0006\u00102\u001a\u00020,2\b\u00103\u001a\u0004\u0018\u000104H\u0016J \u00105\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u00106\u001a\u00020,2\u0006\u00103\u001a\u000204H\u0016J\b\u00107\u001a\u00020\u0015H\u0016J\b\u00108\u001a\u00020\u0015H\u0016J\u001a\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0011\u0010;\u001a\u00020\u00152\u0006\u0010<\u001a\u00020=H\u0001J\b\u0010>\u001a\u00020\u0017H\u0002J\b\u0010?\u001a\u00020\u0015H\u0016J!\u0010@\u001a\u00020\u00152\u0006\u0010A\u001a\u00020B2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u0007H\u0001J\u0011\u0010D\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,H\u0001R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lorg/videolan/vlc/gui/network/MRLPanelFragment;", "Lorg/videolan/vlc/gui/BaseFragment;", "Landroid/view/View$OnKeyListener;", "Landroid/widget/TextView$OnEditorActionListener;", "Landroid/view/View$OnClickListener;", "Lorg/videolan/vlc/interfaces/BrowserFragmentInterface;", "Lorg/videolan/vlc/gui/network/IStreamsFragmentDelegate;", "Lorg/videolan/vlc/gui/network/KeyboardListener;", "()V", "adapter", "Lorg/videolan/vlc/gui/network/MRLAdapter;", "binding", "Lorg/videolan/vlc/databinding/MrlPanelBinding;", "viewModel", "Lorg/videolan/vlc/viewmodels/StreamsModel;", "getTitle", "", "getlistEventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/network/MrlAction;", "hideKeyboard", "", "onActionItemClicked", "", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroyActionMode", "onEditorAction", "Landroid/widget/TextView;", "actionId", "event", "Landroid/view/KeyEvent;", "onKey", "keyCode", "onResume", "onStart", "onViewCreated", "view", "playMedia", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "processUri", "refresh", "setup", "fragment", "Landroidx/fragment/app/Fragment;", "keyboardListener", "showContext", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MRLPanelFragment.kt */
public final class MRLPanelFragment extends BaseFragment implements View.OnKeyListener, TextView.OnEditorActionListener, View.OnClickListener, BrowserFragmentInterface, IStreamsFragmentDelegate, KeyboardListener {
    private final /* synthetic */ StreamsFragmentDelegate $$delegate_0 = new StreamsFragmentDelegate();
    /* access modifiers changed from: private */
    public MRLAdapter adapter;
    /* access modifiers changed from: private */
    public MrlPanelBinding binding;
    /* access modifiers changed from: private */
    public StreamsModel viewModel;

    public SendChannel<MrlAction> getlistEventActor() {
        return this.$$delegate_0.getlistEventActor();
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        this.$$delegate_0.onCtxAction(i, contextOption);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(textView, "v");
        return false;
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

    public String getTitle() {
        return "";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        StreamsModel streamsModel = null;
        StreamsModel streamsModel2 = (StreamsModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new StreamsModel.Factory(requireContext, false, 2, (DefaultConstructorMarker) null)).get(StreamsModel.class);
        this.viewModel = streamsModel2;
        Fragment fragment = this;
        if (streamsModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            streamsModel = streamsModel2;
        }
        setup(fragment, streamsModel, this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        MrlPanelBinding inflate = MrlPanelBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        MrlPanelBinding mrlPanelBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        StreamsModel streamsModel = this.viewModel;
        if (streamsModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel = null;
        }
        inflate.setViewmodel(streamsModel);
        MrlPanelBinding mrlPanelBinding2 = this.binding;
        if (mrlPanelBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding2 = null;
        }
        EditText editText = mrlPanelBinding2.mrlEdit.getEditText();
        if (editText != null) {
            editText.setOnKeyListener(this);
        }
        MrlPanelBinding mrlPanelBinding3 = this.binding;
        if (mrlPanelBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding3 = null;
        }
        EditText editText2 = mrlPanelBinding3.mrlEdit.getEditText();
        if (editText2 != null) {
            editText2.setOnEditorActionListener(this);
        }
        MrlPanelBinding mrlPanelBinding4 = this.binding;
        if (mrlPanelBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding4 = null;
        }
        EditText editText3 = mrlPanelBinding4.mrlEdit.getEditText();
        if (editText3 != null) {
            editText3.requestFocus();
        }
        MrlPanelBinding mrlPanelBinding5 = this.binding;
        if (mrlPanelBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding5 = null;
        }
        mrlPanelBinding5.play.setOnClickListener(this);
        MrlPanelBinding mrlPanelBinding6 = this.binding;
        if (mrlPanelBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            mrlPanelBinding = mrlPanelBinding6;
        }
        return mrlPanelBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        StreamsModel streamsModel = null;
        this.adapter = new MRLAdapter(getlistEventActor(), false, 2, (DefaultConstructorMarker) null);
        MrlPanelBinding mrlPanelBinding = this.binding;
        if (mrlPanelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding = null;
        }
        RecyclerView recyclerView = mrlPanelBinding.mrlList;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "mrlList");
        if (Settings.INSTANCE.getShowTvUi()) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.addItemDecoration(new MRLPanelFragment$onViewCreated$1(this));
            recyclerView.setLayoutManager(gridLayoutManager);
            int dimension = (int) getResources().getDimension(R.dimen.tv_overscan_horizontal);
            int dimension2 = (int) getResources().getDimension(R.dimen.tv_overscan_vertical);
            MrlPanelBinding mrlPanelBinding2 = this.binding;
            if (mrlPanelBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                mrlPanelBinding2 = null;
            }
            mrlPanelBinding2.mrlRoot.setPadding(dimension, dimension2, dimension, dimension2);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        }
        MRLAdapter mRLAdapter = this.adapter;
        if (mRLAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            mRLAdapter = null;
        }
        recyclerView.setAdapter(mRLAdapter);
        StreamsModel streamsModel2 = this.viewModel;
        if (streamsModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel2 = null;
        }
        streamsModel2.getDataset().observe(requireActivity(), new MRLPanelFragmentKt$sam$androidx_lifecycle_Observer$0(new MRLPanelFragment$onViewCreated$2(this)));
        StreamsModel streamsModel3 = this.viewModel;
        if (streamsModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            streamsModel = streamsModel3;
        }
        streamsModel.getLoading().observe(requireActivity(), new MRLPanelFragmentKt$sam$androidx_lifecycle_Observer$0(new MRLPanelFragment$onViewCreated$3(this)));
    }

    public void onResume() {
        ClipData primaryClip;
        ClipData.Item itemAt;
        CharSequence text;
        super.onResume();
        MrlPanelBinding mrlPanelBinding = this.binding;
        MrlPanelBinding mrlPanelBinding2 = null;
        if (mrlPanelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding = null;
        }
        TextInputLayout textInputLayout = mrlPanelBinding.mrlEdit;
        Intrinsics.checkNotNullExpressionValue(textInputLayout, "mrlEdit");
        View view = textInputLayout;
        if (!ViewCompat.isLaidOut(view) || view.isLayoutRequested()) {
            view.addOnLayoutChangeListener(new MRLPanelFragment$onResume$$inlined$doOnLayout$1(this));
            return;
        }
        try {
            Object systemService = requireContext().getSystemService("clipboard");
            ClipboardManager clipboardManager = systemService instanceof ClipboardManager ? (ClipboardManager) systemService : null;
            String obj = (clipboardManager == null || (primaryClip = clipboardManager.getPrimaryClip()) == null || (itemAt = primaryClip.getItemAt(0)) == null || (text = itemAt.getText()) == null) ? null : text.toString();
            if (KotlinExtensionsKt.isValidUrl(obj)) {
                StreamsModel access$getViewModel$p = this.viewModel;
                if (access$getViewModel$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    access$getViewModel$p = null;
                }
                access$getViewModel$p.getObservableSearchText().set(obj);
                MrlPanelBinding access$getBinding$p = this.binding;
                if (access$getBinding$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    mrlPanelBinding2 = access$getBinding$p;
                }
                KotlinExtensionsKt.setVisible(mrlPanelBinding2.clipboardIndicator);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStart() {
        /*
            r3 = this;
            super.onStart()
            org.videolan.vlc.viewmodels.StreamsModel r0 = r3.viewModel
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
            org.videolan.vlc.gui.ContentActivity r0 = (org.videolan.vlc.gui.ContentActivity) r0
            goto L_0x001d
        L_0x001c:
            r0 = r1
        L_0x001d:
            if (r0 == 0) goto L_0x0023
            r2 = 0
            r0.setTabLayoutVisibility(r2)
        L_0x0023:
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r2 = r0 instanceof androidx.appcompat.app.AppCompatActivity
            if (r2 == 0) goto L_0x002e
            r1 = r0
            androidx.appcompat.app.AppCompatActivity r1 = (androidx.appcompat.app.AppCompatActivity) r1
        L_0x002e:
            if (r1 == 0) goto L_0x003b
            androidx.appcompat.app.ActionBar r0 = r1.getSupportActionBar()
            if (r0 == 0) goto L_0x003b
            int r1 = org.videolan.vlc.R.string.streams
            r0.setTitle((int) r1)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.network.MRLPanelFragment.onStart():void");
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        return (i == 6 || i == 2 || (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 66)) && processUri();
    }

    private final boolean processUri() {
        Uri uri;
        String obj;
        StreamsModel streamsModel = this.viewModel;
        StreamsModel streamsModel2 = null;
        if (streamsModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel = null;
        }
        CharSequence charSequence = streamsModel.getObservableSearchText().get();
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        StreamsModel streamsModel3 = this.viewModel;
        if (streamsModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel3 = null;
        }
        String str = streamsModel3.getObservableSearchText().get();
        if (str == null || (obj = StringsKt.trim((CharSequence) str).toString()) == null) {
            uri = null;
        } else {
            uri = Uri.parse(obj);
        }
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(uri);
        Intrinsics.checkNotNull(abstractMediaWrapper);
        playMedia(abstractMediaWrapper);
        StreamsModel streamsModel4 = this.viewModel;
        if (streamsModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            streamsModel2 = streamsModel4;
        }
        streamsModel2.getObservableSearchText().set("");
        return true;
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        processUri();
    }

    public void refresh() {
        StreamsModel streamsModel = this.viewModel;
        if (streamsModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            streamsModel = null;
        }
        streamsModel.refresh();
    }

    public void hideKeyboard() {
        UiTools uiTools = UiTools.INSTANCE;
        MrlPanelBinding mrlPanelBinding = this.binding;
        if (mrlPanelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mrlPanelBinding = null;
        }
        uiTools.setKeyboardVisibility(mrlPanelBinding.mrlEdit, false);
    }
}
