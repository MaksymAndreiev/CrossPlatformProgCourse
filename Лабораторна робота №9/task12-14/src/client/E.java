package client;

import compute.Compute;
import compute.Task;

import java.math.BigDecimal;


public class E implements Compute {
    private static final long serialVersionUID = 22L;
    private int digits;
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;


    @Override
    public BigDecimal execute(int digits) {
        this.digits = digits;
        return computeE(digits);
    }

    private BigDecimal computeE(int digits) {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal x = BigDecimal.ONE;
        BigDecimal term;
        int i = 1;
        do {
            BigDecimal denom = factorial(i);
            term = x.divide(denom, digits, roundingMode);
            e = e.add(term);
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return e;
    }

    private BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 1; i <= n; i++)
            result = result.multiply(BigDecimal.valueOf(i));
        return result;
    }
}
