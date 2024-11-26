package com.wiseSaying;

import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    WiseSayingController wiseSayingController = new WiseSayingController();
    int number = wiseSayingController.loadList();


    public void cmd() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            if (cmd.equals("등록")) {
                number = wiseSayingController.createList(number);
            } else if (cmd.equals("목록")) {
                wiseSayingController.showList();
            } else if (cmd.contains("삭제")) {
                wiseSayingController.deleteList(cmd);
            } else if (cmd.contains("수정")) {
                wiseSayingController.updateList(cmd);
            } else if (cmd.equals("빌드")) {
                wiseSayingController.mergedFile();
            } else if (cmd.equals("종료")) {
                wiseSayingController.exitApp(number);
                break;
            }
        }
    }
}