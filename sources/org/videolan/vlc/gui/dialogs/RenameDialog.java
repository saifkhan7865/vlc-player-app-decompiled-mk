package org.videolan.vlc.gui.dialogs;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.textfield.TextInputEditText;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u0012\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001e\u001a\u00020\nH\u0002J>\u0010\u001f\u001a\u00020\n26\u0010\u0003\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\n0\u0004R>\u0010\u0003\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\n0\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/RenameDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "listener", "Lkotlin/Function2;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlin/ParameterName;", "name", "media", "", "", "newNameInputtext", "Lcom/google/android/material/textfield/TextInputEditText;", "renameButton", "Landroid/widget/Button;", "renameFile", "", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "performRename", "setListener", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RenameDialog.kt */
public final class RenameDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Function2<? super MediaLibraryItem, ? super String, Unit> listener;
    private MediaLibraryItem media;
    /* access modifiers changed from: private */
    public TextInputEditText newNameInputtext;
    private Button renameButton;
    private boolean renameFile;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/RenameDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/RenameDialog;", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "isFile", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RenameDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ RenameDialog newInstance$default(Companion companion, MediaLibraryItem mediaLibraryItem, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.newInstance(mediaLibraryItem, z);
        }

        public final RenameDialog newInstance(MediaLibraryItem mediaLibraryItem, boolean z) {
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "media");
            RenameDialog renameDialog = new RenameDialog();
            renameDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(RenameDialogKt.RENAME_DIALOG_MEDIA, mediaLibraryItem), TuplesKt.to(RenameDialogKt.RENAME_DIALOG_FILE, Boolean.valueOf(z))));
            return renameDialog;
        }
    }

    public final void setListener(Function2<? super MediaLibraryItem, ? super String, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        this.listener = function2;
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        MediaLibraryItem mediaLibraryItem = null;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new RenameDialog$onCreate$1(this, (Continuation<? super RenameDialog$onCreate$1>) null), 3, (Object) null);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(arguments, RenameDialogKt.RENAME_DIALOG_MEDIA, MediaLibraryItem.class);
            } else {
                Parcelable parcelable2 = arguments.getParcelable(RenameDialogKt.RENAME_DIALOG_MEDIA);
                if (parcelable2 instanceof MediaLibraryItem) {
                    mediaLibraryItem = parcelable2;
                }
                parcelable = mediaLibraryItem;
            }
            MediaLibraryItem mediaLibraryItem2 = (MediaLibraryItem) parcelable;
            if (mediaLibraryItem2 != null) {
                this.media = mediaLibraryItem2;
                Bundle arguments2 = getArguments();
                this.renameFile = arguments2 != null ? arguments2.getBoolean(RenameDialogKt.RENAME_DIALOG_FILE) : false;
                super.onCreate(bundle);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008c A[LOOP:0: B:26:0x008c->B:29:0x009a, LOOP_START, PHI: r2 
      PHI: (r2v12 int) = (r2v2 int), (r2v13 int) binds: [B:25:0x008a, B:29:0x009a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.LayoutInflater r8, android.view.ViewGroup r9, android.os.Bundle r10) {
        /*
            r7 = this;
            java.lang.String r10 = "inflater"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r10)
            int r10 = org.videolan.vlc.R.layout.dialog_rename
            android.view.View r8 = r8.inflate(r10, r9)
            boolean r9 = r7.renameFile
            java.lang.String r10 = "media"
            r0 = 0
            if (r9 == 0) goto L_0x002d
            org.videolan.medialibrary.media.MediaLibraryItem r9 = r7.media
            if (r9 != 0) goto L_0x001a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r9 = r0
        L_0x001a:
            boolean r9 = r9 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r9 == 0) goto L_0x002d
            org.videolan.medialibrary.media.MediaLibraryItem r9 = r7.media
            if (r9 != 0) goto L_0x0026
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r9 = r0
        L_0x0026:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r9
            java.lang.String r9 = r9.getFileName()
            goto L_0x0039
        L_0x002d:
            org.videolan.medialibrary.media.MediaLibraryItem r9 = r7.media
            if (r9 != 0) goto L_0x0035
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r9 = r0
        L_0x0035:
            java.lang.String r9 = r9.getTitle()
        L_0x0039:
            int r1 = org.videolan.vlc.R.id.new_name
            android.view.View r1 = r8.findViewById(r1)
            java.lang.String r2 = "findViewById(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.google.android.material.textfield.TextInputEditText r1 = (com.google.android.material.textfield.TextInputEditText) r1
            r7.newNameInputtext = r1
            int r1 = org.videolan.vlc.R.id.rename_button
            android.view.View r1 = r8.findViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.widget.Button r1 = (android.widget.Button) r1
            r7.renameButton = r1
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r7.media
            if (r1 != 0) goto L_0x005d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r1 = r0
        L_0x005d:
            java.lang.String r10 = r1.getTitle()
            java.lang.String r1 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r1)
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            int r10 = r10.length()
            java.lang.String r1 = "newNameInputtext"
            if (r10 <= 0) goto L_0x007e
            com.google.android.material.textfield.TextInputEditText r10 = r7.newNameInputtext
            if (r10 != 0) goto L_0x0078
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r10 = r0
        L_0x0078:
            r2 = r9
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r10.setText(r2)
        L_0x007e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r10 = r9
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            int r2 = r10.length()
            r3 = -1
            int r2 = r2 + r3
            if (r2 < 0) goto L_0x009c
        L_0x008c:
            int r4 = r2 + -1
            char r5 = r10.charAt(r2)
            r6 = 46
            if (r5 != r6) goto L_0x0097
            goto L_0x009d
        L_0x0097:
            if (r4 >= 0) goto L_0x009a
            goto L_0x009c
        L_0x009a:
            r2 = r4
            goto L_0x008c
        L_0x009c:
            r2 = -1
        L_0x009d:
            r4 = 0
            if (r2 == r3) goto L_0x00b0
            boolean r3 = r7.renameFile
            if (r3 == 0) goto L_0x00b0
            com.google.android.material.textfield.TextInputEditText r9 = r7.newNameInputtext
            if (r9 != 0) goto L_0x00ac
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r9 = r0
        L_0x00ac:
            r9.setSelection(r4, r2)
            goto L_0x00bf
        L_0x00b0:
            com.google.android.material.textfield.TextInputEditText r2 = r7.newNameInputtext
            if (r2 != 0) goto L_0x00b8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r2 = r0
        L_0x00b8:
            int r9 = r9.length()
            r2.setSelection(r4, r9)
        L_0x00bf:
            android.widget.Button r9 = r7.renameButton
            if (r9 != 0) goto L_0x00c9
            java.lang.String r9 = "renameButton"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = r0
        L_0x00c9:
            org.videolan.vlc.gui.dialogs.RenameDialog$$ExternalSyntheticLambda0 r2 = new org.videolan.vlc.gui.dialogs.RenameDialog$$ExternalSyntheticLambda0
            r2.<init>(r7)
            r9.setOnClickListener(r2)
            com.google.android.material.textfield.TextInputEditText r9 = r7.newNameInputtext
            if (r9 != 0) goto L_0x00d9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r9 = r0
        L_0x00d9:
            org.videolan.vlc.gui.dialogs.RenameDialog$$ExternalSyntheticLambda1 r2 = new org.videolan.vlc.gui.dialogs.RenameDialog$$ExternalSyntheticLambda1
            r2.<init>(r7)
            r9.setOnEditorActionListener(r2)
            com.google.android.material.textfield.TextInputEditText r9 = r7.newNameInputtext
            if (r9 != 0) goto L_0x00e9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r9 = r0
        L_0x00e9:
            org.videolan.vlc.gui.dialogs.RenameDialog$$ExternalSyntheticLambda2 r1 = new org.videolan.vlc.gui.dialogs.RenameDialog$$ExternalSyntheticLambda2
            r1.<init>(r7)
            r9.setOnKeyListener(r1)
            int r9 = org.videolan.vlc.R.id.media_title
            android.view.View r9 = r8.findViewById(r9)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r9.setText(r10)
            r9 = r7
            androidx.lifecycle.LifecycleOwner r9 = (androidx.lifecycle.LifecycleOwner) r9
            androidx.lifecycle.LifecycleCoroutineScope r9 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)
            r1 = r9
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            r2 = r9
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.gui.dialogs.RenameDialog$onCreateView$4 r9 = new org.videolan.vlc.gui.dialogs.RenameDialog$onCreateView$4
            r9.<init>(r7, r0)
            r4 = r9
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 2
            r6 = 0
            r3 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.RenameDialog.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(RenameDialog renameDialog, View view) {
        Intrinsics.checkNotNullParameter(renameDialog, "this$0");
        renameDialog.performRename();
    }

    /* access modifiers changed from: private */
    public static final boolean onCreateView$lambda$2(RenameDialog renameDialog, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(renameDialog, "this$0");
        if (i != 6) {
            return false;
        }
        renameDialog.performRename();
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean onCreateView$lambda$3(RenameDialog renameDialog, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(renameDialog, "this$0");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(keyEvent, "keyEvent");
        if (i != 6 && i != 2 && (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        renameDialog.performRename();
        return true;
    }

    private final void performRename() {
        TextInputEditText textInputEditText = this.newNameInputtext;
        TextInputEditText textInputEditText2 = null;
        if (textInputEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newNameInputtext");
            textInputEditText = null;
        }
        if (String.valueOf(textInputEditText.getText()).length() > 0) {
            Function2<? super MediaLibraryItem, ? super String, Unit> function2 = this.listener;
            if (function2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("listener");
                function2 = null;
            }
            MediaLibraryItem mediaLibraryItem = this.media;
            if (mediaLibraryItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("media");
                mediaLibraryItem = null;
            }
            TextInputEditText textInputEditText3 = this.newNameInputtext;
            if (textInputEditText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newNameInputtext");
            } else {
                textInputEditText2 = textInputEditText3;
            }
            function2.invoke(mediaLibraryItem, String.valueOf(textInputEditText2.getText()));
            dismiss();
        }
    }

    public View initialFocusedView() {
        TextInputEditText textInputEditText = this.newNameInputtext;
        if (textInputEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newNameInputtext");
            textInputEditText = null;
        }
        return textInputEditText;
    }
}
