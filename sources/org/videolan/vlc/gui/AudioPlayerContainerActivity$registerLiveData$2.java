package org.videolan.vlc.gui;

import android.widget.ProgressBar;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.vlc.ScanProgress;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "scanProgress", "Lorg/videolan/vlc/ScanProgress;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$registerLiveData$2 extends Lambda implements Function1<ScanProgress, Unit> {
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$registerLiveData$2(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        super(1);
        this.this$0 = audioPlayerContainerActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ScanProgress) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ScanProgress scanProgress) {
        ProgressBar access$getScanProgressBar$p;
        ProgressBar access$getScanProgressBar$p2;
        if (scanProgress == null || !Medialibrary.getInstance().isWorking()) {
            AudioPlayerContainerActivity.updateProgressVisibility$default(this.this$0, false, (String) null, 2, (Object) null);
            return;
        }
        this.this$0.updateProgressVisibility(true, scanProgress.getProgressText());
        TextView access$getScanProgressText$p = this.this$0.scanProgressText;
        if (access$getScanProgressText$p != null) {
            access$getScanProgressText$p.setText(scanProgress.getProgressText());
        }
        ProgressBar access$getScanProgressBar$p3 = this.this$0.scanProgressBar;
        if (access$getScanProgressBar$p3 != null) {
            access$getScanProgressBar$p3.setProgress((int) scanProgress.getParsing());
        }
        if (scanProgress.getInDiscovery() && (access$getScanProgressBar$p2 = this.this$0.scanProgressBar) != null && !access$getScanProgressBar$p2.isIndeterminate()) {
            ProgressBar access$getScanProgressBar$p4 = this.this$0.scanProgressBar;
            if (access$getScanProgressBar$p4 != null) {
                access$getScanProgressBar$p4.setVisibility(8);
            }
            ProgressBar access$getScanProgressBar$p5 = this.this$0.scanProgressBar;
            if (access$getScanProgressBar$p5 != null) {
                access$getScanProgressBar$p5.setIndeterminate(true);
            }
            ProgressBar access$getScanProgressBar$p6 = this.this$0.scanProgressBar;
            if (access$getScanProgressBar$p6 != null) {
                access$getScanProgressBar$p6.setVisibility(0);
            }
        }
        if (!scanProgress.getInDiscovery() && (access$getScanProgressBar$p = this.this$0.scanProgressBar) != null && access$getScanProgressBar$p.isIndeterminate()) {
            ProgressBar access$getScanProgressBar$p7 = this.this$0.scanProgressBar;
            if (access$getScanProgressBar$p7 != null) {
                access$getScanProgressBar$p7.setVisibility(8);
            }
            ProgressBar access$getScanProgressBar$p8 = this.this$0.scanProgressBar;
            if (access$getScanProgressBar$p8 != null) {
                access$getScanProgressBar$p8.setIndeterminate(false);
            }
            ProgressBar access$getScanProgressBar$p9 = this.this$0.scanProgressBar;
            if (access$getScanProgressBar$p9 != null) {
                access$getScanProgressBar$p9.setVisibility(0);
            }
        }
    }
}
