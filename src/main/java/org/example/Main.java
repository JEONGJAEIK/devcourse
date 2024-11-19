package org.example;


import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] wise_sayings = new String[10];
        String[] authors = new String[10];
        int[] numbers = new int[10];
        String cmd;
        int count = 0;
        System.out.println("== 명언 앱 ==");

        do {
            System.out.print("명령) ");
            cmd = scanner.nextLine();

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                wise_sayings[count] = scanner.nextLine();
                System.out.print("작가 : ");
                authors[count] = scanner.nextLine();
                numbers[count] = count += 1;
                System.out.println(count + "번 명언이 등록되었습니다");

            } else if (cmd.equals("목록")) {
                for (int i = count - 1; i > - 1; i--) {
                    System.out.println(numbers[i] + " / " + authors[i] + " / " + wise_sayings[i]);
                }
            } else if (cmd.contains("삭제")) {
                char delete_number_char = (char) cmd.charAt(6);
                int delete_number = Character.getNumericValue(delete_number_char);
                wise_sayings[delete_number - 1] = null;
                authors[delete_number - 1] = null;
                numbers[delete_number - 1] = 0;
                System.out.println(delete_number + "번 명언이 삭제되었습니다");

            }

        } while (!Objects.equals(cmd, "종료"));
    }
}