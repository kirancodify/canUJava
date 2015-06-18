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

public class Battery {


private boolean charging;

private long voltage;

private String time;

private Long level;

public boolean isCharging() {
return charging;
}

public void setCharging(boolean charging) {
this.charging = charging;
}

public Battery withCharging(boolean charging) {
this.charging = charging;
return this;
}

public long getVoltage() {
return voltage;
}

public void setVoltage(long voltage) {
this.voltage = voltage;
}

public Battery withVoltage(long voltage) {
this.voltage = voltage;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public Battery withTime(String time) {
this.time = time;
return this;
}

public Long getLevel() {
return level;
}

public void setLevel(Long level) {
this.level = level;
}

public Battery withLevel(Long level) {
this.level = level;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
