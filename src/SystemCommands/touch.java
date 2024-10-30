package SystemCommands;


import java.io.File;

public class touch extends CommandAbstract implements CommandInterface {

    @Override
    public String execute(){
        for(int i = 0; i<multiplePaths.length; i++){
            try {
                //Create File Object
                File newFile = new File(multiplePaths[i]);
    
                if(!newFile.createNewFile()){
                    //IF file exists, Update its modification
                    updateLastModified(newFile);  
                    returnValue = "File Timestamp Updated"; 
                }
                returnValue = "File Created Successfuly";
            } catch (Exception e) {
                return e.toString();
            }
        }
        return returnValue;
    }

    
}
