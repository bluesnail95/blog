package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.Ip;

/**
 * 数据访问层接口
 * @author liuffei
 * @date
 *
 */
public interface IpMapper {

	public int insertIp(Ip ip);
	
	public Ip findIpById(String id);
	
	public int updateIp(Ip ip);
	
	public int deleteIpById(String id);
	
	public List<Ip> findAllIp();
}
