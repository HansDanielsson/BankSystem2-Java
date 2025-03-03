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

  public CreditAccount(int theBalance, double theInterestRate, int theCreditLimit, double theDeptInterest,
      boolean addNumber) {
    super("Kreditkonto", theBalance, theInterestRate, addNumber);
    creditLimit = BigDecimal.valueOf(theCreditLimit);
    deptInterest = BigDecimal.valueOf(theDeptInterest);
  }

  // set- get- rutiner, används inte?
  public BigDecimal getCreditLimit() {
    return creditLimit;
  }

  public BigDecimal getDeptInterest() {
    return deptInterest;
  }

  public void setCreditLimit(BigDecimal creditLimit) {
    this.creditLimit = creditLimit;
  }

  public void setDeptInterest(BigDecimal deptInterest) {
    this.deptInterest = deptInterest;
  }

  @Override
  public String toString() {
    int balanceValue = super.getBalance();
    if (balanceValue < 0) {
      return super.makeAccountInfo(balanceValue, deptInterest.doubleValue());
    }
    return super.toString();
  }
}