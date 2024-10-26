import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class mkdir implements CLIInterface{
    private ArrayList<String> directoryPath;

    public void setDirectoryPath(ArrayList<String> path){
        directoryPath = path;
    }

    @Override
    public void run() {
        String currDir = System.getProperty("user.dir");
        if(currDir==null){
            currDir = Paths.get("").toAbsolutePath().toString();
        }

        for(String path: directoryPath){
            File directory = new File(path);
            if(!directory.isAbsolute()){
                directory = new File(currDir,path);
            }
            if(!directory.exists()){
                boolean isCreated = directory.mkdirs();
                if(isCreated)continue;
                else{
                    System.out.println("Error while creating the "+directory);
                }
            }
            else{
                System.out.println("Directory already exists");
            }
        }
        
    }
    
}
