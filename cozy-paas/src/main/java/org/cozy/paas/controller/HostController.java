package org.cozy.paas.controller;

import java.util.List;
import javax.annotation.Resource;
import org.cozy.paas.pojo.HostDB;
import org.cozy.paas.service.HostService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paas/host")
public class HostController {
	@Resource
	private HostService hostServiceImpl;

	@RequestMapping("/insert")
	public int insert(@RequestBody HostDB host) {
		return hostServiceImpl.insert(host);
	}

	@RequestMapping("/{id}/delete")
	public int delete(@PathVariable int id) {
		return hostServiceImpl.delete(id);
	}

	@RequestMapping("/update")
	public int update(@RequestBody HostDB host) {
		return hostServiceImpl.update(host);
	}

	@RequestMapping("/{id}/selectById")
	public HostDB selectById(@PathVariable int id) {
		return hostServiceImpl.selectById(id);
	}

	@RequestMapping("/selectAll")
	public List<HostDB> selectAll() {
		return hostServiceImpl.selectAll();
	}
}
