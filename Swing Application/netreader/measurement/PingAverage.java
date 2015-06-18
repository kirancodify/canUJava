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

public class PingAverage {


private double value;

private String time;

public double getValue() {
return value;
}

public void setValue(double value) {
this.value = value;
}

public PingAverage withValue(double value) {
this.value = value;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public PingAverage withTime(String time) {
this.time = time;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
