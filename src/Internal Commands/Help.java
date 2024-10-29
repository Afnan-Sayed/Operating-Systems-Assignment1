public class Help implements CommandInterface 
{
    @Override
    public void execute(String... args) 
    {
        System.out.println("Available Commands:\n");

        System.out.println("cat [file1 file2 ...]         : Display content of specified files.");
        System.out.println("cat > [file]                  : Creates a file if not exits and rdirects the input that user types to it, overwrites if file exists.");
        System.out.println("cat >> [file]                 : Creates a file if not exits and rdirects the input that user types to it, append if file exists.");
        System.out.println("exit                          : Terminate the program.");
        System.out.println("help                          : Show this help message.");
        System.out.println("ls                            : List files and directories in the current directory.");
        System.out.println("ls -a                         : List all files, including hidden files in the current directory..");
        System.out.println("ls -r                         : List files in reverse order.");
        System.out.println("pwd                           : Print the current working directory.");
        System.out.println("cd [directory]                : Change the current directory.");
        System.out.println("mkdir [directory]             : Create a new directory.");
        System.out.println("rmdir [directory]             : Remove an empty directory.");
        System.out.println("rm [file]                     : Remove a file.");
        System.out.println("rm -r [directory]             : Remove files recursively from directory");
        System.out.println("mv [source] [destination]     : Move or rename a file or directory.");
        System.out.println("| (Pipe)                      : Redirect output of one command to another.");
    }
}
