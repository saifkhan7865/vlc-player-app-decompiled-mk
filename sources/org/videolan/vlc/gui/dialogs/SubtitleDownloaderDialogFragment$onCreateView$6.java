package org.videolan.vlc.gui.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.databinding.SubtitleDownloaderDialogBinding;
import org.videolan.vlc.gui.view.OnItemSelectListener;
import org.videolan.vlc.viewmodels.SubtitlesModel;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, d2 = {"org/videolan/vlc/gui/dialogs/SubtitleDownloaderDialogFragment$onCreateView$6", "Lorg/videolan/vlc/gui/view/OnItemSelectListener;", "onItemSelect", "", "selectedItems", "", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitleDownloaderDialogFragment.kt */
public final class SubtitleDownloaderDialogFragment$onCreateView$6 implements OnItemSelectListener {
    final /* synthetic */ SubtitleDownloaderDialogFragment this$0;

    SubtitleDownloaderDialogFragment$onCreateView$6(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment) {
        this.this$0 = subtitleDownloaderDialogFragment;
    }

    public void onItemSelect(List<Integer> list) {
        List list2;
        Intrinsics.checkNotNullParameter(list, "selectedItems");
        int size = list.size();
        SubtitleDownloaderDialogBinding access$getBinding$p = this.this$0.binding;
        SubtitlesModel subtitlesModel = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        if (size == access$getBinding$p.languageListSpinner.getAllValuesOfLanguages().length) {
            list2 = CollectionsKt.emptyList();
        } else {
            SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment = this.this$0;
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                int intValue = ((Number) next).intValue();
                if (intValue >= 0) {
                    SubtitleDownloaderDialogBinding access$getBinding$p2 = subtitleDownloaderDialogFragment.binding;
                    if (access$getBinding$p2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p2 = null;
                    }
                    if (intValue < access$getBinding$p2.languageListSpinner.getAllValuesOfLanguages().length) {
                        arrayList.add(next);
                    }
                }
            }
            Iterable<Number> iterable = (List) arrayList;
            SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment2 = this.this$0;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Number intValue2 : iterable) {
                int intValue3 = intValue2.intValue();
                SubtitleDownloaderDialogBinding access$getBinding$p3 = subtitleDownloaderDialogFragment2.binding;
                if (access$getBinding$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    access$getBinding$p3 = null;
                }
                arrayList2.add(access$getBinding$p3.languageListSpinner.getAllValuesOfLanguages()[intValue3]);
            }
            list2 = (List) arrayList2;
        }
        SubtitlesModel access$getViewModel$p = this.this$0.viewModel;
        if (access$getViewModel$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            subtitlesModel = access$getViewModel$p;
        }
        subtitlesModel.getObservableSearchLanguage().set(list2);
    }
}
