package by.zhuk.bankomat;

import java.util.List;

class Service {

    private Persistence persistence;
    private List<Account> accounts;
    private Recycling recycling;

    Service(Persistence persistence, Recycling recycling) throws Exception {
        this.persistence = persistence;
        this.recycling = recycling;
        this.accounts = persistence.readAccounts();
    }

    Integer getBalance(Account account) {
        return account.getBalance();
    }

    void withdraw(Account account, Integer value) throws Exception {
        if (account.checkForWithdraw(value)) {
            if (recycling.checkForWithdraw(value)) {
                account.withdraw(value);
                recycling.withdraw(value);
            } else {
                throw new Exception("Not enough money in an ATM");
            }
        } else {
            throw new Exception("Insufficient funds");
        }
    }

    void replenish(Account account, Integer value) throws Exception {
        if (value <= recycling.MAXIMUM_UPLOAD) {
            account.replenish(value);
            recycling.replenish(value);
        } else {
            throw new Exception("Too much money replenished");
        }
    }

        Account getAccount(String cardNumber, Integer pinCode) throws Exception {
        return accounts.stream()
                .filter(acc -> acc.checkRequisites(cardNumber, pinCode))
                .findFirst()
                .orElseThrow(() -> new Exception("Cards with such details do not exist"));
    }

    void saveAccounts() throws Exception {
        persistence.writeAccounts(accounts);
    }

}
