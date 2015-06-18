/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.measurement;

//

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.netradar.view.MainForm;

/**
 *
 * @author kumark2
 */
public class Measurement {
    
    
private String fileName;

private String createdAt;

//private Date myDate;

private String startedBy;

private String startedAt;

private String ticketId;

private String finishedAt;

private List<Object> tcpPings = new ArrayList<Object>();

private TcpUploadAverage tcpUploadAverage;

private List<TcpUploadSample> tcpUploadSamples = new ArrayList<TcpUploadSample>();

private TcpDownloadAverage tcpDownloadAverage;

private List<TcpDownloadSample> tcpDownloadSamples = new ArrayList<TcpDownloadSample>();

private PingAverage pingAverage;

private List<Ping> pings = new ArrayList<Ping>();

private List<SignalStrength> signalStrengths = new ArrayList<SignalStrength>();

private List<Location> locations = new ArrayList<Location>();

private List<Battery> battery = new ArrayList<Battery>();

private List<Network__> network = new ArrayList<Network__>();

private Operator operator;

private User user;

private Device device;

private Server server;

private Client client;

public String getFileName() {
return fileName;
}

public void setFileName(String fileName) {
this.fileName = fileName;
}

public Measurement withFileName(String fileName) {
this.fileName = fileName;
return this;
}

        
        

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public Measurement withCreatedAt(String createdAt) {
this.createdAt = createdAt;
return this;
}

public String getStartedBy() {
return startedBy;
}

public void setStartedBy(String startedBy) {
this.startedBy = startedBy;
}

public Measurement withStartedBy(String startedBy) {
this.startedBy = startedBy;
return this;
}

public String getStartedAt() {
return startedAt;
}

public void setStartedAt(String startedAt) {
this.startedAt = startedAt;
}

public Measurement withStartedAt(String startedAt) {
this.startedAt = startedAt;
return this;
}

public String getTicketId() {
return ticketId;
}

public void setTicketId(String ticketId) {
this.ticketId = ticketId;
}

public Measurement withTicketId(String ticketId) {
this.ticketId = ticketId;
return this;
}

public String getFinishedAt() {
return finishedAt;
}

public void setFinishedAt(String finishedAt) {
this.finishedAt = finishedAt;
}

public Measurement withFinishedAt(String finishedAt) {
this.finishedAt = finishedAt;
return this;
}

public List<Object> getTcpPings() {
return tcpPings;
}

public void setTcpPings(List<Object> tcpPings) {
this.tcpPings = tcpPings;
}

public Measurement withTcpPings(List<Object> tcpPings) {
this.tcpPings = tcpPings;
return this;
}

public TcpUploadAverage getTcpUploadAverage() {
return tcpUploadAverage;
}

public void setTcpUploadAverage(TcpUploadAverage tcpUploadAverage) {
this.tcpUploadAverage = tcpUploadAverage;
}

public Measurement withTcpUploadAverage(TcpUploadAverage tcpUploadAverage) {
this.tcpUploadAverage = tcpUploadAverage;
return this;
}

public List<TcpUploadSample> getTcpUploadSamples() {
return tcpUploadSamples;
}

public void setTcpUploadSamples(List<TcpUploadSample> tcpUploadSamples) {
this.tcpUploadSamples = tcpUploadSamples;
}

public Measurement withTcpUploadSamples(List<TcpUploadSample> tcpUploadSamples) {
this.tcpUploadSamples = tcpUploadSamples;
return this;
}

public TcpDownloadAverage getTcpDownloadAverage() {
return tcpDownloadAverage;
}

public void setTcpDownloadAverage(TcpDownloadAverage tcpDownloadAverage) {
this.tcpDownloadAverage = tcpDownloadAverage;
}

public Measurement withTcpDownloadAverage(TcpDownloadAverage tcpDownloadAverage) {
this.tcpDownloadAverage = tcpDownloadAverage;
return this;
}

public List<TcpDownloadSample> getTcpDownloadSamples() {
return tcpDownloadSamples;
}

public void setTcpDownloadSamples(List<TcpDownloadSample> tcpDownloadSamples) {
this.tcpDownloadSamples = tcpDownloadSamples;
}

public Measurement withTcpDownloadSamples(List<TcpDownloadSample> tcpDownloadSamples) {
this.tcpDownloadSamples = tcpDownloadSamples;
return this;
}

public PingAverage getPingAverage() {
return pingAverage;
}

public void setPingAverage(PingAverage pingAverage) {
this.pingAverage = pingAverage;
}

public Measurement withPingAverage(PingAverage pingAverage) {
this.pingAverage = pingAverage;
return this;
}

public List<Ping> getPings() {
return pings;
}

public void setPings(List<Ping> pings) {
this.pings = pings;
}

public Measurement withPings(List<Ping> pings) {
this.pings = pings;
return this;
}

public List<SignalStrength> getSignalStrengths() {
return signalStrengths;
}

public void setSignalStrengths(List<SignalStrength> signalStrengths) {
this.signalStrengths = signalStrengths;
}

public Measurement withSignalStrengths(List<SignalStrength> signalStrengths) {
this.signalStrengths = signalStrengths;
return this;
}

public List<Location> getLocations() {
return locations;
}

public void setLocations(List<Location> locations) {
this.locations = locations;
}

public Measurement withLocations(List<Location> locations) {
this.locations = locations;
return this;
}

public List<Battery> getBattery() {
return battery;
}

public void setBattery(List<Battery> battery) {
this.battery = battery;
}

public Measurement withBattery(List<Battery> battery) {
this.battery = battery;
return this;
}

public List<Network__> getNetwork() {
return network;
}

public void setNetwork(List<Network__> network) {
this.network = network;
}

public Measurement withNetwork(List<Network__> network) {
this.network = network;
return this;
}

public Operator getOperator() {
return operator;
}

public void setOperator(Operator operator) {
this.operator = operator;
}

public Measurement withOperator(Operator operator) {
this.operator = operator;
return this;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public Measurement withUser(User user) {
this.user = user;
return this;
}

public Device getDevice() {
return device;
}

public void setDevice(Device device) {
this.device = device;
}

public Measurement withDevice(Device device) {
this.device = device;
return this;
}

public Server getServer() {
return server;
}

public void setServer(Server server) {
this.server = server;
}

public Measurement withServer(Server server) {
this.server = server;
return this;
}

public Client getClient() {
return client;
}

public void setClient(Client client) {
this.client = client;
}

public Measurement withClient(Client client) {
this.client = client;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}
    
}
