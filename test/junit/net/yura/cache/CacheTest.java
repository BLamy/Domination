package net.yura.cache;

import net.yura.cache.Cache;
import junit.framework.TestCase;
import org.junit.Test;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.yura.cache.Cache.logger;

/**
 * Created by zack on 10/31/16.
 */
public class CacheTest extends TestCase {

    protected void tearDown() throws Exception {
        String tmpDir = System.getProperty("java.io.tmpdir");
        File dir = new File(tmpDir);
        for(File file: dir.listFiles()) 
            if (!file.isDirectory()) 
                file.delete();
    }
    
    public CacheTest(String testName) {
        super(testName);
    }

    public void testSmoke() {
        assert(true);
    }
    
    public void testCacheConstructor() {
        Cache test1Cache = new Cache("test");
        Cache test2Cache = new Cache("test");
        Cache test3Cache = new Cache("test");
        String cacheFile = System.getProperty("java.io.tmpdir");
        cacheFile = cacheFile.concat("test.cache");
        File varTmpDir = new File(cacheFile);
        boolean exists = varTmpDir.exists();
        assertEquals(true, exists);
        varTmpDir.delete();
    }
    
    public void testPut() {
        Cache test1Cache = new Cache("test");
        boolean contains = test1Cache.containsKey("test");
        logger.log(Level.WARNING, "The Cache contains the key: {0}", contains);
        byte[] value = new byte[16];
        String s = new String("testtesttesttest");
        for (int i = 0; i < 16; i += 2) {
        value[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
        }
        byte[] value2 = new byte[16];
        String st = new String("testboattestboat");
        for (int i = 0; i < 16; i += 2) {
        value2[i / 2] = (byte) ((Character.digit(st.charAt(i), 16) << 4)
                             + Character.digit(st.charAt(i+1), 16));
        }
        test1Cache.put("test", value);
        test1Cache.put("test", value2);
    }
}
