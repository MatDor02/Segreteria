package myInputReader;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class myScanner {

    Scanner input;

    public myScanner(InputStream source) {
        input = new Scanner(source);
    }

    public myScanner(String source) {
        input = new Scanner(source);
    }

//  parameters:
//    - message: the output message to print before asking for the user input
//  returns: the int value requested
    public int nextInt(String message) {
        boolean inCatch;
        int i = -1;

        do {
            inCatch = false;
            System.out.print(message);
            try {
                i = input.nextInt();
            } catch (InputMismatchException e) {
                System.err.println(e);
                inCatch = true;
            }
        } while (inCatch);

        return i;
    }
}
