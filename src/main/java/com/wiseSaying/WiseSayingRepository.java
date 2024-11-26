package com.wiseSaying;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    String directoryPath = "C:\\Users\\wodlr\\OneDrive\\바탕 화면\\정재익\\프로젝트\\devcourse\\src\\db\\wiseSaying";
    ArrayList<WiseSaying> arrayList = new ArrayList();

    private String createLastId() {
        return String.format("%s\\lastId.txt", directoryPath);
    }

    public void createLastIdFile(int number) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(createLastId()))) {
            writer.write(String.valueOf(number - 1));
        } catch (IOException _) {
        }
    }

    public int createListFile(int number, String wiseSay, String author) {
        String Createfilename = String.format("%s\\%d.json", directoryPath, number);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Createfilename))) {
            writer.write("{ \n  ");
            writer.write("\"id\": " + number + "," + "\n  ");
            writer.write("\"content\": " + '"' + wiseSay + '"' + "," + "\n  ");
            writer.write("\"author\": " + '"' + author + '"' + "\n");
            writer.write("}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return number + 1;
    }

    public void deleteListfile(int deleteNumber) {
        for (int i = arrayList.size() - 1; i > -1; i--) {
            if (arrayList.get(i).getNumber() == deleteNumber) {
                arrayList.remove(i);
            }
        }
        String filename = String.format("%s\\%d.json", directoryPath, deleteNumber);
        File jsonFile = new File(filename);
        jsonFile.delete();
    }

    public void updateListFile(int updateNumber, String newWiseSay, String newAuthor) {
        String filename = String.format("%s\\%d.json", directoryPath, updateNumber);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("{ \n  ");
            writer.write("\"id\": " + updateNumber + "," + "\n  ");
            writer.write("\"content\": " + '"' + newWiseSay + '"' + "," + "\n  ");
            writer.write("\"author\": " + '"' + newAuthor + '"' + "\n");
            writer.write("}");
        } catch (IOException _) {
        }
    }

    public void newMergedFile() {
        String mergedfile = directoryPath + "\\" + "data.json";

        try {
            File folder = new File(directoryPath);
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json") && !name.equalsIgnoreCase("data.json"));

            if (files == null || files.length == 0) {
                System.out.println("JSON 파일이 없습니다.");
                return;
            }

            List<String> jsonObjects = new ArrayList<>();

            for (File file : files) {
                String content = new String(Files.readAllBytes(file.toPath())).trim();

                if (content.startsWith("{") && content.endsWith("}")) {
                    jsonObjects.add(content);
                } else {
                    System.out.println("잘못된 JSON 파일 형식: " + file.getName());
                }
            }

            String mergedJson = "[\n" + String.join(",\n", jsonObjects) + "\n]";

            Files.write(Paths.get(mergedfile), mergedJson.getBytes());
            System.out.println("모든 JSON 파일이 병합되었습니다: " + mergedfile);

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}
