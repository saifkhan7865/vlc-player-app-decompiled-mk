package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0014\u0010\"\u001a\u00020\u000e2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/ConfirmDeleteDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "buttonText", "", "deleteAnimation", "Landroid/widget/ImageView;", "deleteButton", "Landroid/widget/Button;", "description", "Landroid/widget/TextView;", "descriptionString", "listener", "Lkotlin/Function0;", "", "mediaList", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "title", "titleString", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setListener", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConfirmDeleteDialog.kt */
public final class ConfirmDeleteDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String buttonText;
    private ImageView deleteAnimation;
    private Button deleteButton;
    private TextView description;
    private String descriptionString;
    private Function0<Unit> listener;
    private List<? extends MediaLibraryItem> mediaList;
    private TextView title;
    private String titleString;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/ConfirmDeleteDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/ConfirmDeleteDialog;", "medias", "Ljava/util/ArrayList;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlin/collections/ArrayList;", "title", "", "description", "buttonText", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ConfirmDeleteDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ConfirmDeleteDialog newInstance$default(Companion companion, ArrayList arrayList, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                arrayList = new ArrayList();
            }
            if ((i & 2) != 0) {
                str = "";
            }
            if ((i & 4) != 0) {
                str2 = "";
            }
            if ((i & 8) != 0) {
                str3 = "";
            }
            return companion.newInstance(arrayList, str, str2, str3);
        }

        public final ConfirmDeleteDialog newInstance(ArrayList<MediaLibraryItem> arrayList, String str, String str2, String str3) {
            Intrinsics.checkNotNullParameter(arrayList, "medias");
            Intrinsics.checkNotNullParameter(str, "title");
            Intrinsics.checkNotNullParameter(str2, TvContractCompat.Channels.COLUMN_DESCRIPTION);
            Intrinsics.checkNotNullParameter(str3, "buttonText");
            ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();
            confirmDeleteDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(ConfirmDeleteDialogKt.CONFIRM_DELETE_DIALOG_MEDIALIST, arrayList), TuplesKt.to(ConfirmDeleteDialogKt.CONFIRM_DELETE_DIALOG_TITLE, str), TuplesKt.to(ConfirmDeleteDialogKt.CONFIRM_DELETE_DIALOG_DESCRIPTION, str2), TuplesKt.to(ConfirmDeleteDialogKt.CONFIRM_DELETE_DIALOG_BUTTON_TEXT, str3)));
            return confirmDeleteDialog;
        }
    }

    public final void setListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.listener = function0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r9) {
        /*
            r8 = this;
            r0 = r8
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            androidx.lifecycle.LifecycleCoroutineScope r0 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r0)
            r1 = r0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog$onCreate$1 r0 = new org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog$onCreate$1
            r7 = 0
            r0.<init>(r8, r7)
            r4 = r0
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 3
            r6 = 0
            r2 = 0
            r3 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
            android.os.Bundle r0 = r8.getArguments()
            if (r0 == 0) goto L_0x0038
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            java.lang.String r3 = "CONFIRM_DELETE_DIALOG_MEDIALIST"
            if (r1 < r2) goto L_0x002f
            java.lang.Class<org.videolan.medialibrary.media.MediaLibraryItem> r1 = org.videolan.medialibrary.media.MediaLibraryItem.class
            java.util.ArrayList r0 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r0, (java.lang.String) r3, (java.lang.Class) r1)
            goto L_0x0033
        L_0x002f:
            java.util.ArrayList r0 = r0.getParcelableArrayList(r3)
        L_0x0033:
            if (r0 == 0) goto L_0x0038
            java.util.List r0 = (java.util.List) r0
            goto L_0x003c
        L_0x0038:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x003c:
            r8.mediaList = r0
            android.os.Bundle r0 = r8.getArguments()
            if (r0 == 0) goto L_0x004b
            java.lang.String r1 = "CONFIRM_DELETE_DIALOG_TITLE"
            java.lang.String r0 = r0.getString(r1)
            goto L_0x004c
        L_0x004b:
            r0 = r7
        L_0x004c:
            r8.titleString = r0
            android.os.Bundle r0 = r8.getArguments()
            if (r0 == 0) goto L_0x005b
            java.lang.String r1 = "CONFIRM_DELETE_DIALOG_DESCRIPTION"
            java.lang.String r0 = r0.getString(r1)
            goto L_0x005c
        L_0x005b:
            r0 = r7
        L_0x005c:
            r8.descriptionString = r0
            android.os.Bundle r0 = r8.getArguments()
            if (r0 == 0) goto L_0x006a
            java.lang.String r1 = "CONFIRM_DELETE_DIALOG_BUTTON_TEXT"
            java.lang.String r7 = r0.getString(r1)
        L_0x006a:
            r8.buttonText = r7
            super.onCreate(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog.onCreate(android.os.Bundle):void");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence charSequence;
        String str;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_confirm_delete, viewGroup);
        View findViewById = inflate.findViewById(R.id.delete_animation);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.deleteAnimation = (ImageView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.title = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.message);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.description = (TextView) findViewById3;
        View findViewById4 = inflate.findViewById(R.id.delete_button);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.deleteButton = (Button) findViewById4;
        ((Button) inflate.findViewById(R.id.delete_button)).setOnClickListener(new ConfirmDeleteDialog$$ExternalSyntheticLambda0(this));
        ((Button) inflate.findViewById(R.id.cancel_button)).setOnClickListener(new ConfirmDeleteDialog$$ExternalSyntheticLambda1(this));
        TextView textView = this.title;
        ImageView imageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView = null;
        }
        List<? extends MediaLibraryItem> list = this.mediaList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaList");
            list = null;
        }
        if (list.isEmpty()) {
            charSequence = this.titleString;
        } else {
            List<? extends MediaLibraryItem> list2 = this.mediaList;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                list2 = null;
            }
            if (list2.size() > 1) {
                List<? extends MediaLibraryItem> list3 = this.mediaList;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                    list3 = null;
                }
                Collection arrayList = new ArrayList();
                for (Object next : list3) {
                    if (next instanceof MediaWrapper) {
                        arrayList.add(next);
                    }
                }
                int size = ((List) arrayList).size();
                List<? extends MediaLibraryItem> list4 = this.mediaList;
                if (list4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                    list4 = null;
                }
                if (size == list4.size()) {
                    List<? extends MediaLibraryItem> list5 = this.mediaList;
                    if (list5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                        list5 = null;
                    }
                    Collection arrayList2 = new ArrayList();
                    for (Object next2 : list5) {
                        MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) next2;
                        if ((mediaLibraryItem instanceof MediaWrapper) && ((MediaWrapper) mediaLibraryItem).getType() != 3) {
                            arrayList2.add(next2);
                        }
                    }
                    int size2 = ((List) arrayList2).size();
                    List<? extends MediaLibraryItem> list6 = this.mediaList;
                    if (list6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                        list6 = null;
                    }
                    Collection arrayList3 = new ArrayList();
                    for (Object next3 : list6) {
                        MediaLibraryItem mediaLibraryItem2 = (MediaLibraryItem) next3;
                        if ((mediaLibraryItem2 instanceof MediaWrapper) && ((MediaWrapper) mediaLibraryItem2).getType() == 3) {
                            arrayList3.add(next3);
                        }
                    }
                    int size3 = ((List) arrayList3).size();
                    if (size2 == 0) {
                        str = getString(R.string.confirm_delete_folders, Integer.valueOf(size3));
                    } else if (size3 == 0) {
                        str = getString(R.string.confirm_delete_files, Integer.valueOf(size2));
                    } else {
                        str = getString(R.string.confirm_delete_folders_and_files, Integer.valueOf(size3), Integer.valueOf(size2));
                    }
                    charSequence = str;
                }
            }
            List<? extends MediaLibraryItem> list7 = this.mediaList;
            if (list7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                list7 = null;
            }
            if (list7.get(0) instanceof MediaWrapper) {
                List<? extends MediaLibraryItem> list8 = this.mediaList;
                if (list8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                    list8 = null;
                }
                Object obj = list8.get(0);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                int i = ((MediaWrapper) obj).getType() == 3 ? R.string.confirm_delete_folder : R.string.confirm_delete;
                List<? extends MediaLibraryItem> list9 = this.mediaList;
                if (list9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                    list9 = null;
                }
                charSequence = getString(i, ((MediaLibraryItem) list9.get(0)).getTitle());
            } else {
                List<? extends MediaLibraryItem> list10 = this.mediaList;
                if (list10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                    list10 = null;
                }
                if (list10.get(0) instanceof Album) {
                    int i2 = R.string.confirm_delete_album;
                    List<? extends MediaLibraryItem> list11 = this.mediaList;
                    if (list11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                        list11 = null;
                    }
                    charSequence = getString(i2, ((MediaLibraryItem) list11.get(0)).getTitle());
                } else {
                    List<? extends MediaLibraryItem> list12 = this.mediaList;
                    if (list12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                        list12 = null;
                    }
                    if (list12.get(0) instanceof Playlist) {
                        int i3 = R.string.confirm_delete_playlist;
                        List<? extends MediaLibraryItem> list13 = this.mediaList;
                        if (list13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                            list13 = null;
                        }
                        charSequence = getString(i3, ((MediaLibraryItem) list13.get(0)).getTitle());
                    } else {
                        int i4 = R.string.confirm_delete_several_media;
                        List<? extends MediaLibraryItem> list14 = this.mediaList;
                        if (list14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mediaList");
                            list14 = null;
                        }
                        charSequence = getString(i4, Integer.valueOf(list14.size()));
                    }
                }
            }
        }
        textView.setText(charSequence);
        String str2 = this.descriptionString;
        if (str2 != null && str2.length() > 0) {
            TextView textView2 = this.description;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(TvContractCompat.Channels.COLUMN_DESCRIPTION);
                textView2 = null;
            }
            textView2.setText(this.descriptionString);
        }
        String str3 = this.buttonText;
        if (str3 != null && str3.length() > 0) {
            Button button = this.deleteButton;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deleteButton");
                button = null;
            }
            button.setText(this.buttonText);
        }
        AnimatedVectorDrawableCompat create = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_delete);
        Intrinsics.checkNotNull(create);
        ImageView imageView2 = this.deleteAnimation;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deleteAnimation");
        } else {
            imageView = imageView2;
        }
        imageView.setImageDrawable(create);
        create.registerAnimationCallback(new ConfirmDeleteDialog$onCreateView$3(create));
        create.start();
        return inflate;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(ConfirmDeleteDialog confirmDeleteDialog, View view) {
        Intrinsics.checkNotNullParameter(confirmDeleteDialog, "this$0");
        Function0<Unit> function0 = confirmDeleteDialog.listener;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listener");
            function0 = null;
        }
        function0.invoke();
        confirmDeleteDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(ConfirmDeleteDialog confirmDeleteDialog, View view) {
        Intrinsics.checkNotNullParameter(confirmDeleteDialog, "this$0");
        confirmDeleteDialog.dismiss();
    }

    public View initialFocusedView() {
        ImageView imageView = this.deleteAnimation;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deleteAnimation");
            imageView = null;
        }
        return imageView;
    }
}
