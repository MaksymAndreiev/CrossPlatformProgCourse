package client;

import compute.Compute;

import java.math.BigDecimal;


public class Pi implements Compute {
    private static final long serialVersionUID = 22L;
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private int digits;


    @Override
    public BigDecimal execute(int digits) {
        this.digits = digits;
        return computePi(digits);
    }

    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
        return pi.setScale(digits, roundingMode);
    }

    public static BigDecimal arctan(int X, int scale) {

        BigDecimal result;
        BigDecimal x, term;

        BigDecimal X1 = BigDecimal.valueOf(X);
        BigDecimal X2 = BigDecimal.valueOf(X).pow(2);

        x = BigDecimal.ONE.divide(X1, scale, roundingMode);

        result = x;
        int i = 1;
        do {
            x = x.divide(X2, scale, roundingMode);
            int denom = 2 * i + 1;
            term = x.divide(BigDecimal.valueOf(denom), scale, roundingMode);
            System.out.println(term);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }
}
