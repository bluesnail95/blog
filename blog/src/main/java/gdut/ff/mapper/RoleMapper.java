package gdut.ff.mapper;

import gdut.ff.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuffei
 *
 */
public interface RoleMapper {

	public List<Role> findRoleByUser(@Param("userId") String userId);

}
