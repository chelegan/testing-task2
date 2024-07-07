import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scn.nextLine();
        String result = calc(input);
        System.out.println(result);
    }
    public static String calc (String input) throws Exception {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        String outputResult;
        Roma[] romas = Roma.values();

        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }

        if (actionIndex == -1) {
            throw new Exception("Неизвестная математическая операция!");
        }

        String[] data = input.split(regexActions[actionIndex]);
        if (data.length > 2) {
            throw new Exception("Выражение не должно содержать более двух операндов!");
        }

        if (Roma.isRoma(romas, data[0]) == Roma.isRoma(romas, data[1])) {
            int a, b;

            boolean isRoman = Roma.isRoma(romas, data[0]);
            if (isRoman) {

                a = Roma.romanToInt(data[0].replaceAll(" ", ""));
                b = Roma.romanToInt(data[1].replaceAll(" ", ""));

            } else {

                a = Integer.parseInt(data[0].replaceAll(" ", ""));
                b = Integer.parseInt(data[1].replaceAll(" ", ""));
            }
            if (a > 10 || b > 10 || a == 0 || b == 0 || a < 0 || b < 0)  {
                throw new Exception("Операнды не могут быть равны нулю, быть отрицателными или больше '10' или X!");
            }
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }

            if (isRoman) {
                if (result <= 0) {
                    throw new Exception("Результат операции с римскими числами не может ровняться нулю или иметь отрицательное значение!");
                }
                outputResult = (Roma.intToRoman(result));
            } else {
                outputResult = Integer.toString(result);
            }
        } else {
            throw new Exception("Числа должны быть в одном формате!");
        }
        return outputResult;
    }
}