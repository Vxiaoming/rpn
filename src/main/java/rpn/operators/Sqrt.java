package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sqrt implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal ... args) {
        BigDecimal a = context.pop();
        context.push(new BigDecimal(Math.sqrt(a.doubleValue()), MathContext.DECIMAL64));
    }
}
