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

public class TcpUploadAverage {


private double value;

private String time;

private Network network;

public double getValue() {
return value;
}

public void setValue(double value) {
this.value = value;
}

public TcpUploadAverage withValue(double value) {
this.value = value;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public TcpUploadAverage withTime(String time) {
this.time = time;
return this;
}

public Network getNetwork() {
return network;
}

public void setNetwork(Network network) {
this.network = network;
}

public TcpUploadAverage withNetwork(Network network) {
this.network = network;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
