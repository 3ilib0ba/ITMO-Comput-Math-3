public class Solver {

    public static double start(int num, double[] equation, double a, double b, double accuracy, int numRect) {
        double n = 4;
        Result res, prevRes = null;
        while (true) {
            res = callMethod(num, equation, a, b, n, numRect);
            if (prevRes != null && Math.abs(res.ans - prevRes.ans) <= accuracy) {
                break;
            }
            prevRes = res;
            n *= 2;
        }
        System.out.println(res.logs);
        return res.ans;
    }

    private static Result callMethod(int num, double[] equation, double a, double b, double n, int numRect) {
        return num == 1
                ? simpsonMethod(equation, a, b, n)
                : (num == 2
                    ? trapezoidalMethod(equation, a, b, n)
                    : rectangleMethod(equation, a, b, n, numRect)
                  );
    }

    public static Result simpsonMethod(double[] equation, double a, double b, double n) {
        StringBuilder logs = new StringBuilder();
        double odd = 0, even = 0;
        double step = (b - a) / n;
        double x = a + step;
        for (int i = 1; i < n; i++) {
            double y = calc(x, equation);
            if (i % 2 == 0) {
                even += y;
            } else {
                odd += y;
            }
            logs.append("X_").append(i).append(" = ").append(x);
            logs.append("\tY_").append(i).append(" = ").append(y).append("\n");
            x += step;
        }
        logs.append("n = ").append(n).append("\n");
        return new Result((calc(a, equation) + calc(b, equation) + 4 * odd + 2 * even) * step / 3, logs.toString());
    }

    public static Result trapezoidalMethod(double[] equation, double a, double b, double n) {
        StringBuilder logs = new StringBuilder();
        double x = a;
        double step = (b - a) / n;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            double y = calc(x, equation);
            ans += y;
            logs.append("X_").append(i).append(" = ").append(x);
            logs.append("\tY_").append(i).append(" = ").append(y).append("\n");
            x += step;
        }
        ans += (calc(a, equation) + calc(b, equation)) / 2;
        ans *= step;
        logs.append("n = ").append(n).append("\n");
        return new Result(ans, logs.toString());
    }

    public static Result rectangleMethod(double[] equation, double a, double b, double n, int method) {
        StringBuilder logs = new StringBuilder();
        double x = a;
        double step = (b - a) / n;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            double y = calc(method == 0 ? (x + step) : (method == 1 ? x + (step / 2) : x), equation);
            ans += y;
            logs.append("X_").append(i).append(" = ").append(x);
            logs.append("\tY_").append(i).append(" = ").append(y).append("\n");
            x += step;
        }
        ans *= step;
        logs.append("n = ").append(n).append("\n");
        return new Result(ans, logs.toString());
    }

    private static double calc(double x, double[] eq) {
        double ans = 0;
        for (int i = 0; i < eq.length; i++) {
            ans += eq[i] * Math.pow(x, i);
        }
        return ans;
    }

}
