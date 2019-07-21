package by.zhuk.bankomat;

class Recycling {

    final Integer MAXIMUM_UPLOAD = 1_000_000;
    private Integer amount;

    Recycling(Integer amount) {
        this.amount = amount;
    }

    void withdraw(Integer amount) throws Exception {
        if (this.amount < amount) {
            throw new Exception("Not enough money in an ATM");
        } else {
            this.amount = this.amount - amount;
        }
    }

    void replenish(Integer amount) throws Exception {
        if (this.amount > MAXIMUM_UPLOAD) {
            throw new Exception("Too much money replenished");
        } else {
            this.amount = this.amount + amount;
        }

    }

    Boolean checkForWithdraw(Integer amount) {
        return this.amount.compareTo(amount) > 0;
    }

}
