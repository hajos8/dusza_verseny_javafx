package test.fileHandlingTest;

import main.dusza.fileHandling.TxtParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TxtParserTest {
    @BeforeEach
    public void beforeTest(){
        TxtParser.isRunningTest=true;
    }
    @Test
        public void parseTest(){
        TxtParser.parseTxt("parseTest.txt");
        }
    @AfterEach
    public void afterTest(){
        TxtParser.isRunningTest=false;
    }
}
