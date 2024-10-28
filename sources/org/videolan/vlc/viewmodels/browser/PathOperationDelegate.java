package org.videolan.vlc.viewmodels.browser;

import android.net.Uri;
import android.util.Base64;
import androidx.collection.SimpleArrayMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/PathOperationDelegate;", "Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "()V", "appendPathToUri", "", "path", "", "uri", "Landroid/net/Uri$Builder;", "consumeSource", "getAndRemoveDestination", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getSource", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "makePathSafe", "kotlin.jvm.PlatformType", "replaceStoragePath", "retrieveSafePath", "encoded", "setDestination", "media", "setSource", "currentItem", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PathOperationDelegate.kt */
public final class PathOperationDelegate implements IPathOperationDelegate {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static MediaWrapper privateDestination;
    private static MediaLibraryItem privateSource;
    /* access modifiers changed from: private */
    public static final SimpleArrayMap<String, String> storages = new SimpleArrayMap<>();

    public void setDestination(MediaWrapper mediaWrapper) {
        privateDestination = mediaWrapper;
    }

    public void setSource(MediaLibraryItem mediaLibraryItem) {
        privateSource = mediaLibraryItem;
    }

    public MediaWrapper getAndRemoveDestination() {
        MediaWrapper mediaWrapper = privateDestination;
        privateDestination = null;
        return mediaWrapper;
    }

    public void consumeSource() {
        privateSource = null;
    }

    public MediaLibraryItem getSource() {
        return privateSource;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/PathOperationDelegate$Companion;", "", "()V", "privateDestination", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "privateSource", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "storages", "Landroidx/collection/SimpleArrayMap;", "", "getStorages", "()Landroidx/collection/SimpleArrayMap;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PathOperationDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SimpleArrayMap<String, String> getStorages() {
            return PathOperationDelegate.storages;
        }
    }

    public void appendPathToUri(String str, Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(builder, Constants.KEY_URI);
        int size = storages.size();
        for (int i = 0; i < size; i++) {
            SimpleArrayMap<String, String> simpleArrayMap = storages;
            if (Intrinsics.areEqual((Object) simpleArrayMap.valueAt(i), (Object) str)) {
                String keyAt = simpleArrayMap.keyAt(i);
                Intrinsics.checkNotNullExpressionValue(keyAt, "keyAt(...)");
                str = keyAt;
            }
        }
        for (String appendPath : StringsKt.split$default((CharSequence) str, new char[]{'/'}, false, 0, 6, (Object) null)) {
            builder.appendPath(appendPath);
        }
    }

    public String replaceStoragePath(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        try {
            SimpleArrayMap<String, String> simpleArrayMap = storages;
            if (simpleArrayMap.size() > 0) {
                int size = simpleArrayMap.size();
                for (int i = 0; i < size; i++) {
                    SimpleArrayMap<String, String> simpleArrayMap2 = storages;
                    String keyAt = simpleArrayMap2.keyAt(i);
                    Intrinsics.checkNotNullExpressionValue(keyAt, "keyAt(...)");
                    if (StringsKt.startsWith$default(str, keyAt, false, 2, (Object) null)) {
                        String keyAt2 = simpleArrayMap2.keyAt(i);
                        Intrinsics.checkNotNullExpressionValue(keyAt2, "keyAt(...)");
                        String valueAt = simpleArrayMap2.valueAt(i);
                        Intrinsics.checkNotNullExpressionValue(valueAt, "valueAt(...)");
                        return StringsKt.replace$default(str, keyAt2, valueAt, false, 4, (Object) null);
                    }
                }
            }
        } catch (IllegalStateException unused) {
        }
        return str;
    }

    public String makePathSafe(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return Base64.encodeToString(bytes, 0);
    }

    public String retrieveSafePath(String str) {
        Intrinsics.checkNotNullParameter(str, "encoded");
        byte[] decode = Base64.decode(str, 0);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return new String(decode, Charsets.UTF_8);
    }
}
