package j$.util.stream;

import j$.util.Spliterator;

interface TerminalOp {

    /* renamed from: j$.util.stream.TerminalOp$-CC  reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static int $default$getOpFlags(TerminalOp terminalOp) {
            return 0;
        }
    }

    Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator);

    Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator);

    int getOpFlags();
}
