import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private final Map<String, CommandInterface> commandMap;

    public CLI() {
        commandMap = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() 
    {
        // Register each command with its name and corresponding Command instance
        commandMap.put("ls", new LsHandler());
       
        // Add additional commands as needed
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> "); // Command prompt
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                continue; // Skip if the input is empty
            }

            if (input.contains("|")) {
                handlePipedCommands(input);
            } else {
                handleSingleCommand(input);
            }
        }
    }

    private void handlePipedCommands(String input) {
        // Split the input by | to handle each command in the pipeline
        String[] commands = input.split("\\|");
        PipeCommand pipeCommand = new PipeCommand();

        for (String cmd : commands) {
            String[] parts = cmd.trim().split(" ", 2);
            String cmdName = parts[0];
            String cmdArgs = parts.length > 1 ? parts[1] : "";

            CommandInterface command = commandMap.get(cmdName);
            if (command != null) {
                // Wrap the command with the arguments
                pipeCommand.addCommand(new CommandWrapper(command, cmdArgs));
            } else {
                System.out.println("Unknown command: " + cmdName);
                return;
            }
        }

        // Execute the piped commands and print the final output
        String finalOutput = pipeCommand.execute("");
        System.out.println(finalOutput);
    }

    private void handleSingleCommand(String input) {
        // Parse the command name and arguments
        String[] parts = input.trim().split(" ", 2);
        String cmdName = parts[0];
        String cmdArgs = parts.length > 1 ? parts[1] : "";

        // Look up the command in the command map
        CommandInterface command = commandMap.get(cmdName);
        if (command != null) {
            // Execute the command and print its output
            System.out.println(command.execute(cmdArgs));
        } else {
            System.out.println("Unknown command: " + cmdName);
        }
    }
}