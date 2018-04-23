package gdut.ff.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.utils.JsonUtil;

/**
 * 用于文件的上传下载
 * @author liuffei
 * @date 2018-04-23
 *
 */
@RestController
public class FileController extends CommController{

	/**
	 * 上传文件
	 * @param request
	 * @param response
	 */
	@PostMapping(value = "/upload")
	public JSONObject uploadFile(@RequestParam(name = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		if(file.isEmpty()) {
			return JsonUtil.errorJson("上传文件不能为空");
		}
		try {
			byte[] bytes = file.getBytes();
			String root = request.getContextPath();
			Path path = Paths.get(root + "/upload");
			Files.write(path, bytes);
			return JsonUtil.successJson();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson("上传失败");
		}
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/download/{fileId}")
	public JSONObject downloadFile(@PathVariable String fileId,HttpServletRequest request, HttpServletResponse response) {
		try {
			URL url = new URL("http://127.0.0.1:8088/index.html");
			URLConnection urlConnection = url.openConnection();
			urlConnection.connect();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			int responseCode = httpUrlConnection.getResponseCode();
			if(responseCode != HttpURLConnection.HTTP_OK) {
				return JsonUtil.errorJson("连接失败");
			}
			int filesize = httpUrlConnection.getContentLength();
			BufferedReader  reader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(),"UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = reader.readLine();
			while(line != null) {
				buffer.append(line);
				buffer.append("\n");
				line = reader.readLine();
			}
			
			//输出
			response.setHeader("Content-Disposition", "attachment;filename=test.html");
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(buffer.toString().getBytes());
			outputStream.flush();
			outputStream.close();
			return JsonUtil.successJson();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson("下载失败");
		}
	}
}
