package linkbook.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public abstract class AbstractHttpTest {

	private static final HttpClient httpclient;
	static {
		httpclient = new DefaultHttpClient();
	}

	protected String post(String url, Map<String, Object> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		for (String key : params.keySet()) {
			nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		httpPost.setHeader("Content-Type", "application/json");
		HttpResponse response = httpclient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("request failed. code: " + response.getStatusLine().getStatusCode());
		}
		return EntityUtils.toString(response.getEntity());
	}

	protected String get(String url, Map<String, Object> params) throws Exception {
		if (params != null && params.size() > 0) {
			StringBuilder sb = new StringBuilder(url);
			sb.append("?");
			for (String key : params.keySet()) {
				sb.append(key).append("=").append(params.get(key));
			}
		}
		HttpGet httpGet = new HttpGet(url);

		HttpResponse response = httpclient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("request failed. code: " + response.getStatusLine().getStatusCode());
		}
		return EntityUtils.toString(response.getEntity());
	}

}
