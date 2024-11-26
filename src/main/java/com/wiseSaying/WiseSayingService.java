package com.wiseSaying;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class WiseSayingService {
    String directoryPath = "C:\\Users\\wodlr\\OneDrive\\바탕 화면\\정재익\\프로젝트\\devcourse\\src\\db\\wiseSaying";
    private ArrayList<WiseSaying> arrayList;
    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(ArrayList<WiseSaying> arrayList, WiseSayingRepository wiseSayingRepository) {
        this.arrayList = arrayList;
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public void createList(int number, String wiseSay, String author) {
        arrayList.add(new WiseSaying(number, wiseSay, author));
    }


    public int newLoadList() {
        File folder = new File(Objects.requireNonNull(directoryPath));
        File[] files = folder.listFiles((File dir, String name) -> name.endsWith(".json"));

        if (files == null) {
            return 1;
        }

        int lastId = 1;
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line.trim());
                }
                String json = jsonBuilder.toString();

                int id = extractValue(json, "\"id\"");
                String content = extractStringValue(json, "\"content\"");
                String author = extractStringValue(json, "\"author\"");

                arrayList.add(new WiseSaying(id, content, author));
                if (id >= lastId) {
                    lastId = id + 1;
                }
            } catch (IOException e) {
                System.err.println("파일 읽기 오류: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("JSON 파싱 오류: " + file.getName());
            }
        }
        return lastId;
    }

    private int extractValue(String json, String key) {
        String prefix = key + "\\s*:\\s*";
        String regex = prefix + "(\\d+)";
        return Integer.parseInt(json.replaceAll(".*" + regex + ".*", "$1"));
    }

    private String extractStringValue(String json, String key) {
        String prefix = key + "\\s*:\\s*";
        String regex = prefix + "\"([^\"]+)\"";
        return json.replaceAll(".*" + regex + ".*", "$1");
    }

    public String getWiseSay(int updateNumber) {
        for (int i = arrayList.size() - 1; i > -1; i--) {
            if (arrayList.get(i).getNumber() == updateNumber) {
                return arrayList.get(i).getWiseSay();
            }
        }
        return "";
    }


    public String getAuthor(int updateNumber) {
        for (int i = arrayList.size() - 1; i > -1; i--) {
            if (arrayList.get(i).getNumber() == updateNumber) {
                return arrayList.get(i).getAuthor();
            }
        }
        return "";
    }


    public void updateNewList(int updateNumber, String newWiseSay, String newAuthor) {
        for (int i = arrayList.size() - 1; i > -1; i--) {
            if (arrayList.get(i).getNumber() == updateNumber) {
                WiseSaying updateList = arrayList.get(i);
                updateList.setWiseSay(newWiseSay);
                updateList.setAuthor(newAuthor);
                wiseSayingRepository.updateListFile(updateNumber, newWiseSay, newAuthor);
            }
        }
    }

    public StringBuilder showList() {
        StringBuilder showList = new StringBuilder();
        for (int i = arrayList.size() - 1; i > - 1; i--) {
            showList.append(arrayList.get(i).toString()).append("\n");
        } return showList;
    }

}
