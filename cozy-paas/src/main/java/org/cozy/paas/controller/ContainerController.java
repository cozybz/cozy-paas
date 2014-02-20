package org.cozy.paas.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.cozy.paas.model.ContainerCreateResponse;
import org.cozy.paas.model.ContainerInspectResponse;
import org.cozy.paas.pojo.ContainerDB;
import org.cozy.paas.pojo.HostDB;
import org.cozy.paas.service.ContainerService;
import org.cozy.paas.service.HostService;
import org.cozy.paas.tools.DockerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paas/container")
public class ContainerController {

	@Resource
	private ContainerService containerServiceImpl;

	@Resource
	private HostService hostServiceImpl;

	@RequestMapping("/create")
	public int create(@RequestBody ContainerDB c, HttpSession session) {
		HostDB host = hostServiceImpl.selectById(c.getHostId());
		ContainerCreateResponse resp = DockerClient.create(host.getIp(),
				c.getName(), c.getMemory());
		c.setId(resp.getId());
		c.setUserId((int) session.getAttribute("id"));
		return containerServiceImpl.insert(c);

	}

	@RequestMapping("/{id}/start")
	public String start(@PathVariable String id) {
		ContainerDB c = containerServiceImpl.selectById(id);
		HostDB h = hostServiceImpl.selectById(c.getHostId());
		c.setStatus(1);
		containerServiceImpl.update(c);
		return DockerClient.startContainer(h.getIp(), id);
	}

	@RequestMapping("/{id}/stop")
	public String stop(@PathVariable String id) {
		ContainerDB c = containerServiceImpl.selectById(id);
		c.setStatus(0);
		containerServiceImpl.update(c);
		String ip = hostServiceImpl.selectById(c.getHostId()).getIp();
		return DockerClient.stopContainer(ip, id);
	}

	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable String id) {
		ContainerDB c = containerServiceImpl.selectById(id);
		String ip = hostServiceImpl.selectById(c.getHostId()).getIp();
		containerServiceImpl.delete(id);
		return DockerClient.deleteContainer(ip, id);
	}

	@RequestMapping("/{id}/info")
	public ContainerInspectResponse info(@PathVariable String id) {
		ContainerDB c = containerServiceImpl.selectById(id);
		String ip = hostServiceImpl.selectById(c.getHostId()).getIp();
		return DockerClient.inspect(ip, id);
	}

	@RequestMapping("/selectAll")
	public List<ContainerDB> selectAll() {
		return containerServiceImpl.selectAll();
	}

	@RequestMapping("/selectByHostId")
	public List<ContainerDB> selectByHostId(@RequestBody int hostId) {
		return containerServiceImpl.selectByHostId(hostId);
	}

	@RequestMapping("/selectByUserId")
	public List<ContainerDB> selectByUserId(@RequestBody int userId) {
		return containerServiceImpl.selectByUserId(userId);
	}

}
