import java.text.ParseException;

class CommandHandler {
    Database database;

    //initialize database
    CommandHandler(Database database) {
        this.database = database;
    }

    //call run function in Command.java
    void run(Command command) throws ParseException {
        command.run(database);
    }
}
