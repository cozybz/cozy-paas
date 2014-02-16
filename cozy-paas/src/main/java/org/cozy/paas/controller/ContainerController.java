package org.cozy.paas.controller;

import java.util.List;
import javax.annotation.Resource;
import org.cozy.paas.pojo.ContainerDB;
import org.cozy.paas.pojo.HostDB;
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

	@RequestMapping("/createTomcat")
	public String createTomcat(ContainerDB c) {
		HostDB host = hostServiceImpl.selectById(c.getHostId());
		containerServiceImpl.insert(c);
		return DockerClient.create(host.getIp(), "tomcat", c.getMemory());
	}

	@RequestMapping("/createMySQL")
	public String createMySQL(ContainerDB c) {
		HostDB host = hostServiceImpl.selectById(c.getHostId());
		containerServiceImpl.insert(c);
		return DockerClient.create(host.getIp(), "mysql", c.getMemory());
	}

	@RequestMapping("/start")
	public String start(String id, String ip) {
		ContainerDB c = containerServiceImpl.selectById(id);
		c.setStatus(1);
		containerServiceImpl.update(c);
		return DockerClient.startContainer(ip, id);
	}

	@RequestMapping("/stop")
	public String stop(String id, String ip) {
		ContainerDB c = containerServiceImpl.selectById(id);
		c.setStatus(0);
		containerServiceImpl.update(c);
		return DockerClient.stopContainer(ip, id);
	}

	@RequestMapping("/delete")
	public String delete(String id, String ip) {
		containerServiceImpl.delete(id);
		return DockerClient.deleteContainer(ip, id);
	}

	@RequestMapping("/selectAll")
	public List<ContainerDB> selectAll() {
		return containerServiceImpl.selectAll();
	}

	@RequestMapping("/selectByHostId")
	public List<ContainerDB> selectByHostId(int hostId) {
		return containerServiceImpl.selectByHostId(hostId);
	}

	@RequestMapping("/selectAll")
	public List<ContainerDB> selectByUserId(int userId) {
		return containerServiceImpl.selectByUserId(userId);
	}

}
