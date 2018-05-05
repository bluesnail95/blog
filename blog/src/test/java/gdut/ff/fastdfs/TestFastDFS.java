package gdut.ff.fastdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageServer;
import org.junit.Test;

/**
 * 测试FastDFS
 * @author liuffei
 *
 */

public class TestFastDFS {
	
	/**
	 * https://github.com/happyfish100/fastdfs-client-java
	 */
	@Test
	public void testClientGlobal() {
		try {
			ClientGlobal.initByProperties("fastdfs-client.properties");
			System.out.println("ClientGlobal.configInfo(): "+ClientGlobal.configInfo());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetFile(){
		String groupName = "group1";
		String remoteFileName = "M00/00/00/rBAAEFrpxHiAUGPRAAAIdkgFegc18.file";
		try {
			FileInfo fileInfo = FastDFSClient.getFile(groupName, remoteFileName);
			System.out.println(fileInfo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStorageUrl() {
		System.out.println(FastDFSClient.getTrackerUrl());
		
		try {
			StorageServer server[] = FastDFSClient.getStoreStorages("group1");
			if(null != server && server.length > 0) {
				for(int i = 0;i < server.length;i++) {
					System.out.println(server[i].getInetSocketAddress());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
