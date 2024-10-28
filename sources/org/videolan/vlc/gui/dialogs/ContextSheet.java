package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.ContextItemBinding;
import org.videolan.vlc.databinding.ContextualSheetBinding;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J$\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0002J\u0012\u0010%\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX.¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006'"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/ContextSheet;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/ContextualSheetBinding;", "itemPosition", "", "menuItems", "", "Lorg/videolan/vlc/gui/dialogs/CtxMenuItem;", "receiver", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "getReceiver", "()Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "setReceiver", "(Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;)V", "getDefaultState", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "populateMenuItems", "", "flags", "Lorg/videolan/vlc/util/FlagSet;", "Lorg/videolan/vlc/util/ContextOption;", "restoreReceiver", "ContextAdapter", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
public final class ContextSheet extends VLCBottomSheetDialogFragment {
    private ContextualSheetBinding binding;
    /* access modifiers changed from: private */
    public int itemPosition = -1;
    /* access modifiers changed from: private */
    public List<? extends CtxMenuItem> menuItems;
    public CtxActionReceiver receiver;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return false;
    }

    public final CtxActionReceiver getReceiver() {
        CtxActionReceiver ctxActionReceiver = this.receiver;
        if (ctxActionReceiver != null) {
            return ctxActionReceiver;
        }
        Intrinsics.throwUninitializedPropertyAccessException("receiver");
        return null;
    }

    public final void setReceiver(CtxActionReceiver ctxActionReceiver) {
        Intrinsics.checkNotNullParameter(ctxActionReceiver, "<set-?>");
        this.receiver = ctxActionReceiver;
    }

    public View initialFocusedView() {
        ContextualSheetBinding contextualSheetBinding = this.binding;
        if (contextualSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            contextualSheetBinding = null;
        }
        RecyclerView recyclerView = contextualSheetBinding.ctxList;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "ctxList");
        return recyclerView;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.itemPosition = arguments != null ? arguments.getInt(ContextSheetKt.CTX_POSITION_KEY) : -1;
        if (this.receiver == null) {
            restoreReceiver(bundle);
        }
    }

    private final void restoreReceiver(Bundle bundle) {
        if (bundle != null) {
            List<Fragment> fragments = requireActivity().getSupportFragmentManager().getFragments();
            Intrinsics.checkNotNullExpressionValue(fragments, "getFragments(...)");
            int i = 0;
            for (Fragment next : fragments) {
                int i2 = i + 1;
                if (next instanceof CtxActionReceiver) {
                    setReceiver((CtxActionReceiver) next);
                    return;
                } else if (i > 1) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        dismiss();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, R.layout.contextual_sheet, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        ContextualSheetBinding contextualSheetBinding = (ContextualSheetBinding) inflate;
        this.binding = contextualSheetBinding;
        if (contextualSheetBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            contextualSheetBinding = null;
        }
        View root = contextualSheetBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        CharSequence charSequence;
        String string;
        String str;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        ContextualSheetBinding contextualSheetBinding = null;
        if (arguments == null || !arguments.containsKey(ContextSheetKt.CTX_MEDIA_KEY)) {
            Bundle arguments2 = getArguments();
            if (arguments2 != null && arguments2.containsKey(ContextSheetKt.CTX_TITLE_KEY)) {
                ContextualSheetBinding contextualSheetBinding2 = this.binding;
                if (contextualSheetBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    contextualSheetBinding2 = null;
                }
                TextView textView = contextualSheetBinding2.ctxCoverTitle;
                Bundle arguments3 = getArguments();
                if (arguments3 != null && (string = arguments3.getString(ContextSheetKt.CTX_TITLE_KEY)) != null) {
                    charSequence = string;
                }
                textView.setText(charSequence);
            }
        } else {
            Bundle arguments4 = getArguments();
            Object obj = arguments4 != null ? arguments4.get(ContextSheetKt.CTX_MEDIA_KEY) : null;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.media.MediaLibraryItem");
            MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) obj;
            ContextualSheetBinding contextualSheetBinding3 = this.binding;
            if (contextualSheetBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                contextualSheetBinding3 = null;
            }
            contextualSheetBinding3.setItem(mediaLibraryItem);
            if (mediaLibraryItem instanceof MediaWrapper) {
                str = ((MediaWrapper) mediaLibraryItem).getArtworkURL();
            } else {
                str = mediaLibraryItem.getArtworkMrl();
            }
            ContextualSheetBinding contextualSheetBinding4 = this.binding;
            if (contextualSheetBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                contextualSheetBinding4 = null;
            }
            CharSequence charSequence2 = str;
            contextualSheetBinding4.setShowCover(true ^ (charSequence2 == null || StringsKt.isBlank(charSequence2)));
            ContextualSheetBinding contextualSheetBinding5 = this.binding;
            if (contextualSheetBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                contextualSheetBinding5 = null;
            }
            contextualSheetBinding5.ctxCoverTitle.setText(mediaLibraryItem.getTitle());
            ContextualSheetBinding contextualSheetBinding6 = this.binding;
            if (contextualSheetBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                contextualSheetBinding6 = null;
            }
            contextualSheetBinding6.ctxTitle.setText(mediaLibraryItem.getTitle());
        }
        ContextualSheetBinding contextualSheetBinding7 = this.binding;
        if (contextualSheetBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            contextualSheetBinding7 = null;
        }
        contextualSheetBinding7.ctxList.setLayoutManager(new LinearLayoutManager(requireContext()));
        ContextualSheetBinding contextualSheetBinding8 = this.binding;
        if (contextualSheetBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            contextualSheetBinding = contextualSheetBinding8;
        }
        contextualSheetBinding.ctxList.setAdapter(new ContextAdapter());
        FlagSet flagSet = new FlagSet(ContextOption.class);
        Bundle arguments5 = getArguments();
        flagSet.setCapabilities(arguments5 != null ? arguments5.getLong(ContextSheetKt.CTX_FLAGS_KEY) : 0);
        this.menuItems = populateMenuItems(flagSet);
    }

    private final List<CtxMenuItem> populateMenuItems(FlagSet<ContextOption> flagSet) {
        List<CtxMenuItem> arrayList = new ArrayList<>();
        if (flagSet.contains(ContextOption.CTX_PLAY)) {
            ContextOption contextOption = ContextOption.CTX_PLAY;
            String string = getString(R.string.play);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            arrayList.add(new Simple(contextOption, string, R.drawable.ic_play));
        }
        if (flagSet.contains(ContextOption.CTX_PLAY_SHUFFLE)) {
            ContextOption contextOption2 = ContextOption.CTX_PLAY_SHUFFLE;
            String string2 = getString(R.string.shuffle_play);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            arrayList.add(new Simple(contextOption2, string2, R.drawable.ic_shuffle));
        }
        if (flagSet.contains(ContextOption.CTX_PLAY_FROM_START)) {
            ContextOption contextOption3 = ContextOption.CTX_PLAY_FROM_START;
            String string3 = getString(R.string.play_from_start);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            arrayList.add(new Simple(contextOption3, string3, R.drawable.ic_play_from_start));
        }
        if (flagSet.contains(ContextOption.CTX_PLAY_ALL)) {
            ContextOption contextOption4 = ContextOption.CTX_PLAY_ALL;
            String string4 = getString(R.string.play_all);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            arrayList.add(new Simple(contextOption4, string4, R.drawable.ic_play_all));
        }
        if (flagSet.contains(ContextOption.CTX_PLAY_AS_AUDIO)) {
            ContextOption contextOption5 = ContextOption.CTX_PLAY_AS_AUDIO;
            String string5 = getString(R.string.play_as_audio);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            arrayList.add(new Simple(contextOption5, string5, R.drawable.ic_play_as_audio));
        }
        if (flagSet.contains(ContextOption.CTX_APPEND)) {
            ContextOption contextOption6 = ContextOption.CTX_APPEND;
            String string6 = getString(R.string.append);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            arrayList.add(new Simple(contextOption6, string6, R.drawable.ic_play_append));
        }
        if (flagSet.contains(ContextOption.CTX_PLAY_NEXT)) {
            ContextOption contextOption7 = ContextOption.CTX_PLAY_NEXT;
            String string7 = getString(R.string.insert_next);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
            arrayList.add(new Simple(contextOption7, string7, R.drawable.ic_play_next));
        }
        if (flagSet.contains(ContextOption.CTX_DOWNLOAD_SUBTITLES)) {
            ContextOption contextOption8 = ContextOption.CTX_DOWNLOAD_SUBTITLES;
            String string8 = getString(R.string.download_subtitles);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
            arrayList.add(new Simple(contextOption8, string8, R.drawable.ic_download_subtitles));
        }
        if (flagSet.contains(ContextOption.CTX_INFORMATION)) {
            ContextOption contextOption9 = ContextOption.CTX_INFORMATION;
            String string9 = getString(R.string.info);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
            arrayList.add(new Simple(contextOption9, string9, R.drawable.ic_information));
        }
        if (flagSet.contains(ContextOption.CTX_ADD_TO_PLAYLIST)) {
            ContextOption contextOption10 = ContextOption.CTX_ADD_TO_PLAYLIST;
            String string10 = getString(R.string.add_to_playlist);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
            arrayList.add(new Simple(contextOption10, string10, R.drawable.ic_add_to_playlist));
        }
        if (flagSet.contains(ContextOption.CTX_SET_RINGTONE) && AndroidDevices.INSTANCE.isPhone()) {
            ContextOption contextOption11 = ContextOption.CTX_SET_RINGTONE;
            String string11 = getString(R.string.set_song);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
            arrayList.add(new Simple(contextOption11, string11, R.drawable.ic_set_ringtone));
        }
        if (flagSet.contains(ContextOption.CTX_FAV_ADD)) {
            ContextOption contextOption12 = ContextOption.CTX_FAV_ADD;
            String string12 = getString(R.string.favorites_add);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
            arrayList.add(new Simple(contextOption12, string12, R.drawable.ic_fav_add));
        }
        if (flagSet.contains(ContextOption.CTX_ADD_SCANNED)) {
            ContextOption contextOption13 = ContextOption.CTX_ADD_SCANNED;
            String string13 = getString(R.string.add_to_scanned);
            Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
            arrayList.add(new Simple(contextOption13, string13, R.drawable.ic_add_to_scan));
        }
        if (flagSet.contains(ContextOption.CTX_FAV_EDIT)) {
            ContextOption contextOption14 = ContextOption.CTX_FAV_EDIT;
            String string14 = getString(R.string.favorites_edit);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
            arrayList.add(new Simple(contextOption14, string14, R.drawable.ic_edit));
        }
        if (flagSet.contains(ContextOption.CTX_FAV_REMOVE)) {
            ContextOption contextOption15 = ContextOption.CTX_FAV_REMOVE;
            String string15 = getString(R.string.favorites_remove);
            Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
            arrayList.add(new Simple(contextOption15, string15, R.drawable.ic_fav_remove));
        }
        if (flagSet.contains(ContextOption.CTX_REMOVE_FROM_PLAYLIST)) {
            ContextOption contextOption16 = ContextOption.CTX_REMOVE_FROM_PLAYLIST;
            String string16 = getString(R.string.remove);
            Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
            arrayList.add(new Simple(contextOption16, string16, R.drawable.ic_remove_from_playlist));
        }
        if (flagSet.contains(ContextOption.CTX_STOP_AFTER_THIS)) {
            ContextOption contextOption17 = ContextOption.CTX_STOP_AFTER_THIS;
            String string17 = getString(R.string.stop_after_this);
            Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
            arrayList.add(new Simple(contextOption17, string17, R.drawable.ic_stop_after_this));
        }
        if (flagSet.contains(ContextOption.CTX_RENAME)) {
            ContextOption contextOption18 = ContextOption.CTX_RENAME;
            String string18 = getString(R.string.rename);
            Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
            arrayList.add(new Simple(contextOption18, string18, R.drawable.ic_edit));
        }
        if (flagSet.contains(ContextOption.CTX_COPY)) {
            ContextOption contextOption19 = ContextOption.CTX_COPY;
            String string19 = getString(R.string.copy_to_clipboard);
            Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
            arrayList.add(new Simple(contextOption19, string19, R.drawable.ic_link));
        }
        if (flagSet.contains(ContextOption.CTX_DELETE)) {
            ContextOption contextOption20 = ContextOption.CTX_DELETE;
            String string20 = getString(R.string.delete);
            Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
            arrayList.add(new Simple(contextOption20, string20, R.drawable.ic_delete));
        }
        if (flagSet.contains(ContextOption.CTX_SHARE)) {
            ContextOption contextOption21 = ContextOption.CTX_SHARE;
            String string21 = getString(R.string.share);
            Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
            arrayList.add(new Simple(contextOption21, string21, R.drawable.ic_share));
        }
        if (flagSet.contains(ContextOption.CTX_ADD_SHORTCUT) && ShortcutManagerCompat.isRequestPinShortcutSupported(requireActivity())) {
            ContextOption contextOption22 = ContextOption.CTX_ADD_SHORTCUT;
            String string22 = getString(R.string.create_shortcut);
            Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
            arrayList.add(new Simple(contextOption22, string22, R.drawable.ic_app_shortcut));
        }
        if (flagSet.contains(ContextOption.CTX_FIND_METADATA)) {
            ContextOption contextOption23 = ContextOption.CTX_FIND_METADATA;
            String string23 = getString(R.string.find_metadata);
            Intrinsics.checkNotNullExpressionValue(string23, "getString(...)");
            arrayList.add(new Simple(contextOption23, string23, R.drawable.ic_delete));
        }
        if (flagSet.contains(ContextOption.CTX_ADD_FOLDER_PLAYLIST)) {
            ContextOption contextOption24 = ContextOption.CTX_ADD_FOLDER_PLAYLIST;
            String string24 = getString(R.string.this_folder);
            Intrinsics.checkNotNullExpressionValue(string24, "getString(...)");
            arrayList.add(new Simple(contextOption24, string24, R.drawable.ic_add_to_playlist));
        }
        if (flagSet.contains(ContextOption.CTX_ADD_FOLDER_AND_SUB_PLAYLIST)) {
            ContextOption contextOption25 = ContextOption.CTX_ADD_FOLDER_AND_SUB_PLAYLIST;
            String string25 = getString(R.string.all_subfolders);
            Intrinsics.checkNotNullExpressionValue(string25, "getString(...)");
            arrayList.add(new Simple(contextOption25, string25, R.drawable.ic_add_to_playlist));
        }
        if (flagSet.contains(ContextOption.CTX_ADD_GROUP)) {
            ContextOption contextOption26 = ContextOption.CTX_ADD_GROUP;
            String string26 = getString(R.string.add_to_group);
            Intrinsics.checkNotNullExpressionValue(string26, "getString(...)");
            arrayList.add(new Simple(contextOption26, string26, R.drawable.ic_add_to_group));
        }
        if (flagSet.contains(ContextOption.CTX_REMOVE_GROUP)) {
            ContextOption contextOption27 = ContextOption.CTX_REMOVE_GROUP;
            String string27 = getString(R.string.remove_from_group);
            Intrinsics.checkNotNullExpressionValue(string27, "getString(...)");
            arrayList.add(new Simple(contextOption27, string27, R.drawable.ic_remove_from_group));
        }
        if (flagSet.contains(ContextOption.CTX_RENAME_GROUP)) {
            ContextOption contextOption28 = ContextOption.CTX_RENAME_GROUP;
            String string28 = getString(R.string.rename_group);
            Intrinsics.checkNotNullExpressionValue(string28, "getString(...)");
            arrayList.add(new Simple(contextOption28, string28, R.drawable.ic_edit));
        }
        if (flagSet.contains(ContextOption.CTX_UNGROUP)) {
            ContextOption contextOption29 = ContextOption.CTX_UNGROUP;
            String string29 = getString(R.string.ungroup);
            Intrinsics.checkNotNullExpressionValue(string29, "getString(...)");
            arrayList.add(new Simple(contextOption29, string29, R.drawable.ic_delete));
        }
        if (flagSet.contains(ContextOption.CTX_GROUP_SIMILAR)) {
            ContextOption contextOption30 = ContextOption.CTX_GROUP_SIMILAR;
            String string30 = getString(R.string.group_similar);
            Intrinsics.checkNotNullExpressionValue(string30, "getString(...)");
            arrayList.add(new Simple(contextOption30, string30, R.drawable.ic_group_auto));
        }
        if (flagSet.contains(ContextOption.CTX_MARK_AS_PLAYED)) {
            ContextOption contextOption31 = ContextOption.CTX_MARK_AS_PLAYED;
            String string31 = getString(R.string.mark_as_played);
            Intrinsics.checkNotNullExpressionValue(string31, "getString(...)");
            arrayList.add(new Simple(contextOption31, string31, R.drawable.ic_mark_as_played));
        }
        if (flagSet.contains(ContextOption.CTX_MARK_AS_UNPLAYED)) {
            ContextOption contextOption32 = ContextOption.CTX_MARK_AS_UNPLAYED;
            String string32 = getString(R.string.mark_as_not_played);
            Intrinsics.checkNotNullExpressionValue(string32, "getString(...)");
            arrayList.add(new Simple(contextOption32, string32, R.drawable.ic_mark_as_not_played));
        }
        if (flagSet.contains(ContextOption.CTX_MARK_ALL_AS_PLAYED)) {
            ContextOption contextOption33 = ContextOption.CTX_MARK_ALL_AS_PLAYED;
            String string33 = getString(R.string.mark_all_as_played);
            Intrinsics.checkNotNullExpressionValue(string33, "getString(...)");
            arrayList.add(new Simple(contextOption33, string33, R.drawable.ic_mark_all_as_played));
        }
        if (flagSet.contains(ContextOption.CTX_MARK_ALL_AS_UNPLAYED)) {
            ContextOption contextOption34 = ContextOption.CTX_MARK_ALL_AS_UNPLAYED;
            String string34 = getString(R.string.mark_all_as_not_played);
            Intrinsics.checkNotNullExpressionValue(string34, "getString(...)");
            arrayList.add(new Simple(contextOption34, string34, R.drawable.ic_mark_all_as_not_played));
        }
        if (flagSet.contains(ContextOption.CTX_GO_TO_FOLDER)) {
            ContextOption contextOption35 = ContextOption.CTX_GO_TO_FOLDER;
            String string35 = getString(R.string.go_to_folder);
            Intrinsics.checkNotNullExpressionValue(string35, "getString(...)");
            arrayList.add(new Simple(contextOption35, string35, R.drawable.ic_browse_parent));
        }
        if (flagSet.contains(ContextOption.CTX_CUSTOM_REMOVE)) {
            ContextOption contextOption36 = ContextOption.CTX_CUSTOM_REMOVE;
            String string36 = getString(R.string.remove_custom_path);
            Intrinsics.checkNotNullExpressionValue(string36, "getString(...)");
            arrayList.add(new Simple(contextOption36, string36, R.drawable.ic_delete));
        }
        if (flagSet.contains(ContextOption.CTX_BAN_FOLDER)) {
            ContextOption contextOption37 = ContextOption.CTX_BAN_FOLDER;
            String string37 = getString(R.string.group_ban_folder);
            Intrinsics.checkNotNullExpressionValue(string37, "getString(...)");
            arrayList.add(new Simple(contextOption37, string37, R.drawable.ic_hide_source));
        }
        return arrayList;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016J \u0010\u0011\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/ContextSheet$ContextAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/dialogs/ContextSheet$ContextAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/dialogs/ContextSheet;", "(Lorg/videolan/vlc/gui/dialogs/ContextSheet;)V", "inflater", "Landroid/view/LayoutInflater;", "getInflater", "()Landroid/view/LayoutInflater;", "inflater$delegate", "Lkotlin/Lazy;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContextSheet.kt */
    public final class ContextAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final Lazy inflater$delegate;

        public ContextAdapter() {
            this.inflater$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new ContextSheet$ContextAdapter$inflater$2(ContextSheet.this));
        }

        private final LayoutInflater getInflater() {
            Object value = this.inflater$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (LayoutInflater) value;
        }

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/ContextSheet$ContextAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/ContextItemBinding;", "(Lorg/videolan/vlc/gui/dialogs/ContextSheet$ContextAdapter;Lorg/videolan/vlc/databinding/ContextItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/ContextItemBinding;", "focusedColor", "", "getFocusedColor", "()I", "focusedColor$delegate", "Lkotlin/Lazy;", "textColor", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: ContextSheet.kt */
        public final class ViewHolder extends RecyclerView.ViewHolder {
            private final ContextItemBinding binding;
            private final Lazy focusedColor$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new ContextSheet$ContextAdapter$ViewHolder$focusedColor$2(this));
            private final int textColor;
            final /* synthetic */ ContextAdapter this$0;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ViewHolder(ContextAdapter contextAdapter, ContextItemBinding contextItemBinding) {
                super(contextItemBinding.getRoot());
                Intrinsics.checkNotNullParameter(contextItemBinding, "binding");
                this.this$0 = contextAdapter;
                this.binding = contextItemBinding;
                this.textColor = contextItemBinding.contextOptionTitle.getCurrentTextColor();
                this.itemView.setOnClickListener(new ContextSheet$ContextAdapter$ViewHolder$$ExternalSyntheticLambda0(ContextSheet.this, this));
                this.itemView.setOnFocusChangeListener(new ContextSheet$ContextAdapter$ViewHolder$$ExternalSyntheticLambda1(this));
            }

            public final ContextItemBinding getBinding() {
                return this.binding;
            }

            private final int getFocusedColor() {
                return ((Number) this.focusedColor$delegate.getValue()).intValue();
            }

            /* access modifiers changed from: private */
            public static final void _init_$lambda$0(ContextSheet contextSheet, ViewHolder viewHolder, View view) {
                Intrinsics.checkNotNullParameter(contextSheet, "this$0");
                Intrinsics.checkNotNullParameter(viewHolder, "this$1");
                CtxActionReceiver receiver = contextSheet.getReceiver();
                int access$getItemPosition$p = contextSheet.itemPosition;
                List access$getMenuItems$p = contextSheet.menuItems;
                if (access$getMenuItems$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("menuItems");
                    access$getMenuItems$p = null;
                }
                receiver.onCtxAction(access$getItemPosition$p, ((CtxMenuItem) access$getMenuItems$p.get(viewHolder.getLayoutPosition())).getId());
                contextSheet.dismiss();
            }

            /* access modifiers changed from: private */
            public static final void _init_$lambda$1(ViewHolder viewHolder, View view, boolean z) {
                Intrinsics.checkNotNullParameter(viewHolder, "this$0");
                viewHolder.binding.contextOptionTitle.setTextColor(z ? viewHolder.getFocusedColor() : viewHolder.textColor);
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            ContextItemBinding inflate = ContextItemBinding.inflate(getInflater(), viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ViewHolder(this, inflate);
        }

        public int getItemCount() {
            List access$getMenuItems$p = ContextSheet.this.menuItems;
            if (access$getMenuItems$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menuItems");
                access$getMenuItems$p = null;
            }
            return access$getMenuItems$p.size();
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Intrinsics.checkNotNullParameter(viewHolder, "holder");
            ContextItemBinding binding = viewHolder.getBinding();
            List access$getMenuItems$p = ContextSheet.this.menuItems;
            List list = null;
            if (access$getMenuItems$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menuItems");
                access$getMenuItems$p = null;
            }
            binding.setMenuItem((CtxMenuItem) access$getMenuItems$p.get(i));
            ImageView imageView = viewHolder.getBinding().contextOptionIcon;
            List access$getMenuItems$p2 = ContextSheet.this.menuItems;
            if (access$getMenuItems$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menuItems");
            } else {
                list = access$getMenuItems$p2;
            }
            imageView.setImageResource(((CtxMenuItem) list.get(i)).getIcon());
        }
    }
}
