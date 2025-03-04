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

  /**
   * Skapa ett Sparkonto
   *
   * @param theBalance      , Startkapital
   * @param theInterestRate , Ränta 2.4% på insatta pengar > 0
   * @param theWithdrawRate , Uttagsränta 2% på beloppet
   * @param addNumber       , Öka kontonummer med 1
   */
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
    withdrawRate = theWithdrawRate;
  }

  @Override
  public boolean withdraw(int theAmount) {
    boolean result = true;
    if (theAmount < 0) {
      result = false;
    } else {
      int currentBalance = getBalance();
      if (!firstFree) {
        theAmount += (int) (theAmount * withdrawRate / 100.0);
      }
      firstFree = false;
      if (theAmount <= currentBalance) {
        result = balanceSubtract(theAmount);
      } else {
        result = false;
      }
    }
    return result;
  }
}