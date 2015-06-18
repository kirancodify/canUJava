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

public class Sdk {


private String version;

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public Sdk withVersion(String version) {
this.version = version;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
