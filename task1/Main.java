package task1;

public class Main {
    public static void main(String[] args) {
        ValidBracketsSequence validBracketsSequence = new ValidBracketsSequence();
        int bracketsSequence = validBracketsSequence.countValidBracketsSequence
                (validBracketsSequence.getNumber());
        System.out.println("Number of valid brackets sequence = " + bracketsSequence);
    }
}
