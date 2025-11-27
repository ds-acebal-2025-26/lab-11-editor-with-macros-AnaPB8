package es.uniovi.eii.ds.main;

import java.io.*;
import java.util.Arrays;

import es.uniovi.eii.ds.editor.Editor;

public class Main {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	// Represents the editor.
	Editor editor = new Editor();

    public static void main(String[] args) {
        new Main().run();
    }
	
	// Main program loop.
    public void run() {
		drawLogo();
		showHelp();

		while (true) {
			UserCommand command = promptUser();
			String[] args = command.args;

			switch (command.name) {
				case "open" -> {
					if (!checkArguments(args, 1, "open <file>"))
						break;
					String filename = args[0];
					editor.open(filename);
				}
				case "insert" -> {
					editor.insert(args);
				}
				case "delete" -> {
					editor.delete();
				}
				case "replace" -> {
					if (!checkArguments(args, 2, "replace <find> <replace>"))
						break;
					String find = args[0];
					String replace = args[1];
					editor.replace(find, replace);
				}
				case "help" -> showHelp();
				case "record" -> {
					String macroName = args[0];
					editor.recordMacro(macroName);
				}
				case "stop" -> { 
					editor.stopRecording();
				}
				case "execute" -> {
					String macroListName = args[0];
					editor.executeMacro(macroListName);
				}
				default -> {
					System.out.println("Unknown command");
					continue;
				}
			}

			System.out.println(editor.getDoc().getText());
		}
	}

	//$-- Auxiliary methods ---------------------------------------------------

	// YOU DON'T NEED TO UNDERSTAND OR MODIFY THE CODE BELOW THIS LINE

	private record UserCommand(String name, String[] args) {}

    // Prompts the user and reads a line of input and returns it as a record with
	// the command and its arguments. If EOF is reached (i.e., there are nothing to
	// read), an error occurs or the user types "exit", the program exits. If there
	// are no arguments, the args array is empty.
	//
	// Example:
	//
	//   > insert "no quiero acordarme" --> returns UserInput("insert", ["no", "quiero", "acordarme"])
	//	 > delete                       --> returns UserInput("delete", [])
	//
	private UserCommand promptUser() {
		while (true) {
            System.out.print("> ");
            try {
                String line = in.readLine();
				if (line == null) System.exit(0);
				if (line.equals("exit")) exit();
				if (line.isBlank()) continue;
				String[] parts = line.split("\\s+");
				return new UserCommand(parts[0], Arrays.copyOfRange(parts, 1, parts.length));
            } catch (IOException e) {
                System.out.println("Error reading input");
				System.exit(2);
			}
		}
    }

    private boolean checkArguments(String[] args, int expected, String syntax) {
        if (args.length != expected) {
            System.out.println("Invalid number of arguments => " + syntax);
            return false;
        }
        return true;
    }

	private void exit() {
		System.out.println("Goodbye!");
		System.exit(0);
	}	

	private void drawLogo() {
		System.out.println(LOGO);
	}

	private void showHelp() {
		System.out.println(HELP);
	}

	private static final String LOGO = """

			███╗   ███╗ █████╗  ██████╗████████╗███████╗██╗  ██╗
			████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝╚██╗██╔╝
			██╔████╔██║███████║██║        ██║   █████╗   ╚███╔╝ 
			██║╚██╔╝██║██╔══██║██║        ██║   ██╔══╝   ██╔██╗ 
			██║ ╚═╝ ██║██║  ██║╚██████╗   ██║   ███████╗██╔╝ ██╗
			╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝
			""";

	private static final String HELP = """
			┌──────────────────────┬─────────────────────────────────────────────┐
			│ open <file>          │                                             │
			│ insert <text>        │ append text to the end                      │
			│ delete               │ delete the last word                        │
			│ replace <a> <b>      │ replace <a> with <b> in the whole document  │
			├──────────────────────┼─────────────────────────────────────────────┤
			│ record <macro>       │ start recording a macro                     │
			│ stop                 │ stop recording                              │
			│ execute <macro>      │ execute the specified macro                 │
			├──────────────────────┼─────────────────────────────────────────────┤
			│ help                 │                                             │
			│ exit                 │                                             │
			└──────────────────────┴─────────────────────────────────────────────┘
			""";
}
