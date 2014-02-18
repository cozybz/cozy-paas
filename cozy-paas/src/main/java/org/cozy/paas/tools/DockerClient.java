package org.cozy.paas.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.cozy.paas.model.ContainerConfig;
import org.cozy.paas.model.ContainerCreateResponse;
import org.cozy.paas.model.HostConfig;

public class DockerClient {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String inspect(String ip, String id) {
		return get("http://" + ip + "/containers/" + id + "/json");
	}

	public static ContainerCreateResponse create(String ip, String image,
			Long memory) {
		ContainerConfig c = new ContainerConfig();
		ContainerCreateResponse result = null;
		c.setImage(image);
		c.setMemoryLimit(memory);
		try {
			String s = post("http://" + ip + "/containers/create",
					mapper.writeValueAsString(c));
			result = mapper.readValue(s, ContainerCreateResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String deleteContainer(String ip, String id) {
		return delete("http://" + ip + "/containers/" + id);
	}

	public static String stopContainer(String ip, String id) {
		return post("http://" + ip + "/containers/" + id + "/stop", null);
	}

	public static String restartContainer(String ip, String id) {
		return post("http://" + ip + "/containers/" + id + "/restart", null);
	}

	public static String startContainer(String ip, String id) {
		HostConfig hc = new HostConfig();
		hc.setPublishAllPorts(true);
		String result = "";
		try {
			result = post("http://" + ip + "/containers/" + id + "/start",
					mapper.writeValueAsString(hc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getRunningContainers(String ip, String id) {
		return get("http://" + ip + "/containers/json?size=1");
	}

	public static String getAllContainers(String ip) {
		return get("http://" + ip + "/containers/json?size=1&all=1");
	}

	private static String delete(String urlstr) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlstr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("DELETE");
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.connect();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null)
					sb.append(line);
				reader.close();
			}
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private static String get(String urlstr) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlstr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("GET");
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.connect();
			int HttpResult = con.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null)
					sb.append(line);
				reader.close();
			}
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private static String post(String urlstr, String json) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlstr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("POST");
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.connect();
			if (json != null) {
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(con.getOutputStream()));
				writer.write(json);
				writer.close();
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null)
				sb.append(line);
			reader.close();

			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
