package me.KrazyManJ.KrazyEngine.Any;

import java.nio.CharBuffer;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public class AlphanumericComparator implements Comparator<CharSequence> {

    private final Collator collator;

    public AlphanumericComparator() {
        collator = null;
    }

    public AlphanumericComparator(final Locale locale) {
        this(Collator.getInstance(Objects.requireNonNull(locale)));
    }

    public AlphanumericComparator(final Collator collator) {
        this.collator = Objects.requireNonNull(collator);
    }

    @Override
    public int compare(final CharSequence s1, final CharSequence s2) {
        final CharBuffer b1 = CharBuffer.wrap(s1);
        final CharBuffer b2 = CharBuffer.wrap(s2);

        while (b1.hasRemaining() && b2.hasRemaining()) {
            moveWindow(b1);
            moveWindow(b2);

            final int result = compare(b1, b2);
            if (result != 0) {
                return result;
            }

            prepareForNextIteration(b1);
            prepareForNextIteration(b2);
        }

        return s1.length() - s2.length();
    }

    private void moveWindow(final CharBuffer buffer) {
        int start = buffer.position();
        int end = buffer.position();
        final boolean isNumerical = isDigit(buffer.get(start));
        while (end < buffer.limit() && isNumerical == isDigit(buffer.get(end))) {
            ++end;
            if (isNumerical && (start + 1 < buffer.limit()) && isZero(buffer.get(start)) && isDigit(buffer.get(end))) {
                ++start;
            }
        }

        buffer.position(start)
                .limit(end);
    }

    private int compare(final CharBuffer b1, final CharBuffer b2) {
        if (isNumerical(b1) && isNumerical(b2)) {
            return compareNumerically(b1, b2);
        }

        return compareAsStrings(b1, b2);
    }

    private boolean isNumerical(final CharBuffer buffer) {
        return isDigit(buffer.charAt(0));
    }

    private boolean isDigit(final char c) {
        if (collator == null) {
            return (int) c >= 48 && (int) c <= 57;
        }
        return Character.isDigit(c);
    }

    private int compareNumerically(final CharBuffer b1, final CharBuffer b2) {
        final int diff = b1.length() - b2.length();
        if (diff != 0) {
            return diff;
        }
        for (int i = 0; i < b1.remaining() && i < b2.remaining(); ++i) {
            final int result = Character.compare(b1.charAt(i), b2.charAt(i));
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    private void prepareForNextIteration(final CharBuffer buffer) {
        buffer.position(buffer.limit())
                .limit(buffer.capacity());
    }

    private int compareAsStrings(final CharBuffer b1, final CharBuffer b2) {
        if (collator != null) {
            return collator.compare(b1.toString(), b2.toString());
        }
        return b1.toString().compareTo(b2.toString());
    }

    private boolean isZero(final char c) {
        return c == '0';
    }

}
