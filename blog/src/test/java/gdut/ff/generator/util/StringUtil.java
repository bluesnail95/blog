package gdut.ff.generator.util;

/**
 *
 * @author liuffei
 * @date 2018-04-03 update
 * @description
 */
public class StringUtil {
	
	/**
	 * 表名转成类名
	 * @param tableName
	 * @return
	 */
	public static String tableNameToClassName(String tableName) {
		StringBuffer className = new StringBuffer("");
		String names[] = tableName.split("_");
		if(null != names && names.length > 0) {
			for(int i = 0;i < names.length;i++) {
				className.append(names[i].substring(0,1).toUpperCase()+names[i].substring(1));
			}
		}
		return className.toString();
	}
	
	/**
	 * 查找表的字段
	 * @param tableColumn
	 * @return
	 */
	public static String tableColumnToClassProperty(String columnName) {
		StringBuffer propertyName = new StringBuffer("");
		String names[] = columnName.split("_");
		if(null != names && names.length > 0) {
			for(int i = 0;i < names.length;i++) {
				if(i == 0) {
					propertyName.append(names[i]);
				}else {
					propertyName.append(names[i].substring(0,1).toUpperCase()+names[i].substring(1));
				}
			}
		}
		return propertyName.toString();
	}

	/**
	 * 将gdut.ff.domain 转换成gdut/ff/domain
	 * @param packageName
	 * @return
	 */
	public static String formatPathName(String packageName) {
		return packageName.replace(".", "/");
	}
	
}
