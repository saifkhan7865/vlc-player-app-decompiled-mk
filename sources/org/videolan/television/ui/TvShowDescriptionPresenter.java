package org.videolan.television.ui;

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\t"}, d2 = {"Lorg/videolan/television/ui/TvShowDescriptionPresenter;", "Landroidx/leanback/widget/AbstractDetailsDescriptionPresenter;", "()V", "onBindDescription", "", "viewHolder", "Landroidx/leanback/widget/AbstractDetailsDescriptionPresenter$ViewHolder;", "itemData", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvShowDescriptionPresenter.kt */
public final class TvShowDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0013, code lost:
        r0 = r0.getMetadata();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindDescription(androidx.leanback.widget.AbstractDetailsDescriptionPresenter.ViewHolder r5, java.lang.Object r6) {
        /*
            r4 = this;
            java.lang.String r0 = "viewHolder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "itemData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r6 = (org.videolan.moviepedia.viewmodel.MediaMetadataFull) r6
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r0 = r6.getMetadata()
            r1 = 0
            if (r0 == 0) goto L_0x001e
            org.videolan.moviepedia.database.models.MediaMetadata r0 = r0.getMetadata()
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = r0.getSummary()
            goto L_0x001f
        L_0x001e:
            r0 = r1
        L_0x001f:
            android.widget.TextView r2 = r5.getTitle()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r3 = r6.getMetadata()
            if (r3 == 0) goto L_0x0034
            org.videolan.moviepedia.database.models.MediaMetadata r3 = r3.getMetadata()
            if (r3 == 0) goto L_0x0034
            java.lang.String r3 = r3.getTitle()
            goto L_0x0035
        L_0x0034:
            r3 = r1
        L_0x0035:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r2.setText(r3)
            android.widget.TextView r2 = r5.getSubtitle()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r6 = r6.getMetadata()
            if (r6 == 0) goto L_0x004e
            org.videolan.moviepedia.database.models.MediaMetadata r6 = r6.getMetadata()
            if (r6 == 0) goto L_0x004e
            java.lang.String r1 = org.videolan.moviepedia.database.models.MediaMetadataKt.getYear(r6)
        L_0x004e:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2.setText(r1)
            android.widget.TextView r5 = r5.getBody()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r5.setText(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.TvShowDescriptionPresenter.onBindDescription(androidx.leanback.widget.AbstractDetailsDescriptionPresenter$ViewHolder, java.lang.Object):void");
    }
}
