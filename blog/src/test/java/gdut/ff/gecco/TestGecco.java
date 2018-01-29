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

}
