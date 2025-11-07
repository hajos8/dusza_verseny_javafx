package test.setupTest;

import main.dusza.setup.Setup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class setupTest {

    @BeforeEach
    public void beforeTest() {
        Setup.isRunningTest=true;


        ArrayList<String> data = new ArrayList<String>();

        data.add("uj kartya;Aragorn;2;5;tuz");
        data.add("uj kartya;Sadan;2;4;levego");
        data.add("uj kartya;Corky;2;4;fold");
        data.add("uj kartya;Kira;2;7;levego");
        data.add("uj kartya;Eowyn;2;5;viz");
        data.add("uj kartya;ObiWan;2;2;fold");
        data.add("uj kartya;Tul'Arak;2;4;fold");
        data.add("");
        data.add("uj vezer;Darth ObiWan;ObiWan;sebzes");
        data.add("");
        data.add("uj kazamata;egyszeru;Teszt1a Kazamata;Sadan;eletero");
        data.add("uj kazamata;egyszeru;Teszt1b Kazamata;Aragorn;sebzes");
        data.add("uj kazamata;kis;Teszt2a Kazamata;Aragorn,Eowyn,ObiWan;Darth ObiWan;eletero");
        data.add("uj kazamata;kis;Teszt2b Kazamata;Sadan,Eowyn,Kira;Darth ObiWan;sebzes");
        data.add("uj kazamata;nagy;Teszt3 Kazamata;Aragorn,Eowyn,ObiWan,Kira,Tul'Arak;Darth ObiWan");
        data.add("");
        data.add("uj jatekos");
        data.add("");
        data.add("felvetel gyujtemenybe;Aragorn");
        data.add("felvetel gyujtemenybe;Sadan");
        data.add("felvetel gyujtemenybe;Corky");
        data.add("felvetel gyujtemenybe;Kira");
        data.add("");
        data.add("uj pakli;Corky,Kira");

        Setup.testData=data;

    }
    @Test
    public void testSetup(){
        Setup.generateWorld("setupTestWorldString");
    }

    @AfterEach
    public void afterTest(){
        Setup.isRunningTest=false;
    }

}
