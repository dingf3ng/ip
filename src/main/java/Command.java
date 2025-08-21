public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    UNKNOWN("");

    private final String commandWord;

    Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public static Command fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return UNKNOWN;
        }
        
        String command = input.trim().split("\\s+")[0].toLowerCase();
        
        for (Command c : Command.values()) {
            if (c.commandWord.equals(command)) {
                return c;
            }
        }
        return UNKNOWN;
    }
    
    public boolean hasParameters() {
        return this == MARK || this == UNMARK || this == DELETE || 
               this == TODO || this == DEADLINE || this == EVENT;
    }
}