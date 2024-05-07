package com.demo.scheduled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 资源校验工具
 * 
 * @author Raynor
 * 
 */
public class ResourceCheckUtil {
	private static Map<String, String[]> rules;
	static {
		loadRule();
	}
	@SuppressWarnings("unused")
	private static String cookies = "BAIDUID=3493205C1471EB5F1E27F366ED9713C2:FG=1; BIDUPSID=3493205C1471EB5F1E27F366ED9713C2; PSTM=1499826354; PANWEB=1; bdshare_firstime=1499839613146; FP_UID=d6297086ace66e85af18a244318efd02; BDUSS=hNNVdhTVd6UEpsWlNiajU4WmM2VlJZRkp-c2otMlh6UVRlaTdENUtUakdTWTFaTVFBQUFBJCQAAAAAAAAAAAEAAAB37nOtxM66zti8srvGxgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMa8ZVnGvGVZaH; STOKEN=94038b45d7946baa69c708f8eab3068a982c853c98c32433f7b3ae20da37391e; SCRC=dd7d90d2bff34203f0be0317a1eb41cf; BDCLND=t1wcAOzyLmyZdplefWeUzGtlU%2BZjvZoUqavyYmJmVak%3D; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PSINO=7; H_PS_PSSID=1466_21109_17001_19898_20927; Hm_lvt_7a3960b6f067eb0085b7f96ff5e660b0=1499839682,1499841922,1499843792,1500458033; Hm_lpvt_7a3960b6f067eb0085b7f96ff5e660b0=1500458396; PANPSC=5676771253663392184%3AD6NVGkQPO1W%2BNPRGOXuNkun90oj%2BY%2FIsKLSvHXcyIk73RCn7hN7MbuhsXy%2FwDjyAhHVnG%2BbTsWfxTJ3v1z1m%2Bh4XiP1WnkH03pBa%2Fo0YJZj8cU%2F5SgrbTrDDqi%2BsdH8nSlgwQk9xY5zQtFsPtE77GxGUdLbIxdFT%2B%2Bo8ht7g5D2LIWeznIUCtSkA0KYPulwlfkiVl%2Ff%2FRfx4kU4b9dv9by76Oxw8WnAw";

	/**
	 * 加载规则库
	 */
	public static void loadRule() {
		try {
			InputStream in = ResourceCheckUtil.class.getClassLoader().getResourceAsStream("rule.properties");
			Properties p = new Properties();
			p.load(in);
			Set<Object> keys = p.keySet();
			Iterator<Object> iterator = keys.iterator();
			String key = null;
			String value = null;
			String[] rule = null;
			rules = new HashMap<String, String[]>();
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				value = (String) p.get(key);
				rule = value.split("\\|");
				rules.put(key, rule);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String httpRequest(String url) {
		try {
			URL u = new URL(url);
			Random random = new Random();
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setConnectTimeout(3000);// 3 秒超时
			connection.setReadTimeout(3000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("GET");

			String[] user_agents = {
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11",
					"Opera/9.25 (Windows NT 5.1; U; en)",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
					"Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)",
					"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12",
					"Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9",
					"Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
					"Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 " };
			int index = random.nextInt(7);
//			connection.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			connection.setRequestProperty("User-Agent", user_agents[index]);
//			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
//			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//			connection.setRequestProperty("Connection", "keep-alive");
//			connection.setRequestProperty("Host", "pan.baidu.com");
//			connection.setRequestProperty("Cookie", cookies);
//			connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
			InputStream in = connection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取指定页面上标签的内容
	 * 
	 * @param url
	 * @param tagName 标签名称
	 * @return
	 */
	private static String getHtmlContent(String url, String tagName) {
		String html = httpRequest(url);
		if (html == null) {
			return "";
		}
		Document doc = Jsoup.parse(html);
		Elements tag = null;
//		if (tagName.equals("media-bd")) { // 针对微云
//			tag = doc.getElementsByClass(tagName);
//		} else if (tagName.equals("m-error-404")) { // 针对 360
//			tag = doc.getElementsByClass(tagName);
//		} else {
//			tag = doc.getElementsByClass(tagName);
//		}
		tag = doc.getElementsByClass(tagName);
		System.out.println("tag:"+tag +"________tagName:"+ tagName);
		String content = "";
		if (tag != null && !tag.isEmpty()) {
			content = tag.get(0).text();
		}
		System.out.println(content + "...");
		return content;
	}

	public static int isExistResource(String url, String ruleName) {
		try {
			String[] rule = rules.get(ruleName);
			String tagName = rule[0];
			String opt = rule[1];
			String flag = rule[2];
			String content = getHtmlContent(url, tagName);
			System.out.println(content+"==========");
			if (ruleName.equals("baidu")) {
				if (content.contains("百度云升级")) { // 升级作为不存在处理
					return 1;
				}
			}
			String regex = null;
			if ("eq".equals(opt)) {
				regex = "^" + flag + "$";
			} else if ("bg".equals(opt)) {
				regex = "^" + flag + ".*$";
			} else if ("ed".equals(opt)) {
				regex = "^.*" + flag + "$";
			} else if ("like".equals(opt)) {
				regex = "^.*" + flag + ".*$";
			} else if ("contain".equals(opt)) {
				if (content.contains(flag)) {
					return 0;
				} else {
					return 1;
				}
			}
			if (content.matches(regex)) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}