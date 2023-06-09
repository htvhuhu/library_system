package mpp.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
	private ServiceFactory() {
		
	}
	private static final Map<Class<? extends Service>, Service> map = new HashMap<>();
	
	static {
		map.put(LoginService.class, new LoginService());
		map.put(BookService.class, new BookService());
		map.put(MemberService.class, new MemberService());
		map.put(AuthorService.class, new AuthorService());
	}
	
	public static Service getService(Class<? extends Service> serviceClass) {
		if (map.containsKey(serviceClass)) return map.get(serviceClass);
		return null;
	}
}
