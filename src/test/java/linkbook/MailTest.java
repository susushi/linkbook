package linkbook;

public class MailTest {
	public static void main(String[] args) throws Exception {
		String smtp = "smtp.163.com";
		String from = "shinn.1215@163.com";
		String to = "zlan_yichen@163.com";
		String subject = "123加个好友呗";
		String content = "认证地址<a href=\"http://www.baidu.com\" _src=\"http://www.baidu.com\" target=\"_blank\">http://www.baidu.com</a>";
		String username = "shinn.1215@163.com";
		String password = "yichen1214";
		Mail.send(smtp, from, to, subject, content, username, password);
	}
}
