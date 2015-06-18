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

public class Subscriber {
    



private String brandName;

private String mcc;

private String mnc;

private String imsi;

public String getBrandName() {
return brandName;
}

public void setBrandName(String brandName) {
this.brandName = brandName;
}

public Subscriber withBrandName(String brandName) {
this.brandName = brandName;
return this;
}

public String getMcc() {
return mcc;
}

public void setMcc(String mcc) {
this.mcc = mcc;
}

public Subscriber withMcc(String mcc) {
this.mcc = mcc;
return this;
}

public String getMnc() {
return mnc;
}

public void setMnc(String mnc) {
this.mnc = mnc;
}

public Subscriber withMnc(String mnc) {
this.mnc = mnc;
return this;
}

public String getImsi() {
return imsi;
}

public void setImsi(String imsi) {
this.imsi = imsi;
}

public Subscriber withImsi(String imsi) {
this.imsi = imsi;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}