package net.yura.cache;

import junit.framework.TestCase;

/**
 * Created by zack on 10/31/16.
 */
public class CacheTest  extends TestCase {

    public void testSmokeTest() {
        Cache cache = new Cache("foobar");
        byte[] data = new byte[]{0,0,0};
        cache.put("fizzbuzz", data);
        
    }
}
