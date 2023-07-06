package ru.startois;

import ru.startois.arithmetic.ArithmeticOperations;
import ru.startois.arithmetic.ArithmeticOperationsImpl;
import ru.startois.romanPart.ArabicToRomanConverter;
import ru.startois.romanPart.RomanNum;

import java.util.Scanner;


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
        if (s.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один " +
                    "оператор (+, -, /, *)");
        } else if (s[0].matches("-?\\d+") && s[2].matches("-?\\d+")) {
            System.out.printf("Ответ: %d", (arabicCalc(s)));
        } else if (s[0].matches("\\d+") && !s[2].matches("\\d+") || !s[0].matches("\\d+") &&
                s[2].matches("\\d+")) {
            throw new Exception("используются одновременно разные системы счисления");
        } else {
            System.out.printf("Ответ: %s", romanConverter.arabicToRoman(romanCalc(s)));
        }
    }


    public static long arabicCalc(String[] s) throws Exception {
        int num1 = Integer.parseInt(s[0]);
        int num2 = Integer.parseInt(s[2]);
        if (num1 > 10 || num2 > 10 || num1 < 1 || num2 < 1) {
            throw new Exception("вводимые числа не должны выходить за диапозон [1-10]");
        }
        switch (s[1]) {
            case "+":
                return operations.sum(num1, num2);
            case "-":
                return operations.sub(num1, num2);
            case "*":
                return operations.multiply(num1, num2);
            case "/":
                return operations.division(num1, num2);
            default:
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один " +
                        "оператор (+, -, /, *)");
        }
    }


    public static long romanCalc(String[] s) throws Exception {

        RomanNum num1 = RomanNum.valueOf(s[0]);
        RomanNum num2 = RomanNum.valueOf(s[2]);

        if (num1.getValue() > 10 || num2.getValue() > 10 || num1.getValue() < 1 ||
                num2.getValue() < 1) {
            throw new Exception("вводимые числа не должны выходить за диапозон [I-X]");
        }

        switch (s[1]) {
            case "+":
                return operations.sum(num1.getValue(), num2.getValue());
            case "-":
                return operations.sub(num1.getValue(), num2.getValue());
            case "*":
                return operations.multiply(num1.getValue(), num2.getValue());
            case "/":
                return operations.division(num1.getValue(), num2.getValue());
            default:
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один " +
                        "оператор (+, -, /, *)");
        }
    }

}