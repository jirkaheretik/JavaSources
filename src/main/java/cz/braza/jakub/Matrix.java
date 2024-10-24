package cz.braza.jakub;
/*
Zadani: https://github.com/LukasMazl/SPSMB-2022-4BI/tree/main/Lessons/03.Lesson
Vytvorte metodu, ktera na vstupu ma tridu Matrix (matice), vystupem teto metody je determinant matice. Matice muze mit nasledujici velikosti:

2 x 2
3 x 3
4 x 4
5 x 5
6 x 6

Obecny vzorec pro determinant ctvercove matice:
- suma soucinu jednotlivych permutaci, viz napr. https://www2.karlin.mff.cuni.cz/~barto/student/Determinant.pdf
- sgn(permutace) je pocet "prohozeni", zrejme mozne ziskat tak, ze na kazde pozici spocteme rozdil od puvodni/zakladni hodnoty (vzdy kladny),
 secteme a vysledek delitelny ctyrma je suda permutace, "jen" dvema pak licha
 */


import java.util.Arrays;
import java.util.stream.IntStream;

public class Matrix {
    public static final int[][] PERM2 = { {0, 1}, {1, 0}};

    private final int[][] matice;

    public Matrix(int[][] value) {
        matice = value.clone();
    }

    /*
    For creating permutations. Creates a new (size+1) array with a given new value at the given position
     */
    private static int[] insertValueAtPos(int[] base, int value, int pos) {
        int[] result = new int[base.length + 1];
        for (int i = 0; i < pos; i++)
            result[i] = base[i];
        result[pos] = value;
        for (int i = pos; i < base.length; i++)
            result[i + 1] = base[i];
        return result;
    }

    /*
    Recursive function to get all the possible permutations of a given size
     */
    public static int[][] getPermutations(int size) {
        if (size <= 2)
            return PERM2;
        // otherwise, start with smaller perm size:
        int[][] prevResult = getPermutations(size - 1);
        int[][] result = new int[size * prevResult.length][size];
        // for each permutation (element of prevResult) add size times arrays, with
        // new value size at each possible place <0; size) in that array
        for (int row = 0; row < prevResult.length; row++) {
            for (int i = 0; i < size; i++)
                result[size * row + i] = insertValueAtPos(prevResult[row], size - 1, i);
        }
        // debug string:
        System.out.println("Computing permutations for size " + size + ", created array of " + result.length + " possibilities.");
        return result;
    }

    /*
    Public method to get the row count
     */
    public int getDimension() {
        return matice.length;
    }

    /*
    Public method to get the column count (for a given row)
     */
    public int getRowLength(int row) {
        return matice[row].length;
    }

    /*
    For public reading of single values in the matrix
     */
    public int f(int row, int col) {
        return matice[row][col];
    }

    /*
    Determines if that is a square matrix (true) or not (false, different number of rows/columns)
    */
    public static boolean isSquare(Matrix matice) {
        int rows = matice.getDimension();
        for (int i = 0; i < rows; i++)
            if (matice.getRowLength(i) != rows)
                return false;
        return true;
    }
    public boolean isSquare() { return isSquare(this); }

    public int countDeterminant() { return countDeterminant(this); }
    public static int countDeterminant(Matrix matice) {
        if (!isSquare(matice))
            return 0;
        int max = matice.getDimension();
        StringBuffer sb = new StringBuffer();
        // 1. vygenerovat VSECHNY permutace zadane delky
        int[][] permutace = matice.getPermutations(max);
        // 2. vynulovat vysledek, projit vsechny, vynasobit cleny, znamenko, pricist k vysledku
        int result = 0;
        for (int i = 0; i < permutace.length; i++) {
            int dummy = sgn2Perm(permutace[i]);
            if (dummy == 1)
                sb.append(" +");
            else
                sb.append(" -");
            for (int j = 0; j < permutace[i].length; j++) {
                if (j > 0)
                    sb.append("*");
                int val = matice.f(j, permutace[i][j]);
                sb.append(val);
                dummy *= val;
            }
            result += dummy;
        }
        System.out.println("Determinant = " + sb.toString() + " = " + result);
        //
        return result;
    }

    /*
    returns +1 for even permutations, -1 for odd
     */
    public static int sgnPerm(int[] pole) {
        int sum = 0;
        for (int i = 0; i < pole.length; i++)
            sum += Math.abs(i - pole[i]);
        // sanity check:
        if (sum %2 == 1)
            System.out.println("Podivna permutace: " + Arrays.toString(pole));
        return sum % 4 == 0 ? 1 : -1;
    }

    public static int sgn2Perm(int[] pole) {
        int moves = 0;
        int index = 0;
        System.out.println(Arrays.toString(pole));
        for (int i = 0; i < pole.length; i++) {
            while (i != pole[index]) {
                if (pole[index] > i)
                    // count this
                    moves++;
                index = (index + 1) % pole.length;
                if (moves > 1000) {
                    System.out.println("Something fishy, can't find the way out... moves: " + moves + ", index: " + index + ", i: " + i);
                    return 0;
                }
            }
            index = (index + 1) % pole.length;
        }
        return moves % 2 == 0 ? 1 : -1;
    }

    private static void testSgnFunctions() {
        System.out.println("Testing sgn functions:");
        int[] temp = {0, 1, 2, 3, 4};
        System.out.println("{0, 1, 2, 3, 4}, S1: " + sgnPerm(temp) + ", S2: " + sgn2Perm(temp));
        temp = IntStream.of(2, 1, 0).toArray();
        System.out.println("{2, 1, 0}, S1: " + sgnPerm(temp) + ", S2: " + sgn2Perm(temp));
        int[] temp1 = IntStream.of(3, 2, 1, 0).toArray();
        System.out.println("{3, 2, 1, 0}, S1: " + sgnPerm(temp1) + ", S2: " + sgn2Perm(temp1));
        temp = IntStream.of(1, 2, 0).toArray();
        System.out.println("{1, 2, 0}, S1: " + sgnPerm(temp) + ", S2: " + sgn2Perm(temp));
        int[] temp2 = {5, 4, 3, 2, 1, 0};
        System.out.println("{5, 4, 3, 2, 1, 0}, S1: " + sgnPerm(temp2) + ", S2: " + sgn2Perm(temp2));
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int r = 0; r < matice.length; r++) {
            sb.append("|");
            for (int c = 0; c < matice[r].length; c++)
                sb.append(" ").append(matice[r][c]);
            sb.append(" |\n");
        }
        return sb.toString();
    }
    public static String toString(int[][] A, String label) {
        StringBuffer sb = new StringBuffer(label + " = ");
        for (int[] ints : A) {
            sb.append("|");
            for (int anInt : ints) sb.append(" ").append(anInt);
            sb.append(" |\n    ");
        }
        return sb.toString();
    }

    public static int[][] multiplyByScalar(int[][] A, int scalar) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[i].length; j++)
                result[i][j] = A[i][j] * scalar;
        return result;
    }
    public static Matrix multiplyByScalar(Matrix A, int scalar) {
        return new Matrix(multiplyByScalar(A.matice, scalar));
    }
    public int[][] multiplyByScalar(int scalar) {
        return multiplyByScalar(this.matice, scalar);
    }
    public Matrix multiplySelfByScalar(int scalar) {
        return new Matrix(multiplyByScalar(this.matice, scalar));
    }

    public static int[][] addMatrices(int[][] A, int[][] B) throws IllegalArgumentException {
        if (A.length != B.length)
            throw new IllegalArgumentException("Cannot add two matrices of different sizes");
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            if (B[i].length != A[i].length)
                throw new IllegalArgumentException("Cannot add two matrices of different sizes");
            for (int j = 0; j < A[i].length; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }
    public int[][] addMatrix(int[][] B) {
        return addMatrices(this.matice, B);
    }

    public static int[][] subtractMatrices(int[][] A, int[][] B) throws IllegalArgumentException {
        if (A.length != B.length)
            throw new IllegalArgumentException("Cannot subtract two matrices of different sizes");
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            if (B[i].length != A[i].length)
                throw new IllegalArgumentException("Cannot subtract two matrices of different sizes");
            for (int j = 0; j < A[i].length; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }
    public int[][] subtractMatrix(int[][] B) {
        return subtractMatrices(this.matice, B);
    }

    public static int[][] multiplyMatrices(int[][] A, int[][] B) throws IllegalArgumentException {
        int targetRows = A.length;
        int targetCols = B[0].length;
        int midCount = B.length;
        if (midCount != A[0].length)
            throw new IllegalArgumentException("Cannot multiply two matrices of those sizes");
        int[][] result = new int[targetRows][targetCols];
        for (int i = 0; i < targetRows; i++) {
            for (int j = 0; j < targetCols; j++) {
                int tmp = 0;
                for (int k = 0; k < midCount; k++)
                    tmp += A[i][k] * B[k][j];
                result[i][j] = tmp;
            }
        }
        return result;
    }
    public int[][] multiplyMatrix(int[][] B) {
        return multiplyMatrices(this.matice, B);
    }

    public static int[][] transposeMatrix(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[j][i] = A[i][j];
        return result;
    }
    public int[][] transposeMatrix() {
        return transposeMatrix(this.matice);
    }

    public static void main(String[] args) {
        System.out.println("Begin testing");
        int[][] arr2x2 = {{2, 1}, {3, 3}};
        int[][] arr2x3 = {{1, 2, 3}, {4, 5, 6}};
        int[][] arr3x2 = {{1, 2}, {3, 4}, {5, 6}};
        int[][] arr3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr5x5 = {{1, 0, 2, 1, 2}, {2, 2}, {0, -1, 3, 1, 1}, {2, 3, 1, 1, 1}, {1, 1, 1, 1, 1}};
        int[][] arr6x6 = {{1, 0, 2, 1, 2, 2}, {2, 2, 2, 2, 2, 2}, {0, -1, 3, 1, 1, -1}, {2, 3, 1, 1, 1, -1}, {1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 0, 1}};

        int[][] dumy = {{1, 0, 1, 5}, {0, 0, 4, -1}, {3, 2, 0, 4}, {1, 0, 3, 4}};

        Matrix m2 = new Matrix(arr2x2);
        System.out.println(m2);
        System.out.println("Determinant: " + m2.countDeterminant() + "\n\n");

        Matrix m3 = new Matrix(arr3x3);
        System.out.println(m3);
        System.out.println("Determinant: " + m3.countDeterminant() + "\n\n");

        Matrix dummy = new Matrix(dumy);
        System.out.println(dummy);
        System.out.println("Determinant: " + dummy.countDeterminant() + "\n\n");

        //System.out.println(new Matrix(Matrix.getPermutations(7)));
/*
        System.out.println(toString(multiplyMatrices(arr2x3, arr3x2), "X"));
        System.out.println(toString(multiplyMatrices(arr3x2, arr2x3), "Y"));
        System.out.println(toString(multiplyMatrices(arr2x3, arr3x3), "Z"));

        testSgnFunctions();
*/
        System.out.println("End of testing");
    }



}
