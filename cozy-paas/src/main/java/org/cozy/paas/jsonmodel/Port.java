package org.cozy.paas.jsonmodel;

import org.codehaus.jackson.annotate.JsonProperty;

public class Port {
	
    @JsonProperty("PrivatePort")
    private long privatePort;

    @JsonProperty("PublicPort")
    private long publicPort;

    @JsonProperty("Type")
    private String type;

    public long getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(long privatePort) {
        this.privatePort = privatePort;
    }

    public long getPublicPort() {
        return publicPort;
    }

    public void setPublicPort(long publicPort) {
        this.publicPort = publicPort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Port{" +
                "privatePort=" + privatePort +
                ", publicPort=" + publicPort +
                ", type='" + type + '\'' +
                '}';
    }
}