package test.fileHandlingTest;

import main.dusza.fileHandling.TxtParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TxtParserTest {
    @BeforeEach
    public void beforeTest(){
        TxtParser.isRunningTest=true;
    }
    @Test
        public void txtParserTest_withInvalidFilePath_shouldCatchExpression(){
        String invalidPath = "wrongFilePath.txt";
        TxtParser.isRunningTest=false;

        assertDoesNotThrow(()->{
            var result = TxtParser.parseTxt(invalidPath);
            assertTrue(result.isEmpty());
        });

        }
    @AfterEach
    public void afterTest(){
        TxtParser.isRunningTest=false;
    }
}
