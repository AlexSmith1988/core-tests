package practice;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * Created by AIKuznetsov on 07.12.2017.
 */
public class FiveBitsOptimization {

    public static void main(String[] args) {
        int batchSize = 100_000_000;
        byte[] batch = new byte[batchSize];

        new Random().nextBytes(batch);

        long start = currentTimeMillis();
        System.out.println("Result: " + compute(batch) + ", time: " + (currentTimeMillis() - start) + " ms");
    }


    static long compute(byte[] chunk) {
        final short TOKEN_SIZE = 5;
        final int FIVE_BITS_MASK = (1 << TOKEN_SIZE) - 1;

        long result = 0;

        int limit = chunk.length - 1;
        int position = 0;
        byte current = chunk[0];
        byte offset = 0;
        while (true) {
            int token = (current >> offset) & FIVE_BITS_MASK;
            int loaded = Math.min(8 - offset, TOKEN_SIZE);
            int leftToLoad = TOKEN_SIZE - loaded;
            offset += loaded;

            if (offset == 8)
                if (position == limit)
                    return result;
                else
                    current = chunk[++position];

            if (leftToLoad > 0) {
                token += (current & ((1 << leftToLoad) - 1)) << loaded;
                offset += leftToLoad;
            }

            result += token;
        }
    }


}