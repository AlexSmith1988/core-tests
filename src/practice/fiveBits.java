
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * Created by AIKuznetsov on 07.12.2017.
 */
public class FiveBits {

    public static void main(String[] args) {
        Source source = new Source() {
            Random random = new Random();

            int batchSize = 100_000_000;
            byte[] batch = new byte[batchSize];

            {
                random.nextBytes(batch);
            }

            @Override
            public byte[] get() {
                if  (batch != null) {
                    byte[] x = batch;
                    batch = null;
                    return x;
                }
                else {
                    return null;
                }
            }
        };
        long start = currentTimeMillis();
        consume(source, false);
        System.out.println(currentTimeMillis() - start + " ms");
    }

    private static void consume(Source source, boolean print) {
        Sequencer sequencer = new Sequencer(source);
        for (short next = sequencer.getNext(); next != Sequencer.NO_MORE_CHUNKS; next = sequencer.getNext())
            if (print)
                System.out.println(next);
    }


}

interface Source {
    byte[] get();
}

class Sequencer {
    private static final short TOKEN_SIZE = 5;

    private static final short FIVE_BITS_MASK = (byte) (1 << TOKEN_SIZE) - 1;

    public static final short NO_MORE_CHUNKS = FIVE_BITS_MASK + 1;

    private final Source source;
    private final boolean empty;

    private byte[] chunk;
    private int position;

    private byte current;
    private short offset;

    public Sequencer(Source source) {
        this.source = source;
        empty = !loadNextByte();
    }

    public short getNext() {
        return empty ? NO_MORE_CHUNKS : get();
    }

    private byte get() {
        byte token = (byte) ((current >> offset) & FIVE_BITS_MASK);
        int loaded = Math.min(8 - offset, TOKEN_SIZE);
        int leftToLoad = TOKEN_SIZE - loaded;
        offset += loaded;
        if (offset == 8 && !loadNextByte())
            return NO_MORE_CHUNKS;

        if (leftToLoad > 0) {
            token += ((byte) (current & ((1 << leftToLoad) - 1))) << loaded;
            offset += leftToLoad;
        }

        return token;
    }

    private boolean loadNextByte() {
        if (!loadChunk())
            return false;
        offset = 0;
        current = chunk[position++];
        return true;
    }

    private boolean loadChunk() {
        if (null == chunk || position == chunk.length) {
            chunk = source.get();
            position = 0;
        }
        return null != chunk && chunk.length != 0;
    }

}
 
 


