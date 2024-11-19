package org.example;


import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cmd;

        System.out.println("== 명언 앱 ==");
        do {
            System.out.print("명령) ");
            cmd = scanner.nextLine();
        } while (!Objects.equals(cmd, "종료"));
    }
}