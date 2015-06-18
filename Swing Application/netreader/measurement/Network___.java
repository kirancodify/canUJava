/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.measurement;

/**
 *
 * @author kumark2
 */
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Network___ {


private String brandName;

private String mcc;

private String mnc;

public String getBrandName() {
return brandName;
}

public void setBrandName(String brandName) {
this.brandName = brandName;
}

public Network___ withBrandName(String brandName) {
this.brandName = brandName;
return this;
}

public String getMcc() {
return mcc;
}

public void setMcc(String mcc) {
this.mcc = mcc;
}

public Network___ withMcc(String mcc) {
this.mcc = mcc;
return this;
}

public String getMnc() {
return mnc;
}

public void setMnc(String mnc) {
this.mnc = mnc;
}

public Network___ withMnc(String mnc) {
this.mnc = mnc;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public boolean equals(Object other){
    if (this== other){
        return true;
    }
    if (other==null ||(this.getClass() != other.getClass())){
        return false;
    }   
        Network___ guest = (Network___ ) other;
        return (mcc == guest.mcc || (  mcc != null && mcc.equals(guest.getMcc()))) && (mnc == guest.mnc || (  mcc != null && mnc.equals(guest.getMnc())))
                && (brandName == guest.brandName || (  brandName != null && brandName.equals(guest.getBrandName())));
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.mcc);
        hash = 67 * hash + Objects.hashCode(this.mnc);
        hash = 67 * hash + Objects.hashCode(this.brandName);
        return hash;
    }

}
