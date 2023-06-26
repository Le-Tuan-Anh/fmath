public class Calculator {
    private String[] ops = {"add", "sub", "mul", "div", "pow"};

    public String cal(String op, int a, int b) {
        if (op.equals(ops[0])) {
            return String.valueOf(a + b);
        } else if (op.equals(ops[1])) {
            return String.valueOf(a - b);
        } else if (op.equals(ops[2])) {
            return String.valueOf(a * b);
        } else if (op.equals(ops[3])) {
            return String.valueOf(a / b);
        } else if (op.equals("pow")) {
            return String.valueOf((int) Math.pow(a, b));
        } else {
            return "Invalid operation";
        }
    }
}
