package org.videolan.vlc.gui.helpers;

import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "bookmarkList", "", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BookmarkListDelegate.kt */
final class BookmarkListDelegate$show$1$4 extends Lambda implements Function1<List<Bookmark>, Unit> {
    final /* synthetic */ BookmarkListDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BookmarkListDelegate$show$1$4(BookmarkListDelegate bookmarkListDelegate) {
        super(1);
        this.this$0 = bookmarkListDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<Bookmark>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<Bookmark> list) {
        List<Bookmark> list2 = list;
        BookmarkAdapter access$getAdapter$p = this.this$0.adapter;
        View view = null;
        if (access$getAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            access$getAdapter$p = null;
        }
        Intrinsics.checkNotNull(list);
        access$getAdapter$p.update(list2);
        this.this$0.getMarkerContainer().removeAllViews();
        MediaWrapper currentMediaWrapper = this.this$0.getService().getCurrentMediaWrapper();
        if (currentMediaWrapper != null) {
            long length = currentMediaWrapper.getLength();
            BookmarkListDelegate bookmarkListDelegate = this.this$0;
            if (length >= 1) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(bookmarkListDelegate.getMarkerContainer());
                for (Bookmark time : list2) {
                    ImageView imageView = new ImageView(bookmarkListDelegate.getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setId(View.generateViewId());
                    int generateViewId = View.generateViewId();
                    constraintSet.create(generateViewId, 1);
                    constraintSet.setGuidelinePercent(generateViewId, ((float) time.getTime()) / ((float) length));
                    ConstraintSet constraintSet2 = constraintSet;
                    ImageView imageView2 = imageView;
                    constraintSet2.connect(imageView.getId(), 6, generateViewId, 6, 0);
                    constraintSet2.connect(imageView2.getId(), 7, generateViewId, 7, 0);
                    constraintSet.constrainWidth(imageView2.getId(), -2);
                    constraintSet.constrainHeight(imageView2.getId(), -2);
                    constraintSet.connect(imageView2.getId(), 4, 0, 4);
                    constraintSet.connect(imageView2.getId(), 3, 0, 3);
                    imageView2.setImageDrawable(ContextCompat.getDrawable(bookmarkListDelegate.getActivity(), R.drawable.ic_bookmark_marker));
                    bookmarkListDelegate.getMarkerContainer().addView(imageView2);
                }
                constraintSet.applyTo(bookmarkListDelegate.getMarkerContainer());
            }
        }
        if (!list2.isEmpty()) {
            View access$getEmptyView$p = this.this$0.emptyView;
            if (access$getEmptyView$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emptyView");
            } else {
                view = access$getEmptyView$p;
            }
            KotlinExtensionsKt.setGone(view);
            return;
        }
        View access$getEmptyView$p2 = this.this$0.emptyView;
        if (access$getEmptyView$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyView");
        } else {
            view = access$getEmptyView$p2;
        }
        KotlinExtensionsKt.setVisible(view);
    }
}
