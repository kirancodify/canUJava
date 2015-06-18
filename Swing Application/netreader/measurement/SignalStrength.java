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

public class SignalStrength {


private long value;

private String time;

private Gsm gsm;

public long getValue() {
return value;
}

public void setValue(long value) {
this.value = value;
}

public SignalStrength withValue(long value) {
this.value = value;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public SignalStrength withTime(String time) {
this.time = time;
return this;
}

public Gsm getGsm() {
return gsm;
}

public void setGsm(Gsm gsm) {
this.gsm = gsm;
}

public SignalStrength withGsm(Gsm gsm) {
this.gsm = gsm;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
