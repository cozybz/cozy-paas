package org.cozy.paas.pojo;

public class ContainerDB {
	private String id;
	private Long memory;
	private String description;
	private int status;
	private int hostId;
	private int userId;


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getMemory() {
		return memory;
	}

	public void setMemory(Long memory) {
		this.memory = memory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
