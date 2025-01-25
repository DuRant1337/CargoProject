package implementation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerImplTest {



    @Test
    void testCustomerCreationWithValidName() { //Test ob Konstruktor den Namen richtig setzt
        CustomerImpl customer = new CustomerImpl("Pavel A"); //erstellt CustomerImpl-Objekt mit meinem Namen
        assertEquals("Pavel A", customer.getName()); //guckt ob der Name des erstellten Objekts, dem übergebenen Namen entspricht
    }

    @Test
    void testSetNameWithValidName() { // Test ob Set namen richtig setzt nach dem das Objekt richtig erstellt wurde
        CustomerImpl customer = new CustomerImpl("Pavel A");
        customer.setName("Pavel A");
        assertEquals("Pavel A", customer.getName());
    }

}
