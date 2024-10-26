import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing the mkdir, cd, rmdir");
        mkdir makeDir = new mkdir();
        rmdir removeDir = new rmdir();
        cd changeDir = new cd();
        String line;
        Scanner scanner = new Scanner(System.in);
        while (true) { 
            System.out.print(System.getProperty("user.dir")+": ");
            line = scanner.nextLine();
            
            String command = "";
            int i = 0;
            while (i < line.length() && line.charAt(i) != ' ') {
                command += line.charAt(i);
                i++;
            }
            i++;
            String path = line.substring(i).trim();

            switch (command) {
                case "mkdir":
                    ArrayList<String> dir = new ArrayList<>();
                    dir.add(path); 
                    makeDir.setDirectoryPath(dir);
                    makeDir.run();
                    break;
                case "cd":

                    changeDir.setPath(path);
                    changeDir.run();
                    
                    break;
                case "rmdir":
                    removeDir.setDir(path);
                    removeDir.run();
                    break;
                default:
                    System.out.println(System.getProperty(line));
            }

                
        }

    }
}
