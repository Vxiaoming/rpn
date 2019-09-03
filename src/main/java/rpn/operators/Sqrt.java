package rpn.operators;

import rpn.RpnContext;
import rpn.exception.RpnException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sqrt implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal ... args) throws RpnException {
        BigDecimal a = context.pop();
        if (a.compareTo(BigDecimal.ZERO) < 0) {
            throw new RpnException("cannot apply to negative number");
        }

        context.push(new BigDecimal(Math.sqrt(a.doubleValue()), MathContext.DECIMAL64));
    }
}
