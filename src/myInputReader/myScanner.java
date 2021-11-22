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

//  Method that asks a user for input an integer number by keyboard.
//  It catches InputMismatchException, thus it cycles until an integer number is entered by the user.
//  Parameters:
//    - message: the output message to print before asking the user for enter the number.
//  Returns: the integer value requested.
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
                input.nextLine();
                inCatch = true;
            }
        } while (inCatch);

        return i;
    }

//  Method that asks a user for input a double number by keyboard.
//  It catches InputMismatchException, thus it cycles until a double number is entered by the user.
//  Parameters:
//    - message: the output message to print before asking the user for enter the number.
//  Returns: the double value requested.
    public double nextDouble(String message) {
        boolean inCatch;
        double d = -1;

        do {
            inCatch = false;
            System.out.print(message);
            try {
                d = input.nextDouble();
            } catch (InputMismatchException e) {
                System.err.println(e);
                input.nextLine();
                inCatch = true;
            }
        } while (inCatch);

        return d;
    }

//  Method that asks a user for input a byte number by keyboard.
//  It catches InputMismatchException, thus it cycles until a byte number is entered by the user.
//  Parameters:
//    - message: the output message to print before asking the user for enter the number.
//  Returns: the byte value requested.
    public byte nextByte(String message) {
        boolean inCatch;
        byte b = -1;

        do {
            inCatch = false;
            System.out.print(message);
            try {
                b = input.nextByte();
            } catch (InputMismatchException e) {
                System.err.println(e);
                input.nextLine();
                inCatch = true;
            }
        } while (inCatch);

        return b;
    }

//  Parameters:
//    - message: the string to output before getting the next line
//  Returns: the classic nextLine of the Scanner
    public String nextLine(String message) {
        System.out.print(message);
        String str = input.nextLine();
        return str;
    }

//  Parameters:
//    - message: the string to output before getting the next complete token from the Scanner
//  Returns: the classic next of the Scanner
    public String next(String message) {
        System.out.print(message);
        String str = input.next();
        return str;
    }

}
