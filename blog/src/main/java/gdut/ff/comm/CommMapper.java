package gdut.ff.comm;

import java.util.List;

/**
 * Mybatis的接口Mapper的公共父类
 * @author Administrator
 * @T 实体
 */
public interface CommMapper<T> {
	
	/**
	 * 查找全部记录
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 查找指定的记录
	 * @param id
	 * @return
	 */
	public T findOne(Object id);

	/**
	 * 保存某条记录
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 更新某条记录
	 * @param entity
	 */
	public void update(T entity);
}
