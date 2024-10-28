package org.videolan.vlc.gui.dialogs;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialog;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0016¨\u0006\r"}, d2 = {"org/videolan/vlc/gui/dialogs/DisplaySettingsDialog$onViewCreated$11", "Landroid/widget/AdapterView$OnItemSelectedListener;", "onItemSelected", "", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "", "id", "", "onNothingSelected", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DisplaySettingsDialog.kt */
public final class DisplaySettingsDialog$onViewCreated$11 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ ArrayAdapter<DisplaySettingsDialog.VideoGroup> $spinnerArrayAdapter;
    final /* synthetic */ DisplaySettingsDialog this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    DisplaySettingsDialog$onViewCreated$11(ArrayAdapter<DisplaySettingsDialog.VideoGroup> arrayAdapter, DisplaySettingsDialog displaySettingsDialog) {
        this.$spinnerArrayAdapter = arrayAdapter;
        this.this$0 = displaySettingsDialog;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        DisplaySettingsDialog.VideoGroup item = this.$spinnerArrayAdapter.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.vlc.gui.dialogs.DisplaySettingsDialog.VideoGroup");
        DisplaySettingsDialog.VideoGroup videoGroup = item;
        if (!Intrinsics.areEqual((Object) videoGroup.getValue(), (Object) this.this$0.showVideoGroups)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new DisplaySettingsDialog$onViewCreated$11$onItemSelected$1(this.this$0, videoGroup, (Continuation<? super DisplaySettingsDialog$onViewCreated$11$onItemSelected$1>) null), 3, (Object) null);
            this.this$0.dismiss();
        }
    }
}
