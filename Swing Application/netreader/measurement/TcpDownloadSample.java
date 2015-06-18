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

public class TcpDownloadSample {

private long value;

private String time;

public long getValue() {
return value;
}

public void setValue(long value) {
this.value = value;
}

public TcpDownloadSample withValue(long value) {
this.value = value;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public TcpDownloadSample withTime(String time) {
this.time = time;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
