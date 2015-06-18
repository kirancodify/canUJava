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

public class User {


private String uid;

private String provider;

public String getUid() {
return uid;
}

public void setUid(String uid) {
this.uid = uid;
}

public User withUid(String uid) {
this.uid = uid;
return this;
}

public String getProvider() {
return provider;
}

public void setProvider(String provider) {
this.provider = provider;
}

public User withProvider(String provider) {
this.provider = provider;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}