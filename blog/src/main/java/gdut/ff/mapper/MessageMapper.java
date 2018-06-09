package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.Message;

/**
 * 数据访问层接口
 * @author liuffei
 * @date
 *
 */
public interface MessageMapper {

	public int insertMessage(Message message);
	
	public Message findMessageById(String id);
	
	public int updateMessage(Message message);
	
	public int deleteMessageById(String id);
	
	public List<Message> findAllMessage();
}
