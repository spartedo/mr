import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    protected static String session_user = "anonymous";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter your command:");

        boolean resume = true;
        while (resume) {
            String text = keyboard.nextLine();
            String[] parts = text.split(" ");

            if (parts[0].equals("login")) {
                login(parts);
            } else if (parts[0].equals("whoami")) {
                whoami(parts);
            } else if (parts[0].equals("exit")) {
                resume = false;
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    public static void whoami(String[] parts){
        System.out.println(session_user);
    }

    public static void login(String[] parts){
        String filename = "data/users/" + parts[1];
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            try {
                String password = Files.readString(f.toPath());
                if(password.equals(parts[2])) {
                    System.out.println("Hello, " + parts[1]);
                    session_user = parts[1];
                } else {
                    System.out.println("incorrect login and password");
                }
            } catch (IOException e) {
                System.out.println("failed to load user profile");
            }
        } else {
            System.out.println("incorrect login and password");
        }
    }
}

//