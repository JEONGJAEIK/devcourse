package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> wiseSayings = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        String directoryPath = "C:\\Users\\wodlr\\OneDrive\\바탕 화면\\정재익\\프로젝트\\devcourse\\src\\db\\wiseSaying";

        int number = 1;
        loadList(directoryPath,numbers,wiseSayings,authors);
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            if (cmd.equals("등록")) {
                number = createList(wiseSayings, scanner, authors, numbers, number, directoryPath);
            } else if (cmd.equals("목록")) {
                showList(numbers, authors, wiseSayings);
            } else if (cmd.contains("삭제")) {
                deleteList(cmd, wiseSayings, numbers, authors, directoryPath);
            } else if (cmd.contains("수정")) {
                updateList(cmd, wiseSayings, numbers, scanner, authors, directoryPath);
            } else if (cmd.contains("종료")) {
                deleteList(number, directoryPath);
                break;
            }
        }
    }

    private static void deleteList(int number, String directoryPath) throws IOException {
        String lastfilename = String.format("%s\\lastId.txt", directoryPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastfilename))) {
            writer.write(String.valueOf(number - 1));
        } catch (IOException _) {
        }
    }

    private static int createList(ArrayList<String> wise_sayings, Scanner scanner, ArrayList<String> authors, ArrayList<Integer> numbers, int number, Object directoryPath) {
        System.out.print("명언 : ");
        String wise_saying = scanner.nextLine();
        wise_sayings.add(wise_saying);
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        authors.add(author);
        numbers.add(number);
        System.out.println(number + "번 명언이 등록되었습니다.");
        String Createfilename = String.format("%s\\%d.json", directoryPath, number);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Createfilename))) {
            writer.write("{ \n");
            writer.write("  id : " + (number) + "," + "\n");
            writer.write("  content : " + wise_saying + "," + "\n");
            writer.write("  author : " + author + "\n");
            writer.write("}");
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

    private static void deleteList(String cmd, ArrayList<String> wise_sayings, ArrayList<Integer> numbers, ArrayList<String> authors, Object directoryPath) {
        char delete_number_char = (char) cmd.charAt(6);
        int delete_number = Character.getNumericValue(delete_number_char);
        try {
            wise_sayings.remove(numbers.indexOf(delete_number));
            authors.remove(numbers.indexOf(delete_number));
            numbers.remove((Integer) delete_number);
            String filename = String.format("%s\\%d.json", directoryPath, delete_number);
            File jsonFile = new File(filename);
            jsonFile.delete();
            System.out.println(delete_number + "번 명언이 삭제되었습니다.");
        } catch (Exception e1) {
            System.out.println(delete_number + "번 명언은 존재하지 않습니다.");
        }
    }

    private static void updateList(String cmd, ArrayList<String> wise_sayings, ArrayList<Integer> numbers, Scanner scanner, ArrayList<String> authors, String directoryPath) {
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("{ \n");
                writer.write("  id : " + update_number + "," + "\n");
                writer.write("  content : " + wise_saying + "," + "\n");
                writer.write("  author : " + author + "\n");
                writer.write("}");
            } catch (IOException _) {
            }
        } catch (Exception e1) {
            System.out.println(update_number + "번 명언은 존재하지 않습니다.");
        }
    }

    private static int loadList(String directoryPath, List<Integer> numbers, List<String> wiseSayings, List<String> authors) {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null) {
            return 1;
        }

        int lastId = 1;
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int id = 0;
                String content = "";
                String author = "";

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("id")) {
                        id = Integer.parseInt(line.split(":")[1].trim().replace(",", ""));
                    } else if (line.startsWith("content")) {
                        content = line.split(":")[1].trim().replace(",", "").replace("\"", "");
                    } else if (line.startsWith("author")) {
                        author = line.split(":")[1].trim().replace(",", "").replace("\"", "");
                    }
                }

                numbers.add(id);
                wiseSayings.add(content);
                authors.add(author);
                if (id >= lastId) {
                    lastId = id + 1; // 마지막 ID 갱신
                }
            } catch (IOException e) {
                System.out.println("파일 읽기 실패: " + file.getName());
            }
        }
        return lastId;
    }
}

