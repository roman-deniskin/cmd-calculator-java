package com.company;

public class Calculator {
    private String operator;
    private Integer[] args;
    private float result;

    public Calculator(Integer[] args, String operator) {
        this.operator = operator;
        this.args = args;
    }

    /**
     * Рассчёт значений из параметров
     * @return
     * @throws Exception
     */
    public float process() {
        try {
            switch (operator) {
                case "+":
                    this.result = this.args[0] + this.args[1];
                    break;
                case "-":
                    this.result = this.args[0] - this.args[1];
                    break;
                case "*":
                    this.result = this.args[0] * this.args[1];
                    break;
                case "/":
                    if (this.args[0] == 0) {
                        throw new Exception("Деление на 0 не возможно");
                    }
                    this.result = (float)this.args[0] / (float)this.args[1];
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return this.result;
    }
}
