import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Password;

public class TestPassword {
    private Password testPassword1;
    private Password testPassword2; 
    private Password testPassword3; 
    private Password testPassword4; 
    private Password testPassword5; 

       @BeforeEach
    void runBefore() {
        testPassword1 = new Password("43123467897!Abc");
        testPassword2 = new Password("43123467897Abc");
        testPassword3 = new Password("431234678971A");
        testPassword4 = new Password("4312346789731");
        testPassword5 = new Password("12789");
    }

    @Test
    void testAccount() {
        assertEquals("43123467897!Abc", testPassword1.getPassword());
        assertEquals(0, testPassword1.getNumReqMet());
    }

    @Test
    void meetsRequirements() {
        assertEquals(5,testPassword1.meetsRequirements());
        assertEquals(4,testPassword2.meetsRequirements());
        assertEquals(3,testPassword3.meetsRequirements());
        assertEquals(2,testPassword4.meetsRequirements());
        assertEquals(1,testPassword5.meetsRequirements());
    }   

}
