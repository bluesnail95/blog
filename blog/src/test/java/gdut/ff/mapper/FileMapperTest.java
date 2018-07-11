package gdut.ff.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gdut.ff.domain.File;
import gdut.ff.service.FileServiceImpl;

/**
 * 测试文件的增删改查接口
 * @author bluesnail95
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileMapperTest {
	
	@Autowired
	private FileServiceImpl fileServiceImpl;

	@Test
	public void testFindInsertFile() {
		File file = new File();
		file.setGroupName("group1");
		file.setRemoteFileName("M00/00/00/rBAAEFrpxHiAUGPRAAAIdkgFegc18.file");
		file.setFileName("photo.jpg");
		file.setFileIntroduction("上传头像");
		fileServiceImpl.insertFile(file);
	}
	
	@Test
	public void testFindFile() {
		File file = fileServiceImpl.findFileById("6ba4ecb6-5937-4f9c-817d-5a42078f693e");
		System.out.println(file.toString());
		
		List files = fileServiceImpl.findAllFile(null);
		files.forEach(uploadFile -> {
			System.out.println(uploadFile);
		});
	}
	
	@Test
	public void testUpdateFile() {
		File file = new File();
		//file.setGroupName("group1");
		//file.setRemoteFileName("M00/00/00/rBAAEFrpxHiAUGPRAAAIdkgFegc18.file");
		file.setFileIntroduction("上传头像测试");
		fileServiceImpl.updateFile(file);
	}
	
	@Test
	public void testDeleteFile() {
		fileServiceImpl.deleteFileById("6ba4ecb6-5937-4f9c-817d-5a42078f693e");
	}
	
}
