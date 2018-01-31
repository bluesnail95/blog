package gdut.ff.gecco;

import org.junit.Test;

import com.geccocrawler.gecco.GeccoEngine;

public class TestGecco {
	
	@Test
	public void testGecco() {
		GeccoEngine.create()
		           .classpath("gdut.ff.domain")
		           .start("https://www.cnblogs.com/")
		           .thread(5)
		           .run();
	}
	
	@Test
	public void testGeccoOsc() {
		GeccoEngine.create()
		           .classpath("gdut.ff.gecco")
		           .start("https://www.csdn.net/")
		           .thread(5)
		           .run();
	}

}
