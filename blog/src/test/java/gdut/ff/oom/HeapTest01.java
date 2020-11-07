package gdut.ff.oom;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author bluesnail95
 * @Date 2020/10/18 16:44
 * @Description
 */
public class HeapTest01 {
    public static void main(String[] args) throws Exception{
        Map cache = new HashMap();
        for (int i = 0; i < 128; i++) {
            Thread.sleep(500);
            cache.put(i, new byte[1024 * 1024]);
        }
    }
}
