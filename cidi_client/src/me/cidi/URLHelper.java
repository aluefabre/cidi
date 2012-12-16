package me.cidi;



import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class URLHelper {
	private static final Logger log = Logger.getLogger(URLHelper.class.getName());
	Integer retry;
	Integer delay;

	public URLHelper(Integer retry, Integer delay) {
		this.retry = retry;
		this.delay = delay;
	}

	public String getUrl(String url, String encoding) {
		for (int i = 0; i < retry; i++) {
			String html = tryGetUrl(url, encoding);
			if (html != null) {
				return html;
			} else if (i < retry - 1) {
				try {
					Thread.sleep(delay * 1000);
				} catch (InterruptedException e) {
					log.warning(e.getMessage());
				}
				log.warning("Retrying to open " + url + ". Number: " + (i + 1));
			} else {
				log.warning("Give up...");
			}
		}
		return null;
	}

	private String tryGetUrl(String url, String encoding) {
		String result = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url); 	
					
			httpget.addHeader(new BasicHeader("Host", httpget.getURI().getHost()));
			httpget.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10"));
			httpget.addHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
			httpget.addHeader(new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5"));
			httpget.addHeader(new BasicHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7"));
			httpget.addHeader(new BasicHeader("Keep-Alive", "115"));
			httpget.addHeader(new BasicHeader("Connection", "keep-alive"));
			httpget.addHeader(new BasicHeader("Cache-Control", "max-age=0"));			
			
			HttpResponse response = httpclient.execute(httpget);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
		        // to bother about connection release
		        if (entity != null) {
		        	result = EntityUtils.toString(entity, encoding);
		        	log.fine(url + " opened, found the following content " + result);
		        }
			}
			
		} catch (Exception e) {
			log.warning("Could not open " + url);
		}
		return result;
	}

	public String postUrl(String url, Map<String, String> payload, String encoding) {
		for (int i = 0; i < retry; i++) {
			String html = tryPostUrl(url, payload, encoding);
			if (html != null) {
				return html;
			} else if (i < retry - 1) {
				try {
					Thread.sleep(delay * 1000);
				} catch (InterruptedException e) {
					log.warning(e.getMessage());
				}
				log.warning("Retrying to post " + url + ". Number: " + (i + 1));
			} else {
				log.warning("Give up...");
			}
		}
		return null;
	}
	
	public String tryPostUrl(String url, Map<String, String> payload, String encoding){
		String result = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url); 
					
			httppost.addHeader(new BasicHeader("Host", httppost.getURI().getHost()));
			httppost.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10"));
			httppost.addHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
			httppost.addHeader(new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5"));
			httppost.addHeader(new BasicHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7"));
			httppost.addHeader(new BasicHeader("Keep-Alive", "115"));
			httppost.addHeader(new BasicHeader("Connection", "keep-alive"));
			httppost.addHeader(new BasicHeader("Cache-Control", "max-age=0"));
			httppost.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
			httppost.addHeader(new BasicHeader("Content-Encoding", "utf-8"));

			HttpParams params = new BasicHttpParams();
			for(String key : payload.keySet()){
				params.setParameter(key, payload.get(key));
			}	
			httppost.setParams(params);
			
			HttpResponse response = httpclient.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
		        // to bother about connection release
		        if (entity != null) {
		        	result = EntityUtils.toString(entity, encoding);
		        	log.fine(url + " opened, found the following content " + result);
		        }
			}
		} catch (Exception e) {
			log.log(Level.WARNING, e.getMessage(), e);
		}
		return result;
	}
}
