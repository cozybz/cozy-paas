package org.cozy.paas.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DockerClient {
	public static String getRunningContainers(String ip, String id) {
		return send("http://" + ip + "/containers/json&size=1", "GET");
	}

	public static String getAllContainers(String ip, String id) {
		return send("http://" + ip + "/containers/json&size=1&all=1", "GET");
	}

	public static String getImages(String ip) {
		return send("http://" + ip + "/images/json", "GET");
	}

	public static String send(String urlstr, String method) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlstr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null)
				sb.append(line);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
