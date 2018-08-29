package DivideAndConquer;

public class Power {


    /**
     * 求 x 的 n 次方
     */
    public static double power(int x, int n) {
        boolean nag = false;
        if (n < 0) {
            nag = true;
            n = -n;
        }
        int result = _power(x, n);
        if (nag) {
            return 1.0 / result;
        } else {
            return result;
        }
    }

    public static int _power(int x, int n) {

        int half = n >> 1;
        int sub = n % 2;

        int result = 1;
        if (half > 0) {
            result = _power(x, half);
            result *= result;
        }

        if (sub > 0) {
            result = result * x;
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(power(2, -8));
        System.out.println(power(2, 19));
    }
}
