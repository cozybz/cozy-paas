package org.cozy.paas.controller;

import org.cozy.paas.tools.DockerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {
	@RequestMapping("/getImages")
	public String getImages(String ip) {
		return DockerClient.getImages(ip);
	}
	
}
