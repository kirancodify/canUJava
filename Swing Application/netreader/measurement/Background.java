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

public class Background {

private long interval;

private String timer;

public long getInterval() {
return interval;
}

public void setInterval(long interval) {
this.interval = interval;
}

public Background withInterval(long interval) {
this.interval = interval;
return this;
}

public String getTimer() {
return timer;
}

public void setTimer(String timer) {
this.timer = timer;
}

public Background withTimer(String timer) {
this.timer = timer;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
