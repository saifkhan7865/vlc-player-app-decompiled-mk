package org.videolan.vlc.gui.dialogs;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.textfield.TextInputLayout;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.AppScope;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.gui.MainActivity;
import org.videolan.vlc.repository.BrowserFavRepository;

@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 U2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001UB\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J(\u0010)\u001a\u00020&2\u0006\u0010'\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,H\u0016J\b\u0010/\u001a\u00020,H\u0016J\u0010\u00100\u001a\u00020\u00192\u0006\u00101\u001a\u00020,H\u0002J\u0010\u00102\u001a\u00020,2\u0006\u00103\u001a\u00020\u0019H\u0002J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u00020\u0013H\u0002J\b\u00107\u001a\u00020\u0013H\u0016J\u0010\u00108\u001a\u00020&2\u0006\u00109\u001a\u000205H\u0016J\u0012\u0010:\u001a\u00020&2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J&\u0010=\u001a\u0004\u0018\u0001052\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010A2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010B\u001a\u00020&H\u0016J\u0010\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020EH\u0016J0\u0010F\u001a\u00020&2\f\u0010G\u001a\b\u0012\u0002\b\u0003\u0018\u00010H2\b\u0010I\u001a\u0004\u0018\u0001052\u0006\u00101\u001a\u00020,2\u0006\u0010J\u001a\u00020KH\u0016J\u0014\u0010L\u001a\u00020&2\n\u0010G\u001a\u0006\u0012\u0002\b\u00030HH\u0016J(\u0010M\u001a\u00020&2\u0006\u0010'\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010N\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0016J\u001a\u0010O\u001a\u00020&2\u0006\u0010I\u001a\u0002052\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010P\u001a\u00020&H\u0002J\u000e\u0010Q\u001a\u00020&2\u0006\u0010R\u001a\u00020SJ\b\u0010T\u001a\u00020&H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001fX.¢\u0006\u0004\n\u0002\u0010 R\u000e\u0010!\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/NetworkServerDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "Landroid/text/TextWatcher;", "Landroid/view/View$OnClickListener;", "()V", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "cancel", "Landroid/widget/Button;", "editAddress", "Landroid/widget/EditText;", "editAddressLayout", "Lcom/google/android/material/textfield/TextInputLayout;", "editFolder", "editPort", "editServername", "editUsername", "ignoreFirstSpinnerCb", "", "getIgnoreFirstSpinnerCb", "()Z", "setIgnoreFirstSpinnerCb", "(Z)V", "networkName", "", "networkUri", "Landroid/net/Uri;", "portTitle", "Landroid/widget/TextView;", "protocols", "", "[Ljava/lang/String;", "save", "spinnerProtocol", "Landroid/widget/Spinner;", "url", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "getDefaultState", "getPortForProtocol", "position", "getProtocolSpinnerPosition", "protocol", "initialFocusedView", "Landroid/view/View;", "needPort", "needToManageOrientation", "onClick", "v", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onItemSelected", "parent", "Landroid/widget/AdapterView;", "view", "id", "", "onNothingSelected", "onTextChanged", "before", "onViewCreated", "saveServer", "setServer", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "updateUrl", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkServerDialog.kt */
public final class NetworkServerDialog extends VLCBottomSheetDialogFragment implements AdapterView.OnItemSelectedListener, TextWatcher, View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String FTPES_DEFAULT_PORT = "21";
    public static final String FTPS_DEFAULT_PORT = "990";
    public static final String FTP_DEFAULT_PORT = "21";
    public static final String HTTPS_DEFAULT_PORT = "443";
    public static final String HTTP_DEFAULT_PORT = "80";
    public static final String SFTP_DEFAULT_PORT = "22";
    private static final String TAG = "VLC/NetworkServerDialog";
    /* access modifiers changed from: private */
    public BrowserFavRepository browserFavRepository;
    private Button cancel;
    private EditText editAddress;
    private TextInputLayout editAddressLayout;
    private EditText editFolder;
    private EditText editPort;
    private EditText editServername;
    private TextInputLayout editUsername;
    private boolean ignoreFirstSpinnerCb;
    private String networkName;
    /* access modifiers changed from: private */
    public Uri networkUri;
    private TextView portTitle;
    private String[] protocols;
    private Button save;
    private Spinner spinnerProtocol;
    private TextView url;

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkNotNullParameter(editable, "s");
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, "s");
    }

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        Intrinsics.checkNotNullParameter(adapterView, "parent");
    }

    public final boolean getIgnoreFirstSpinnerCb() {
        return this.ignoreFirstSpinnerCb;
    }

    public final void setIgnoreFirstSpinnerCb(boolean z) {
        this.ignoreFirstSpinnerCb = z;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        super.onDismiss(dialogInterface);
        FragmentActivity activity = getActivity();
        MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
        if (mainActivity != null) {
            mainActivity.forceRefresh();
        }
    }

    public View initialFocusedView() {
        Spinner spinner = this.spinnerProtocol;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerProtocol");
            spinner = null;
        }
        return spinner;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new NetworkServerDialog$onCreate$1(this, (Continuation<? super NetworkServerDialog$onCreate$1>) null), 3, (Object) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        if (this.browserFavRepository == null) {
            BrowserFavRepository.Companion companion = BrowserFavRepository.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            this.browserFavRepository = (BrowserFavRepository) companion.getInstance(requireActivity);
        }
        View inflate = layoutInflater.inflate(R.layout.network_server_dialog, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.server_domain);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        TextInputLayout textInputLayout = (TextInputLayout) findViewById;
        this.editAddressLayout = textInputLayout;
        if (textInputLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editAddressLayout");
            textInputLayout = null;
        }
        EditText editText = textInputLayout.getEditText();
        Intrinsics.checkNotNull(editText);
        this.editAddress = editText;
        View findViewById2 = inflate.findViewById(R.id.server_folder);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
        EditText editText2 = ((TextInputLayout) findViewById2).getEditText();
        Intrinsics.checkNotNull(editText2);
        this.editFolder = editText2;
        View findViewById3 = inflate.findViewById(R.id.server_username);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
        this.editUsername = (TextInputLayout) findViewById3;
        View findViewById4 = inflate.findViewById(R.id.server_name);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type com.google.android.material.textfield.TextInputLayout");
        EditText editText3 = ((TextInputLayout) findViewById4).getEditText();
        Intrinsics.checkNotNull(editText3);
        this.editServername = editText3;
        View findViewById5 = inflate.findViewById(R.id.server_protocol);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.spinnerProtocol = (Spinner) findViewById5;
        View findViewById6 = inflate.findViewById(R.id.server_port);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.editPort = (EditText) findViewById6;
        View findViewById7 = inflate.findViewById(R.id.server_url);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.url = (TextView) findViewById7;
        View findViewById8 = inflate.findViewById(R.id.server_save);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.save = (Button) findViewById8;
        View findViewById9 = inflate.findViewById(R.id.server_cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.cancel = (Button) findViewById9;
        View findViewById10 = inflate.findViewById(R.id.server_port_text);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.portTitle = (TextView) findViewById10;
        String[] stringArray = getResources().getStringArray(R.array.server_protocols);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        this.protocols = stringArray;
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.server_protocols));
        Spinner spinner = this.spinnerProtocol;
        TextInputLayout textInputLayout = null;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerProtocol");
            spinner = null;
        }
        spinner.setAdapter(arrayAdapter);
        if (this.networkUri != null) {
            this.ignoreFirstSpinnerCb = true;
            EditText editText = this.editAddress;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editAddress");
                editText = null;
            }
            Uri uri = this.networkUri;
            if (uri == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                uri = null;
            }
            editText.setText(uri.getHost());
            Uri uri2 = this.networkUri;
            if (uri2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                uri2 = null;
            }
            CharSequence userInfo = uri2.getUserInfo();
            if (!(userInfo == null || userInfo.length() == 0)) {
                TextInputLayout textInputLayout2 = this.editUsername;
                if (textInputLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editUsername");
                    textInputLayout2 = null;
                }
                EditText editText2 = textInputLayout2.getEditText();
                Intrinsics.checkNotNull(editText2);
                Uri uri3 = this.networkUri;
                if (uri3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                    uri3 = null;
                }
                editText2.setText(uri3.getUserInfo());
            }
            Uri uri4 = this.networkUri;
            if (uri4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                uri4 = null;
            }
            CharSequence path = uri4.getPath();
            if (!(path == null || path.length() == 0)) {
                EditText editText3 = this.editFolder;
                if (editText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                    editText3 = null;
                }
                Uri uri5 = this.networkUri;
                if (uri5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                    uri5 = null;
                }
                editText3.setText(uri5.getPath());
            }
            String str = this.networkName;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkName");
                str = null;
            }
            if (str.length() != 0) {
                EditText editText4 = this.editServername;
                if (editText4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editServername");
                    editText4 = null;
                }
                String str2 = this.networkName;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkName");
                    str2 = null;
                }
                editText4.setText(str2);
            }
            Uri uri6 = this.networkUri;
            if (uri6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                uri6 = null;
            }
            String scheme = uri6.getScheme();
            if (scheme != null) {
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String upperCase = scheme.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                if (upperCase != null) {
                    int protocolSpinnerPosition = getProtocolSpinnerPosition(upperCase);
                    Spinner spinner2 = this.spinnerProtocol;
                    if (spinner2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("spinnerProtocol");
                        spinner2 = null;
                    }
                    spinner2.setSelection(protocolSpinnerPosition);
                    Uri uri7 = this.networkUri;
                    if (uri7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                        uri7 = null;
                    }
                    int port = uri7.getPort();
                    EditText editText5 = this.editPort;
                    if (editText5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("editPort");
                        editText5 = null;
                    }
                    editText5.setText(port != -1 ? String.valueOf(port) : getPortForProtocol(protocolSpinnerPosition));
                }
            }
        }
        Spinner spinner3 = this.spinnerProtocol;
        if (spinner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerProtocol");
            spinner3 = null;
        }
        spinner3.setOnItemSelectedListener(this);
        Button button = this.save;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("save");
            button = null;
        }
        View.OnClickListener onClickListener = this;
        button.setOnClickListener(onClickListener);
        Button button2 = this.cancel;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancel");
            button2 = null;
        }
        button2.setOnClickListener(onClickListener);
        EditText editText6 = this.editPort;
        if (editText6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPort");
            editText6 = null;
        }
        TextWatcher textWatcher = this;
        editText6.addTextChangedListener(textWatcher);
        EditText editText7 = this.editAddress;
        if (editText7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editAddress");
            editText7 = null;
        }
        editText7.addTextChangedListener(textWatcher);
        EditText editText8 = this.editFolder;
        if (editText8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editFolder");
            editText8 = null;
        }
        editText8.addTextChangedListener(textWatcher);
        TextInputLayout textInputLayout3 = this.editUsername;
        if (textInputLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUsername");
        } else {
            textInputLayout = textInputLayout3;
        }
        EditText editText9 = textInputLayout.getEditText();
        Intrinsics.checkNotNull(editText9);
        editText9.addTextChangedListener(textWatcher);
        updateUrl();
    }

    private final void saveServer() {
        String str;
        EditText editText = this.editServername;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editServername");
            editText = null;
        }
        if (editText.getText().toString().length() == 0) {
            EditText editText2 = this.editAddress;
            if (editText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editAddress");
                editText2 = null;
            }
            str = editText2.getText().toString();
        } else {
            EditText editText3 = this.editServername;
            if (editText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editServername");
                editText3 = null;
            }
            str = editText3.getText().toString();
        }
        TextView textView = this.url;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RtspHeaders.Values.URL);
            textView = null;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new NetworkServerDialog$saveServer$1(this, Uri.parse(textView.getText().toString()), str, (Continuation<? super NetworkServerDialog$saveServer$1>) null), 3, (Object) null);
    }

    private final void updateUrl() {
        StringBuilder sb = new StringBuilder();
        Spinner spinner = this.spinnerProtocol;
        EditText editText = null;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerProtocol");
            spinner = null;
        }
        String obj = spinner.getSelectedItem().toString();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = obj.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        sb.append(lowerCase);
        sb.append("://");
        TextInputLayout textInputLayout = this.editUsername;
        if (textInputLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUsername");
            textInputLayout = null;
        }
        if (textInputLayout.isEnabled()) {
            TextInputLayout textInputLayout2 = this.editUsername;
            if (textInputLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editUsername");
                textInputLayout2 = null;
            }
            EditText editText2 = textInputLayout2.getEditText();
            Intrinsics.checkNotNull(editText2);
            CharSequence text = editText2.getText();
            if (!(text == null || text.length() == 0)) {
                TextInputLayout textInputLayout3 = this.editUsername;
                if (textInputLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editUsername");
                    textInputLayout3 = null;
                }
                EditText editText3 = textInputLayout3.getEditText();
                Intrinsics.checkNotNull(editText3);
                sb.append(editText3.getText());
                sb.append('@');
            }
        }
        EditText editText4 = this.editAddress;
        if (editText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editAddress");
            editText4 = null;
        }
        sb.append(editText4.getText());
        if (needPort()) {
            sb.append(AbstractJsonLexerKt.COLON);
            EditText editText5 = this.editPort;
            if (editText5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editPort");
                editText5 = null;
            }
            sb.append(editText5.getText());
        }
        EditText editText6 = this.editFolder;
        if (editText6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editFolder");
            editText6 = null;
        }
        boolean z = false;
        if (editText6.isEnabled()) {
            EditText editText7 = this.editFolder;
            if (editText7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                editText7 = null;
            }
            CharSequence text2 = editText7.getText();
            if (!(text2 == null || text2.length() == 0)) {
                EditText editText8 = this.editFolder;
                if (editText8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                    editText8 = null;
                }
                if (!StringsKt.startsWith$default(editText8.getText().toString(), "/", false, 2, (Object) null)) {
                    sb.append('/');
                }
                EditText editText9 = this.editFolder;
                if (editText9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                    editText9 = null;
                }
                sb.append(editText9.getText());
            }
        }
        TextView textView = this.url;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RtspHeaders.Values.URL);
            textView = null;
        }
        textView.setText(sb.toString());
        Button button = this.save;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("save");
            button = null;
        }
        EditText editText10 = this.editAddress;
        if (editText10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editAddress");
        } else {
            editText = editText10;
        }
        if (editText.getText().toString().length() > 0) {
            z = true;
        }
        button.setEnabled(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0074 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean needPort() {
        /*
            r4 = this;
            android.widget.EditText r0 = r4.editPort
            r1 = 0
            java.lang.String r2 = "editPort"
            if (r0 != 0) goto L_0x000b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x000b:
            boolean r0 = r0.isEnabled()
            r3 = 0
            if (r0 == 0) goto L_0x0075
            android.widget.EditText r0 = r4.editPort
            if (r0 != 0) goto L_0x001a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x001a:
            android.text.Editable r0 = r0.getText()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0075
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0029
            goto L_0x0075
        L_0x0029:
            android.widget.EditText r0 = r4.editPort
            if (r0 != 0) goto L_0x0031
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x0032
        L_0x0031:
            r1 = r0
        L_0x0032:
            android.text.Editable r0 = r1.getText()
            java.lang.String r0 = r0.toString()
            int r1 = r0.hashCode()
            r2 = 1599(0x63f, float:2.24E-42)
            if (r1 == r2) goto L_0x006b
            r2 = 1600(0x640, float:2.242E-42)
            if (r1 == r2) goto L_0x0062
            r2 = 1784(0x6f8, float:2.5E-42)
            if (r1 == r2) goto L_0x0059
            r2 = 51635(0xc9b3, float:7.2356E-41)
            if (r1 == r2) goto L_0x0050
            goto L_0x0074
        L_0x0050:
            java.lang.String r1 = "443"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0075
            goto L_0x0074
        L_0x0059:
            java.lang.String r1 = "80"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0075
            goto L_0x0074
        L_0x0062:
            java.lang.String r1 = "22"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0075
            goto L_0x0074
        L_0x006b:
            java.lang.String r1 = "21"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0074
            goto L_0x0075
        L_0x0074:
            r3 = 1
        L_0x0075:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.NetworkServerDialog.needPort():boolean");
    }

    private final int getProtocolSpinnerPosition(String str) {
        String[] strArr = this.protocols;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("protocols");
            strArr = null;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual((Object) strArr[i], (Object) str)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getPortForProtocol(int r3) {
        /*
            r2 = this;
            java.lang.String[] r0 = r2.protocols
            if (r0 != 0) goto L_0x000a
            java.lang.String r0 = "protocols"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = 0
        L_0x000a:
            r3 = r0[r3]
            int r0 = r3.hashCode()
            java.lang.String r1 = "21"
            switch(r0) {
                case 69954: goto L_0x004f;
                case 2168657: goto L_0x0043;
                case 2228360: goto L_0x0037;
                case 2542607: goto L_0x002b;
                case 67228016: goto L_0x0022;
                case 69079243: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0057
        L_0x0016:
            java.lang.String r0 = "HTTPS"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x001f
            goto L_0x0057
        L_0x001f:
            java.lang.String r1 = "443"
            goto L_0x0059
        L_0x0022:
            java.lang.String r0 = "FTPES"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0059
            goto L_0x0057
        L_0x002b:
            java.lang.String r0 = "SFTP"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0034
            goto L_0x0057
        L_0x0034:
            java.lang.String r1 = "22"
            goto L_0x0059
        L_0x0037:
            java.lang.String r0 = "HTTP"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0040
            goto L_0x0057
        L_0x0040:
            java.lang.String r1 = "80"
            goto L_0x0059
        L_0x0043:
            java.lang.String r0 = "FTPS"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x004c
            goto L_0x0057
        L_0x004c:
            java.lang.String r1 = "990"
            goto L_0x0059
        L_0x004f:
            java.lang.String r0 = "FTP"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0059
        L_0x0057:
            java.lang.String r1 = ""
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.NetworkServerDialog.getPortForProtocol(int):java.lang.String");
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z;
        int i2 = 0;
        if (this.ignoreFirstSpinnerCb) {
            this.ignoreFirstSpinnerCb = false;
            return;
        }
        String portForProtocol = getPortForProtocol(i);
        int i3 = R.string.server_domain_hint;
        String[] strArr = this.protocols;
        TextInputLayout textInputLayout = null;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("protocols");
            strArr = null;
        }
        String str = strArr[i];
        boolean z2 = true;
        if (Intrinsics.areEqual((Object) str, (Object) "SMB")) {
            i3 = R.string.server_share_hint;
            z = false;
        } else if (Intrinsics.areEqual((Object) str, (Object) "NFS")) {
            i3 = R.string.server_share_hint;
            z = false;
            z2 = false;
        } else {
            z = true;
        }
        TextInputLayout textInputLayout2 = this.editAddressLayout;
        if (textInputLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editAddressLayout");
            textInputLayout2 = null;
        }
        textInputLayout2.setHint((CharSequence) getString(i3));
        TextView textView = this.portTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("portTitle");
            textView = null;
        }
        int i4 = 4;
        textView.setVisibility(z2 ? 0 : 4);
        EditText editText = this.editPort;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPort");
            editText = null;
        }
        if (z2) {
            i4 = 0;
        }
        editText.setVisibility(i4);
        EditText editText2 = this.editPort;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPort");
            editText2 = null;
        }
        editText2.setText(portForProtocol);
        EditText editText3 = this.editPort;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPort");
            editText3 = null;
        }
        editText3.setEnabled(z2);
        TextInputLayout textInputLayout3 = this.editUsername;
        if (textInputLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUsername");
            textInputLayout3 = null;
        }
        if (!z) {
            i2 = 8;
        }
        textInputLayout3.setVisibility(i2);
        TextInputLayout textInputLayout4 = this.editUsername;
        if (textInputLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUsername");
        } else {
            textInputLayout = textInputLayout4;
        }
        textInputLayout.setEnabled(z);
        updateUrl();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, "s");
        TextInputLayout textInputLayout = this.editUsername;
        EditText editText = null;
        if (textInputLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editUsername");
            textInputLayout = null;
        }
        if (textInputLayout.hasFocus()) {
            Spinner spinner = this.spinnerProtocol;
            if (spinner == null) {
                Intrinsics.throwUninitializedPropertyAccessException("spinnerProtocol");
                spinner = null;
            }
            if (Intrinsics.areEqual((Object) spinner.getSelectedItem().toString(), (Object) "SFTP")) {
                EditText editText2 = this.editFolder;
                if (editText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                    editText2 = null;
                }
                TextWatcher textWatcher = this;
                editText2.removeTextChangedListener(textWatcher);
                EditText editText3 = this.editFolder;
                if (editText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                    editText3 = null;
                }
                StringBuilder sb = new StringBuilder("/home/");
                TextInputLayout textInputLayout2 = this.editUsername;
                if (textInputLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editUsername");
                    textInputLayout2 = null;
                }
                EditText editText4 = textInputLayout2.getEditText();
                Intrinsics.checkNotNull(editText4);
                sb.append(editText4.getText());
                editText3.setText(sb.toString());
                EditText editText5 = this.editFolder;
                if (editText5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("editFolder");
                } else {
                    editText = editText5;
                }
                editText.addTextChangedListener(textWatcher);
            }
        }
        updateUrl();
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.server_save) {
            saveServer();
        } else if (id == R.id.server_cancel) {
            dismiss();
        }
    }

    public final void setServer(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        Uri uri = mediaWrapper.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        this.networkUri = uri;
        String title = mediaWrapper.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        this.networkName = title;
    }

    public void onDestroy() {
        super.onDestroy();
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogActivity) {
            ((DialogActivity) activity).finish();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/NetworkServerDialog$Companion;", "", "()V", "FTPES_DEFAULT_PORT", "", "FTPS_DEFAULT_PORT", "FTP_DEFAULT_PORT", "HTTPS_DEFAULT_PORT", "HTTP_DEFAULT_PORT", "SFTP_DEFAULT_PORT", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NetworkServerDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
