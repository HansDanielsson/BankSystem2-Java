/**
 * Klass som definierar ett bankkonto.
 * @author Hans Danielsson, handan-2
 */
package handan;

// Importsatser
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

// Klassdeklarationer för Kontot
public abstract class Account {

  // Variabler som är gemensamt för alla konton
  private static int lastAssignedNumber = 1000;
  private static String accountName = "Sparkonto";

  // Variabler för enskilda konton
  private int accountNumber; // 1001, 1002, 1003, 1004 osv.
  private String accountType;
  private BigDecimal balance;
  private BigDecimal interestRate;

  // Default Konstruktor för ett nytt bankkonto
  public Account() {
    this(accountName, 0, 2.4, false);
  }

  public Account(String theAccountType, int theBalance, double theInterestRate, boolean addNumber) {
    if (addNumber) {
      lastAssignedNumber++; // Ska bara räknas upp med 1 ibland.
    }
    accountNumber = lastAssignedNumber;
    accountType = theAccountType;
    balance = BigDecimal.valueOf(theBalance);
    interestRate = BigDecimal.valueOf(theInterestRate);
  }

  /**
   * Rutin som tar bort belopper (theAmount) från saldo (balance) Använder
   * BigDecimal
   *
   * @param theAmount
   * @return om det gick bra
   */
  public boolean balanceSubtract(int theAmount) {
    boolean result = true;
    try {
      balance = balance.subtract(BigDecimal.valueOf(theAmount));
    } catch (Exception e) {
      result = false;
    }
    return result;
  }

  /**
   * Rutin som beräknar räntan på BigDecimal-modell enligt saldo*räntesats / 100.0
   * konverterar till double-tal
   *
   * @return x xxx kr
   */
  public String calculateInterest() {
    double numberInterest = balance.multiply(interestRate).divide(BigDecimal.valueOf(100)).doubleValue();
    return NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(numberInterest);
  }

  /**
   * Rutin som sätter in beloppet (theAmount) till saldo (balance) Kontroll har
   * redan utförts på theAmount > 0
   *
   * @param theAmount
   * @return true hela tiden för att theAmount > 0
   */
  public boolean deposit(int theAmount) {
    boolean result = true;
    try {
      balance = balance.add(BigDecimal.valueOf(theAmount));
    } catch (Exception e) {
      result = false;
    }
    return result;
  }

  /**
   * Hämtar kontonummer
   *
   * @return accountNumber
   */
  public int getAccountNumber() {
    return accountNumber;
  }

  public String getAccountType() {
    return accountType;
  }

  public int getBalance() {
    return balance.intValue();
  }

  /**
   * Vid bearbetning av kontot med kontonummer saldo kontotyp.
   *
   * @return "kontonr saldo kontotyp <procent %>"
   */
  public String infoAccount() {
    String balanceStr = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(balance);
    return accountNumber + " " + balanceStr + " " + accountType;
  }

  public String makeAccountInfo(int theBalance, double theInterestRate) {
    String balanceStr = NumberFormat.getCurrencyInstance(Locale.of("SV", "SE")).format(theBalance);
    NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.of("SV", "SE"));
    percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
    String percentStr = percentFormat.format(theInterestRate / 100.0);
    return accountNumber + " " + balanceStr + " " + accountType + " " + percentStr;
  }

  public void setAccountType(String theAccountType) {
    accountType = theAccountType;
  }

  /**
   * Vid utskrift av kontot med kontonummer saldo kontotyp, percent.
   *
   * @return "kontonr saldo kontotyp <procent %>"
   */
  @Override
  public String toString() {
    return makeAccountInfo(balance.intValue(), interestRate.doubleValue());
  }

  /**
   * Rutin som tar bort beloppet (amount) från saldo (balance) belopet ska vara >
   * 0 och att beloppet finns på saldo
   *
   * @param amount
   * @return om beloppet har minskat saldo
   */
  public abstract boolean withdraw(int theAmount);
}