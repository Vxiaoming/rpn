package rpn;

import java.math.BigDecimal;
import java.util.Stack;

public class RpnContext {
    private Stack<BigDecimal> numberStack;
    private Stack<Stack<BigDecimal>> log;

    public RpnContext() {
        this.numberStack = new Stack<>();
        this.log = new Stack<>();
    }

    public void push(BigDecimal number) {
        this.numberStack.push(number);
    }

    public BigDecimal pop() {
        return this.numberStack.pop();
    }

    public void resetBack(int step) {
        while (step-- > 0) {
            numberStack = log.pop();
        }
    }

    public void removeAll() {
        numberStack = new Stack<>();
    }

    public Stack<Stack<BigDecimal>> getLog() {
        return log;
    }

    public Stack<BigDecimal> getNumberStack() {
        return numberStack;
    }

}
