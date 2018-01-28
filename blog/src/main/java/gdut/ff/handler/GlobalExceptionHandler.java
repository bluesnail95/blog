package gdut.ff.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.utils.NodeUtil;

/**
 * 
 * @author liuffei
 * @date 2018-01-27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public ObjectNode handlerError(MultipartException e) {
		ObjectNode result = NodeUtil.create();
		result.put("message",e.getCause().getMessage());
		return result;
	}
}
