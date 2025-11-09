package test.fileHandlingTest;

import main.dusza.fileHandling.BattleLogSaver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BattleLogSaverTest {
    /*@BeforeEach
        public void beforeTest() {
            BattleLogSaver.isRunningTest=true;

        }
*/
    @Test
        public void testSaveBattleLog_withInvalidPath_shouldCatchException() {
            String badFilename = "WrongBattleLogSaverTest.txt";

            BattleLogSaver.isRunningTest=false;

            assertDoesNotThrow(()-> BattleLogSaver.saveBattleLog(badFilename));
        }

    /*@AfterEach
        public void afterTest() {
           BattleLogSaver.isRunningTest=false;

        }*/

}
