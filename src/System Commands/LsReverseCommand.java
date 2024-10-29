import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
    public class LsReverseCommand implements LsInterface{
    
     @Override
     public String execute() {
         File cur =new File(System.getProperty("user.dir"));
         File[] files = cur.listFiles();
         StringBuilder result = new StringBuilder();
          if(files==null){
            return "error";
            
        }
Arrays.sort(files, Comparator.comparing(File::getName).reversed());
        for(File file:files){
            
                result.append(file.getName()).append(file.isDirectory() ? "/" : "").append(" ");
                
     }
       return result.toString();
}
}
