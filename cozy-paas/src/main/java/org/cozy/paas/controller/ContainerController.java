package org.cozy.paas.controller;

import javax.annotation.Resource;
import org.cozy.paas.service.ContainerService;
import org.cozy.paas.service.HostService;
import org.cozy.paas.tools.DockerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/container")
public class ContainerController {
	@Resource
	private ContainerService containerServiceImpl;

	@Resource
	private HostService hostServiceImpl;

	@RequestMapping("createTomcat")
	public String createTomcat(String ip, int userId, int memory) {
		return DockerClient.create(ip, "tomcat", memory);
	}

	@RequestMapping("/getAllContainers")
	public String getAllContainers(String id, String ip) {
		return DockerClient.getAllContainers(ip);
	}

	@RequestMapping("/getRunningContainers")
	public String getRunningContainers(String id, String ip) {
		return DockerClient.getRunningContainers(ip, id);
	}

}
