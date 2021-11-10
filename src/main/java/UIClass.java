import java.sql.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIClass extends Account {

    //initialization and instances
    UserDAO userDAO = new UserDAO();
    user User = new user();
    AccountDAO accountDAO = new AccountDAO();
    PreparedStatement stmt = null;
    //main page will not accept invalid input returns an int 1-3
    public int mainPage(Scanner scanner) {
        System.out.println("Welcome to JBank! Please Login or Create An Account.");
        System.out.println("Select:");
        int option =0;
        do {
            try{System.out.println("1. Create an account.");
            System.out.println("2. Login.");
            System.out.println("3. Exit.");
            System.out.println("Please enter a correct option.");
            option = scanner.nextInt();}catch(InputMismatchException e){
            System.out.println("Please enter valid input.");
            scanner.next();
        }
        } while (option < 1 || option > 3);
        return option;
    }

    //Account creation and stored into Account class as string array
    public int accountCreation(Scanner scanner){
        System.out.println("Account creation.");
        System.out.println("if you would like to go back please enter: 9");
       System.out.println("All other option will continue to the next screen");
     try{ if  (scanner.nextInt() == 9) {
           return 2;}}catch (InputMismatchException e){scanner.next();}
            System.out.println("Please enter a User Name. Please do not use spaces.");
        String username = scanner.next();
            User.setUsername(username);
        System.out.println("Please enter a password. Please do not use spaces.");
        String pass = scanner.next();
        User.setPassword(pass);
        userDAO.create(User);

        String[] loginCred = {username, pass};
        setAccountInfo(loginCred);
        System.out.println("Your login information is " + Arrays.toString(loginCred));
        return 1;

    }

    //basic array equality checker
    public static boolean equalityCheck(String[] Username, String[] password) {
        if (Arrays.equals(Username, password)) {
            return true;
        } else return false;
    }

    //login screen to validate credentials
    public int loginValidate(Scanner scanner) {
        System.out.println("JBank Login.");
        System.out.println("please enter your User Name.");
        String user = scanner.next();
        System.out.println("please enter your Password.");
        String pass = scanner.next();
        String[] logCred = {user, pass};
        if (equalityCheck(logCred, getAccountInfo())) {
            System.out.println("login Successful");
            return 1;
        } else {
            System.out.println("Incorrect login information, please try again.");
            return 2;
        }
    }

    //deposit UI calls deposit method
    public void depositUI(Scanner scanner) {
        System.out.println("please enter Deposit amount: ");
        double amount = scanner.nextDouble();
        deposit(amount);

    }
    //withdraw UI calls deposit method
    public void withdrawUI(Scanner scanner) {
        System.out.println("Please enter Withdrawal amount:");
        double amount = scanner.nextDouble();
        withdraw(amount);
    }

    //exit method
    public void exit(Scanner scanner) {
        System.out.println("thank you for your business, goodbye!");
    }
    //transaction UI switch case to call correct method
    public void transactionsUI(Scanner scanner) {
    int selection = 0;
        do {
            try{
            System.out.println("Please select your banking option:");
            System.out.println("1. Check Balance.");
            System.out.println("2. Withdraw Cash.");
            System.out.println("3. Deposit Cash.");
            System.out.println("4. Exit.");
            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                   // accountDAO.getAccountBalance();
                    checkBalance();
                    break;
                case 2:
                    withdrawUI(scanner);
                    break;
                case 3:
                    depositUI(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for your business!");
                    break;
                default:
                    System.out.println("Enter valid input");
                    break;
            }}catch(InputMismatchException e){
                    System.out.println("Please enter valid input.");
                    scanner.next();
                }
        }
        while (selection != 4);


    }
}
