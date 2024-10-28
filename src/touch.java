
import java.io.File;

public class touch extends  CommandAbstract {

    @Override
    public void run(){
        try {
            //Create File Object
            File newFile = new File(path);

            if(!newFile.createNewFile()){
                //IF file exists, Update its modification
                updateLastModified(newFile);   
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
}
