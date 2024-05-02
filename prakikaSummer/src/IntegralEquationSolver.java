import java.util.Scanner;

public class IntegralEquationSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите значение границы отрезка a: ");
        double a = scanner.nextDouble();
        System.out.print("Введите значение границы отрезка b: ");
        double b = scanner.nextDouble();
        System.out.print("Введите значение константы c: ");
        double c = scanner.nextDouble();
        System.out.print("Введите значение точности eps1: ");
        double eps1 = scanner.nextDouble();
        System.out.print("Введите значение точности eps2: ");
        double eps2 = scanner.nextDouble();

        double root = solveEquation(a, b, c, eps1, eps2);

        System.out.println("Приближенное значение корня уравнения: " + root);
    }

    // Определение функции f(t)
    private static double f(double t) {
        // Здесь задается функция f(t)
        return Math.sin(t);
    }

    // Вычисление приближенного значения интеграла функции f(t) на отрезке [a, x]
    // с заданной точностью eps1 с помощью метода средних прямоугольников
    private static double integrate(double a, double x, double eps1) {
        double h = x - a;
        double integral = f((a + x) / 2) * h;
        double prevIntegral = 0;

        while (Math.abs(integral - prevIntegral) >= eps1) {
            prevIntegral = integral;
            h /= 2;
            integral = 0;

            for (double i = a + h / 2; i < x; i += h) {
                integral += f(i) * h;
            }

            integral += f(a) * h / 2 + f(x) * h / 2;
        }

        return integral;
    }

    // Вычисление значения функции F(x) = ∫a to x f(t)dt
    private static double F(double x, double eps1, double a) {
        return integrate(a, x, eps1);
    }

    // Решение уравнения F(x) - c = 0 с заданной точностью eps2 с помощью метода дихотомии
    private static double solveEquation(double a, double b, double c, double eps1, double eps2) {
        double x1 = a, x2 = b, x = 0;

        while (Math.abs(x2 - x1) >= eps2) {
            x = (x1 + x2) / 2;

            if (F(x, eps1, a) * F(x1, eps1, a) <= 0) {
                x2 = x;
            } else {
                x1 = x;
            }
        }
        return x;
    }
}