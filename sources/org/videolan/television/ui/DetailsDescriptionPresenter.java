package org.videolan.television.ui;

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lorg/videolan/television/ui/DetailsDescriptionPresenter;", "Landroidx/leanback/widget/AbstractDetailsDescriptionPresenter;", "()V", "metadata", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getMetadata", "()Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "setMetadata", "(Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;)V", "onBindDescription", "", "viewHolder", "Landroidx/leanback/widget/AbstractDetailsDescriptionPresenter$ViewHolder;", "itemData", "", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DetailsDescriptionPresenter.kt */
public final class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "DetailsDescriptionPresenter";
    private MediaMetadataWithImages metadata;

    public final MediaMetadataWithImages getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(MediaMetadataWithImages mediaMetadataWithImages) {
        this.metadata = mediaMetadataWithImages;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0071, code lost:
        r2 = org.videolan.moviepedia.database.models.MediaMetadataKt.subtitle(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindDescription(androidx.leanback.widget.AbstractDetailsDescriptionPresenter.ViewHolder r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.lang.String r0 = "viewHolder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "itemData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            org.videolan.television.ui.MediaItemDetails r5 = (org.videolan.television.ui.MediaItemDetails) r5
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r0 = r3.metadata
            if (r0 == 0) goto L_0x001c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            org.videolan.moviepedia.database.models.MediaMetadata r0 = r0.getMetadata()
            java.lang.String r0 = r0.getSummary()
            goto L_0x004b
        L_0x001c:
            java.lang.String r0 = r5.getBody()
            if (r0 != 0) goto L_0x002b
            java.lang.String r0 = r5.getLocation()
            java.lang.String r0 = android.net.Uri.decode(r0)
            goto L_0x004b
        L_0x002b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r5.getBody()
            r0.append(r1)
            r1 = 10
            r0.append(r1)
            java.lang.String r1 = r5.getLocation()
            java.lang.String r1 = android.net.Uri.decode(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x004b:
            android.widget.TextView r1 = r4.getTitle()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r2 = r3.metadata
            if (r2 == 0) goto L_0x0060
            org.videolan.moviepedia.database.models.MediaMetadata r2 = r2.getMetadata()
            if (r2 == 0) goto L_0x0060
            java.lang.String r2 = r2.getTitle()
            if (r2 == 0) goto L_0x0060
            goto L_0x0064
        L_0x0060:
            java.lang.String r2 = r5.getTitle()
        L_0x0064:
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
            android.widget.TextView r1 = r4.getSubtitle()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r2 = r3.metadata
            if (r2 == 0) goto L_0x007a
            java.lang.String r2 = org.videolan.moviepedia.database.models.MediaMetadataKt.subtitle(r2)
            if (r2 == 0) goto L_0x007a
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L_0x0081
        L_0x007a:
            java.lang.String r5 = r5.getSubTitle()
            r2 = r5
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L_0x0081:
            r1.setText(r2)
            android.widget.TextView r4 = r4.getBody()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r4.setText(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.DetailsDescriptionPresenter.onBindDescription(androidx.leanback.widget.AbstractDetailsDescriptionPresenter$ViewHolder, java.lang.Object):void");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/DetailsDescriptionPresenter$Companion;", "", "()V", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DetailsDescriptionPresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
