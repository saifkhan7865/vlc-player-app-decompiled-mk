package org.videolan.vlc;

import android.content.ContentProvider;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ArtworkProvider$$ExternalSyntheticLambda1 implements ContentProvider.PipeDataWriter {
    public final /* synthetic */ ArtworkProvider f$0;

    public /* synthetic */ ArtworkProvider$$ExternalSyntheticLambda1(ArtworkProvider artworkProvider) {
        this.f$0 = artworkProvider;
    }

    public final void writeDataToPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, Object obj) {
        ArtworkProvider.getPFDFromBitmap$lambda$8(this.f$0, parcelFileDescriptor, uri, str, bundle, (Bitmap) obj);
    }
}
