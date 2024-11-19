package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> wise_sayings = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        String directoryPath = String.format("C:\\Users\\wodlr\\OneDrive\\바탕 화면\\정재익\\프로젝트\\devcourse\\src\\db\\wiseSaying");
        File directory = new File(directoryPath);

        String cmd;
        int number = 1;
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            cmd = scanner.nextLine();
            if (cmd.equals("등록")) {
                number = createList(wise_sayings, scanner, authors, numbers, number, directory, directoryPath);
            } else if (cmd.equals("목록")) {
                showList(numbers, authors, wise_sayings);
            } else if (cmd.contains("삭제")) {
                deleteList(cmd, wise_sayings, numbers, authors);
            } else if (cmd.contains("수정")) {
                updateList(cmd, wise_sayings, numbers, scanner, authors, directory, directoryPath);
            } else if (cmd.contains("종료")) {
                deleteList(number, directory, directoryPath);
                break;
            }
        }
    }

    private static void deleteList(int number, File directory, String directoryPath) throws IOException {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String lastfilename = String.format("%s\\lastId.txt", directoryPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastfilename))) {
            writer.write(String.valueOf(number - 1));
        } catch (IOException _) {
        }
    }

    private static int createList(ArrayList<String> wise_sayings, Scanner scanner, ArrayList<String> authors, ArrayList<Integer> numbers, int number, File directory, Object directoryPath) {
        System.out.print("명언 : ");
        String wise_saying = scanner.nextLine();
        wise_sayings.add(wise_saying);
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        authors.add(author);
        numbers.add(number);
        System.out.println(number + "번 명언이 등록되었습니다.");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String Createfilename = String.format("%s\\%d.json", directoryPath, number);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Createfilename))) {
            writer.write(wise_saying + ' ');
            writer.write(author + ' ');
            writer.write(String.valueOf(number) + ' ');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        number += 1;
        return number;
    }

    private static void showList(ArrayList<Integer> numbers, ArrayList<String> authors, ArrayList<String> wise_sayings) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = numbers.size() - 1; i > -1; i--) {
            System.out.println(numbers.get(i) + " / " + authors.get(i) + " / " + wise_sayings.get(i));
        }
    }

    private static void deleteList(String cmd, ArrayList<String> wise_sayings, ArrayList<Integer> numbers, ArrayList<String> authors) {
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
    }

    private static void updateList(String cmd, ArrayList<String> wise_sayings, ArrayList<Integer> numbers, Scanner scanner, ArrayList<String> authors, File directory, String directoryPath) {
        char update_number_char = cmd.charAt(6);
        int update_number = Character.getNumericValue(update_number_char);
        try {
            int index = numbers.indexOf(update_number);

            System.out.println("명언(기존) : " + wise_sayings.get(index));
            System.out.print("명언 : ");
            String wise_saying = scanner.nextLine();
            wise_sayings.set(index, wise_saying);

            System.out.println("작가(기존) : " + authors.get(index));
            System.out.print("작가 : ");
            String author = scanner.nextLine();
            authors.set(index, author);

            System.out.println(update_number + "번 명언이 수정되었습니다.");

            String filename = String.format("%s\\%d.json", directoryPath, update_number);

            if (!directory.exists()) {
                directory.mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(wise_saying + "\n"); // 명언 기록
                writer.write(author + "\n"); // 작가 기록
                writer.write(update_number + "\n"); // 번호 기록
            } catch (IOException _) {
            }
        } catch (Exception e1) {
            System.out.println(update_number + "번 명언은 존재하지 않습니다.");
        }
    }
}



