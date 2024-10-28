package nl.dionsegijn.konfetti.xml;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.Particle;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartySystem;
import nl.dionsegijn.konfetti.xml.listeners.OnParticleSystemUpdateListener;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u00014B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0014J(\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0014J\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\tH\u0014J\u0006\u0010)\u001a\u00020\u001eJ\u001f\u0010*\u001a\u00020\u001e2\u0012\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0,\"\u00020-¢\u0006\u0002\u0010.J\u0014\u0010*\u001a\u00020\u001e2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0/J\u000e\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020-J\u000e\u00100\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020-J\u0006\u00101\u001a\u00020\u001eJ\u0014\u00102\u001a\u00020\u001e*\u0002032\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lnl/dionsegijn/konfetti/xml/KonfettiView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "drawArea", "Landroid/graphics/Rect;", "onParticleSystemUpdateListener", "Lnl/dionsegijn/konfetti/xml/listeners/OnParticleSystemUpdateListener;", "getOnParticleSystemUpdateListener", "()Lnl/dionsegijn/konfetti/xml/listeners/OnParticleSystemUpdateListener;", "setOnParticleSystemUpdateListener", "(Lnl/dionsegijn/konfetti/xml/listeners/OnParticleSystemUpdateListener;)V", "paint", "Landroid/graphics/Paint;", "systems", "", "Lnl/dionsegijn/konfetti/core/PartySystem;", "timer", "Lnl/dionsegijn/konfetti/xml/KonfettiView$TimerIntegration;", "getActiveSystems", "isActive", "", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "onVisibilityChanged", "changedView", "visibility", "reset", "start", "party", "", "Lnl/dionsegijn/konfetti/core/Party;", "([Lnl/dionsegijn/konfetti/core/Party;)V", "", "stop", "stopGracefully", "display", "Lnl/dionsegijn/konfetti/core/Particle;", "TimerIntegration", "xml_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KonfettiView.kt */
public class KonfettiView extends View {
    private Rect drawArea = new Rect();
    private OnParticleSystemUpdateListener onParticleSystemUpdateListener;
    private final Paint paint = new Paint();
    private final List<PartySystem> systems = new ArrayList();
    private TimerIntegration timer = new TimerIntegration();

    public KonfettiView(Context context) {
        super(context);
    }

    public KonfettiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KonfettiView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final OnParticleSystemUpdateListener getOnParticleSystemUpdateListener() {
        return this.onParticleSystemUpdateListener;
    }

    public final void setOnParticleSystemUpdateListener(OnParticleSystemUpdateListener onParticleSystemUpdateListener2) {
        this.onParticleSystemUpdateListener = onParticleSystemUpdateListener2;
    }

    public final List<PartySystem> getActiveSystems() {
        return this.systems;
    }

    public final boolean isActive() {
        return !this.systems.isEmpty();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float deltaTime = this.timer.getDeltaTime();
        int size = this.systems.size();
        while (true) {
            size--;
            if (-1 >= size) {
                break;
            }
            PartySystem partySystem = this.systems.get(size);
            if (this.timer.getTotalTimeRunning(partySystem.getCreatedAt()) >= ((long) partySystem.getParty().getDelay())) {
                for (Particle display : partySystem.render(deltaTime, this.drawArea)) {
                    display(display, canvas);
                }
            }
            if (partySystem.isDoneEmitting()) {
                this.systems.remove(size);
                OnParticleSystemUpdateListener onParticleSystemUpdateListener2 = this.onParticleSystemUpdateListener;
                if (onParticleSystemUpdateListener2 != null) {
                    onParticleSystemUpdateListener2.onParticleSystemEnded(this, partySystem.getParty(), this.systems.size());
                }
            }
        }
        if (this.systems.size() != 0) {
            invalidate();
        } else {
            this.timer.reset();
        }
    }

    private final void display(Particle particle, Canvas canvas) {
        this.paint.setColor(particle.getColor());
        float f = (float) 2;
        float scaleX = (particle.getScaleX() * particle.getWidth()) / f;
        int save = canvas.save();
        canvas.translate(particle.getX() - scaleX, particle.getY());
        canvas.rotate(particle.getRotation(), scaleX, particle.getWidth() / f);
        canvas.scale(particle.getScaleX(), 1.0f);
        DrawShapesKt.draw(particle.getShape(), canvas, this.paint, particle.getWidth());
        canvas.restoreToCount(save);
    }

    public final void start(Party... partyArr) {
        Intrinsics.checkNotNullParameter(partyArr, "party");
        List<PartySystem> list = this.systems;
        Collection arrayList = new ArrayList(partyArr.length);
        for (Party party : partyArr) {
            OnParticleSystemUpdateListener onParticleSystemUpdateListener2 = this.onParticleSystemUpdateListener;
            if (onParticleSystemUpdateListener2 != null) {
                onParticleSystemUpdateListener2.onParticleSystemStarted(this, party, this.systems.size());
            }
            arrayList.add(new PartySystem(party, 0, 0.0f, 6, (DefaultConstructorMarker) null));
        }
        list.addAll((List) arrayList);
        invalidate();
    }

    public final void start(List<Party> list) {
        Intrinsics.checkNotNullParameter(list, "party");
        List<PartySystem> list2 = this.systems;
        Iterable<Party> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Party party : iterable) {
            OnParticleSystemUpdateListener onParticleSystemUpdateListener2 = this.onParticleSystemUpdateListener;
            if (onParticleSystemUpdateListener2 != null) {
                onParticleSystemUpdateListener2.onParticleSystemStarted(this, party, this.systems.size());
            }
            arrayList.add(new PartySystem(party, 0, 0.0f, 6, (DefaultConstructorMarker) null));
        }
        list2.addAll((List) arrayList);
        invalidate();
    }

    public final void start(Party party) {
        Intrinsics.checkNotNullParameter(party, "party");
        this.systems.add(new PartySystem(party, 0, 0.0f, 6, (DefaultConstructorMarker) null));
        OnParticleSystemUpdateListener onParticleSystemUpdateListener2 = this.onParticleSystemUpdateListener;
        if (onParticleSystemUpdateListener2 != null) {
            onParticleSystemUpdateListener2.onParticleSystemStarted(this, party, this.systems.size());
        }
        invalidate();
    }

    public final void stop(Party party) {
        Intrinsics.checkNotNullParameter(party, "party");
        CollectionsKt.removeAll(this.systems, new KonfettiView$stop$1(party));
        OnParticleSystemUpdateListener onParticleSystemUpdateListener2 = this.onParticleSystemUpdateListener;
        if (onParticleSystemUpdateListener2 != null) {
            onParticleSystemUpdateListener2.onParticleSystemEnded(this, party, this.systems.size());
        }
    }

    public final void reset() {
        this.systems.clear();
    }

    public final void stopGracefully() {
        for (PartySystem enabled : this.systems) {
            enabled.setEnabled(false);
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lnl/dionsegijn/konfetti/xml/KonfettiView$TimerIntegration;", "", "()V", "previousTime", "", "getDeltaTime", "", "getTotalTimeRunning", "startTime", "reset", "", "xml_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: KonfettiView.kt */
    public static final class TimerIntegration {
        private long previousTime = -1;

        public final void reset() {
            this.previousTime = -1;
        }

        public final float getDeltaTime() {
            if (this.previousTime == -1) {
                this.previousTime = System.nanoTime();
            }
            long nanoTime = System.nanoTime();
            this.previousTime = nanoTime;
            return (((float) (nanoTime - this.previousTime)) / 1000000.0f) / ((float) 1000);
        }

        public final long getTotalTimeRunning(long j) {
            return System.currentTimeMillis() - j;
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.drawArea = new Rect(0, 0, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "changedView");
        super.onVisibilityChanged(view, i);
        this.timer.reset();
    }
}
