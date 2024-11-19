package org.example;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> wise_sayings = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();

        String cmd;
        int number = 1;
        System.out.println("== 명언 앱 ==");

        do {
            System.out.print("명령) ");
            cmd = scanner.nextLine();

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                wise_sayings.add(scanner.nextLine());
                System.out.print("작가 : ");
                authors.add(scanner.nextLine());
                numbers.add(number);
                System.out.println(number + "번 명언이 등록되었습니다.");
                number += 1;

            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = numbers.size() - 1; i > -1; i--) {
                    System.out.println(numbers.get(i) + " / " + authors.get(i) + " / " + wise_sayings.get(i));
                }
            } else if (cmd.contains("삭제")) {
                char delete_number_char = (char) cmd.charAt(6);
                int delete_number = Character.getNumericValue(delete_number_char);
                try {
                    wise_sayings.remove(numbers.indexOf(delete_number));
                    authors.remove(numbers.indexOf(delete_number));
                    numbers.remove((Integer) delete_number);
                    System.out.println(delete_number + "번 명언이 삭제되었습니다.");
                } catch (Exception e1) {
                    System.out.println(delete_number + "번 명언은 존재하지 않습니다.");
                }

            } else if (cmd.contains("수정")) {
                char update_number_char = (char) cmd.charAt(6);
                int update_number = Character.getNumericValue(update_number_char);
                try {
                    System.out.println("명언(기존) : " + wise_sayings.get(numbers.indexOf(update_number)));
                    System.out.print("명언 : ");
                    wise_sayings.set(numbers.indexOf(update_number), scanner.nextLine());
                    System.out.println("작가(기존) : " + authors.get(numbers.indexOf(update_number)));
                    System.out.print("작가 : ");
                    authors.set(numbers.indexOf(update_number), scanner.nextLine());
                    System.out.println(update_number + "번 명언이 수정되었습니다.");
                } catch (Exception e1) {
                    System.out.println(update_number + "번 명언은 존재하지 않습니다.");
                }
            }
        } while (!Objects.equals(cmd, "종료"));
    }
}



