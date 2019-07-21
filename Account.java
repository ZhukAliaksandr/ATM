package by.zhuk.bankomat;

class Account {

    private String cardNumber;
    private Integer pinCode;
    private Integer amount;

    Account(String cardNumber, Integer pinCode, Integer amount) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.amount = amount;
    }

    Boolean checkRequisites(String cardNumber, Integer pinCode) {
        return this.cardNumber.equals(cardNumber) && this.pinCode.equals(pinCode);
      }

    void withdraw(Integer amount) throws Exception {
        if (this.amount < amount) {
            throw new Exception("Insufficient funds");
        } else {
            this.amount = this.amount - amount;
        }
    }

    Boolean checkForWithdraw(Integer amount) {
        return this.amount >= amount;
    }

    void replenish(Integer amount) {
        this.amount = this.amount + amount;
    }

    Integer getBalance() {
        return amount;
    }

    String toLine() {
        return cardNumber + " " + pinCode + " " + amount;
    }
}
