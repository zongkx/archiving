package com.demo.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class UrlUtils {

	public static boolean checkUrl(String url) throws IOException {
		Document doc = null;
		doc = Jsoup.connect(url).timeout(5 * 1000).userAgent(
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.header("Cookie",
						"BAIDUID=3493205C1471EB5F1E27F366ED9713C2:FG=1; BIDUPSID=3493205C1471EB5F1E27F366ED9713C2; PSTM=1499826354; PANWEB=1; bdshare_firstime=1499839613146; FP_UID=d6297086ace66e85af18a244318efd02; BDUSS=hNNVdhTVd6UEpsWlNiajU4WmM2VlJZRkp-c2otMlh6UVRlaTdENUtUakdTWTFaTVFBQUFBJCQAAAAAAAAAAAEAAAB37nOtxM66zti8srvGxgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMa8ZVnGvGVZaH; STOKEN=94038b45d7946baa69c708f8eab3068a982c853c98c32433f7b3ae20da37391e; SCRC=dd7d90d2bff34203f0be0317a1eb41cf; BDCLND=t1wcAOzyLmyZdplefWeUzGtlU%2BZjvZoUqavyYmJmVak%3D; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PSINO=7; H_PS_PSSID=1466_21109_17001_19898_20927; Hm_lvt_7a3960b6f067eb0085b7f96ff5e660b0=1499839682,1499841922,1499843792,1500458033; Hm_lpvt_7a3960b6f067eb0085b7f96ff5e660b0=1500458396; PANPSC=5676771253663392184%3AD6NVGkQPO1W%2BNPRGOXuNkun90oj%2BY%2FIsKLSvHXcyIk73RCn7hN7MbuhsXy%2FwDjyAhHVnG%2BbTsWfxTJ3v1z1m%2Bh4XiP1WnkH03pBa%2Fo0YJZj8cU%2F5SgrbTrDDqi%2BsdH8nSlgwQk9xY5zQtFsPtE77GxGUdLbIxdFT%2B%2Bo8ht7g5D2LIWeznIUCtSkA0KYPulwlfkiVl%2Ff%2FRfx4kU4b9dv9by76Oxw8WnAw")
				.get();
		Elements links = doc.getElementsByClass("share-error-left");
		if (links.size() > 0) { 
			return false;
		} else {
			return true;
		}

	}

}
