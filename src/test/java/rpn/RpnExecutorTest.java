package rpn;

import org.junit.jupiter.api.*;
import rpn.exception.RpnException;
import rpn.operators.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RpnExecutorTest {
    Map<String, Operator> operators;

    @BeforeEach
    void setUp() {
        operators = new HashMap<>();
        operators.put("+", new Addition());
        operators.put("-", new Subtraction());
        operators.put("*", new Multiplication());
        operators.put("/", new Division());
        operators.put("sqrt", new Sqrt());
        operators.put("undo", new Undo());
        operators.put("clear", new Clear());
        operators.put("push", new Push());
    }

    @Test
    void evaluateMultiplication() {
        test(new RpnContext(), "1 2 3 * *", "6");
    }

    @Test
    void evaluateAddition() {
        test(new RpnContext(), "1 2 +", "3");
    }

    @Test
    void evaluateSubtraction() {
        test(new RpnContext(), "1 2 -", "-1");
    }

    @Test
    void evaluatePush() throws RpnException {
        test(new RpnContext(), "1", "1");

    }

    @Test
    void evaluateDivision() throws RpnException {
        test(new RpnContext(), "1 2 /", "0.5");
    }

    @Test
    void evaluateSqrt() throws RpnException {
        test(new RpnContext(), "4 sqrt", "2");
    }

    @Test
    void evaluateUndo() throws RpnException {
        RpnContext context = new RpnContext();
        RpnExecutor.execute(context, operators, "4 sqrt");
        Assertions.assertEquals(context.getNumberStack().peek(), new BigDecimal("2"));

        test(context, "undo", "4");
    }

    @Test
    void evaluateClear() throws RpnException {
        RpnContext context = new RpnContext();
        RpnExecutor.execute(context, operators, "4 sqrt");
        Assertions.assertEquals(context.getNumberStack().peek(), new BigDecimal("2"));

        test(context, "clear 1", "1");
    }

    @Test
    void evaluateUnsupported() {
        RpnContext context = new RpnContext();

        RpnException exception = assertThrows(
                RpnException.class,
                () -> RpnExecutor.execute(context, operators, "4 test"),
                "should throw exception here"
        );

        Assertions.assertEquals(context.getNumberStack().peek(), new BigDecimal("4"));
        Assertions.assertEquals("operator <test> (position: <3>): is not supported", exception.getMessage());

    }

    @Test
    void evaluateInsufficientParas() {
        RpnContext context = new RpnContext();

        RpnException exception = assertThrows(
                RpnException.class,
                () -> RpnExecutor.execute(context, operators, "4 +"),
                "should throw exception here"
        );

        Assertions.assertEquals(context.getNumberStack().peek(), new BigDecimal("4"));
        Assertions.assertEquals("operator <+> (position: <3>): insufficient parameters", exception.getMessage());

    }

    private void test(RpnContext context, String expression, String expectedValue) {
        try {
            RpnExecutor.execute(context, operators, expression);
        } catch (RpnException e) {
            fail(e.getMessage());
        }

        Assertions.assertEquals(context.getNumberStack().peek(), new BigDecimal(expectedValue));
    }
}