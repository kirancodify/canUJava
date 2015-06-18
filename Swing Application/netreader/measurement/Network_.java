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

public class Network_ {


private String type;

private long technology;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Network_ withType(String type) {
this.type = type;
return this;
}

public long getTechnology() {
return technology;
}

public void setTechnology(long technology) {
this.technology = technology;
}

public Network_ withTechnology(long technology) {
this.technology = technology;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
