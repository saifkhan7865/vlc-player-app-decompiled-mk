package j$.util;

import j$.util.Spliterator;
import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractList extends AbstractCollection implements List {

    static final class RandomAccessSpliterator implements Spliterator {
        private int expectedModCount;
        private int fence;
        private int index;
        private final List list;

        private RandomAccessSpliterator(RandomAccessSpliterator randomAccessSpliterator, int i, int i2) {
            this.list = randomAccessSpliterator.list;
            this.index = i;
            this.fence = i2;
            this.expectedModCount = randomAccessSpliterator.expectedModCount;
        }

        RandomAccessSpliterator(List list2) {
            this.list = list2;
            this.index = 0;
            this.fence = -1;
            this.expectedModCount = 0;
        }

        static void checkAbstractListModCount(AbstractList abstractList, int i) {
        }

        private static Object get(List list2, int i) {
            try {
                return list2.get(i);
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }

        private int getFence() {
            List list2 = this.list;
            int i = this.fence;
            if (i >= 0) {
                return i;
            }
            int size = list2.size();
            this.fence = size;
            return size;
        }

        public int characteristics() {
            return 16464;
        }

        public long estimateSize() {
            return (long) (getFence() - this.index);
        }

        public void forEachRemaining(Consumer consumer) {
            Objects.requireNonNull(consumer);
            List list2 = this.list;
            int fence2 = getFence();
            this.index = fence2;
            for (int i = this.index; i < fence2; i++) {
                consumer.accept(get(list2, i));
            }
            checkAbstractListModCount((AbstractList) null, this.expectedModCount);
        }

        public /* synthetic */ Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            int fence2 = getFence();
            int i = this.index;
            if (i >= fence2) {
                return false;
            }
            this.index = i + 1;
            consumer.accept(get(this.list, i));
            checkAbstractListModCount((AbstractList) null, this.expectedModCount);
            return true;
        }

        public Spliterator trySplit() {
            int fence2 = getFence();
            int i = this.index;
            int i2 = (fence2 + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            this.index = i2;
            return new RandomAccessSpliterator(this, i, i2);
        }
    }
}
