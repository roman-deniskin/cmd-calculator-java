package com.company;

import java.util.ArrayList;

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
     * @param expr
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
                for(String romanDigit: ROMAN_DIGITS) {
                    if (sign.equals(romanDigit)) {
                        exprHasRomanDigit = true;
                    }
                }
            }
            if (exprHasArabicDigit && exprHasRomanDigit) {
                throw new Exception("В выражении не должно быть римских и арабских цифр одновременно.");
            }
            if (isSubtraction && exprHasRomanDigit) {
                throw new Exception("В римской системе нет операций вычитания");
            }
            if (!hasOperation) {
                throw new Exception("В выражении должна быть математическая операция");
            }
            Integer[] argsMapper = new Integer[2]; // Тут по хорошему нужен или динамический массив, или коллекция.
            for (Integer i = 0; i < this.args.size(); i++) {
                argsMapper[i] = this.args.get(i);
            }

            Calculator calc = new Calculator(argsMapper, operator);
            System.out.println(this.expr + " = " + calc.process());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
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
     * @param expr
     * @return
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
}
