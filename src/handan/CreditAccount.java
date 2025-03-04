/**
 * Klass som definierar ett kreditkonto.
 * Modellen ärver egenskaper från Account
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.math.BigDecimal;

public class CreditAccount extends Account {

  // Variabler för enskilt kreditkonto
  private BigDecimal creditLimit; // Kreditgräns (5000 kr)
  private BigDecimal deptInterest; // Skuldränta om negativt saldo.

  public CreditAccount() {
    this(0, 1.1, 5000, 5.0, false);
  }

  /**
   * Skapa ett Kreditkonto
   *
   * @param theBalance      , Startkapital
   * @param theInterestRate , Räntan 1.1% på insatta pengar > 0
   * @param theCreditLimit  , Kreditgräns på 5000, kan ta ut pengar till belopp
   *                        -5000 kr
   * @param theDeptInterest , Skuldränta 5% om saldo < 0
   * @param addNumber       , Öka kontonummer med 1
   */
  public CreditAccount(int theBalance, double theInterestRate, int theCreditLimit, double theDeptInterest,
      boolean addNumber) {
    super("Kreditkonto", theBalance, theInterestRate, addNumber);
    creditLimit = BigDecimal.valueOf(theCreditLimit);
    deptInterest = BigDecimal.valueOf(theDeptInterest);
  }

  @Override
  public String toString() {
    int balanceValue = getBalance();
    if (balanceValue < 0) {
      return makeAccountInfo(balanceValue, deptInterest.doubleValue());
    }
    return super.toString();
  }

  @Override
  public boolean withdraw(int theAmount) {
    boolean result = true;
    int currentBalance = getBalance();
    if (theAmount < 0) {
      result = false;
    } else if (currentBalance - theAmount + creditLimit.intValue() > 0) {
      // Ta bort theAmount från balance
      result = balanceSubtract(theAmount);
    } else {
      result = false;
    }
    return result;
  }
}