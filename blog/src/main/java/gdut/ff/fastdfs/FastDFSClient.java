package gdut.ff.fastdfs;

import java.io.IOException;
import java.io.InputStream;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class FastDFSClient {
	
	private static Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

	private static TrackerClient trackerClient;
	
	private static TrackerServer trackerServer;
	
	private static StorageClient storageClient;
	
	private static StorageServer storageServer;
	
	static {
		
		try {
			ClientGlobal.initByProperties("fastdfs-client.properties");
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageServer = trackerClient.getStoreStorage(trackerServer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 上传文件
	 * @param fileName 文件名
	 * @param content 文件内容
	 * @param pair
	 * @return
	 * @throws MyException 
	 * @throws IOException 
	 */
	public static String[] upload(String fileName, byte[] content, NameValuePair[] pair) throws IOException, MyException {
		
		storageClient = new StorageClient(trackerServer,storageServer);
		String[] uploadResults = storageClient.upload_file(content, fileName, pair);
		
		return uploadResults;
	}
	
	/**
	 * 获取文件信息
	 * @param groupName 存储的分组
	 * @param remoteFileName 远程文件名
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
		storageClient = new StorageClient(trackerServer,storageServer);
		return storageClient.get_file_info(groupName, remoteFileName);
	}
	
	/**
	 * 下载文件
	 * @return
	 * @throws MyException 
	 * @throws IOException 
	 */
	public static byte[] donwloadFile(String groupName, String remoteFileName) throws IOException, MyException {
		storageClient = new StorageClient(trackerServer,storageServer);
		byte[] content = storageClient.download_file(groupName, remoteFileName);
		return content;
	}
	
	public static int deleteFile(String groupName, String remoteFileName) throws IOException, MyException {
		storageClient = new StorageClient(trackerServer,storageServer);
		return storageClient.delete_file(groupName, remoteFileName);
	}
	
	public static StorageServer[] getStoreStorages(String groupName) throws IOException {
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}

	public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}

	public static String getTrackerUrl() {
		return "http://"+trackerServer.getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
	}
	
}
