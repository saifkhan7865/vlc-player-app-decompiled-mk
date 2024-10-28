package kotlin.streams.jdk8;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.stream.Collectors;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\u001a%\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002*\u00020\u0005H\u0007¢\u0006\u0004\b\u0003\u0010\u0007\u001a\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\t0\u0002*\u00020\bH\u0007¢\u0006\u0004\b\u0003\u0010\n\u001a\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\f0\u0002*\u00020\u000bH\u0007¢\u0006\u0004\b\u0003\u0010\r\u001a%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a%\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010*\u00020\u0005H\u0007¢\u0006\u0004\b\u0011\u0010\u0013\u001a\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0010*\u00020\bH\u0007¢\u0006\u0004\b\u0011\u0010\u0014\u001a\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0010*\u00020\u000bH\u0007¢\u0006\u0004\b\u0011\u0010\u0015¨\u0006\u0016"}, d2 = {"T", "j$/util/stream/Stream", "Lkotlin/sequences/Sequence;", "asSequence", "(Lj$/util/stream/Stream;)Lkotlin/sequences/Sequence;", "j$/util/stream/IntStream", "", "(Lj$/util/stream/IntStream;)Lkotlin/sequences/Sequence;", "j$/util/stream/LongStream", "", "(Lj$/util/stream/LongStream;)Lkotlin/sequences/Sequence;", "j$/util/stream/DoubleStream", "", "(Lj$/util/stream/DoubleStream;)Lkotlin/sequences/Sequence;", "asStream", "(Lkotlin/sequences/Sequence;)Lj$/util/stream/Stream;", "", "toList", "(Lj$/util/stream/Stream;)Ljava/util/List;", "(Lj$/util/stream/IntStream;)Ljava/util/List;", "(Lj$/util/stream/LongStream;)Ljava/util/List;", "(Lj$/util/stream/DoubleStream;)Ljava/util/List;", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 9, 0})
/* compiled from: Streams.kt */
public final class StreamsKt {
    public static final <T> Sequence<T> asSequence(Stream<T> stream) {
        Intrinsics.checkNotNullParameter(stream, "<this>");
        return new StreamsKt$asSequence$$inlined$Sequence$1(stream);
    }

    public static final Sequence<Integer> asSequence(IntStream intStream) {
        Intrinsics.checkNotNullParameter(intStream, "<this>");
        return new StreamsKt$asSequence$$inlined$Sequence$2(intStream);
    }

    public static final Sequence<Long> asSequence(LongStream longStream) {
        Intrinsics.checkNotNullParameter(longStream, "<this>");
        return new StreamsKt$asSequence$$inlined$Sequence$3(longStream);
    }

    public static final Sequence<Double> asSequence(DoubleStream doubleStream) {
        Intrinsics.checkNotNullParameter(doubleStream, "<this>");
        return new StreamsKt$asSequence$$inlined$Sequence$4(doubleStream);
    }

    public static final <T> Stream<T> asStream(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Stream<T> stream = StreamSupport.stream(new StreamsKt$$ExternalSyntheticLambda0(sequence), 16, false);
        Intrinsics.checkNotNullExpressionValue(stream, "stream(...)");
        return stream;
    }

    /* access modifiers changed from: private */
    public static final Spliterator asStream$lambda$4(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "$this_asStream");
        return Spliterators.spliteratorUnknownSize(sequence.iterator(), 16);
    }

    public static final <T> List<T> toList(Stream<T> stream) {
        Intrinsics.checkNotNullParameter(stream, "<this>");
        Object collect = stream.collect(Collectors.toList());
        Intrinsics.checkNotNullExpressionValue(collect, "collect(...)");
        return (List) collect;
    }

    public static final List<Integer> toList(IntStream intStream) {
        Intrinsics.checkNotNullParameter(intStream, "<this>");
        int[] array = intStream.toArray();
        Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
        return ArraysKt.asList(array);
    }

    public static final List<Long> toList(LongStream longStream) {
        Intrinsics.checkNotNullParameter(longStream, "<this>");
        long[] array = longStream.toArray();
        Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
        return ArraysKt.asList(array);
    }

    public static final List<Double> toList(DoubleStream doubleStream) {
        Intrinsics.checkNotNullParameter(doubleStream, "<this>");
        double[] array = doubleStream.toArray();
        Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
        return ArraysKt.asList(array);
    }
}
