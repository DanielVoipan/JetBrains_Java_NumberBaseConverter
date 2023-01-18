package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean back = false;
        String number;
        List<BigInteger> output;
        String out;
        int sourceBase = 0;
        int targetBase = 0;
        while (true) {
            if (!back) {
                System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            } else {
                System.out.printf("Enter number in base %d to convert to base %d (To go back type /back)\n", sourceBase, targetBase);
                back = true;
            }
            Scanner scanner = new Scanner(System.in);
            String type = "started";
            if (!back) {
                type = scanner.next();
            }
            if (!back) {
                if ("/exit".equalsIgnoreCase(String.valueOf(type))) {
                    break;
                }
                sourceBase = Integer.parseInt(type);
                targetBase = Integer.parseInt(scanner.nextLine().trim());
                back = true;
            } else {
                number = scanner.next();
                if ("/back".equalsIgnoreCase(number)) {
                    back = false;
                    continue;
                }
                if (sourceBase == 10) {
                    if (number.matches("\\w+\\.\\w+")) {
                        convertFraction(number, targetBase,sourceBase);
                    } else {
                        out = convertFromDecimal(number, targetBase);
                        System.out.printf("Conversion result: %s\n", out);
                    }
                } else {
                    if (number.matches("\\w+\\.\\w+")) {
                        convertFraction(number, targetBase,sourceBase);
                    } else {
                        output = convertToDecimal(number, sourceBase);
                        String decimal = String.valueOf(output.get(0));
                        out = convertFromDecimal(decimal, targetBase);
                        System.out.printf("Conversion result: %s\n", out);
                    }
                }
            }
        }
    }
    static void convertFraction(String number, int targetBase, int sourceBase) {
        String[] splitN = number.split("\\.");
        String integer = splitN[0];
        String fractional = splitN[1];
        String IntegerConvert;
        String FractionConvert = "";
        List<BigInteger> IntegerList;
        String FractionList;
        BigDecimal Fraction;
        if (sourceBase == 10) {
            IntegerConvert = convertFromDecimal(integer, targetBase);

            // FractionConvert = convertFractionFromDecimal(IntegerConvert, targetBase).toString();
            String out = IntegerConvert + "." + FractionConvert;
            System.out.printf("Conversion result: %s\n", out);
        } else {
            // get the integer part
            IntegerList = convertToDecimal(integer, sourceBase);
            String decimal = String.valueOf(IntegerList.get(0));
            IntegerConvert = convertFromDecimal(decimal, targetBase);

            // get the franction part
            Fraction = convertFractionToDecimal(fractional, sourceBase);
            System.out.println(Fraction);
            FractionList = convertFractionFromDecimal(Fraction, targetBase);
            String out = IntegerConvert + "." + FractionList;
            System.out.printf("Conversion result: %s\n", out);
        }
    }

    static BigDecimal convertFractionToDecimal(String number, int base) {
        String[] str = String.valueOf(number).split("");
        List<BigDecimal> lst = new ArrayList<>();
        BigDecimal c;
        BigDecimal m;
        BigDecimal newSum;
        lst.add(BigDecimal.valueOf(0));
        BigDecimal j = BigDecimal.valueOf(1);
        for (int i = str.length - 1; i >= 0; i--) {
            BigDecimal get = lst.get(0);
            BigDecimal b = new BigDecimal(String.valueOf(base));
            BigDecimal pow = j.divide(b, 5, RoundingMode.HALF_UP);
            c = new BigDecimal(String.valueOf(convertHexToN(str[i])));
            newSum = c.add(get);
            m = newSum.multiply(pow);
            lst.set(0, m);
        }
        return lst.get(0);
    }

    static List<BigInteger> convertToDecimal(String number, int base) {
        String[] str = String.valueOf(number).split("");
        List<BigInteger> lst = new ArrayList<>();
        int counter = 0;
        BigInteger sum = new BigInteger(String.valueOf(0));
        BigInteger c;
        BigInteger m;
        BigInteger newSum;

        boolean started = false;
        for (int i = str.length - 1; i >= 0; i--) {
            BigInteger b = new BigInteger(String.valueOf(base));
            BigInteger pow = b.pow(counter);
            c = new BigInteger(String.valueOf(convertHexToN(str[i])));
            m = c.multiply(pow);
            newSum = sum.add(m);
            lst.add(newSum);
            if (!started) {
                lst.set(0, newSum);
            } else {
                lst.set(0, lst.get(0).add(newSum));
            }
            started = true;
            counter++;
        }
        return lst;
    }

    static String convertFractionFromDecimal(BigDecimal num, int targetBase) {
        int integral = num.intValue();
        List<BigDecimal> lst = new ArrayList<>();
        lst.add(num.subtract(BigDecimal.valueOf(integral)));
        StringBuilder str = new StringBuilder();
        int precision = 5;
        while (precision-- > 0)
        {
            BigDecimal get = lst.get(0);
            BigDecimal fraction = get.multiply(BigDecimal.valueOf(targetBase));
            int fractBit = fraction.intValue();
            if (fractBit > 0) {
                lst.set(0, fraction.subtract(BigDecimal.valueOf(fractBit)));
                str.append(convertNtoHex(fractBit));
            } else {
                str.append('0');
            }
        }
        return str.toString();
    }

    static String convertFromDecimal(String number, int targetBase) {
        BigInteger result;
        BigInteger remainder;
        BigInteger num = new BigInteger(number);
        StringBuilder adder = new StringBuilder();
        StringBuilder output = new StringBuilder();
        if (number.equals(String.valueOf(targetBase))) {
            return number;
        }
        do {
            result = num.divide(BigInteger.valueOf(targetBase));
            remainder = num.remainder(BigInteger.valueOf(targetBase));
            adder.append(convertNtoHex(Integer.parseInt(String.valueOf(remainder))));
            num = result;
            if (num.compareTo(BigInteger.valueOf(targetBase)) < 0) {
                remainder = num.remainder(BigInteger.valueOf(targetBase));
                if (remainder.compareTo(BigInteger.valueOf(0)) > 0 || targetBase == 2) {
                    adder.append(convertNtoHex(Integer.parseInt(String.valueOf(remainder))));
                }
                break;
            }
        } while (true);
        for (int i = adder.length() - 1; i >= 0 ; i--) {
            output.append(adder.charAt(i));
        }
        return output.toString();
    }

    static int convertHexToN(String hex) {
        int r = 0;
        int counter = 10;
        if (hex.matches("[a-zA-Z]")) {
            for (int i = 97; i <= 168; i++) {
                char c = (char) i;
                if (hex.equals(String.valueOf(c))) {
                    r = counter;
                    break;
                }
                counter++;
            }
            return r;
        } else {
            return Integer.parseInt(hex);
        }
    }

    static String convertNtoHex(int num) {
        String out = null;
        int counter = 10;
        if (num >= 10 && num < 36) {
            for (int i = 97; i <= 168; i++) {
                char c = (char) i;
                if (num == counter) {
                    out = String.valueOf(c);
                    break;
                }
                counter++;
            }
            return out;
        } else {
            return String.valueOf(num);
        }
    }
}