package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.stream.ReferencePipeline;
import j$.util.stream.Sink;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;

abstract class DistinctOps {
    static ReferencePipeline makeRef(AbstractPipeline abstractPipeline) {
        return new ReferencePipeline.StatefulOp(abstractPipeline, StreamShape.REFERENCE, StreamOpFlag.IS_DISTINCT | StreamOpFlag.NOT_SIZED) {
            static /* synthetic */ void lambda$opEvaluateParallel$0(AtomicBoolean atomicBoolean, ConcurrentHashMap concurrentHashMap, Object obj) {
                if (obj == null) {
                    atomicBoolean.set(true);
                } else {
                    concurrentHashMap.putIfAbsent(obj, Boolean.TRUE);
                }
            }

            /* access modifiers changed from: package-private */
            public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
                if (StreamOpFlag.DISTINCT.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return pipelineHelper.evaluate(spliterator, false, intFunction);
                }
                if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return reduce(pipelineHelper, spliterator);
                }
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                ForEachOps.makeRef(new DistinctOps$1$$ExternalSyntheticLambda0(atomicBoolean, concurrentHashMap), false).evaluateParallel(pipelineHelper, spliterator);
                Set keySet = concurrentHashMap.keySet();
                if (atomicBoolean.get()) {
                    HashSet hashSet = new HashSet(keySet);
                    hashSet.add((Object) null);
                    keySet = hashSet;
                }
                return Nodes.node((Collection) keySet);
            }

            /* access modifiers changed from: package-private */
            public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return StreamOpFlag.DISTINCT.isKnown(pipelineHelper.getStreamAndOpFlags()) ? pipelineHelper.wrapSpliterator(spliterator) : StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()) ? reduce(pipelineHelper, spliterator).spliterator() : new StreamSpliterators$DistinctSpliterator(pipelineHelper.wrapSpliterator(spliterator));
            }

            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                Objects.requireNonNull(sink);
                return StreamOpFlag.DISTINCT.isKnown(i) ? sink : StreamOpFlag.SORTED.isKnown(i) ? new Sink.ChainedReference(sink) {
                    Object lastSeen;
                    boolean seenNull;

                    public void accept(Object obj) {
                        if (obj != null) {
                            Object obj2 = this.lastSeen;
                            if (obj2 == null || !obj.equals(obj2)) {
                                Sink sink = this.downstream;
                                this.lastSeen = obj;
                                sink.accept(obj);
                            }
                        } else if (!this.seenNull) {
                            this.seenNull = true;
                            Sink sink2 = this.downstream;
                            this.lastSeen = null;
                            sink2.accept(null);
                        }
                    }

                    public void begin(long j) {
                        this.seenNull = false;
                        this.lastSeen = null;
                        this.downstream.begin(-1);
                    }

                    public void end() {
                        this.seenNull = false;
                        this.lastSeen = null;
                        this.downstream.end();
                    }
                } : new Sink.ChainedReference(sink) {
                    Set seen;

                    public void accept(Object obj) {
                        if (!this.seen.contains(obj)) {
                            this.seen.add(obj);
                            this.downstream.accept(obj);
                        }
                    }

                    public void begin(long j) {
                        this.seen = new HashSet();
                        this.downstream.begin(-1);
                    }

                    public void end() {
                        this.seen = null;
                        this.downstream.end();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            public Node reduce(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return Nodes.node((Collection) ReduceOps.makeRef((Supplier) new DistinctOps$1$$ExternalSyntheticLambda1(), (BiConsumer) new DistinctOps$1$$ExternalSyntheticLambda2(), (BiConsumer) new DistinctOps$1$$ExternalSyntheticLambda3()).evaluateParallel(pipelineHelper, spliterator));
            }
        };
    }
}
