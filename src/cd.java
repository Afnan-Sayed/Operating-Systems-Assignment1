import java.io.File;
import java.nio.file.Paths;
public class cd implements CLIInterface {

    private String path;

    public  void setPath(String path){
        this.path = path.trim();
    }
    
    @Override
    public void run() {
        String currentDir = System.getProperty("user.dir");
        File targetDir;

        if(path.equals("..")){
            targetDir = new File(currentDir).getParentFile();
            if(targetDir==null){
                System.out.println("You are in the root directory");
                return;
            }
        }else{
            targetDir = new File(path);
            if(!targetDir.isAbsolute()){
                targetDir = Paths.get(currentDir,path).toFile();
            }
        }
        if(targetDir.exists() && targetDir.isDirectory()){
            System.setProperty("user.dir", targetDir.getAbsolutePath());
        }
        else{
            System.out.println("Directory does not exist: " + targetDir.getAbsolutePath());
        }
        
    }

   
    
}
