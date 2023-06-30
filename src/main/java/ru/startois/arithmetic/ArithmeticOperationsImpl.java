package ru.startois.arithmetic;

public class ArithmeticOperationsImpl implements ArithmeticOperations {
    @Override
    public long sum(int a, int b) {
        return a + b;
    }

    @Override
    public long sub(int a, int b) {
        return a - b;
    }

    @Override
    public long multiply(int a, int b) {
        return (long) a * b;
    }

    @Override
    public long division(int a, int b) {
        return a / b;
    }
}
