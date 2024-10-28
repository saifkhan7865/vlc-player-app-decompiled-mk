package org.videolan.vlc.gui.video.benchmark;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.AppScope;
import org.videolan.tools.Settings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.media.PlaylistManager;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 F2\u00020\u0001:\u0002FGB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020'H\u0002J\b\u0010)\u001a\u00020'H\u0002J\u0010\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020\bH\u0002J\u0010\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020\u0015H\u0002J\u0010\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0006H\u0016J\u0010\u00100\u001a\u00020\b2\u0006\u0010+\u001a\u00020\bH\u0002J\b\u00101\u001a\u00020'H\u0016J\b\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020\bH\u0002J\u0010\u00105\u001a\u00020'2\u0006\u0010+\u001a\u00020\bH\u0002J\u0018\u00106\u001a\u00020'2\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\bH\u0014J\u0012\u00109\u001a\u00020'2\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010<\u001a\u00020'H\u0014J\u0010\u0010=\u001a\u00020'2\u0006\u0010>\u001a\u00020?H\u0017J\b\u0010@\u001a\u00020'H\u0014J\u0012\u0010A\u001a\u00020'2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010D\u001a\u00020'H\u0002J\b\u0010E\u001a\u00020'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lorg/videolan/vlc/gui/video/benchmark/BenchActivity;", "Lorg/videolan/vlc/gui/video/benchmark/ShallowVideoPlayer;", "()V", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "direction", "", "hasLimit", "", "hasVLCFailed", "hasVout", "interval", "", "isHardware", "isScreenshot", "isSeeking", "isSetup", "isSpeed", "lateFrameCounter", "oldHistoryBoolean", "oldOpenglValue", "", "oldRate", "oldRepeating", "position", "positionCounter", "screenshotCount", "screenshotsTimestamp", "", "", "speed", "speedIteration", "stacktraceFile", "timeLimit", "timeOut", "Ljava/lang/Runnable;", "timeoutHandler", "Landroid/os/Handler;", "checkLogs", "", "continueScreenshots", "continueSpeedTest", "converge", "dropped", "errorFinish", "resultString", "exit", "resultCode", "findLimit", "finish", "getStackTrace", "Lkotlinx/coroutines/Job;", "heuristic", "initConvergeance", "loadMedia", "fromStart", "forceUsingNew", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMediaPlayerEvent", "event", "Lorg/videolan/libvlc/MediaPlayer$Event;", "onResume", "onServiceChanged", "service", "Lorg/videolan/vlc/PlaybackService;", "seekScreenshot", "setTimeout", "Companion", "ScreenshotBroadcastReceiver", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BenchActivity.kt */
public final class BenchActivity extends ShallowVideoPlayer {
    private static final String ACTION_CONTINUE_BENCHMARK = "org.videolan.vlc.gui.video.benchmark.CONTINUE_BENCHMARK";
    private static final String ACTION_TRIGGER_SCREENSHOT = "org.videolan.vlcbenchmark.TRIGGER_SCREENSHOT";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EXTRA_ACTION = "extra_benchmark_action";
    private static final String EXTRA_ACTION_PLAYBACK = "extra_benchmark_action_playback";
    private static final String EXTRA_ACTION_QUALITY = "extra_benchmark_action_quality";
    private static final String EXTRA_ACTION_SPEED = "extra_benchmark_action_speed";
    private static final String EXTRA_HARDWARE = "extra_benchmark_disable_hardware";
    private static final String EXTRA_STACKTRACE_FILE = "stacktrace_file";
    private static final String EXTRA_TIMESTAMPS = "extra_benchmark_timestamps";
    private static final String EXTRA_TIME_LIMIT = "extra_benchmark_time_limit";
    private static final String PREFERENCE_OPENGL = "opengl";
    private static final String PREFERENCE_PLAYBACK_HISTORY = "playback_history";
    private static final int RESULT_FAILED = 6;
    private static final int RESULT_NO_HW = 1;
    private static final int SPEED_TEST_ITERATION_LIMIT = 5;
    private static final String TAG = "VLCBenchmark";
    private BroadcastReceiver broadcastReceiver = new ScreenshotBroadcastReceiver();
    private int direction;
    private boolean hasLimit;
    private boolean hasVLCFailed;
    private boolean hasVout;
    private float interval = 1.0f;
    private boolean isHardware;
    private boolean isScreenshot;
    private boolean isSeeking;
    private boolean isSetup;
    private boolean isSpeed;
    private int lateFrameCounter;
    /* access modifiers changed from: private */
    public boolean oldHistoryBoolean = true;
    /* access modifiers changed from: private */
    public String oldOpenglValue = "-2";
    private float oldRate;
    private int oldRepeating;
    private float position;
    private int positionCounter;
    private int screenshotCount;
    private List<Long> screenshotsTimestamp;
    private float speed = 1.0f;
    private int speedIteration;
    /* access modifiers changed from: private */
    public String stacktraceFile;
    private long timeLimit;
    private Runnable timeOut;
    private final Handler timeoutHandler = new Handler(Looper.getMainLooper());

    public void onServiceChanged(PlaybackService playbackService) {
        super.onServiceChanged(playbackService);
        if (this.isSpeed && getService() != null) {
            Intrinsics.checkNotNull(playbackService);
            this.oldRate = playbackService.getRate();
            this.oldRepeating = PlaylistManager.Companion.getRepeating().getValue().intValue();
            playbackService.getPlaylistManager().setRepeatType(1);
        } else if (!this.isSpeed && getService() != null) {
            this.oldRepeating = PlaylistManager.Companion.getRepeating().getValue().intValue();
            Intrinsics.checkNotNull(playbackService);
            playbackService.getPlaylistManager().setRepeatType(0);
        }
        if (this.isHardware && getService() != null) {
            SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(this);
            this.oldOpenglValue = sharedPreferences.getString(PREFERENCE_OPENGL, Constants.GROUP_VIDEOS_NONE);
            this.oldHistoryBoolean = sharedPreferences.getBoolean("playback_history", true);
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new BenchActivity$onServiceChanged$1(sharedPreferences, (Continuation<? super BenchActivity$onServiceChanged$1>) null), 2, (Object) null);
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BenchActivity$onServiceChanged$2((Continuation<? super BenchActivity$onServiceChanged$2>) null), 3, (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void loadMedia(boolean z, boolean z2) {
        PlaybackService service;
        PlaybackService service2 = getService();
        if (service2 != null) {
            service2.setBenchmark();
        }
        if (this.isHardware && (service = getService()) != null) {
            service.setHardware();
        }
        super.loadMedia(z, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        StartActivityOnCrash.Companion.setUp(this);
        Intent intent = getIntent();
        this.isHardware = !intent.getBooleanExtra(EXTRA_HARDWARE, true);
        setBenchmark(true);
        super.onCreate(bundle);
        if (!intent.hasExtra(EXTRA_ACTION)) {
            errorFinish("Missing action intent extra");
            return;
        }
        if (intent.hasExtra(EXTRA_STACKTRACE_FILE)) {
            this.stacktraceFile = intent.getStringExtra(EXTRA_STACKTRACE_FILE);
        }
        this.timeLimit = intent.getLongExtra(EXTRA_TIME_LIMIT, 0);
        String stringExtra = intent.getStringExtra(EXTRA_ACTION);
        if (stringExtra != null) {
            int hashCode = stringExtra.hashCode();
            if (hashCode != -643058489) {
                if (hashCode == 411392851) {
                    stringExtra.equals(EXTRA_ACTION_PLAYBACK);
                } else if (hashCode == 698238479 && stringExtra.equals(EXTRA_ACTION_SPEED)) {
                    this.isSpeed = true;
                }
            } else if (stringExtra.equals(EXTRA_ACTION_QUALITY)) {
                boolean hasExtra = intent.hasExtra(EXTRA_TIMESTAMPS);
                this.isScreenshot = hasExtra;
                if (!hasExtra) {
                    errorFinish("Missing screenshots timestamps");
                    return;
                } else if (intent.getSerializableExtra(EXTRA_TIMESTAMPS) instanceof List) {
                    Serializable serializableExtra = intent.getSerializableExtra(EXTRA_TIMESTAMPS);
                    Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                    this.screenshotsTimestamp = (List) serializableExtra;
                    setEnableCloneMode(true);
                    getDisplayManager().release();
                } else {
                    errorFinish("Failed to get timestamps");
                    return;
                }
            }
        }
        setRequestedOrientation(0);
        setRequestedOrientation(14);
        ExtensionsKt.registerReceiverCompat(this, this.broadcastReceiver, new IntentFilter(ACTION_CONTINUE_BENCHMARK), true);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setTimeout();
    }

    private final void setTimeout() {
        if (this.isSetup) {
            Runnable runnable = this.timeOut;
            if (runnable != null) {
                Handler handler = this.timeoutHandler;
                if (runnable == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("timeOut");
                    runnable = null;
                }
                handler.removeCallbacks(runnable);
            }
            BenchActivity$$ExternalSyntheticLambda1 benchActivity$$ExternalSyntheticLambda1 = new BenchActivity$$ExternalSyntheticLambda1(this);
            this.timeOut = benchActivity$$ExternalSyntheticLambda1;
            this.timeoutHandler.postDelayed(benchActivity$$ExternalSyntheticLambda1, 10000);
        }
    }

    /* access modifiers changed from: private */
    public static final void setTimeout$lambda$0(BenchActivity benchActivity) {
        Intrinsics.checkNotNullParameter(benchActivity, "this$0");
        Log.e(TAG, "VLC Seek Froze");
        benchActivity.errorFinish("VLC Seek Froze");
    }

    public void onMediaPlayerEvent(MediaPlayer.Event event) {
        PlaylistManager playlistManager;
        PlayerController player;
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        super.onMediaPlayerEvent(event);
        int i = event.type;
        if (i != 259) {
            if (i == 265) {
                checkLogs();
                if (this.isSpeed) {
                    continueSpeedTest();
                }
            } else if (i == 274) {
                this.hasVout = true;
            } else if (i == 267) {
                setTimeout();
                if (!this.isScreenshot && !this.isSpeed && this.timeLimit > 0 && event.getTimeChanged() > this.timeLimit) {
                    Log.i(TAG, "onMediaPlayerEvent: closing vlc-android after time limit reached");
                    PlaybackService service = getService();
                    if (!(service == null || (playlistManager = service.getPlaylistManager()) == null || (player = playlistManager.getPlayer()) == null)) {
                        player.setCurrentStats();
                    }
                    checkLogs();
                    finish();
                }
            } else if (i == 268) {
                float positionChanged = event.getPositionChanged();
                if (this.isScreenshot) {
                    return;
                }
                if (positionChanged == this.position) {
                    int i2 = this.positionCounter;
                    if (i2 > 50) {
                        errorFinish("VLC Playback Froze");
                    } else {
                        this.positionCounter = i2 + 1;
                    }
                } else {
                    this.position = positionChanged;
                    this.positionCounter = 0;
                }
            }
        } else if (event.getBuffering() == 100.0f) {
            if (!this.isSetup) {
                this.isSetup = true;
                if (this.isScreenshot) {
                    PlaybackService service2 = getService();
                    if (service2 != null) {
                        service2.pause();
                    }
                    this.timeoutHandler.postDelayed(new BenchActivity$$ExternalSyntheticLambda0(this), 1000);
                }
            }
            if (this.isScreenshot && this.isSetup) {
                int i3 = this.screenshotCount;
                List<Long> list = this.screenshotsTimestamp;
                Intrinsics.checkNotNull(list);
                if (i3 < list.size() && this.isSeeking) {
                    this.isSeeking = false;
                    View decorView = getWindow().getDecorView();
                    Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                    decorView.setSystemUiVisibility(6);
                    String string = getString(R.string.benchmark_package_name);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    Intent intent = new Intent(ACTION_TRIGGER_SCREENSHOT);
                    intent.setPackage(string);
                    intent.putExtra("screenshot", this.screenshotCount);
                    intent.addFlags(32);
                    sendBroadcast(intent);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void onMediaPlayerEvent$lambda$1(BenchActivity benchActivity) {
        Intrinsics.checkNotNullParameter(benchActivity, "this$0");
        benchActivity.seekScreenshot();
    }

    private final void initConvergeance(boolean z) {
        if (z) {
            this.direction = -1;
            this.interval = 0.5f;
            return;
        }
        this.direction = 1;
        this.interval = 1.0f;
    }

    private final boolean findLimit(boolean z) {
        int i = this.direction;
        if (i == -1 && z) {
            this.interval /= (float) 2;
            return true;
        } else if (i == -1 && !z) {
            return true;
        } else {
            if (i == 1 && z) {
                return true;
            }
            this.speed += this.interval * ((float) i);
            return false;
        }
    }

    private final void converge(boolean z) {
        if (this.hasLimit) {
            this.speedIteration++;
            int i = this.direction;
            if (i == -1 && z && ((double) this.speed) < 1.0d) {
                this.interval /= (float) 2;
            } else if (i == -1 && !z) {
                this.direction = 1;
                this.interval /= (float) 2;
            } else if (i == 1 && z) {
                this.direction = -1;
                this.interval /= (float) 2;
            }
            this.speed += this.interval * ((float) this.direction);
        }
    }

    private final boolean heuristic() {
        PlaybackService service = getService();
        Intrinsics.checkNotNull(service);
        IMedia.Stats lastStats = service.getLastStats();
        Intrinsics.checkNotNull(lastStats);
        int i = lastStats.lostPictures;
        int i2 = this.direction;
        if (i2 != 0 && this.speed >= 9.0f && i >= 50) {
            errorFinish("Failed speed test");
            return false;
        } else if (i2 == 0 && i > 0) {
            return true;
        } else {
            if (i2 != 0 && ((double) this.speed) >= 1.0d) {
                int i3 = this.lateFrameCounter;
                this.lateFrameCounter = 0;
                if (i3 > 0) {
                    return true;
                }
            } else if (i2 != 0 && ((double) this.speed) < 1.0d) {
                this.lateFrameCounter = 0;
                if (i > 0) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r0 >= 10.0f) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void continueSpeedTest() {
        /*
            r3 = this;
            org.videolan.vlc.PlaybackService r0 = r3.getService()
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = "SpeedTesting: There is no service"
            r3.errorFinish(r0)
            return
        L_0x000c:
            boolean r0 = r3.heuristic()
            int r1 = r3.direction
            if (r1 != 0) goto L_0x0019
            r3.hasLimit = r0
            r3.initConvergeance(r0)
        L_0x0019:
            boolean r1 = r3.hasLimit
            if (r1 != 0) goto L_0x0023
            boolean r1 = r3.findLimit(r0)
            r3.hasLimit = r1
        L_0x0023:
            r3.converge(r0)
            int r0 = r3.speedIteration
            r1 = 5
            if (r0 == r1) goto L_0x0039
            float r0 = r3.speed
            r1 = 0
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0033
            goto L_0x0039
        L_0x0033:
            r1 = 1092616192(0x41200000, float:10.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x004c
        L_0x0039:
            org.videolan.vlc.PlaybackService r0 = r3.getService()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            org.videolan.vlc.media.PlaylistManager r0 = r0.getPlaylistManager()
            int r1 = r3.oldRepeating
            r0.setRepeatType(r1)
            r3.finish()
        L_0x004c:
            org.videolan.vlc.PlaybackService r0 = r3.getService()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            float r1 = r3.speed
            r2 = 1
            r0.setRate(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.benchmark.BenchActivity.continueSpeedTest():void");
    }

    /* access modifiers changed from: private */
    public final void continueScreenshots() {
        int i = this.screenshotCount + 1;
        this.screenshotCount = i;
        List<Long> list = this.screenshotsTimestamp;
        Intrinsics.checkNotNull(list);
        if (i < list.size()) {
            seekScreenshot();
        } else {
            finish();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/video/benchmark/BenchActivity$ScreenshotBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lorg/videolan/vlc/gui/video/benchmark/BenchActivity;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BenchActivity.kt */
    public final class ScreenshotBroadcastReceiver extends BroadcastReceiver {
        public ScreenshotBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && Intrinsics.areEqual((Object) intent.getAction(), (Object) BenchActivity.ACTION_CONTINUE_BENCHMARK)) {
                BenchActivity.this.continueScreenshots();
            }
        }
    }

    private final void seekScreenshot() {
        if (getService() == null) {
            Log.w(TAG, "seekScreenshot: service is null");
            errorFinish("PlayerService is null");
            return;
        }
        int i = this.screenshotCount;
        List<Long> list = this.screenshotsTimestamp;
        Intrinsics.checkNotNull(list);
        if (i < list.size()) {
            setTimeout();
            List<Long> list2 = this.screenshotsTimestamp;
            Intrinsics.checkNotNull(list2);
            VideoPlayerActivity.seek$default(this, list2.get(this.screenshotCount).longValue(), false, 2, (Object) null);
            this.isSeeking = true;
            return;
        }
        finish();
    }

    public void exit(int i) {
        if (i != -1) {
            this.hasVLCFailed = true;
        }
        super.exit(i);
    }

    private final void errorFinish(String str) {
        Log.e(TAG, "errorFinish: " + str);
        Intent intent = new Intent();
        intent.putExtra("Error", str);
        getStackTrace();
        setResult(6, intent);
        super.finish();
    }

    private final Job getStackTrace() {
        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new BenchActivity$getStackTrace$1(this, (Continuation<? super BenchActivity$getStackTrace$1>) null), 2, (Object) null);
    }

    private final void checkLogs() {
        int i;
        int i2 = 0;
        try {
            int myPid = Process.myPid();
            Runtime runtime = Runtime.getRuntime();
            InputStreamReader inputStreamReader = new InputStreamReader(runtime.exec("logcat -d -v time --pid=" + myPid).getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = bufferedReader.readLine();
            i = 0;
            while (readLine != null) {
                try {
                    if ((StringsKt.contains$default((CharSequence) readLine, (CharSequence) "W/", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) readLine, (CharSequence) "E/", false, 2, (Object) null)) && StringsKt.contains$default((CharSequence) readLine, (CharSequence) " late ", false, 2, (Object) null)) {
                        i++;
                    }
                    readLine = bufferedReader.readLine();
                } catch (IOException e) {
                    e = e;
                    i2 = i;
                    Log.e(TAG, e.toString());
                    i = i2;
                    this.lateFrameCounter = i;
                }
            }
            inputStreamReader.close();
            bufferedReader.close();
            new ProcessBuilder(new String[0]).command(new String[]{"logcat", "-c"}).redirectErrorStream(true).start();
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, e.toString());
            i = i2;
            this.lateFrameCounter = i;
        }
        this.lateFrameCounter = i;
    }

    public void finish() {
        if (this.isSpeed) {
            PlaybackService service = getService();
            Intrinsics.checkNotNull(service);
            service.setRate(this.oldRate, true);
        } else {
            PlaybackService service2 = getService();
            Intrinsics.checkNotNull(service2);
            service2.getPlaylistManager().setRepeatType(this.oldRepeating);
        }
        Integer num = null;
        if (this.isHardware && !Intrinsics.areEqual((Object) this.oldOpenglValue, (Object) "-2")) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new BenchActivity$finish$1((SharedPreferences) Settings.INSTANCE.getInstance(this), this, (Continuation<? super BenchActivity$finish$1>) null), 2, (Object) null);
        }
        if (this.hasVLCFailed) {
            super.finish();
            return;
        }
        if (!this.hasVout) {
            setResult(1, (Intent) null);
            super.finish();
        }
        Intent intent = new Intent();
        if (getService() != null) {
            PlaybackService service3 = getService();
            Intrinsics.checkNotNull(service3);
            IMedia.Stats lastStats = service3.getLastStats();
            intent.putExtra("percent_of_bad_seek", 0.0d);
            intent.putExtra("number_of_dropped_frames", lastStats != null ? lastStats.lostPictures : 100);
            if (lastStats != null) {
                num = Integer.valueOf(lastStats.displayedPictures);
            }
            intent.putExtra("displayed_frames", num);
            intent.putExtra("late_frames", this.lateFrameCounter);
            setResult(-1, intent);
            intent.putExtra("speed", this.speed);
            intent.putExtra("dav1d_version", getString(R.string.dav1d_version));
            super.finish();
            return;
        }
        errorFinish("PlaybackService is null");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Runnable runnable = this.timeOut;
        if (runnable != null) {
            Handler handler = this.timeoutHandler;
            if (runnable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timeOut");
                runnable = null;
            }
            handler.removeCallbacks(runnable);
        }
        unregisterReceiver(this.broadcastReceiver);
        super.onDestroy();
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/video/benchmark/BenchActivity$Companion;", "", "()V", "ACTION_CONTINUE_BENCHMARK", "", "ACTION_TRIGGER_SCREENSHOT", "EXTRA_ACTION", "EXTRA_ACTION_PLAYBACK", "EXTRA_ACTION_QUALITY", "EXTRA_ACTION_SPEED", "EXTRA_HARDWARE", "EXTRA_STACKTRACE_FILE", "EXTRA_TIMESTAMPS", "EXTRA_TIME_LIMIT", "PREFERENCE_OPENGL", "PREFERENCE_PLAYBACK_HISTORY", "RESULT_FAILED", "", "RESULT_NO_HW", "SPEED_TEST_ITERATION_LIMIT", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BenchActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
