package by.zhuk.bankomat;

import java.util.Scanner;

class Controller {

    private Service service;

    Controller(Service service) {
        this.service = service;
    }

    void startConsole() {

        Scanner in = new Scanner(System.in);
        try {
            Account acc = recognizeAccount(in);

            handleCommand(acc, in);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleCommand(Account acc, Scanner in) {
        String command = readCommand(in);

        if (command.startsWith("balance")) {
            Integer balance = service.getBalance(acc);
            System.out.println("Card Balance " + balance);
            handleCommand(acc, in);
        }

        if (command.startsWith("put")) {
            try {
                Integer value = Integer.parseInt(command.split(" ")[1]);
                service.replenish(acc, value);
                System.out.println("Card Balance replenished");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            handleCommand(acc, in);
        }

        if (command.startsWith("take")) {
            try {
                Integer value = Integer.parseInt(command.split(" ")[1]);
                service.withdraw(acc, value);
                System.out.println("Money Issued");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            handleCommand(acc, in);
        }

        if (command.startsWith("exit")) {
            try {
                service.saveAccounts();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Good buy");
        }

    }

    private String readCommand(Scanner in) {
        System.out.println("Please select operation: balance; put <value>; take <value>; exit");
        return in.nextLine();
    }

    private Account recognizeAccount(Scanner in) throws Exception {
        String cardNumber = scanCardNumber(in);
        Integer pinCode = scanPinCode(in);

        return service.getAccount(cardNumber, pinCode);
    }

    private Integer scanPinCode(Scanner in) {
        System.out.println("Please enter PIN");
        return Integer.parseInt(in.nextLine());
    }

    private String scanCardNumber(Scanner in) {
        System.out.println(" Please enter your card number");
        System.out.println("in the format ХХХХ-ХХХХ-ХХХХ-ХХХХ");
        String cardNumber = in.nextLine();
        if (validateCardNumber(cardNumber)) {
            return cardNumber;
        } else {
            System.out.println("Invalid card number format. Try again");
            return scanCardNumber(in);
        }
    }

    private Boolean validateCardNumber(String cardNumber) {
        return cardNumber.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
    }

}