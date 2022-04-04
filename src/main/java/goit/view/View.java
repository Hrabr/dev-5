package goit.view;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public String read() {
        return scanner.nextLine();
    }

    public void write(String massage) {
        System.out.println(massage);
    }
}
