package practice;

import com.sun.javafx.fxml.expression.Expression;
import org.jetbrains.annotations.NotNull;

/**
 * Created by AIKuznetsov on 07.12.2017.
 */
public class fiveBits {

    public static void main(String[] args) {

        Sequencer sequencer = new Sequencer(new Source() {
            short[] chunk = {(short) ((1 << 3) + (1 << 7)), 1, 1, 1};

            @Override
            public short[] get() {
                short[] result = chunk;
                chunk = null;
                return result;
            }
        });

        for (short next = sequencer.getNext(); next != Sequencer.NO_MORE_CHUNKS; next = sequencer.getNext())
            System.out.println(next);
    }


}

interface Source {
    short[] get();
}

class Sequencer {
    private static final short TOKEN_SIZE = 5;

    private static final short FIVE_BITS_MASK = (byte) (1 << TOKEN_SIZE) - 1;

    public static final short NO_MORE_CHUNKS = FIVE_BITS_MASK + 1;

    private final Source source;
    private final boolean empty;

    private short[] chunk;
    private int position;

    private short current;
    private short offset;

    public Sequencer(@NotNull Source source) {
        this.source = source;
        empty = !loadNextByte();
    }

    public short getNext() {
        return empty ? NO_MORE_CHUNKS : get();
    }

    private short get() {
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
