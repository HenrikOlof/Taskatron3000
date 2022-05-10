package helpers;

import java.util.Scanner;

public class UserInteractionHelper {
    Scanner input;

    public UserInteractionHelper(Scanner input) {
        this.input = input;
    }

    public String[] getNameAndDescriptionFromUser() {
        System.out.println("Please enter the name of the new Task:");
        String name = input.nextLine();
        System.out.println("Please type the description of the new Task:");
        String description = input.nextLine();
        String[] nameAndDesc = {name, description};
        return nameAndDesc;
    }

}
