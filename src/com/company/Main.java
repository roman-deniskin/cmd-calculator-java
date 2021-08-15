package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanInst = new Scanner(System.in);
	    System.out.println("Введите выражение в арабских или римских цифрах: ");
	    String cmd;
	    while (true) {
	        cmd = scanInst.nextLine();
            System.out.println("Вы ввели: " + cmd);
            if (cmd.equals("exit")) {
                return;
            } else {
                Expression expr = new Expression(cmd);
            }
        }
    }
}
