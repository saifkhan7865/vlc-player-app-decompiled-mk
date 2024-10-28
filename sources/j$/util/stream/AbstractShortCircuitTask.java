package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap$SearchEntriesTask$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;

abstract class AbstractShortCircuitTask extends AbstractTask {
    protected volatile boolean canceled;
    protected final AtomicReference sharedResult;

    protected AbstractShortCircuitTask(AbstractShortCircuitTask abstractShortCircuitTask, Spliterator spliterator) {
        super((AbstractTask) abstractShortCircuitTask, spliterator);
        this.sharedResult = abstractShortCircuitTask.sharedResult;
    }

    protected AbstractShortCircuitTask(PipelineHelper pipelineHelper, Spliterator spliterator) {
        super(pipelineHelper, spliterator);
        this.sharedResult = new AtomicReference((Object) null);
    }

    /* access modifiers changed from: protected */
    public void cancel() {
        this.canceled = true;
    }

    /* access modifiers changed from: protected */
    public void cancelLaterNodes() {
        AbstractShortCircuitTask abstractShortCircuitTask = this;
        for (AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) getParent(); abstractShortCircuitTask2 != null; abstractShortCircuitTask2 = (AbstractShortCircuitTask) abstractShortCircuitTask2.getParent()) {
            if (abstractShortCircuitTask2.leftChild == abstractShortCircuitTask) {
                AbstractShortCircuitTask abstractShortCircuitTask3 = (AbstractShortCircuitTask) abstractShortCircuitTask2.rightChild;
                if (!abstractShortCircuitTask3.canceled) {
                    abstractShortCircuitTask3.cancel();
                }
            }
            abstractShortCircuitTask = abstractShortCircuitTask2;
        }
    }

    public void compute() {
        Object obj;
        Spliterator trySplit;
        Spliterator spliterator = this.spliterator;
        long estimateSize = spliterator.estimateSize();
        long targetSize = getTargetSize(estimateSize);
        AtomicReference atomicReference = this.sharedResult;
        boolean z = false;
        AbstractShortCircuitTask abstractShortCircuitTask = this;
        while (true) {
            obj = atomicReference.get();
            if (obj != null) {
                break;
            } else if (abstractShortCircuitTask.taskCanceled()) {
                obj = abstractShortCircuitTask.getEmptyResult();
                break;
            } else if (estimateSize <= targetSize || (trySplit = spliterator.trySplit()) == null) {
                obj = abstractShortCircuitTask.doLeaf();
            } else {
                AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) abstractShortCircuitTask.makeChild(trySplit);
                abstractShortCircuitTask.leftChild = abstractShortCircuitTask2;
                AbstractShortCircuitTask abstractShortCircuitTask3 = (AbstractShortCircuitTask) abstractShortCircuitTask.makeChild(spliterator);
                abstractShortCircuitTask.rightChild = abstractShortCircuitTask3;
                abstractShortCircuitTask.setPendingCount(1);
                if (z) {
                    spliterator = trySplit;
                    abstractShortCircuitTask = abstractShortCircuitTask2;
                    abstractShortCircuitTask2 = abstractShortCircuitTask3;
                } else {
                    abstractShortCircuitTask = abstractShortCircuitTask3;
                }
                z = !z;
                abstractShortCircuitTask2.fork();
                estimateSize = spliterator.estimateSize();
            }
        }
        abstractShortCircuitTask.setLocalResult(obj);
        abstractShortCircuitTask.tryComplete();
    }

    /* access modifiers changed from: protected */
    public abstract Object getEmptyResult();

    public Object getLocalResult() {
        if (!isRoot()) {
            return super.getLocalResult();
        }
        Object obj = this.sharedResult.get();
        return obj == null ? getEmptyResult() : obj;
    }

    public Object getRawResult() {
        return getLocalResult();
    }

    /* access modifiers changed from: protected */
    public void setLocalResult(Object obj) {
        if (!isRoot()) {
            super.setLocalResult(obj);
        } else if (obj != null) {
            ConcurrentHashMap$SearchEntriesTask$$ExternalSyntheticBackportWithForwarding0.m(this.sharedResult, (Object) null, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void shortCircuit(Object obj) {
        if (obj != null) {
            ConcurrentHashMap$SearchEntriesTask$$ExternalSyntheticBackportWithForwarding0.m(this.sharedResult, (Object) null, obj);
        }
    }

    /* access modifiers changed from: protected */
    public boolean taskCanceled() {
        boolean z = this.canceled;
        if (!z) {
            AbstractTask parent = getParent();
            while (true) {
                AbstractShortCircuitTask abstractShortCircuitTask = (AbstractShortCircuitTask) parent;
                if (z || abstractShortCircuitTask == null) {
                    break;
                }
                z = abstractShortCircuitTask.canceled;
                parent = abstractShortCircuitTask.getParent();
            }
        }
        return z;
    }
}
