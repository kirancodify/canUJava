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

public class Location {


private double longitude;

private String time;

private double latitude;

private String source;

private float accuracy;

private float speed;

public double getLongitude() {
return longitude;
}

public void setLongitude(double longitude) {
this.longitude = longitude;
}

public Location withLongitude(double longitude) {
this.longitude = longitude;
return this;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public Location withTime(String time) {
this.time = time;
return this;
}

public double getLatitude() {
return latitude;
}

public void setLatitude(double latitude) {
this.latitude = latitude;
}

public Location withLatitude(double latitude) {
this.latitude = latitude;
return this;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public Location withSource(String source) {
this.source = source;
return this;
}

public float getAccuracy() {
return accuracy;
}

public void setAccuracy(long accuracy) {
this.accuracy = accuracy;
}

public Location withAccuracy(long accuracy) {
this.accuracy = accuracy;
return this;
}

public float getSpeed() {
return speed;
}

public void setSpeed(long speed) {
this.speed = speed;
}

public Location withSpeed(long speed) {
this.speed = speed;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}