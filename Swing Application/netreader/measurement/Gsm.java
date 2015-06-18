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


public class Gsm {


private long rssi;

private long ber;

public long getRssi() {
return rssi;
}

public void setRssi(long rssi) {
this.rssi = rssi;
}

public Gsm withRssi(long rssi) {
this.rssi = rssi;
return this;
}

public long getBer() {
return ber;
}

public void setBer(long ber) {
this.ber = ber;
}

public Gsm withBer(long ber) {
this.ber = ber;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
