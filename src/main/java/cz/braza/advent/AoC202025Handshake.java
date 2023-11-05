package cz.braza.advent;

public class AoC202025Handshake {
    static final long CARD = 15628416;
    static final long DOOR = 11161639;
    static final long DIVIDER = 20201227;

    public long transform(long subject) {
        return transform(subject, 1);
    }
    public static long transform(long subject, long value) {
        return (value * subject) % DIVIDER;
    }
    public static long findLoopSize(long target, long subject) {
        long result = 1;
        long loopsize = 0;
        while (result != target) {
            loopsize++;
            result = transform(subject, result);
        }
        return loopsize;
    }
    public static long encrypt(long subject, long loopsize) {
        long result = 1;
        for (long i = 0; i < loopsize; i++)
            result = transform(subject, result);
        return result;
    }
    public static void main(String[] args) {
        long cardLoopsize = findLoopSize(CARD, 7);
        long doorLoopsize = findLoopSize(DOOR, 7);
        System.out.println("Card: " + cardLoopsize + ", door: " + doorLoopsize);
        long encCard = encrypt(DOOR, cardLoopsize);
        long encDoor = encrypt(CARD, doorLoopsize);
        System.out.println("Encryption key by card: " + encCard + ", by door: " + encDoor);
    }
}
