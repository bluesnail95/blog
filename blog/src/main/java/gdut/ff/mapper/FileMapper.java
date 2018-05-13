package gdut.ff.mapper;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import gdut.ff.domain.File;

/**
 * 文件数据访问层接口
 * @author liuffei
 *
 */
@CacheConfig(cacheNames="files")
public interface FileMapper {

	@CachePut
	public int insertFile(File file);
	
	@Cacheable
	public File findFileById(String id);
	
	@CachePut
	public int updateFile(File file);
	
	@CacheEvict
	public int deleteFileById(String id);
	
	@Cacheable
	public List<File> findAllFile(File file);
}
