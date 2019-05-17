package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The ValidBracketsSequence class has methods for getting valid sequence
 * of brackets that determines by the number of opened and closed brackets
 * from user's input.
 */
public class ValidBracketsSequence {

    /**
     * Method allows to determine the valid brackets sequence for given
     * brackets number. The main algorithm is based on the formula for
     * calculating Catalan sequence. The numbers of this sequence equal
     * for the sequence of valid brackets.
     *
     * @param bracketsNumber - quantity of brackets
     * @return - number of valid brackets sequence
     */
    public int countValidBracketsSequence(int bracketsNumber) {
        List<Integer> validBracketsList = new ArrayList<>();

        if (bracketsNumber == 0) {
            return 0;
        } else {
            validBracketsList.add(1);
        }

        int sequenceCount;
        for (int i = 1; i <= bracketsNumber; i++) {
            sequenceCount = 0;
            for (int j = 0; j < i; j++) {
                sequenceCount += validBracketsList.get(j) * validBracketsList.get(i - 1 - j);
            }
            validBracketsList.add(sequenceCount);
        }
        return validBracketsList.get(bracketsNumber);
    }

    /**
     * Get number of opened and closed brackets from user
     * using keyboard.
     * @return number of brackets
     */
    public int getNumber() {
        int enteredNumber;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the count of braces: ");
        while (true) {
            try {
                enteredNumber = Integer.parseInt(scanner.nextLine());
                if (enteredNumber < 0) {
                    throw new NegativeNumberException();
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Please, enter integer number: ");
            } catch (NegativeNumberException ex) {
                System.out.print("Please, enter positive number: ");
            }
        }
        return enteredNumber;
    }
}
