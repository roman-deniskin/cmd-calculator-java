package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Expression {

    public static final String[] ROMAN_DIGITS = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    };
    public static final String[] OPERATIONS = {
            "+", "-", "*", "/"
    };
    private java.util.List<Integer> args = new java.util.ArrayList();

    boolean isRomanExpr = false;
    String expr;
    String operator;

    /**
     * Заданны ограничения из задачи, инициализируется вызов калькулятора
     * @param String expr
     */
    public Expression(String expr) {
        this.expr = expr;
        String[] signs = this.parse(this.expr);

        boolean exprHasRomanDigit = false; // В выражении есть римские цифры
        boolean exprHasArabicDigit = false; // В выражении есть арабские цифры
        boolean isSubtraction = false; // В выражении есть операция вычитание
        boolean hasOperation = false; // В выражении есть символ +,-,*,/
        try {
            for (String sign: signs) {
                if (this.isNumeric(sign)) {
                    exprHasArabicDigit = true;
                    if (Integer.parseInt(sign) < 0 || Integer.parseInt(sign) > 10) {
                        throw new Exception("Вводимые числа должны быть в диапазоне от 0 до 10");
                    }
                    try {
                        Integer.parseInt(sign);
                    } catch (NumberFormatException ex) {
                        throw new Exception("Число должно быть целым");
                    }
                    this.args.add(Integer.parseInt(sign));
                }
                for(String operation: OPERATIONS) {
                    if (sign.equals("-")) {
                        isSubtraction = true;
                    }
                    if (sign.equals(operation)) {
                        this.operator = sign;
                        hasOperation = true;
                    }
                }
                if (Arrays.asList(ROMAN_DIGITS).contains(sign)) {
                    exprHasRomanDigit = true;
                    this.isRomanExpr = true;
                    this.args.add(this.romanToArabic(sign));
                }
            }
            if (exprHasArabicDigit && exprHasRomanDigit) {
                throw new Exception("В выражении не должно быть римских и арабских цифр одновременно.");
            }
            if (!hasOperation) {
                throw new Exception("В выражении должна быть математическая операция");
            }
            Integer[] argsMapper = new Integer[2]; // Тут по хорошему нужен или динамический массив, или коллекция.
            for (Integer i = 0; i < this.args.size(); i++) {
                argsMapper[i] = this.args.get(i);
            }

            Calculator calc = new Calculator(argsMapper, operator);
            if (this.isRomanExpr) {
                System.out.println(this.expr + " = " + this.arabicToRoman(Math.round(calc.process())));
            } else {
                System.out.println(this.expr + " = " + Math.round(calc.process()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getOperator() {
        return this.operator;
    }

    public Object[] getArgs() {
        return this.args.toArray();
    }

    /**
     * Парсинг введённого значения
     * @param String expr
     * @return String
     */
    public String[] parse(String expr) {
        return expr.split("((?<=[\\+|\\*|\\-|/])|(?=[\\+|\\*|\\-|/]))");
    }

    /**
     * Проверка явояется ли число римским
     * @param str
     */
    public void isRomanDigitInExpression(String str) {
        for (String romanNum:ROMAN_DIGITS) {
            if (romanNum.equals(str)) {
                this.isRomanExpr = true;
            }
        }
        this.isRomanExpr = false;
    }

    /**
     * Проверка является ли значение числом
     * @param sign
     * @return
     */
    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public int romanToArabic(String number) {
        // Можно, конечно же, сделать это более изящно через Map, но пока более простой вариант
        int returnedVal = -1;
        switch (number) {
            case "I":
                returnedVal = 1;
                break;
            case "II":
                returnedVal = 2;
                break;
            case "III":
                returnedVal = 3;
                break;
            case "IV":
                returnedVal = 4;
                break;
            case "V":
                returnedVal = 5;
                break;
            case "VI":
                returnedVal = 6;
                break;
            case "VII":
                returnedVal = 7;
                break;
            case "VIII":
                returnedVal = 8;
                break;
            case "IX":
                returnedVal = 9;
                break;
            case "X":
                returnedVal = 10;
                break;
        }
        return returnedVal;
    }

    public String arabicToRoman(int number) {
        if (number < 1)
            return "В Римской системе нет отрицательных чисел и нуля";
        if (number > 100)
            return "Римское число больше 100";
        String romanDigit = "";
        while (number >= 100) {
            romanDigit += "C";
            number -= 100;
        }
        while (number >= 90) {
            romanDigit += "XC";
            number -= 90;
        }
        while (number >= 50) {
            romanDigit += "L";
            number -= 50;
        }
        while (number >= 40) {
            romanDigit += "XL";
            number -= 40;
        }
        while (number >= 10) {
            romanDigit += "X";
            number -= 10;
        }
        while (number >= 9) {
            romanDigit += "IX";
            number -= 9;
        }
        while (number >= 5) {
            romanDigit += "V";
            number -= 5;
        }
        while (number >= 4) {
            romanDigit += "IV";
            number -= 4;
        }
        while (number >= 1) {
            romanDigit += "I";
            number -= 1;
        }
        return romanDigit;
    }
}
