package test;

import dao.*;
import model.*;
import singleton.MainDispatcher;
import view.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        MainDispatcher.getInstance().callAction("START", "DO_CONTROL", null);
    }
}
