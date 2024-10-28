package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.databinding.BrowserItemBinding;
import org.videolan.vlc.databinding.CardBrowserItemBinding;
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;
import org.videolan.vlc.gui.helpers.ThreeStatesCheckbox;
import org.videolan.vlc.gui.view.MiniVisualizer;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u000200J\u0010\u00101\u001a\u00020+2\b\u00102\u001a\u0004\u0018\u000103J\u000e\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020-J\u0018\u00106\u001a\u00020+2\u0010\u00107\u001a\f\u0012\u0004\u0012\u00020\u000308R\u000209J\u000e\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020-J\u000e\u0010<\u001a\u00020+2\u0006\u0010;\u001a\u00020-J\u000e\u0010=\u001a\u00020+2\u0006\u0010>\u001a\u00020-J\u0016\u0010?\u001a\u00020+2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020-J\u000e\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020-J\u000e\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020GJ\u001e\u0010H\u001a\u00020+2\u0006\u0010@\u001a\u00020A2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020JJ\u0010\u0010L\u001a\u00020+2\b\u0010M\u001a\u0004\u0018\u000103J\u000e\u0010N\u001a\u00020+2\u0006\u0010O\u001a\u00020JJ\u0006\u0010P\u001a\u00020+J\u0006\u0010Q\u001a\u00020+J\u0006\u0010R\u001a\u00020+R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\b\"\u0004\b\u001b\u0010\nR\u001a\u0010\u001c\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\b\"\u0004\b\u001e\u0010\nR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$¨\u0006S"}, d2 = {"Lorg/videolan/vlc/gui/browser/BrowserItemBindingContainer;", "", "binding", "Landroidx/databinding/ViewDataBinding;", "(Landroidx/databinding/ViewDataBinding;)V", "banIcon", "Landroid/widget/ImageView;", "getBanIcon", "()Landroid/widget/ImageView;", "setBanIcon", "(Landroid/widget/ImageView;)V", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "browserCheckbox", "Lorg/videolan/vlc/gui/helpers/ThreeStatesCheckbox;", "getBrowserCheckbox", "()Lorg/videolan/vlc/gui/helpers/ThreeStatesCheckbox;", "setBrowserCheckbox", "(Lorg/videolan/vlc/gui/helpers/ThreeStatesCheckbox;)V", "container", "Landroid/view/View;", "getContainer", "()Landroid/view/View;", "setContainer", "(Landroid/view/View;)V", "itemIcon", "getItemIcon", "setItemIcon", "moreIcon", "getMoreIcon", "setMoreIcon", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "setText", "(Landroid/widget/TextView;)V", "title", "getTitle", "setTitle", "getVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "setCheckEnabled", "", "enabled", "", "setCover", "value", "Landroid/graphics/drawable/BitmapDrawable;", "setFileName", "filename", "", "setHasContextMenu", "hasContextMenu", "setHolder", "holder", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "setIsBanned", "banned", "setIsBannedByParent", "setIsFavorite", "favorite", "setIsPlayed", "context", "Landroid/content/Context;", "played", "setIsTv", "isTv", "setItem", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "setProgress", "progress", "", "max", "setProtocol", "protocol", "setVisuVisibility", "visible", "setupGrid", "startVisu", "stopVisu", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserItemBindingContainer.kt */
public final class BrowserItemBindingContainer {
    private ImageView banIcon;
    private final ViewDataBinding binding;
    private ThreeStatesCheckbox browserCheckbox;
    private View container;
    private ImageView itemIcon;
    private ImageView moreIcon;
    private TextView text;
    private TextView title;

    public BrowserItemBindingContainer(ViewDataBinding viewDataBinding) {
        Intrinsics.checkNotNullParameter(viewDataBinding, "binding");
        this.binding = viewDataBinding;
        if (!(viewDataBinding instanceof CardBrowserItemBinding) && !(viewDataBinding instanceof BrowserItemBinding)) {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        } else if (viewDataBinding instanceof CardBrowserItemBinding) {
            TextView textView = ((CardBrowserItemBinding) viewDataBinding).text;
            Intrinsics.checkNotNullExpressionValue(textView, "text");
            this.text = textView;
            TextView textView2 = ((CardBrowserItemBinding) viewDataBinding).title;
            Intrinsics.checkNotNullExpressionValue(textView2, "title");
            this.title = textView2;
            ImageView imageView = ((CardBrowserItemBinding) viewDataBinding).itemIcon;
            Intrinsics.checkNotNullExpressionValue(imageView, "itemIcon");
            this.itemIcon = imageView;
            ThreeStatesCheckbox threeStatesCheckbox = ((CardBrowserItemBinding) viewDataBinding).browserCheckbox;
            Intrinsics.checkNotNullExpressionValue(threeStatesCheckbox, "browserCheckbox");
            this.browserCheckbox = threeStatesCheckbox;
            ImageView imageView2 = ((CardBrowserItemBinding) viewDataBinding).itemMore;
            Intrinsics.checkNotNullExpressionValue(imageView2, "itemMore");
            this.moreIcon = imageView2;
            MaterialCardView materialCardView = ((CardBrowserItemBinding) viewDataBinding).browserContainer;
            Intrinsics.checkNotNullExpressionValue(materialCardView, "browserContainer");
            this.container = materialCardView;
            ImageView imageView3 = ((CardBrowserItemBinding) viewDataBinding).itemBan;
            Intrinsics.checkNotNullExpressionValue(imageView3, "itemBan");
            this.banIcon = imageView3;
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            TextView textView3 = ((BrowserItemBinding) viewDataBinding).text;
            Intrinsics.checkNotNullExpressionValue(textView3, "text");
            this.text = textView3;
            TextView textView4 = ((BrowserItemBinding) viewDataBinding).title;
            Intrinsics.checkNotNullExpressionValue(textView4, "title");
            this.title = textView4;
            ImageView imageView4 = ((BrowserItemBinding) viewDataBinding).itemIcon;
            Intrinsics.checkNotNullExpressionValue(imageView4, "itemIcon");
            this.itemIcon = imageView4;
            ThreeStatesCheckbox threeStatesCheckbox2 = ((BrowserItemBinding) viewDataBinding).browserCheckbox;
            Intrinsics.checkNotNullExpressionValue(threeStatesCheckbox2, "browserCheckbox");
            this.browserCheckbox = threeStatesCheckbox2;
            ImageView imageView5 = ((BrowserItemBinding) viewDataBinding).itemMore;
            Intrinsics.checkNotNullExpressionValue(imageView5, "itemMore");
            this.moreIcon = imageView5;
            ConstraintLayout constraintLayout = ((BrowserItemBinding) viewDataBinding).browserContainer;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "browserContainer");
            this.container = constraintLayout;
            AppCompatImageView appCompatImageView = ((BrowserItemBinding) viewDataBinding).itemBan;
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "itemBan");
            this.banIcon = appCompatImageView;
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final ViewDataBinding getBinding() {
        return this.binding;
    }

    public final void setCheckEnabled(boolean z) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setCheckEnabled(z);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setCheckEnabled(z);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setCover(BitmapDrawable bitmapDrawable) {
        Intrinsics.checkNotNullParameter(bitmapDrawable, "value");
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setCover(bitmapDrawable);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setCover(bitmapDrawable);
        }
    }

    public final void setProtocol(String str) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setProtocol(str);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setProtocol(str);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setFileName(String str) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setFilename(str);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setFilename(str);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setHasContextMenu(boolean z) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setHasContextMenu(z);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setHasContextMenu(z);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setIsBanned(boolean z) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setIsBanned(z);
        }
    }

    public final void setIsBannedByParent(boolean z) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setIsBannedParent(z);
        }
    }

    public final void setItem(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setItem(mediaLibraryItem);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setItem(mediaLibraryItem);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setIsFavorite(boolean z) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setFavorite(z);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setFavorite(z);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setIsPlayed(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean(SettingsKt.PLAYBACK_HISTORY, true)) {
            ViewDataBinding viewDataBinding = this.binding;
            if (viewDataBinding instanceof CardBrowserItemBinding) {
                ((CardBrowserItemBinding) viewDataBinding).setPlayed(z);
            } else if (viewDataBinding instanceof BrowserItemBinding) {
                ((BrowserItemBinding) viewDataBinding).setPlayed(z);
            } else {
                throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
            }
        }
    }

    public final void setProgress(Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean(SettingsKt.PLAYBACK_HISTORY, true)) {
            ViewDataBinding viewDataBinding = this.binding;
            if (viewDataBinding instanceof CardBrowserItemBinding) {
                ((CardBrowserItemBinding) viewDataBinding).setProgress(i);
                ((CardBrowserItemBinding) this.binding).setMax(i2);
            } else if (viewDataBinding instanceof BrowserItemBinding) {
                ((BrowserItemBinding) viewDataBinding).setProgress(i);
                ((BrowserItemBinding) this.binding).setMax(i2);
            } else {
                throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
            }
        }
    }

    public final MiniVisualizer getVisu() {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            MiniVisualizer miniVisualizer = ((CardBrowserItemBinding) viewDataBinding).playing;
            Intrinsics.checkNotNull(miniVisualizer);
            return miniVisualizer;
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            MiniVisualizer miniVisualizer2 = ((BrowserItemBinding) viewDataBinding).playing;
            Intrinsics.checkNotNull(miniVisualizer2);
            return miniVisualizer2;
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void startVisu() {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).playing.start();
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).playing.start();
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void stopVisu() {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).playing.start();
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).playing.stop();
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setVisuVisibility(int i) {
        ViewDataBinding viewDataBinding = this.binding;
        boolean z = true;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).playing.setVisibility(i);
            CardBrowserItemBinding cardBrowserItemBinding = (CardBrowserItemBinding) this.binding;
            if (i != 0) {
                z = false;
            }
            cardBrowserItemBinding.setForceCoverHiding(z);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).playing.setVisibility(i);
            BrowserItemBinding browserItemBinding = (BrowserItemBinding) this.binding;
            if (i != 0) {
                z = false;
            }
            browserItemBinding.setForceCoverHiding(z);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setIsTv(boolean z) {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setIsTv(z);
        }
    }

    public final void setHolder(BaseBrowserAdapter.ViewHolder<ViewDataBinding> viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).setHolder(viewHolder);
        } else if (viewDataBinding instanceof BrowserItemBinding) {
            ((BrowserItemBinding) viewDataBinding).setHolder(viewHolder);
        } else {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final void setupGrid() {
        ViewDataBinding viewDataBinding = this.binding;
        if (viewDataBinding instanceof CardBrowserItemBinding) {
            ((CardBrowserItemBinding) viewDataBinding).browserContainer.getLayoutParams().width = -1;
        } else if (!(viewDataBinding instanceof BrowserItemBinding)) {
            throw new IllegalStateException("Binding should be either a CardBrowserItemBinding or BrowserItemBinding");
        }
    }

    public final TextView getTitle() {
        return this.title;
    }

    public final void setTitle(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.title = textView;
    }

    public final ImageView getItemIcon() {
        return this.itemIcon;
    }

    public final void setItemIcon(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.itemIcon = imageView;
    }

    public final ThreeStatesCheckbox getBrowserCheckbox() {
        return this.browserCheckbox;
    }

    public final void setBrowserCheckbox(ThreeStatesCheckbox threeStatesCheckbox) {
        Intrinsics.checkNotNullParameter(threeStatesCheckbox, "<set-?>");
        this.browserCheckbox = threeStatesCheckbox;
    }

    public final ImageView getBanIcon() {
        return this.banIcon;
    }

    public final void setBanIcon(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.banIcon = imageView;
    }

    public final TextView getText() {
        return this.text;
    }

    public final void setText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.text = textView;
    }

    public final View getContainer() {
        return this.container;
    }

    public final void setContainer(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.container = view;
    }

    public final ImageView getMoreIcon() {
        return this.moreIcon;
    }

    public final void setMoreIcon(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.moreIcon = imageView;
    }
}
