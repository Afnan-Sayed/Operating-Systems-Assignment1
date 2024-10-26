import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class rmdir implements CLIInterface {

    private String dir;

    public void setDir(String path){
        dir = path;
    }


    @Override
    public void run() {
        try {
            removeDir(dir); 
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeDir(String dir) throws  IOException{
        Path path = Paths.get(dir);

        if(!Files.exists(path)){
            System.out.println("Error directory does not exits");
            return;
        }

        if(!Files.isDirectory(path)){
            System.out.println("The specified path is not a directory");
            return;
        }

        if(Files.list(path).findAny().isPresent()){
            System.out.println("Directory is not empty");
            return;
        }

        Files.delete(path);
    }
    
}
