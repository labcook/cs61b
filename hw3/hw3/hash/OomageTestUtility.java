package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        int bucketNum;
        for (Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum] += 1;
        }
        for (int t : bucket) {
            if (t * 50 < oomages.size() || t * 2.5 > oomages.size()) {
                return false;
            }
        }
        return true;
    }
}
