package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.Message;
import gdut.ff.mapper.MessageMapper;

/**
 * 
 * @author liuffei
 * @date 
 */
@Service
@Transactional
public class MessageServiceImpl {
	
	@Autowired
	private MessageMapper messageMapper;

	@Transactional(readOnly = true)
	public Message findMessageById(String id) {
		return messageMapper.findMessageById(id);
	}
	
	public int insertMessage(Message message) {
		message.setGmtCreate(new Date());
		message.setGmtModified(new Date());
		message.setMessageId(UUID.randomUUID().toString());
		return messageMapper.insertMessage(message);
	}
	
	public int updateMessage(Message message) {
		message.setGmtModified(new Date());
		return messageMapper.updateMessage(message);
	}

	public int deleteMessageById(String id) {
		return messageMapper.deleteMessageById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Message> findAllMessage(Message message) {
		return messageMapper.findAllMessage();
	}
	
	/**
	 * 查询时间最近的一条通知记录
	 */
	@Transactional(readOnly = true)
	public Message findLastestMessage() {
		return messageMapper.findLastestMessage();
	}
	
}
