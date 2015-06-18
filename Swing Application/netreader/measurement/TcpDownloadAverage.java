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
public class TcpDownloadAverage {


private double value;

private String time;

private Network_ network;

public double getValue() {
return value;
}

public void setValue(double value) {
this.value = value;
}

public TcpDownloadAverage withValue(double value) {
this.value = value;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public TcpDownloadAverage withTime(String time) {
this.time = time;
return this;
}

public Network_ getNetwork() {
return network;
}

public void setNetwork(Network_ network) {
this.network = network;
}

public TcpDownloadAverage withNetwork(Network_ network) {
this.network = network;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}