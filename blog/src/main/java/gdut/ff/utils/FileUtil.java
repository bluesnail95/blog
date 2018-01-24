package gdut.ff.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件工具类
 * @author liuffei
 *
 */
public class FileUtil {
	
	/**
	 * 将通道(文件的内容)写入缓冲
	 * @param source 源文件
	 * @return
	 * @throws Exception
	 */
	public ByteBuffer readToByteBuffer(String source) throws Exception {
		FileInputStream input = new FileInputStream(source);
		FileChannel fileChannel = input.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		while(byteBuffer.hasRemaining()) {
			fileChannel.read(byteBuffer);
		}
		return byteBuffer;
	}
	
	/**
	 * 将缓冲的内容写入通道(文件)
	 * @param buffer
	 * @param target 目标文件
	 * @return
	 * @throws Exception
	 */
	public boolean writeFromByteBuffer(ByteBuffer buffer,String target) throws Exception {
		boolean finish = false;
		FileOutputStream output = new FileOutputStream(target);
		FileChannel fileChannel = output.getChannel();
		while(buffer.hasRemaining()) {
			fileChannel.write(buffer);
		}
		finish = true;
		return finish;
	}

}
