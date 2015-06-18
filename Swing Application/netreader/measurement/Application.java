/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.measurement;


/**
 *
 * @author kumark2
 */

import com.google.gson.annotations.Expose;
import java.lang.StringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Application {
    

private String version;
private String name;

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public Application withVersion(String version) {
this.version = version;
return this;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Application withName(String name) {
this.name = name;
return this;
}
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
