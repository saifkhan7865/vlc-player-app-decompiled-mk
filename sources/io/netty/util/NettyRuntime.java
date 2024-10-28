package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SystemPropertyUtil;
import java.util.Locale;

public final class NettyRuntime {
    private static final AvailableProcessorsHolder holder = new AvailableProcessorsHolder();

    static class AvailableProcessorsHolder {
        private int availableProcessors;

        AvailableProcessorsHolder() {
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAvailableProcessors(int i) {
            ObjectUtil.checkPositive(i, "availableProcessors");
            if (this.availableProcessors == 0) {
                this.availableProcessors = i;
            } else {
                throw new IllegalStateException(String.format(Locale.ROOT, "availableProcessors is already set to [%d], rejecting [%d]", new Object[]{Integer.valueOf(this.availableProcessors), Integer.valueOf(i)}));
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized int availableProcessors() {
            if (this.availableProcessors == 0) {
                setAvailableProcessors(SystemPropertyUtil.getInt("io.netty.availableProcessors", Runtime.getRuntime().availableProcessors()));
            }
            return this.availableProcessors;
        }
    }

    public static void setAvailableProcessors(int i) {
        holder.setAvailableProcessors(i);
    }

    public static int availableProcessors() {
        return holder.availableProcessors();
    }

    private NettyRuntime() {
    }
}