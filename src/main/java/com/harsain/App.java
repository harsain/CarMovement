package com.harsain;

import java.util.*;

/**
 * Car Movement!
 *
 */
public class App 
{
    private static CommandBroker broker = new CommandBroker();
    private static Car car = new Car();

    /**
     * entry point for the application
     * @param args
     */
    public static void main( String[] args ) {
        System.out.println("Welcome to the Car Movement App:");
        System.out.println("------------------------------------");
        System.out.printf("Available Commands: %s\n", Arrays.asList(UserInputEnum.values()));

        try {
            run();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * start the application
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.nextLine();
        // it will keep asking for input till quit command is entered
        while (!inputString.equalsIgnoreCase(UserInputEnum.QUIT.toString())) {

            HashMap userInput = parseUserInput(inputString);
            executeUserInput(userInput);

            inputString = scanner.nextLine();
        }

        // Quiting the program
        System.out.println("Thanks for using Car Movement. See you again.");
    }

    /**
     * executes the user's input
     * based on the command & arguments
     * @param userInput
     */
    public static void executeUserInput(HashMap userInput) {
        try {

            Command command = null;
            UserInputEnum commandName = UserInputEnum.valueOf(userInput.get("command").toString().toUpperCase());

            switch (commandName) {
                case INIT:
                    command = new InitCommand(car);
                    break;
                case FORWARD:
                    command = new ForwardCommand(car);
                    break;
                case TURN_LEFT:
                    command = new TurnLeftCommand(car);
                    break;
                case TURN_RIGHT:
                    command = new TurnRightCommand(car);
                    break;
                case GPS_REPORT:
                    command = new GPSReportCommand(car);
                    break;
                default:
                    break;
            }
            // check if no command was created
            if (command != null) {
                broker.setCommand(command);
                broker.execute((String) userInput.get("arguments"));
            }
        } catch (IllegalArgumentException argumentException) {
            System.out.printf("Invalid input passed. Valid commands are: %s \n", Arrays.asList(UserInputEnum.values()));
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }
    }

    /**
     * parses the user's input into command & arguments
     * @param inputString
     * @return
     */
    private static HashMap parseUserInput(String inputString) {
        HashMap response = new HashMap();

        // parsing user inputs
        ArrayList<String> userInputs = new ArrayList<String>(Arrays.asList(inputString.split(" ")));
        // check if input exists
        if (userInputs.size() > 0) {
            String commandName = userInputs.get(0);
            userInputs.remove(0);
            // get the arguments
            String arguments = "";
            if (userInputs.size() > 0) {
                arguments = userInputs.get(0);
            }
            response.put("command", commandName);
            response.put("arguments", arguments);
        }

        return response;
    }
}
