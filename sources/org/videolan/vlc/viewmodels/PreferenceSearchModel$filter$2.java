package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "i0", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "kotlin.jvm.PlatformType", "i1", "invoke", "(Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;)Ljava/lang/Integer;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceSearchModel.kt */
final class PreferenceSearchModel$filter$2 extends Lambda implements Function2<PreferenceItem, PreferenceItem, Integer> {
    final /* synthetic */ String $query;
    final /* synthetic */ PreferenceSearchModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceSearchModel$filter$2(PreferenceSearchModel preferenceSearchModel, String str) {
        super(2);
        this.this$0 = preferenceSearchModel;
        this.$query = str;
    }

    public final Integer invoke(PreferenceItem preferenceItem, PreferenceItem preferenceItem2) {
        PreferenceSearchModel preferenceSearchModel = this.this$0;
        Intrinsics.checkNotNull(preferenceItem2);
        int access$score = preferenceSearchModel.score(preferenceItem2, this.$query);
        PreferenceSearchModel preferenceSearchModel2 = this.this$0;
        Intrinsics.checkNotNull(preferenceItem);
        return Integer.valueOf(access$score - preferenceSearchModel2.score(preferenceItem, this.$query));
    }
}
