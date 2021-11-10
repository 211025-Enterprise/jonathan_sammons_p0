import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UIClass UI = new UIClass();
        SQLConnection u = new SQLConnection();
        int selection;
        u.getInstance();
       do {
            selection = UI.mainPage(scanner);
            switch(selection) {
                case 1:
                    int i = UI.accountCreation(scanner);
                    if (i == 2) {
                        break;
                    }
                    break;
                case 2:
                    int o = UI.loginValidate(scanner);
                    if (o == 1) {
                        UI.transactionsUI(scanner);
                    }
                    break;
                case 3:
                    UI.exit(scanner);
            }


        }while(selection != 3);



    }
}
//        i.mainPage(scanner);
//        i.accountCreation(scanner);
//        i.loginValidate(scanner);



