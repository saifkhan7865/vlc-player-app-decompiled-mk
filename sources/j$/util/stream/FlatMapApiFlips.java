package j$.util.stream;

import j$.util.ConversionRuntimeException;
import j$.util.function.Function$CC;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

public abstract class FlatMapApiFlips {

    public static class DoubleFunctionStreamWrapper implements DoubleFunction {
        public DoubleFunction function;

        public DoubleFunctionStreamWrapper(DoubleFunction doubleFunction) {
            this.function = doubleFunction;
        }

        private Object flipStream(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof DoubleStream) {
                return DoubleStream.Wrapper.convert((DoubleStream) obj);
            }
            if (obj instanceof java.util.stream.DoubleStream) {
                return DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) obj);
            }
            throw ConversionRuntimeException.exception("java.util.stream.DoubleStream", obj.getClass());
        }

        public Object apply(double d) {
            return flipStream(this.function.apply(d));
        }
    }

    public static class FunctionStreamWrapper implements Function {
        public Function function;

        public FunctionStreamWrapper(Function function2) {
            this.function = function2;
        }

        private Object flipStream(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof Stream) {
                return Stream.Wrapper.convert((Stream) obj);
            }
            if (obj instanceof java.util.stream.Stream) {
                return Stream.VivifiedWrapper.convert((java.util.stream.Stream) obj);
            }
            if (obj instanceof IntStream) {
                return IntStream.Wrapper.convert((IntStream) obj);
            }
            if (obj instanceof java.util.stream.IntStream) {
                return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) obj);
            }
            if (obj instanceof DoubleStream) {
                return DoubleStream.Wrapper.convert((DoubleStream) obj);
            }
            if (obj instanceof java.util.stream.DoubleStream) {
                return DoubleStream.VivifiedWrapper.convert((java.util.stream.DoubleStream) obj);
            }
            if (obj instanceof LongStream) {
                return LongStream.Wrapper.convert((LongStream) obj);
            }
            if (obj instanceof java.util.stream.LongStream) {
                return LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) obj);
            }
            throw ConversionRuntimeException.exception("java.util.stream.*Stream", obj.getClass());
        }

        public /* synthetic */ Function andThen(Function function2) {
            return Function$CC.$default$andThen(this, function2);
        }

        public Object apply(Object obj) {
            return flipStream(this.function.apply(obj));
        }

        public /* synthetic */ Function compose(Function function2) {
            return Function$CC.$default$compose(this, function2);
        }
    }

    public static class IntFunctionStreamWrapper implements IntFunction {
        public IntFunction function;

        public IntFunctionStreamWrapper(IntFunction intFunction) {
            this.function = intFunction;
        }

        private Object flipStream(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof IntStream) {
                return IntStream.Wrapper.convert((IntStream) obj);
            }
            if (obj instanceof java.util.stream.IntStream) {
                return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) obj);
            }
            throw ConversionRuntimeException.exception("java.util.stream.IntStream", obj.getClass());
        }

        public Object apply(int i) {
            return flipStream(this.function.apply(i));
        }
    }

    public static class LongFunctionStreamWrapper implements LongFunction {
        public LongFunction function;

        public LongFunctionStreamWrapper(LongFunction longFunction) {
            this.function = longFunction;
        }

        private Object flipStream(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof LongStream) {
                return LongStream.Wrapper.convert((LongStream) obj);
            }
            if (obj instanceof java.util.stream.LongStream) {
                return LongStream.VivifiedWrapper.convert((java.util.stream.LongStream) obj);
            }
            throw ConversionRuntimeException.exception("java.util.stream.LongStream", obj.getClass());
        }

        public Object apply(long j) {
            return flipStream(this.function.apply(j));
        }
    }

    public static DoubleFunction flipFunctionReturningStream(DoubleFunction doubleFunction) {
        return new DoubleFunctionStreamWrapper(doubleFunction);
    }

    public static Function flipFunctionReturningStream(Function function) {
        return new FunctionStreamWrapper(function);
    }

    public static IntFunction flipFunctionReturningStream(IntFunction intFunction) {
        return new IntFunctionStreamWrapper(intFunction);
    }

    public static LongFunction flipFunctionReturningStream(LongFunction longFunction) {
        return new LongFunctionStreamWrapper(longFunction);
    }
}
