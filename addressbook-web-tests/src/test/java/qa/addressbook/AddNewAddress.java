package qa.addressbook;

import org.testng.annotations.Test;

public class AddNewAddress extends TestBase {


  @Test
  public void testsAddNewAddress() {

    initNewContact();
    fillNewContact(new NewContact("Viacheslav",
                                  "Pykhydko",
                                  "Slava17",
                                  "3D Printers",
                                  "Printers Ltd.",
                                  "Ukraine, Kiev",
                                  "044-11-22-3-33",
                                  "+308-63-077-77-77",
                                  "044-11-22-444",
                                  "slava17puh@gmail.com"));
    submitNewContact();
  }

}
