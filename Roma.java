import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public enum Roma {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9),
    X(10), XL(40), L(50), XC(90), C(100);
    private int value;
    Roma(Integer value) {
        this.value = value;
    }

    public Integer Convertation() {
        return value;
    }

    public static boolean isRoma(Roma[] array, String data) {
        boolean b = false;
        for (Roma roma : array) {
            if (roma.toString().equals(data.replaceAll(" ",""))) {
                b=true;
                break;
            }

        }
        return b;
    }
    public static List<Roma> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((Roma e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

    public static Integer romanToInt(String data) throws Exception {
        String romanNumeral = data.toUpperCase();
        int result = 0;

        List<Roma> romanNumerals = Roma.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            Roma symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.Convertation();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new Exception ("Проверьте правильность ввода операнда!");
        }

        return result;
    }
    public static String intToRoman(int data) {

        List<Roma> romanNumerals = Roma.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((data > 0) && (i < romanNumerals.size())) {
            Roma currentSymbol = romanNumerals.get(i);
            if (currentSymbol.Convertation() <= data) {
                sb.append(currentSymbol.name());
                data -= currentSymbol.Convertation();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}




