public class LsHandler implements CommandInterface{
    @Override 
    public String execute(String... args) 
    {
        LsInterface command = getLsCommand(args[0]);
            if (command != null) {
                return command.execute();
            } else {
                return "Unrecognized command. Try 'ls', 'ls -a', 'ls -R', or 'ls -a -R'.";
            }
    }
    

    public LsInterface getLsCommand(String input) 
    {
        return switch (input) {
            case "ls" -> new LsCommand();
            case "ls -a" -> new LsAllCommand();
            case "ls -R" -> new LsRecursiveCommand();
            case "ls -r" -> new LsReverseCommand();
            default -> null;
        };
    }




}