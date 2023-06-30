package ru.startois.romanPart;

import java.util.List;

public class ArabicToRomanConverter {


    public String arabicToRoman(long number) {
        if ((number < 1) || (number > 3999)) {
            throw new IllegalArgumentException(number + ": число не попадает в диапазон римских чисел [1,3999]");
        }

        List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNum romeNum = romanNumerals.get(i);
            if (romeNum.getValue() <= number) {
                sb.append(romeNum.name());
                number -= romeNum.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}
