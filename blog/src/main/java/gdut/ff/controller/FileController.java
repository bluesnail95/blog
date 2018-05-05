
package gdut.ff.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.domain.File;
import gdut.ff.fastdfs.FastDFSClient;
import gdut.ff.service.FileServiceImpl;
import gdut.ff.utils.JsonUtil;

/**
 * 文件操作控制器
 * @author liuffei
 * @date 
 */
@RestController
public class FileController {
	
	private Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileServiceImpl fileServiceImpl;
	
	/**
	 * 添加文件
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/file")
	public JSONObject insertFile(@RequestBody File file,HttpServletRequest request) throws Exception {
		if(StringUtil.isNotBlank(file.getId())) {
			fileServiceImpl.updateFile(file);
		}else {
			fileServiceImpl.insertFile(file);
		}
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询指定文件记录
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/file/{id}")
	public JSONObject findFileById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		File file = fileServiceImpl.findFileById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("content", file);
		return result;
	}
	
	/**
	 * 更新指定的文件记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/file/{id}")
	public JSONObject updateFilegById(@PathVariable String id, @RequestBody File file) {
		fileServiceImpl.updateFile(file);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除文件记录
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/file/{id}")
	public JSONObject deleteFileById(@PathVariable String id) {
		fileServiceImpl.deleteFileById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部文件
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/files")
	public JSONObject finaAllFiles() {
		List<File> files = fileServiceImpl.findAllFile(null);
		JSONObject result = JsonUtil.successJson();
		result.put("files", files);
		return result;
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws MyException 
	 */
	@PostMapping(value = "/upload")
	public JSONObject uploadFile(@RequestParam(name = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, MyException {
		if(file.isEmpty()) {
			return JsonUtil.errorJson("上传文件不能为空");
		}
		byte[] content = file.getBytes();
		String fileName = file.getName();
		NameValuePair pair[] = new NameValuePair[1];
		pair[0] = new NameValuePair("author","liuffei");
		String[] uploadResult = FastDFSClient.upload(fileName, content, pair);
		//保存到数据库
		String groupName = uploadResult[0];
		String remoteFileName = uploadResult[1];
		File uploadFile = new File();
		uploadFile.setRemoteFileName(remoteFileName);
		uploadFile.setGroupName(groupName);
		uploadFile.setFileName(file.getOriginalFilename());
		fileServiceImpl.insertFile(uploadFile);
		//返回结果
		JSONObject result = JsonUtil.successJson();
		result.put("uploadResult", uploadResult);
		return result;
		
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws MyException 
	 */
	@GetMapping(value = "/download/{id}")
	public void downloadFile(@PathVariable String id, HttpServletResponse response) throws IOException, MyException {
		File downloadFile = fileServiceImpl.findFileById(id);
		if(null != downloadFile) {
			//获取文件内容
			String groupName = downloadFile.getGroupName();
			String remoteFileName = downloadFile.getRemoteFileName();
			byte[] content = FastDFSClient.donwloadFile(groupName, remoteFileName);
			//输出
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(downloadFile.getFileName(), "UTF-8"));
			response.setContentType("application/octet-stream");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(content);
			outputStream.flush();
			outputStream.close();
		}else {
			throw new FileNotFoundException("不存在该文件!!!");
		}
		
	}
}
