package org.example;


import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        int count = 0;
        System.out.println("== 명언 앱 ==");
        do {
            System.out.print("명령) ");
            cmd = scanner.nextLine();
            if (Objects.equals(cmd, "등록")) {
                System.out.print("명언 : ")
                ;scanner.nextLine();
                System.out.print("작가 : ");
                scanner.nextLine();
                count += 1;
                System.out.println(count + "번 명언이 등록되었습니다");
            }

        } while (!Objects.equals(cmd, "종료"));
    }
}