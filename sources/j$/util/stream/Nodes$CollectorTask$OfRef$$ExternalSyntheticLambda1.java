package j$.util.stream;

import j$.util.function.BiFunction$CC;
import j$.util.stream.Nodes;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public final /* synthetic */ class Nodes$CollectorTask$OfRef$$ExternalSyntheticLambda1 implements BinaryOperator {
    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj, Object obj2) {
        return new Nodes.ConcNode((Node) obj, (Node) obj2);
    }
}
