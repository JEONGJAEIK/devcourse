package com.wiseSaying;

import java.util.Scanner;

public class WiseSayingController {
    WiseSayingService wiseSayingService = new WiseSayingService();
    WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
    Scanner scanner = new Scanner(System.in);

    public void exitApp(int number) {
        wiseSayingRepository.createLastIdFile(number);
    }

    public int createList(int number) {
        System.out.print("명언 : ");
        String wiseSay = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        wiseSayingService.createList(number, wiseSay, author);
        System.out.println(number + "번 명언이 등록되었습니다.");
        number = wiseSayingRepository.createListFile(number, wiseSay, author);
        return number;
    }

    public void showList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        System.out.println(wiseSayingService.showList());
    }

    public void deleteList(String cmd) {
        int deleteNumber = Integer.parseInt(cmd.substring(6));
        System.out.println(deleteNumber + "번 명언이 삭제되었습니다.");
        wiseSayingRepository.deleteListfile(deleteNumber);
    }

    public void updateList(String cmd) {
        int updateNumber = Integer.parseInt(cmd.substring(6));
        try {
            System.out.print("명언(기존) : ");
            System.out.println(wiseSayingService.getWiseSay(updateNumber));
            System.out.print("명언 : ");
            String newWiseSay = scanner.nextLine();
            System.out.println("작가(기존) : ");
            System.out.println(wiseSayingService.getAuthor(updateNumber));
            System.out.print("작가 : "); // 위치 수정
            String newAuthor = scanner.nextLine();
            wiseSayingService.updateNewList(updateNumber, newWiseSay, newAuthor);
            System.out.println(updateNumber + "번 명언이 수정되었습니다.");
        } catch (Exception e1) {
            System.out.println(updateNumber + "번 명언은 존재하지 않습니다.");
        }
    }


    public int loadList() {
        return wiseSayingService.newLoadList();
    }


    public void mergedFile() {
        wiseSayingRepository.newMergedFile();
    }
}
