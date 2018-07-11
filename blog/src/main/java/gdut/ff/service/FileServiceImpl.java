package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.File;
import gdut.ff.mapper.FileMapper;

/**
 * 
 * @author liuffei
 * @date 
 */
@Service
@Transactional
public class FileServiceImpl {
	
	@Autowired
	private FileMapper fileMapper;

	@Transactional(readOnly = true)
	public File findFileById(String id) {
		return fileMapper.findFileById(id);
	}
	
	public int insertFile(File file) {
		file.setFileId(UUID.randomUUID().toString());
		file.setGmtCreate(new Date());
		file.setGmtModified(new Date());
		return fileMapper.insertFile(file);
	}
	
	public int updateFile(File file) {
		file.setGmtModified(new Date());
		return fileMapper.updateFile(file);
	}

	public int deleteFileById(String id) {
		return fileMapper.deleteFileById(id);
	}
	
	@Transactional(readOnly = true)
	public List<File> findAllFile(File file) {
		return fileMapper.findAllFile(file);
	}
	
}
