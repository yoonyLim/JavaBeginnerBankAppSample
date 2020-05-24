package bank;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Account[] accountArray = new Account[100];
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("===============================================================================");
            System.out.println("1. CREATE AN ACCOUNT | 2. ACCOUNT LIST | 3. DEPOSIT | 4. WITHDRAWAL | 5. CANCEL");
            System.out.println("===============================================================================");
            System.out.print("OPTION: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> createAccount();
                case 2 -> accountList();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> run = false;
                default -> {
                    System.out.println("\n-!-!-!-INVALID OPTION-!-!-!-\n-!-!-!-CANCELLING THE SERVICE-!-!-!-\n");
                    run = false;
                }
            }
        }
        System.out.println("\n-------SERVICE ENDED-------\n\nTHANK YOU FOR USING OUR SERVICE\n        SEE YOU AGAIN!\n===============================");
    }

    private static void createAccount() {
        System.out.print("YOUR ASSIGNED ACCOUNT NUMBER: ");
        int check = 0;
        String ano;
        for (int i = 0; i < 100; i++) { //assign the user a number from 1 to 100 in order
            if (accountArray[i] == null) {
                check = i;
                break;
            }
        }
        if (check < 9) {
            ano = "00" + (check + 1); //the first user will have '001' for the account number
        } else if (check < 99) {
            ano = "0" + (check + 1);
        } else {
            ano = "100";
        }
        System.out.println(ano);
        System.out.print("ACCOUNT OWNER NAME: ");
        String owner = scanner.next();
        System.out.print("ACTIVATION DEPOSIT AMOUNT: $");
        double balance = scanner.nextDouble();
        Account account = new Account(ano, owner, balance);
        accountArray[check] = account; //save the new account object to the objects array
        System.out.println("\n-------ACCOUNT CREATED SUCCESSFULLY-------\n");
    }

    private static void accountList() {
        System.out.println("----------------");
        System.out.println("LIST OF ACCOUNTS");
        System.out.println("----------------\n");
        for (int i = 0; i < 100; i++) {
            if (accountArray[i] != null) {
                System.out.println("ACCOUNT NUMBER: " + accountArray[i].getAno() + " | ACCOUNT OWNER: " + accountArray[i].getOwner() + " | ACCOUNT BALANCE: $" + accountArray[i].getBalance());
            } else if (accountArray[0] == null) { //if there is no created account
                System.out.println("-!-!-!-NO ACCOUNT TO SHOW YET-!-!-!-");
                break;
            } else { //if there is no more account to print out
                break;
            }
        }
        System.out.println("\n-------All THE ACCOUNTS PRINTED-------\n");
    }

    private static void deposit() {
        System.out.print("ENTER YOUR ACCOUNT NUMBER: ");
        String ano = scanner.next();
        if (findAccount(ano) != null) { //use function 'findAccount()'
            System.out.print("ENTER YOUR DEPOSIT AMOUNT: $");
            double temp = scanner.nextDouble();
            while (temp < 0) { //prevent negative input
                    System.out.println("\n-!-!-!-INVALID DEPOSIT AMOUNT-!-!-!-\n");
                    System.out.print("ENTER A VALID DEPOSIT AMOUNT: $");
                    temp = scanner.nextDouble();
            }
            double deposit = temp;
            findAccount(ano).setBalance(findAccount(ano).getBalance() + deposit);
            System.out.println("\n-------DEPOSIT SUCCESSFUL-------\n");
        }
    }

    private static void withdraw() {
        System.out.print("ENTER YOUR ACCOUNT NUMBER: ");
        String ano = scanner.next();
        if (null != findAccount(ano)) {
            System.out.print("ENTER YOUR WITHDRAWAL AMOUNT: $");
            double temp = scanner.nextInt();
            while (temp < 0) {
                    System.out.println("\n-!-!-!-INVALID DEPOSIT AMOUNT-!-!-!-\n");
                    System.out.print("ENTER A VALID WITHDRAWAL AMOUNT: $");
                    temp = scanner.nextDouble();
            }
            double withdrawal = temp;
            findAccount(ano).setBalance(findAccount(ano).getBalance() - withdrawal);
            System.out.println("\n-------WITHDRAWAL SUCCESSFUL-------\n");
        }
    }

    private static Account findAccount(String ano) {
        try {
            for (Account account : accountArray) {
                if (ano.equals(account.getAno())) {
                    return account;
                }
            }
        } catch (NullPointerException e) { //if there is no matching account object so that it creates NullPointerException
            System.out.println("\n-!-!-!-THERE IS NO SUCH ACCOUNT-!-!-!-\n");
        }
        return null;
    }
}
