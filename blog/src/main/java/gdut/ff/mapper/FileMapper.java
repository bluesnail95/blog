package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.File;

/**
 * 文件数据访问层接口
 * @author liuffei
 *
 */
public interface FileMapper {

	public int insertFile(File file);
	
	public File findFileById(String id);
	
	public int updateFile(File file);
	
	public int deleteFileById(String id);
	
	public List<File> findAllFile(File file);
}
