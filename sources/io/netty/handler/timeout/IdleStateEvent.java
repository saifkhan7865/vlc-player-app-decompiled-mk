package io.netty.handler.timeout;

import io.ktor.server.auth.OAuth2RequestParameters;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

public class IdleStateEvent {
    public static final IdleStateEvent ALL_IDLE_STATE_EVENT = new DefaultIdleStateEvent(IdleState.ALL_IDLE, false);
    public static final IdleStateEvent FIRST_ALL_IDLE_STATE_EVENT = new DefaultIdleStateEvent(IdleState.ALL_IDLE, true);
    public static final IdleStateEvent FIRST_READER_IDLE_STATE_EVENT = new DefaultIdleStateEvent(IdleState.READER_IDLE, true);
    public static final IdleStateEvent FIRST_WRITER_IDLE_STATE_EVENT = new DefaultIdleStateEvent(IdleState.WRITER_IDLE, true);
    public static final IdleStateEvent READER_IDLE_STATE_EVENT = new DefaultIdleStateEvent(IdleState.READER_IDLE, false);
    public static final IdleStateEvent WRITER_IDLE_STATE_EVENT = new DefaultIdleStateEvent(IdleState.WRITER_IDLE, false);
    private final boolean first;
    private final IdleState state;

    protected IdleStateEvent(IdleState idleState, boolean z) {
        this.state = (IdleState) ObjectUtil.checkNotNull(idleState, OAuth2RequestParameters.State);
        this.first = z;
    }

    public IdleState state() {
        return this.state;
    }

    public boolean isFirst() {
        return this.first;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName((Object) this));
        sb.append('(');
        sb.append(this.state);
        sb.append(this.first ? ", first" : "");
        sb.append(')');
        return sb.toString();
    }

    private static final class DefaultIdleStateEvent extends IdleStateEvent {
        private final String representation;

        DefaultIdleStateEvent(IdleState idleState, boolean z) {
            super(idleState, z);
            StringBuilder sb = new StringBuilder("IdleStateEvent(");
            sb.append(idleState);
            sb.append(z ? ", first" : "");
            sb.append(')');
            this.representation = sb.toString();
        }

        public String toString() {
            return this.representation;
        }
    }
}
