package SystemCommands;

import java.io.File;

public class rm extends CommandAbstract implements CommandInterface {

    @Override
    public String execute(){
        
        for(int i = 0; i< multiplePaths.length; i++){
            //Create File Object
            File file = new File(multiplePaths[i]);
            try {
                //Delete File
                if(!file.delete()){
                    return "Failed To Delete File";
                }
                returnValue = "File Deleted";
    
            } catch (Exception e) {
                return e.toString();
            }
        }
        return returnValue;
    }
}
