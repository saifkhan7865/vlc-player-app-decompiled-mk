package io.netty.handler.traffic;

import io.netty.handler.traffic.GlobalChannelTrafficShapingHandler;
import io.netty.util.internal.ObjectUtil;
import java.util.concurrent.ScheduledExecutorService;

public class GlobalChannelTrafficCounter extends TrafficCounter {
    public GlobalChannelTrafficCounter(GlobalChannelTrafficShapingHandler globalChannelTrafficShapingHandler, ScheduledExecutorService scheduledExecutorService, String str, long j) {
        super(globalChannelTrafficShapingHandler, scheduledExecutorService, str, j);
        ObjectUtil.checkNotNullWithIAE(scheduledExecutorService, "executor");
    }

    private static class MixedTrafficMonitoringTask implements Runnable {
        private final TrafficCounter counter;
        private final GlobalChannelTrafficShapingHandler trafficShapingHandler1;

        MixedTrafficMonitoringTask(GlobalChannelTrafficShapingHandler globalChannelTrafficShapingHandler, TrafficCounter trafficCounter) {
            this.trafficShapingHandler1 = globalChannelTrafficShapingHandler;
            this.counter = trafficCounter;
        }

        public void run() {
            if (this.counter.monitorActive) {
                long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
                this.counter.resetAccounting(milliSecondFromNano);
                for (GlobalChannelTrafficShapingHandler.PerChannel perChannel : this.trafficShapingHandler1.channelQueues.values()) {
                    perChannel.channelTrafficCounter.resetAccounting(milliSecondFromNano);
                }
                this.trafficShapingHandler1.doAccounting(this.counter);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void start() {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.monitorActive     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r8)
            return
        L_0x0007:
            java.util.concurrent.atomic.AtomicLong r0 = r8.lastTime     // Catch:{ all -> 0x003a }
            long r1 = milliSecondFromNano()     // Catch:{ all -> 0x003a }
            r0.set(r1)     // Catch:{ all -> 0x003a }
            java.util.concurrent.atomic.AtomicLong r0 = r8.checkInterval     // Catch:{ all -> 0x003a }
            long r5 = r0.get()     // Catch:{ all -> 0x003a }
            r0 = 0
            int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0038
            r0 = 1
            r8.monitorActive = r0     // Catch:{ all -> 0x003a }
            io.netty.handler.traffic.GlobalChannelTrafficCounter$MixedTrafficMonitoringTask r0 = new io.netty.handler.traffic.GlobalChannelTrafficCounter$MixedTrafficMonitoringTask     // Catch:{ all -> 0x003a }
            io.netty.handler.traffic.AbstractTrafficShapingHandler r1 = r8.trafficShapingHandler     // Catch:{ all -> 0x003a }
            io.netty.handler.traffic.GlobalChannelTrafficShapingHandler r1 = (io.netty.handler.traffic.GlobalChannelTrafficShapingHandler) r1     // Catch:{ all -> 0x003a }
            r0.<init>(r1, r8)     // Catch:{ all -> 0x003a }
            r8.monitor = r0     // Catch:{ all -> 0x003a }
            java.util.concurrent.ScheduledExecutorService r1 = r8.executor     // Catch:{ all -> 0x003a }
            java.lang.Runnable r2 = r8.monitor     // Catch:{ all -> 0x003a }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x003a }
            r3 = 0
            java.util.concurrent.ScheduledFuture r0 = r1.scheduleAtFixedRate(r2, r3, r5, r7)     // Catch:{ all -> 0x003a }
            r8.scheduledFuture = r0     // Catch:{ all -> 0x003a }
        L_0x0038:
            monitor-exit(r8)
            return
        L_0x003a:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.traffic.GlobalChannelTrafficCounter.start():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.monitorActive     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 0
            r2.monitorActive = r0     // Catch:{ all -> 0x0022 }
            long r0 = milliSecondFromNano()     // Catch:{ all -> 0x0022 }
            r2.resetAccounting(r0)     // Catch:{ all -> 0x0022 }
            io.netty.handler.traffic.AbstractTrafficShapingHandler r0 = r2.trafficShapingHandler     // Catch:{ all -> 0x0022 }
            r0.doAccounting(r2)     // Catch:{ all -> 0x0022 }
            java.util.concurrent.ScheduledFuture r0 = r2.scheduledFuture     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0020
            java.util.concurrent.ScheduledFuture r0 = r2.scheduledFuture     // Catch:{ all -> 0x0022 }
            r1 = 1
            r0.cancel(r1)     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r2)
            return
        L_0x0022:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.traffic.GlobalChannelTrafficCounter.stop():void");
    }

    public void resetCumulativeTime() {
        for (GlobalChannelTrafficShapingHandler.PerChannel perChannel : ((GlobalChannelTrafficShapingHandler) this.trafficShapingHandler).channelQueues.values()) {
            perChannel.channelTrafficCounter.resetCumulativeTime();
        }
        super.resetCumulativeTime();
    }
}
