package org.videolan.vlc.gui.dialogs;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.RendererItem;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.RendererDelegate;
import org.videolan.vlc.databinding.DialogRenderersBinding;
import org.videolan.vlc.databinding.ItemRendererBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/RenderersDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "adapter", "Lorg/videolan/vlc/gui/dialogs/RenderersDialog$RendererAdapter;", "clickHandler", "Lorg/videolan/vlc/gui/dialogs/RenderersDialog$RendererClickhandler;", "dialogRenderersBinding", "Lorg/videolan/vlc/databinding/DialogRenderersBinding;", "renderers", "", "Lorg/videolan/libvlc/RendererItem;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "RendererAdapter", "RendererClickhandler", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RenderersDialog.kt */
public final class RenderersDialog extends DialogFragment {
    /* access modifiers changed from: private */
    public final RendererAdapter adapter = new RendererAdapter();
    /* access modifiers changed from: private */
    public final RendererClickhandler clickHandler = new RendererClickhandler();
    private DialogRenderersBinding dialogRenderersBinding;
    /* access modifiers changed from: private */
    public List<RendererItem> renderers = RendererDelegate.INSTANCE.getRenderers().getValue();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LifecycleOwner lifecycleOwner = this;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new RenderersDialog$onCreate$1(this, (Continuation<? super RenderersDialog$onCreate$1>) null), 3, (Object) null);
        RendererDelegate.INSTANCE.getRenderers().observe(lifecycleOwner, new RenderersDialog$sam$androidx_lifecycle_Observer$0(new RenderersDialog$onCreate$2(this)));
    }

    public Dialog onCreateDialog(Bundle bundle) {
        DialogRenderersBinding dialogRenderersBinding2 = null;
        DialogRenderersBinding inflate = DialogRenderersBinding.inflate(LayoutInflater.from(requireContext()), (ViewGroup) null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.dialogRenderersBinding = inflate;
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(1);
        DialogRenderersBinding dialogRenderersBinding3 = this.dialogRenderersBinding;
        if (dialogRenderersBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogRenderersBinding");
        } else {
            dialogRenderersBinding2 = dialogRenderersBinding3;
        }
        dialog.setContentView(dialogRenderersBinding2.getRoot());
        return dialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogRenderersBinding inflate = DialogRenderersBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.dialogRenderersBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogRenderersBinding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        DialogRenderersBinding dialogRenderersBinding2 = this.dialogRenderersBinding;
        DialogRenderersBinding dialogRenderersBinding3 = null;
        if (dialogRenderersBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogRenderersBinding");
            dialogRenderersBinding2 = null;
        }
        dialogRenderersBinding2.setHolder(this.clickHandler);
        DialogRenderersBinding dialogRenderersBinding4 = this.dialogRenderersBinding;
        if (dialogRenderersBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogRenderersBinding");
            dialogRenderersBinding4 = null;
        }
        dialogRenderersBinding4.renderersList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        DialogRenderersBinding dialogRenderersBinding5 = this.dialogRenderersBinding;
        if (dialogRenderersBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogRenderersBinding");
            dialogRenderersBinding5 = null;
        }
        dialogRenderersBinding5.renderersList.setAdapter(this.adapter);
        DialogRenderersBinding dialogRenderersBinding6 = this.dialogRenderersBinding;
        if (dialogRenderersBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogRenderersBinding");
        } else {
            dialogRenderersBinding3 = dialogRenderersBinding6;
        }
        dialogRenderersBinding3.renderersDisconnect.setVisibility(PlaybackService.Companion.hasRenderer() ? 0 : 8);
        this.adapter.update(this.renderers);
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0007H\u0016J\u001e\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0014R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/RenderersDialog$RendererAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/libvlc/RendererItem;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/databinding/ItemRendererBinding;", "(Lorg/videolan/vlc/gui/dialogs/RenderersDialog;)V", "orangeColor", "", "getOrangeColor", "()I", "orangeColor$delegate", "Lkotlin/Lazy;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onUpdateFinished", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RenderersDialog.kt */
    private final class RendererAdapter extends DiffUtilAdapter<RendererItem, SelectorViewHolder<ItemRendererBinding>> {
        private final Lazy orangeColor$delegate;

        /* access modifiers changed from: protected */
        public void onUpdateFinished() {
        }

        public RendererAdapter() {
            this.orangeColor$delegate = LazyKt.lazy(new RenderersDialog$RendererAdapter$orangeColor$2(RenderersDialog.this));
        }

        public final int getOrangeColor() {
            return ((Number) this.orangeColor$delegate.getValue()).intValue();
        }

        public SelectorViewHolder<ItemRendererBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            ItemRendererBinding inflate = ItemRendererBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            inflate.setClicHandler(RenderersDialog.this.clickHandler);
            return new SelectorViewHolder<>(inflate);
        }

        public void onBindViewHolder(SelectorViewHolder<ItemRendererBinding> selectorViewHolder, int i) {
            Intrinsics.checkNotNullParameter(selectorViewHolder, "holder");
            selectorViewHolder.getBinding().setRenderer((RendererItem) RenderersDialog.this.renderers.get(i));
            selectorViewHolder.getBinding().rendererIcon.setImageDrawable(ContextCompat.getDrawable(selectorViewHolder.getBinding().rendererIcon.getContext(), Intrinsics.areEqual((Object) ((RendererItem) RenderersDialog.this.renderers.get(i)).type, (Object) "chromecast") ? R.drawable.ic_dialog_renderer : R.drawable.ic_dialog_unknown));
            if (Intrinsics.areEqual(RenderersDialog.this.renderers.get(i), PlaybackService.Companion.getRenderer().getValue())) {
                selectorViewHolder.getBinding().rendererName.setTextColor(getOrangeColor());
                ImageViewCompat.setImageTintList(selectorViewHolder.getBinding().rendererIcon, ColorStateList.valueOf(getOrangeColor()));
                return;
            }
            ImageViewCompat.setImageTintList(selectorViewHolder.getBinding().rendererIcon, (ColorStateList) null);
        }

        public int getItemCount() {
            return getDataset().size();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/RenderersDialog$RendererClickhandler;", "", "(Lorg/videolan/vlc/gui/dialogs/RenderersDialog;)V", "connect", "", "item", "Lorg/videolan/libvlc/RendererItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RenderersDialog.kt */
    public final class RendererClickhandler {
        public RendererClickhandler() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
            r0 = r6.this$0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void connect(org.videolan.libvlc.RendererItem r7) {
            /*
                r6 = this;
                org.videolan.vlc.PlaybackService$Companion r0 = org.videolan.vlc.PlaybackService.Companion
                org.videolan.vlc.util.RendererLiveData r0 = r0.getRenderer()
                r0.setValue((org.videolan.libvlc.RendererItem) r7)
                org.videolan.vlc.gui.dialogs.RenderersDialog r0 = org.videolan.vlc.gui.dialogs.RenderersDialog.this
                r0.dismissAllowingStateLoss()
                if (r7 == 0) goto L_0x004c
                org.videolan.vlc.gui.dialogs.RenderersDialog r0 = org.videolan.vlc.gui.dialogs.RenderersDialog.this
                androidx.fragment.app.FragmentActivity r1 = r0.getActivity()
                if (r1 == 0) goto L_0x004c
                android.view.Window r1 = r1.getWindow()
                if (r1 == 0) goto L_0x004c
                int r2 = org.videolan.vlc.R.id.audio_player_container
                android.view.View r1 = r1.findViewById(r2)
                if (r1 == 0) goto L_0x004c
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                org.videolan.vlc.gui.helpers.UiTools r1 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
                androidx.fragment.app.FragmentActivity r2 = r0.requireActivity()
                java.lang.String r3 = "requireActivity(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                android.app.Activity r2 = (android.app.Activity) r2
                int r3 = org.videolan.vlc.R.string.casting_connected_renderer
                java.lang.String r7 = r7.displayName
                r4 = 1
                java.lang.Object[] r4 = new java.lang.Object[r4]
                r5 = 0
                r4[r5] = r7
                java.lang.String r7 = r0.getString(r3, r4)
                java.lang.String r0 = "getString(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
                r1.snacker(r2, r7)
            L_0x004c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.RenderersDialog.RendererClickhandler.connect(org.videolan.libvlc.RendererItem):void");
        }
    }
}
