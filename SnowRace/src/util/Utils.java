package util;

import java.util.Scanner;

public class Utils
{
    public static String askString(Scanner scanner, String prompt)
    {
        String request;

        System.out.println(prompt);

        request = scanner.nextLine();

        if (request == null || request.isBlank())
            request = null;
        else
            request = request.trim();

        return request;
    }

}
