package gdut.ff.mydispatcher;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

public class Handler {
	
	protected Object controller;
	protected Method method;
	protected Pattern pattern;
	protected Map<String, Integer> paramIndexMapping;
	
	
}
