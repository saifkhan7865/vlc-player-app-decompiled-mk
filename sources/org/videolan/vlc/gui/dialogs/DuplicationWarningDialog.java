package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.os.BundleKt;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentKt;
import com.google.android.material.color.MaterialColors;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogDuplicationWarningBinding;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0012\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J$\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0018\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DuplicationWarningDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Landroid/view/View$OnClickListener;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogDuplicationWarningBinding;", "duplicationMessages", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "finalMessage", "Landroid/text/SpannableString;", "playlistTitles", "shouldShowThreeOptions", "", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onClick", "", "view", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "setupSecondaryText", "secondaryMessage", "playlistTitle", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DuplicationWarningDialog.kt */
public final class DuplicationWarningDialog extends VLCBottomSheetDialogFragment implements View.OnClickListener {
    public static final int ADD_ALL = 0;
    public static final int ADD_NEW = 1;
    public static final int CANCEL = 2;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DUPLICATION_MESSAGES_KEY = "duplication_messages";
    public static final int NO_OPTION = -1;
    public static final String OPTION_KEY = "option";
    public static final String REQUEST_KEY = "REQUEST_KEY";
    private static final String SHOW_OPTIONS_KEY = "show_three_options";
    private static final String TITLE_KEY = "playlist_title";
    private DialogDuplicationWarningBinding binding;
    private ArrayList<String> duplicationMessages = new ArrayList<>();
    private SpannableString finalMessage = new SpannableString("");
    private ArrayList<String> playlistTitles = new ArrayList<>();
    private boolean shouldShowThreeOptions;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public View initialFocusedView() {
        DialogDuplicationWarningBinding dialogDuplicationWarningBinding = null;
        if (this.shouldShowThreeOptions) {
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding2 = this.binding;
            if (dialogDuplicationWarningBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogDuplicationWarningBinding = dialogDuplicationWarningBinding2;
            }
            Button button = dialogDuplicationWarningBinding.addNewButton;
            Intrinsics.checkNotNullExpressionValue(button, "addNewButton");
            return button;
        }
        DialogDuplicationWarningBinding dialogDuplicationWarningBinding3 = this.binding;
        if (dialogDuplicationWarningBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDuplicationWarningBinding = dialogDuplicationWarningBinding3;
        }
        Button button2 = dialogDuplicationWarningBinding.cancelButton;
        Intrinsics.checkNotNullExpressionValue(button2, "cancelButton");
        return button2;
    }

    public void onCreate(Bundle bundle) {
        Bundle arguments = getArguments();
        ArrayList<String> arrayList = null;
        ArrayList<String> stringArrayList = arguments != null ? arguments.getStringArrayList(DUPLICATION_MESSAGES_KEY) : null;
        Intrinsics.checkNotNull(stringArrayList);
        this.duplicationMessages = stringArrayList;
        Bundle arguments2 = getArguments();
        Boolean valueOf = arguments2 != null ? Boolean.valueOf(arguments2.getBoolean(SHOW_OPTIONS_KEY)) : null;
        Intrinsics.checkNotNull(valueOf);
        this.shouldShowThreeOptions = valueOf.booleanValue();
        Bundle arguments3 = getArguments();
        if (arguments3 != null) {
            arrayList = arguments3.getStringArrayList(TITLE_KEY);
        }
        Intrinsics.checkNotNull(arrayList);
        this.playlistTitles = arrayList;
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogDuplicationWarningBinding inflate = DialogDuplicationWarningBinding.inflate(getLayoutInflater(), viewGroup, false);
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
        DialogDuplicationWarningBinding dialogDuplicationWarningBinding = null;
        if (this.shouldShowThreeOptions) {
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding2 = this.binding;
            if (dialogDuplicationWarningBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding2 = null;
            }
            View.OnClickListener onClickListener = this;
            dialogDuplicationWarningBinding2.addAllButton.setOnClickListener(onClickListener);
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding3 = this.binding;
            if (dialogDuplicationWarningBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding3 = null;
            }
            dialogDuplicationWarningBinding3.addNewButton.setOnClickListener(onClickListener);
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding4 = this.binding;
            if (dialogDuplicationWarningBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding4 = null;
            }
            dialogDuplicationWarningBinding4.cancelButton.setOnClickListener(onClickListener);
        } else {
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding5 = this.binding;
            if (dialogDuplicationWarningBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding5 = null;
            }
            dialogDuplicationWarningBinding5.addNewButton.setVisibility(8);
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding6 = this.binding;
            if (dialogDuplicationWarningBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding6 = null;
            }
            dialogDuplicationWarningBinding6.addAllButton.setText(getResources().getString(R.string.add_button));
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding7 = this.binding;
            if (dialogDuplicationWarningBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding7 = null;
            }
            View.OnClickListener onClickListener2 = this;
            dialogDuplicationWarningBinding7.addAllButton.setOnClickListener(onClickListener2);
            DialogDuplicationWarningBinding dialogDuplicationWarningBinding8 = this.binding;
            if (dialogDuplicationWarningBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogDuplicationWarningBinding8 = null;
            }
            dialogDuplicationWarningBinding8.cancelButton.setOnClickListener(onClickListener2);
        }
        int size = this.duplicationMessages.size();
        for (int i = 0; i < size; i++) {
            String str = this.duplicationMessages.get(i);
            Intrinsics.checkNotNullExpressionValue(str, "get(...)");
            String str2 = this.playlistTitles.get(i);
            Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
            setupSecondaryText(str, str2);
        }
        DialogDuplicationWarningBinding dialogDuplicationWarningBinding9 = this.binding;
        if (dialogDuplicationWarningBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDuplicationWarningBinding = dialogDuplicationWarningBinding9;
        }
        dialogDuplicationWarningBinding.secondaryTextview.setText(this.finalMessage);
    }

    public void onClick(View view) {
        int i;
        Intrinsics.checkNotNullParameter(view, "view");
        int id = view.getId();
        if (id == R.id.add_all_button) {
            i = 0;
        } else if (id == R.id.add_new_button) {
            i = 1;
        } else {
            i = id == R.id.cancel_button ? 2 : -1;
        }
        FragmentKt.setFragmentResult(this, REQUEST_KEY, BundleKt.bundleOf(TuplesKt.to(OPTION_KEY, Integer.valueOf(i))));
        dismiss();
    }

    private final void setupSecondaryText(String str, String str2) {
        String str3 = "\"" + str2 + '\"';
        SpannableString valueOf = SpannableString.valueOf(str);
        Intrinsics.checkNotNull(valueOf);
        int indexOf$default = StringsKt.indexOf$default((CharSequence) valueOf, str3, 0, false, 6, (Object) null);
        int length = str3.length() + indexOf$default;
        valueOf.setSpan(new StyleSpan(1), indexOf$default, length, 33);
        valueOf.setSpan(new ForegroundColorSpan(MaterialColors.getColor(requireContext(), R.attr.font_default, (int) ViewCompat.MEASURED_STATE_MASK)), indexOf$default, length, 33);
        SpannableString valueOf2 = SpannableString.valueOf(this.finalMessage.toString() + valueOf + 10);
        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(...)");
        this.finalMessage = valueOf2;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0013j\b\u0012\u0004\u0012\u00020\b`\u00142\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0013j\b\u0012\u0004\u0012\u00020\b`\u0014R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/DuplicationWarningDialog$Companion;", "", "()V", "ADD_ALL", "", "ADD_NEW", "CANCEL", "DUPLICATION_MESSAGES_KEY", "", "NO_OPTION", "OPTION_KEY", "REQUEST_KEY", "SHOW_OPTIONS_KEY", "TITLE_KEY", "newInstance", "Lorg/videolan/vlc/gui/dialogs/DuplicationWarningDialog;", "shouldShowThreeOptions", "", "playlistTitle", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "duplicationMessages", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DuplicationWarningDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DuplicationWarningDialog newInstance(boolean z, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            Intrinsics.checkNotNullParameter(arrayList, "playlistTitle");
            Intrinsics.checkNotNullParameter(arrayList2, "duplicationMessages");
            DuplicationWarningDialog duplicationWarningDialog = new DuplicationWarningDialog();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(DuplicationWarningDialog.TITLE_KEY, arrayList);
            bundle.putStringArrayList(DuplicationWarningDialog.DUPLICATION_MESSAGES_KEY, arrayList2);
            bundle.putBoolean(DuplicationWarningDialog.SHOW_OPTIONS_KEY, z);
            duplicationWarningDialog.setArguments(bundle);
            return duplicationWarningDialog;
        }
    }
}
