package org.videolan.vlc.gui.browser;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.SimpleArrayMap;
import androidx.core.net.UriKt;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.R;
import org.videolan.vlc.viewmodels.browser.IPathOperationDelegate;
import org.videolan.vlc.viewmodels.browser.PathOperationDelegate;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001 B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u001c\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0014H\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/browser/PathAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/browser/PathAdapter$ViewHolder;", "browser", "Lorg/videolan/vlc/gui/browser/PathAdapterListener;", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "(Lorg/videolan/vlc/gui/browser/PathAdapterListener;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "getBrowser", "()Lorg/videolan/vlc/gui/browser/PathAdapterListener;", "browserTitle", "", "getMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "otgDevice", "pathOperationDelegate", "Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "segments", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "prepareSegments", "uri", "Landroid/net/Uri;", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PathAdapter.kt */
public final class PathAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final PathAdapterListener browser;
    private final String browserTitle;
    private final MediaWrapper media;
    private final String otgDevice;
    private final IPathOperationDelegate pathOperationDelegate;
    /* access modifiers changed from: private */
    public final List<String> segments;

    public PathAdapter(PathAdapterListener pathAdapterListener, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(pathAdapterListener, "browser");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        this.browser = pathAdapterListener;
        this.media = mediaWrapper;
        IPathOperationDelegate pathOperationDelegate2 = pathAdapterListener.getPathOperationDelegate();
        this.pathOperationDelegate = pathOperationDelegate2;
        if (mediaWrapper.hasStateFlags(4)) {
            SimpleArrayMap<String, String> storages = PathOperationDelegate.Companion.getStorages();
            String decode = Uri.decode(mediaWrapper.getUri().getPath());
            String title = mediaWrapper.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            storages.put(decode, pathOperationDelegate2.makePathSafe(title));
        }
        SimpleArrayMap<String, String> storages2 = PathOperationDelegate.Companion.getStorages();
        String external_public_directory = AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY();
        String string = pathAdapterListener.currentContext().getString(R.string.internal_memory);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        storages2.put(external_public_directory, pathOperationDelegate2.makePathSafe(string));
        String string2 = pathAdapterListener.currentContext().getString(R.string.browser);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.browserTitle = string2;
        String string3 = pathAdapterListener.currentContext().getString(R.string.otg_device_title);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        this.otgDevice = string3;
        Uri uri = mediaWrapper.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        this.segments = prepareSegments(uri);
    }

    public final PathAdapterListener getBrowser() {
        return this.browser;
    }

    public final MediaWrapper getMedia() {
        return this.media;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browser_path_item, viewGroup, false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.TextView");
        return new ViewHolder(this, (TextView) inflate);
    }

    public int getItemCount() {
        return this.segments.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String str;
        boolean z;
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (PathOperationDelegate.Companion.getStorages().containsKey(Uri.parse(this.segments.get(i)).getPath())) {
            IPathOperationDelegate iPathOperationDelegate = this.pathOperationDelegate;
            String valueAt = PathOperationDelegate.Companion.getStorages().valueAt(PathOperationDelegate.Companion.getStorages().indexOfKey(Uri.parse(this.segments.get(i)).getPath()));
            Intrinsics.checkNotNullExpressionValue(valueAt, "valueAt(...)");
            str = iPathOperationDelegate.retrieveSafePath(valueAt);
        } else {
            str = Uri.parse(this.segments.get(i)).getLastPathSegment();
        }
        viewHolder.getRoot().setText(str);
        if (str != null) {
            try {
                Uri parse = Uri.parse(this.segments.get(i));
                Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
                z = UriKt.toFile(parse).isFile();
            } catch (Exception unused) {
                z = false;
            }
            viewHolder.getRoot().setContentDescription(viewHolder.getRoot().getContext().getString(z ? R.string.talkback_file : R.string.talkback_folder, new Object[]{viewHolder.getRoot().getText()}));
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/browser/PathAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "root", "Landroid/widget/TextView;", "(Lorg/videolan/vlc/gui/browser/PathAdapter;Landroid/widget/TextView;)V", "getRoot", "()Landroid/widget/TextView;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PathAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView root;
        final /* synthetic */ PathAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(PathAdapter pathAdapter, TextView textView) {
            super(textView);
            Intrinsics.checkNotNullParameter(textView, "root");
            this.this$0 = pathAdapter;
            this.root = textView;
            textView.setOnClickListener(new PathAdapter$ViewHolder$$ExternalSyntheticLambda0(this, pathAdapter));
        }

        public final TextView getRoot() {
            return this.root;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(ViewHolder viewHolder, PathAdapter pathAdapter, View view) {
            String str;
            Intrinsics.checkNotNullParameter(viewHolder, "this$0");
            Intrinsics.checkNotNullParameter(pathAdapter, "this$1");
            int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
            if (absoluteAdapterPosition != pathAdapter.segments.size() - 1) {
                PathAdapterListener browser = pathAdapter.getBrowser();
                if (absoluteAdapterPosition == 0) {
                    str = "root";
                } else {
                    str = (String) pathAdapter.segments.get(absoluteAdapterPosition);
                }
                browser.backTo(str);
            }
        }
    }

    private final List<String> prepareSegments(Uri uri) {
        String str;
        String decode = Uri.decode(uri.getPath());
        Intrinsics.checkNotNull(decode);
        boolean startsWith$default = StringsKt.startsWith$default(decode, "/tree/", false, 2, (Object) null);
        if (startsWith$default) {
            str = StringsKt.endsWith$default((CharSequence) decode, (char) AbstractJsonLexerKt.COLON, false, 2, (Object) null) ? "" : StringsKt.substringAfterLast$default(decode, (char) AbstractJsonLexerKt.COLON, (String) null, 2, (Object) null);
        } else {
            str = this.pathOperationDelegate.replaceStoragePath(decode);
        }
        List<String> arrayList = new ArrayList<>();
        if (startsWith$default) {
            arrayList.add(this.otgDevice);
        }
        Collection arrayList2 = new ArrayList();
        for (Object next : StringsKt.split$default((CharSequence) str, new char[]{'/'}, false, 0, 6, (Object) null)) {
            if (((String) next).length() > 0) {
                arrayList2.add(next);
            }
        }
        List list = (List) arrayList2;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Uri.Builder encodedAuthority = new Uri.Builder().scheme(uri.getScheme()).encodedAuthority(uri.getAuthority());
            if (i >= 0) {
                int i2 = 0;
                while (true) {
                    Intrinsics.checkNotNull(encodedAuthority);
                    this.pathOperationDelegate.appendPathToUri((String) list.get(i2), encodedAuthority);
                    if (i2 == i) {
                        break;
                    }
                    i2++;
                }
            }
            String builder = encodedAuthority.toString();
            Intrinsics.checkNotNullExpressionValue(builder, "toString(...)");
            arrayList.add(builder);
        }
        if (this.browser.showRoot()) {
            arrayList.add(0, this.browserTitle);
        }
        return arrayList;
    }
}
