/**
 * Klass som definierar en kund.
 * @author Hans Danielsson, handan-2
 */
package handan;

import java.util.ArrayList;
import java.util.List;

public class Customer extends SavingsAccount {
  // Privata variabler till kund.
  private String customerName;
  private String customerSurname;
  private String personalNumber;
  private List<Account> accounts; // Lista med konton

  // Konstruktor för en ny kund
  public Customer() {
    this("UnknownA", "UnknownB", "UnknownC");
  }

  // Används när en ny kund skapas med namn, efternamn och personnummer.
  public Customer(String inCustomerName, String inCustomerSurname, String inPersonalNumber) {
    customerName = inCustomerName;
    customerSurname = inCustomerSurname;
    personalNumber = inPersonalNumber;
    accounts = null;
  }

  /**
   * Ändrar på kunden. Endast tillåtet att ändra på sin egen post.
   *
   * @param inName
   * @param inSureName
   * @return om något värde har ändrats
   */
  public boolean changeCustomerName(String inName, String inSureName) {
    boolean result = false;
    // Byter endast om det är någon information
    if (!inName.isEmpty()) {
      customerName = inName;
      result = true;
    }
    if (!inSureName.isEmpty()) {
      customerSurname = inSureName;
      result = true;
    }
    return result;
  }

  /**
   * Rutin som ger ut den privata variabeln accounts
   *
   * @return pekare till listan
   */
  public List<Account> getAccounts() {
    return accounts;
  }

  /**
   * Hämtar personnummer
   *
   * @return personalNumber
   */
  public String getPersonalNumber() {
    return personalNumber;
  }

  /**
   * Rutin som sätter den privata pekaren för konton till en ny lista
   */
  public void setAccounts() {
    accounts = new ArrayList<>();
  }

  @Override
  public String toString() {
    return personalNumber + " " + customerName + " " + customerSurname;
  }
}