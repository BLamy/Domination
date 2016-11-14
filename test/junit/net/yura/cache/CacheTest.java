package net.yura.cache;

import net.yura.cache.Cache;
import junit.framework.TestCase;
import org.junit.Test;
import java.io.File;

/**
 * Created by zack on 10/31/16.
 */
public class CacheTest extends TestCase {
    
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
    }
}
