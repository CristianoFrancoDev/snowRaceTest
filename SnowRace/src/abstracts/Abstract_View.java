package abstracts;

import java.util.Scanner;

public abstract class Abstract_View
{
    protected Scanner scanner;

    protected String getInput()
    {
        scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
