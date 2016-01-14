package linkbook.controller.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class LoginTest extends AbstractHttpTest {

	@Test
	public void getLogin() throws Exception {
//		String str = get("http://localhost:8081/login.htm", null);
//		System.out.println(str);
	}
	
	@Test
	public void postLogin() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "67i99");
		map.put("password", "123456");
//		String str = post("http://localhost:8081/login.htm", map);
//		System.out.println(str);
	}

}
