package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

abstract class AbstractTask extends CountedCompleter {
    private static final int LEAF_TARGET = (ForkJoinPool.getCommonPoolParallelism() << 2);
    protected final PipelineHelper helper;
    protected AbstractTask leftChild;
    private Object localResult;
    protected AbstractTask rightChild;
    protected Spliterator spliterator;
    protected long targetSize;

    protected AbstractTask(AbstractTask abstractTask, Spliterator spliterator2) {
        super(abstractTask);
        this.spliterator = spliterator2;
        this.helper = abstractTask.helper;
        this.targetSize = abstractTask.targetSize;
    }

    protected AbstractTask(PipelineHelper pipelineHelper, Spliterator spliterator2) {
        super((CountedCompleter) null);
        this.helper = pipelineHelper;
        this.spliterator = spliterator2;
        this.targetSize = 0;
    }

    public static int getLeafTarget() {
        return LEAF_TARGET;
    }

    public static long suggestTargetSize(long j) {
        long leafTarget = j / ((long) getLeafTarget());
        if (leafTarget > 0) {
            return leafTarget;
        }
        return 1;
    }

    public void compute() {
        Spliterator trySplit;
        Spliterator spliterator2 = this.spliterator;
        long estimateSize = spliterator2.estimateSize();
        long targetSize2 = getTargetSize(estimateSize);
        boolean z = false;
        AbstractTask abstractTask = this;
        while (estimateSize > targetSize2 && (trySplit = spliterator2.trySplit()) != null) {
            AbstractTask makeChild = abstractTask.makeChild(trySplit);
            abstractTask.leftChild = makeChild;
            AbstractTask makeChild2 = abstractTask.makeChild(spliterator2);
            abstractTask.rightChild = makeChild2;
            abstractTask.setPendingCount(1);
            if (z) {
                spliterator2 = trySplit;
                abstractTask = makeChild;
                makeChild = makeChild2;
            } else {
                abstractTask = makeChild2;
            }
            z = !z;
            makeChild.fork();
            estimateSize = spliterator2.estimateSize();
        }
        abstractTask.setLocalResult(abstractTask.doLeaf());
        abstractTask.tryComplete();
    }

    /* access modifiers changed from: protected */
    public abstract Object doLeaf();

    /* access modifiers changed from: protected */
    public Object getLocalResult() {
        return this.localResult;
    }

    /* access modifiers changed from: protected */
    public AbstractTask getParent() {
        return (AbstractTask) getCompleter();
    }

    public Object getRawResult() {
        return this.localResult;
    }

    /* access modifiers changed from: protected */
    public final long getTargetSize(long j) {
        long j2 = this.targetSize;
        if (j2 != 0) {
            return j2;
        }
        long suggestTargetSize = suggestTargetSize(j);
        this.targetSize = suggestTargetSize;
        return suggestTargetSize;
    }

    /* access modifiers changed from: protected */
    public boolean isLeaf() {
        return this.leftChild == null;
    }

    /* access modifiers changed from: protected */
    public boolean isLeftmostNode() {
        AbstractTask abstractTask = this;
        while (abstractTask != null) {
            AbstractTask parent = abstractTask.getParent();
            if (parent != null && parent.leftChild != abstractTask) {
                return false;
            }
            abstractTask = parent;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isRoot() {
        return getParent() == null;
    }

    /* access modifiers changed from: protected */
    public abstract AbstractTask makeChild(Spliterator spliterator2);

    public void onCompletion(CountedCompleter countedCompleter) {
        this.spliterator = null;
        this.rightChild = null;
        this.leftChild = null;
    }

    /* access modifiers changed from: protected */
    public void setLocalResult(Object obj) {
        this.localResult = obj;
    }

    /* access modifiers changed from: protected */
    public void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }
}
