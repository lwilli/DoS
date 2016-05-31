package unittesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestByteCrypto.class, TestFileSystemManager.class, TestGameStateSerializer.class })
public class TestAlecJamesSuite {

}
