package gdut.ff.generator.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换
 * @author liuffei
 * @date 2018��3��1��
 * @description
 */
public class TypeAliasRegistry {

	private final Map<String,Class<?>> TYPE_ALIASES = new HashMap<String,Class<?>>();
	
	public TypeAliasRegistry() {
		TYPE_ALIASES.put("varchar",String.class);
		TYPE_ALIASES.put("datetime",Date.class);
		TYPE_ALIASES.put("int",int.class);
	}

	public Map<String, Class<?>> getTYPE_ALIASES() {
		return TYPE_ALIASES;
	}
	
}
