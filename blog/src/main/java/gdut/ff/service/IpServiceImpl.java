package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.Ip;
import gdut.ff.mapper.IpMapper;

/**
 * 
 * @author liuffei
 * @date 
 */
@Service
@Transactional
public class IpServiceImpl {
	
	@Autowired
	private IpMapper ipMapper;

	@Transactional(readOnly = true)
	public Ip findIpById(String id) {
		return ipMapper.findIpById(id);
	}
	
	public int insertIp(Ip ip) {
		ip.setGmtCreate(new Date());
		ip.setGmtModified(new Date());
		return ipMapper.insertIp(ip);
	}
	
	public int updateIp(Ip ip) {
		ip.setGmtModified(new Date());
		return ipMapper.updateIp(ip);
	}

	public int deleteIpById(String id) {
		return ipMapper.deleteIpById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Ip> findAllIp(Ip ip) {
		return ipMapper.findAllIp();
	}
	
}
