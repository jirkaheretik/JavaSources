package cz.braza.advent;

class Snailfish {
    private Snailfish left;
    private Snailfish right;
    private int value;
    private int depth;
    private Snailfish parent;
    public Snailfish(int value) {
        this.value = value;
    }
    public Snailfish(int left, int right) {
        this.left = new Snailfish(left);
        this.right = new Snailfish(right);
    }
    public Snailfish(Snailfish left, Snailfish right) {
        this.left = left;
        this.right = right;
    }

    public Snailfish add(Snailfish value) {
        Snailfish result = new Snailfish(this, value);
        result.reduce();
        return result;
    }

    private void reduce() {
        boolean action;
        do {
            action = false;
            // find first depth 5:
            // explode()
            // action = true;
            // if (!action) { // find value >= 10
            //  split()
        } while (action);
    }

    public int getMagnitude() {
        if (left == null && right == null)
            return value;
        else return 3 * left.getMagnitude() + 2* right.getMagnitude();

    }
}
public class AoC202118SnailfishNumbers {
}
