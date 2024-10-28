package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import io.ktor.http.ContentDisposition;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.medialibrary.Tools;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.ChapterListItemBinding;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0004\u001e\u001f !B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u001a\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Lorg/videolan/vlc/gui/dialogs/IOnChapterSelectedListener;", "()V", "chapterList", "Landroidx/recyclerview/widget/RecyclerView;", "nestedScrollView", "Landroidx/core/widget/NestedScrollView;", "service", "Lorg/videolan/vlc/PlaybackService;", "getDefaultState", "", "initChapterList", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onChapterSelected", "position", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onServiceChanged", "onViewCreated", "view", "Chapter", "ChapterAdapter", "ChapterViewHolder", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SelectChapterDialog.kt */
public final class SelectChapterDialog extends VLCBottomSheetDialogFragment implements IOnChapterSelectedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private RecyclerView chapterList;
    /* access modifiers changed from: private */
    public NestedScrollView nestedScrollView;
    private PlaybackService service;

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SelectChapterDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SelectChapterDialog newInstance() {
            return new SelectChapterDialog();
        }
    }

    public View initialFocusedView() {
        RecyclerView recyclerView = this.chapterList;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chapterList");
            recyclerView = null;
        }
        return recyclerView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_select_chapter, viewGroup);
        View findViewById = inflate.findViewById(R.id.chapter_list);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.chapterList = (RecyclerView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.chapter_nested_scroll);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.nestedScrollView = (NestedScrollView) findViewById2;
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        KextensionsKt.launchWhenStarted(FlowKt.onEach(PlaybackService.Companion.getServiceFlow(), new SelectChapterDialog$onViewCreated$1(this, (Continuation<? super SelectChapterDialog$onViewCreated$1>) null)), LifecycleOwnerKt.getLifecycleScope(this));
    }

    private final void initChapterList() {
        MediaPlayer.Chapter[] chapters;
        PlaybackService playbackService = this.service;
        if (playbackService != null && (chapters = playbackService.getChapters(-1)) != null && chapters.length > 1) {
            ArrayList arrayList = new ArrayList();
            int length = chapters.length;
            int i = 0;
            while (i < length) {
                TextUtils textUtils = TextUtils.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                int i2 = i + 1;
                String formatChapterTitle = textUtils.formatChapterTitle(requireActivity, i2, chapters[i].name);
                String millisToString = Tools.millisToString(chapters[i].timeOffset);
                Intrinsics.checkNotNullExpressionValue(millisToString, "millisToString(...)");
                arrayList.add(new Chapter(formatChapterTitle, millisToString));
                i = i2;
            }
            ChapterAdapter chapterAdapter = new ChapterAdapter(this, arrayList, Integer.valueOf(playbackService.getChapterIdx()), this);
            RecyclerView recyclerView = this.chapterList;
            RecyclerView recyclerView2 = null;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chapterList");
                recyclerView = null;
            }
            recyclerView.setLayoutManager(new SelectChapterDialog$initChapterList$1(playbackService, this, getActivity()));
            RecyclerView recyclerView3 = this.chapterList;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chapterList");
                recyclerView3 = null;
            }
            ViewCompat.setNestedScrollingEnabled(recyclerView3, false);
            RecyclerView recyclerView4 = this.chapterList;
            if (recyclerView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chapterList");
            } else {
                recyclerView2 = recyclerView4;
            }
            recyclerView2.setAdapter(chapterAdapter);
        }
    }

    public void onChapterSelected(int i) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.setChapterIdx(i);
        }
        dismiss();
    }

    /* access modifiers changed from: private */
    public final void onServiceChanged(PlaybackService playbackService) {
        if (playbackService != null) {
            this.service = playbackService;
            initChapterList();
            return;
        }
        this.service = null;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog$Chapter;", "", "name", "", "time", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SelectChapterDialog.kt */
    public static final class Chapter {
        private final String name;
        private final String time;

        public static /* synthetic */ Chapter copy$default(Chapter chapter, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = chapter.name;
            }
            if ((i & 2) != 0) {
                str2 = chapter.time;
            }
            return chapter.copy(str, str2);
        }

        public final String component1() {
            return this.name;
        }

        public final String component2() {
            return this.time;
        }

        public final Chapter copy(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(str2, RtspHeaders.Values.TIME);
            return new Chapter(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Chapter)) {
                return false;
            }
            Chapter chapter = (Chapter) obj;
            return Intrinsics.areEqual((Object) this.name, (Object) chapter.name) && Intrinsics.areEqual((Object) this.time, (Object) chapter.time);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.time.hashCode();
        }

        public String toString() {
            return "Chapter(name=" + this.name + ", time=" + this.time + ')';
        }

        public Chapter(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(str2, RtspHeaders.Values.TIME);
            this.name = str;
            this.time = str2;
        }

        public final String getName() {
            return this.name;
        }

        public final String getTime() {
            return this.time;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u000f\u001a\u00020\bH\u0016J\u001c\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0016R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog$ChapterAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog$ChapterViewHolder;", "Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog;", "chapters", "", "Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog$Chapter;", "selectedIndex", "", "listener", "Lorg/videolan/vlc/gui/dialogs/IOnChapterSelectedListener;", "(Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog;Ljava/util/List;Ljava/lang/Integer;Lorg/videolan/vlc/gui/dialogs/IOnChapterSelectedListener;)V", "binding", "Lorg/videolan/vlc/databinding/ChapterListItemBinding;", "Ljava/lang/Integer;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SelectChapterDialog.kt */
    public final class ChapterAdapter extends RecyclerView.Adapter<ChapterViewHolder> {
        private ChapterListItemBinding binding;
        private final List<Chapter> chapters;
        private final IOnChapterSelectedListener listener;
        private final Integer selectedIndex;
        final /* synthetic */ SelectChapterDialog this$0;

        public ChapterAdapter(SelectChapterDialog selectChapterDialog, List<Chapter> list, Integer num, IOnChapterSelectedListener iOnChapterSelectedListener) {
            Intrinsics.checkNotNullParameter(list, "chapters");
            Intrinsics.checkNotNullParameter(iOnChapterSelectedListener, "listener");
            this.this$0 = selectChapterDialog;
            this.chapters = list;
            this.selectedIndex = num;
            this.listener = iOnChapterSelectedListener;
        }

        public void onBindViewHolder(ChapterViewHolder chapterViewHolder, int i) {
            Intrinsics.checkNotNullParameter(chapterViewHolder, "holder");
            ChapterListItemBinding chapterListItemBinding = this.binding;
            ChapterListItemBinding chapterListItemBinding2 = null;
            if (chapterListItemBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                chapterListItemBinding = null;
            }
            chapterListItemBinding.setChapter(this.chapters.get(i));
            ChapterListItemBinding chapterListItemBinding3 = this.binding;
            if (chapterListItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                chapterListItemBinding2 = chapterListItemBinding3;
            }
            Integer num = this.selectedIndex;
            chapterListItemBinding2.setSelected(Boolean.valueOf(num != null && num.intValue() == i));
        }

        public ChapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.chapter_list_item, viewGroup, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type org.videolan.vlc.databinding.ChapterListItemBinding");
            this.binding = (ChapterListItemBinding) inflate;
            SelectChapterDialog selectChapterDialog = this.this$0;
            ChapterListItemBinding chapterListItemBinding = this.binding;
            if (chapterListItemBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                chapterListItemBinding = null;
            }
            return new ChapterViewHolder(selectChapterDialog, chapterListItemBinding, this.listener);
        }

        public int getItemCount() {
            return this.chapters.size();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog$ChapterViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/ChapterListItemBinding;", "listener", "Lorg/videolan/vlc/gui/dialogs/IOnChapterSelectedListener;", "(Lorg/videolan/vlc/gui/dialogs/SelectChapterDialog;Lorg/videolan/vlc/databinding/ChapterListItemBinding;Lorg/videolan/vlc/gui/dialogs/IOnChapterSelectedListener;)V", "getBinding", "()Lorg/videolan/vlc/databinding/ChapterListItemBinding;", "setBinding", "(Lorg/videolan/vlc/databinding/ChapterListItemBinding;)V", "onClick", "", "v", "Landroid/view/View;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SelectChapterDialog.kt */
    public final class ChapterViewHolder extends RecyclerView.ViewHolder {
        private ChapterListItemBinding binding;
        private final IOnChapterSelectedListener listener;
        final /* synthetic */ SelectChapterDialog this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ChapterViewHolder(SelectChapterDialog selectChapterDialog, ChapterListItemBinding chapterListItemBinding, IOnChapterSelectedListener iOnChapterSelectedListener) {
            super(chapterListItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(chapterListItemBinding, "binding");
            Intrinsics.checkNotNullParameter(iOnChapterSelectedListener, "listener");
            this.this$0 = selectChapterDialog;
            this.binding = chapterListItemBinding;
            this.listener = iOnChapterSelectedListener;
            chapterListItemBinding.setHolder(this);
        }

        public final ChapterListItemBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(ChapterListItemBinding chapterListItemBinding) {
            Intrinsics.checkNotNullParameter(chapterListItemBinding, "<set-?>");
            this.binding = chapterListItemBinding;
        }

        public final void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            this.listener.onChapterSelected(getLayoutPosition());
        }
    }
}
