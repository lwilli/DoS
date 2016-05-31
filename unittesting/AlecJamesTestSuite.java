package unittesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ByteCryptoTest.class, FileSystemManagerTest.class, GameStateSerializerTest.class })
public class AlecJamesTestSuite {

}
