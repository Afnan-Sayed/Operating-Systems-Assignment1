import java.io.File;
import java.util.Arrays;
import java.util.Comparator;


public class LsCommand implements Command{
    @Override 
    public void execute(String args) {

        Lscommand command = getLsCommand(args);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Unrecognized command. Try 'ls', 'ls -a', 'ls -R', or 'ls -a -R'.");
            }
        
    
    }
        public interface Lscommand {
    
    void execute();

}
    public class SimpleLsCommand implements Lscommand {
    @Override
    public void execute() {
        File cur = new File(System.getProperty("user.dir"));
        File[] files = cur.listFiles();
        if (files == null) {
            System.out.println("error");
            return;
        }
        for (File file : files) {
           if (!file.isHidden()) System.out.print(file.getName() + (file.isDirectory() ? "/" : "") + " ");
        }
        System.out.println();
    }
}
    public class LsAllCommand implements Lscommand {
    
     @Override
     public void execute() {
         File cur =new File(System.getProperty("user.dir"));
         File[] files = cur.listFiles();
          if(files==null){
            System.out.println("error");
            return;
        }
          Arrays.sort(files,Comparator.comparing(File :: getName));
        for(File file:files){
            
                System.out.print(file.getName()+(file.isDirectory() ? "/" : "")+" ");
                
     }
        System.out.println();
}
}


public class LsRecursiveCommand implements Lscommand {
     @Override
    public void execute() {
        File cur = new File(System.getProperty("user.dir"));
        System.out.println(lsRecursive(cur, ""));
    }
    private String lsRecursive(File dir, String indent) {
        StringBuilder result = new StringBuilder();
        File[] files = dir.listFiles();
        if (files == null) {
            return "Error";
        }

        Arrays.sort(files, Comparator.comparing(File::getName));
        for (File file : files) {
            if (!file.isHidden()) {
                result.append(indent).append(file.getName()).append(file.isDirectory()? "/" :"").append("\n");
                if (file.isDirectory()) {
                    result.append(lsRecursive(file, indent + "  "));
                }
            }
        }
        return result.toString();
    }
    
}




    public Lscommand getLsCommand(String input) {
        switch (input) {
            case "ls":
                return new SimpleLsCommand();
            case "ls -a":
                return new LsAllCommand();
            case "ls -R":
                return new LsRecursiveCommand();
            case "ls -a -R":
                return new LsAllCommand();
            default:
                return null; 
        }
    }




}



