import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {
            //create InputReader object and call getCommands() to read user input
            InputReader inputReader = InputReader.getInstance();
            ArrayList<Command> commands = inputReader.getCommands();
            Iterator<Command> currentCommand = commands.iterator();

            CommandHandler commandHandler = new CommandHandler(new Database());
            //go through the commands
            while (currentCommand.hasNext()) {
                //call the command handler
                commandHandler.run(currentCommand.next());
            }
        }
        //catch the two exceptions that are thrown when something goes wrong
        catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (BadCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
