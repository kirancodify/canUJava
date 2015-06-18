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

public class Device {


private String model;

private String vendor;

private String osVersion;

public String getModel() {
return model;
}

public void setModel(String model) {
this.model = model;
}

public Device withModel(String model) {
this.model = model;
return this;
}

public String getVendor() {
return vendor;
}

public void setVendor(String vendor) {
this.vendor = vendor;
}

public Device withVendor(String vendor) {
this.vendor = vendor;
return this;
}

public String getOsVersion() {
return osVersion;
}

public void setOsVersion(String osVersion) {
this.osVersion = osVersion;
}

public Device withOsVersion(String osVersion) {
this.osVersion = osVersion;
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
        Device guest = (Device) other;
        return (model == guest.model || (  model != null && model.equals(guest.getModel()))) && (vendor == guest.vendor || (  vendor != null && vendor.equals(guest.getVendor())))
                && (osVersion == guest.osVersion || (  osVersion != null && osVersion.equals(guest.getOsVersion())));
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.model);
        hash = 67 * hash + Objects.hashCode(this.vendor);
        hash = 67 * hash + Objects.hashCode(this.osVersion);
        return hash;
    }
}

    

