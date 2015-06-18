/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.measurement;

/**
 *
 * @author kumark2
 */

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Server {


private long port;

private long protocolVersion;

private String ticketRequestedAt;

private String ticketResponseAt;

private String ipAddress;

public long getPort() {
return port;
}

public void setPort(long port) {
this.port = port;
}

public Server withPort(long port) {
this.port = port;
return this;
}

public long getProtocolVersion() {
return protocolVersion;
}

public void setProtocolVersion(long protocolVersion) {
this.protocolVersion = protocolVersion;
}

public Server withProtocolVersion(long protocolVersion) {
this.protocolVersion = protocolVersion;
return this;
}

public String getTicketRequestedAt() {
return ticketRequestedAt;
}

public void setTicketRequestedAt(String ticketRequestedAt) {
this.ticketRequestedAt = ticketRequestedAt;
}

public Server withTicketRequestedAt(String ticketRequestedAt) {
this.ticketRequestedAt = ticketRequestedAt;
return this;
}

public String getTicketResponseAt() {
return ticketResponseAt;
}

public void setTicketResponseAt(String ticketResponseAt) {
this.ticketResponseAt = ticketResponseAt;
}

public Server withTicketResponseAt(String ticketResponseAt) {
this.ticketResponseAt = ticketResponseAt;
return this;
}

public String getIpAddress() {
return ipAddress;
}

public void setIpAddress(String ipAddress) {
this.ipAddress = ipAddress;
}

public Server withIpAddress(String ipAddress) {
this.ipAddress = ipAddress;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
