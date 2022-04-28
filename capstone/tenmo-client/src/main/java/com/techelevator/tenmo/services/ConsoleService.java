package com.techelevator.tenmo.services;


import com.techelevator.tenmo.App;
import com.techelevator.tenmo.model.AccountHolder;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printTransferListBanner() {
        System.out.println("-------------------------------------------");
        System.out.println("User");
        System.out.println("ID          Name");
        System.out.println("-------------------------------------------");
    }


    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void listContacts(AccountHolder[] contacts) {
        printTransferListBanner();
        if (contacts.length > 0) {
            for (AccountHolder accountHolder : contacts) {
                System.out.println(accountHolder.getTransferContacts());
            }
        } else {
            System.out.println("You dont have any contacts");
        }
        System.out.println("-------------------------------------------");
        System.out.println();
    }

    public void printTransferList(Transfer[] transfers,int userId, AccountHolderService accountHolderService){
        System.out.println("-------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To                 Amount");
        System.out.println("-------------------------------------------");
        for (Transfer transfer: transfers) {
            System.out.println(transfer.viewTransferToString(userId,accountHolderService));
        }
    }



    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForTransferAmount() {
        String amountToTransfer = "Enter amount:";
        return promptForBigDecimal(amountToTransfer);
    }

    public int promptForUserIdToSendMoneyTo() {
        String enterId = "Enter ID of user you are sending to (0 to cancel):";
        return promptForInt(enterId);
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public String printBalance(int userId, AccountHolderService accountHolderService) {
        return accountHolderService.getAccountHolderByUserId(userId).getHolderBalance();

    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

}
