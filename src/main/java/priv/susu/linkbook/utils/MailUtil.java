package priv.susu.linkbook.utils;

import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtil {

	private static final Logger EMAIL_LOG = LoggerFactory.getLogger("EMAIL_LOG");

	private static Properties props;
	static {
		props = System.getProperties();
	}

	/**
	 * 发送邮件
	 * 
	 * @param param
	 *            <ul>
	 *            参数Map
	 *            <li>smtp:协议
	 *            <li>from:发件人
	 *            <li>to:收件人
	 *            <li>subject:邮件主题
	 *            <li>content:邮件内容
	 *            <li>username:登录名
	 *            <li>password:登录密码
	 * 
	 * @return
	 */
	public static boolean send(Map<String, String> param) {
		String smtp = param.get("smtp"); // 协议
		String from = param.get("from"); // 发件人
		String to = param.get("to"); // 收件人
		String subject = param.get("subject"); // 邮件主题
		String content = param.get("content"); // 邮件内容
		String username = param.get("username"); // 登录名
		String password = param.get("password"); // 登录密码

		boolean result = false;
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.auth", "true");

		Session mailSession = Session.getDefaultInstance(props);
		MimeMessage mimeMsg = new MimeMessage(mailSession);
		MimeMultipart mp = new MimeMultipart();

		Transport transport = null;
		try {
			mimeMsg.setSubject(subject);
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + content, "text/html;charset=GBK");
			mp.addBodyPart(bp);
			mimeMsg.setFrom(new InternetAddress(from));
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();

			transport = mailSession.getTransport("smtp");
			transport.connect(smtp, username, password);
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
		} catch (Exception e) {
			EMAIL_LOG.error("email send failed. params: " + param, e);
		} finally {
			if (transport != null)
				try {
					transport.close();
				} catch (Exception e) {
					EMAIL_LOG.error("transport close error.", e);
				}
		}

		return result;
	}

}
