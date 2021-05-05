import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;

    //create scanner
    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    //creates instance if there is not already one
    //static makes it so there is only one InputReader
    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    //get user input
    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;
        //try catch to make sure we do not get errors
        try {
            //keep asking for input
            while (keyboard.hasNext()) {
                //increment lineNumber and get line of input
                lineNumber++;
                line = keyboard.nextLine();
                //depending on what it starts with, execute a command
                if (line.startsWith("PRINT ")) {
                    //call makePrintCommand and add it to arraylist of commands
                    commands.add(makePrintCommand(line));
                } else if (line.startsWith("BEGIN_")) {
                    //call makeBlockCommand and add to arraylist of commands
                    commands.add(makeBlockCommand(line));
                } else if (line.equals("FINISH")) { //exit if FINISH is inputted
                    break;
                } else if (!line.equals("")) { //if input is not empty string
                    //print line and throw an exception
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
            }
        } catch (BadCommandException e) { //the exception we throw for invalid input is caught
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        //return all the user input
        return commands;
    }

    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));

        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            //if input for this block is over
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                //gets list of string using split
                String [] tokens = line.split(" ", 3);
                //valid length must be three because format is "name = value"
                //second token can only be one char e.g '=', '>', '<'
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                //add it to HashMap of tags
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    private Command makePrintCommand(String line) {
        String[] tokens = line.split(" ", 5);
        //print commands have to be 4
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        //return PrintCommand object that is created using tokens
        return new PrintCommand(tokens);
    }
}