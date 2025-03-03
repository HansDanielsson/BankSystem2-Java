/**
 * Klass som definierar ett bankkonto.
 * Modellen ärver egenskaper från Account
 * Begränsning på ett fritt uttag/år
 * @author Hans Danielsson, handan-2
 */
package handan;

public class SavingsAccount extends Account {

  // Variabler för enskilt sparkonto
  private boolean firstFree; // Första uttaget är fritt, ingen uttagsränta.
  private double withdrawRate; // Uttagsränta, max 100%: 2.0 är 2% av uttaget belopp

  public SavingsAccount() {
    this(0, 2.4, 2.0, false);
  }

  public SavingsAccount(int theBalance, double theInterestRate, double theWithdrawRate, boolean addNumber) {
    super("Sparkonto", theBalance, theInterestRate, addNumber);
    firstFree = true;
    withdrawRate = theWithdrawRate;
  }

  public double getWithdrawRate() {
    return withdrawRate;
  }

  // set- get- rutiner, används inte?
  public boolean isFirstFree() {
    return firstFree;
  }

  public void setFirstFree(boolean theFirstFree) {
    firstFree = theFirstFree;
  }

  public void setWithdrawRate(double theWithdrawRate) {
    this.withdrawRate = theWithdrawRate;
  }

  @Override
  public boolean withdraw(int theAmount) {
    if (!firstFree) {
      theAmount += (int) (theAmount * withdrawRate / 100.0);
    }
    firstFree = false;
    return super.withdraw(theAmount);
  }
}