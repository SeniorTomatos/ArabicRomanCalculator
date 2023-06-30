package ru.startois;

import ru.startois.arithmetic.ArithmeticOperations;
import ru.startois.arithmetic.ArithmeticOperationsImpl;
import ru.startois.romanPart.ArabicToRomanConverter;

import java.util.Scanner;

import static ru.startois.romanPart.RomanNum.valueOf;

public class Main {

    private final static ArithmeticOperations operations = new ArithmeticOperationsImpl();
    private final static ArabicToRomanConverter romanConverter = new ArabicToRomanConverter();

    public static void main(String[] args) throws Exception {

        System.out.println("Данный калькулятор умеет складывать, вычитать, умножать и делить, как арабские" +
                " так и римские числа.");
        System.out.println("Введите два числа от 1(I) до 10(X) и действие: например [X + V].");
        System.out.println("Прошу обратить внимание, что для корректной работы необходимо поставить пробелы по " +
                "обеим сторонам оператора!");


        Scanner scanner = new Scanner(System.in);

        String[] arithmetic = scanner.nextLine().split(" ");

        calculator(arithmetic);


    }

    public static void calculator(String[] s) throws Exception {
        if (s[0].matches("\\d+") && s[2].matches("\\d+")) {
            System.out.printf("Ответ: %d", (arabicCalc(s)));
        } else if (s[0].matches("\\d+") && !s[2].matches("\\d+") || !s[0].matches("\\d+") &&
                s[2].matches("\\d+")){
            throw new Exception("используются одновременно разные системы счисления");
        } else {
            System.out.printf("Ответ: %s", romanConverter.arabicToRoman(romanCalc(s)));
        }
    }


    public static long arabicCalc(String[] s) throws Exception {
        switch (s[1]) {
            case "+":
                return operations.sum(Integer.parseInt(s[0]), Integer.parseInt(s[2]));
            case "-":
                return operations.sub(Integer.parseInt(s[0]), Integer.parseInt(s[2]));
            case "*":
                return operations.multiply(Integer.parseInt(s[0]), Integer.parseInt(s[2]));
            case "/":
                return operations.division(Integer.parseInt(s[0]), Integer.parseInt(s[2]));
            default:
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один " +
                        "оператор (+, -, /, *)");
        }
    }


    public static long romanCalc(String[] s) throws Exception {
        if (valueOf(s[0]).getValue() > 10 || valueOf(s[2]).getValue() > 10 || valueOf(s[0]).getValue() < 1 ||
                valueOf(s[2]).getValue() < 1) {
            throw new Exception("вводимые числа не должны выходить за диапозон [I-X]");
        }

        switch (s[1]) {
            case "+":
                return operations.sum(valueOf(s[0]).getValue(), valueOf(s[2]).getValue());
            case "-":
                return operations.sub(valueOf(s[0]).getValue(), valueOf(s[2]).getValue());
            case "*":
                return operations.multiply(valueOf(s[0]).getValue(), valueOf(s[2]).getValue());
            case "/":
                return operations.division(valueOf(s[0]).getValue(), valueOf(s[2]).getValue());
            default:
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один " +
                        "оператор (+, -, /, *)");
        }
    }

}