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

public class Client {


private String platform;

private long publicPort;

private String localIpAddress;

private String publicIpAddress;

private String brand;

private String installationId;

private long localPort;

private Settings settings;

private Sdk sdk;

private Application application;

public String getPlatform() {
return platform;
}

public void setPlatform(String platform) {
this.platform = platform;
}

public Client withPlatform(String platform) {
this.platform = platform;
return this;
}

public long getPublicPort() {
return publicPort;
}

public void setPublicPort(long publicPort) {
this.publicPort = publicPort;
}

public Client withPublicPort(long publicPort) {
this.publicPort = publicPort;
return this;
}

public String getLocalIpAddress() {
return localIpAddress;
}

public void setLocalIpAddress(String localIpAddress) {
this.localIpAddress = localIpAddress;
}

public Client withLocalIpAddress(String localIpAddress) {
this.localIpAddress = localIpAddress;
return this;
}

public String getPublicIpAddress() {
return publicIpAddress;
}

public void setPublicIpAddress(String publicIpAddress) {
this.publicIpAddress = publicIpAddress;
}

public Client withPublicIpAddress(String publicIpAddress) {
this.publicIpAddress = publicIpAddress;
return this;
}

public String getBrand() {
return brand;
}

public void setBrand(String brand) {
this.brand = brand;
}

public Client withBrand(String brand) {
this.brand = brand;
return this;
}

public String getInstallationId() {
return installationId;
}

public void setInstallationId(String installationId) {
this.installationId = installationId;
}

public Client withInstallationId(String installationId) {
this.installationId = installationId;
return this;
}

public long getLocalPort() {
return localPort;
}

public void setLocalPort(long localPort) {
this.localPort = localPort;
}

public Client withLocalPort(long localPort) {
this.localPort = localPort;
return this;
}

public Settings getSettings() {
return settings;
}

public void setSettings(Settings settings) {
this.settings = settings;
}

public Client withSettings(Settings settings) {
this.settings = settings;
return this;
}

public Sdk getSdk() {
return sdk;
}

public void setSdk(Sdk sdk) {
this.sdk = sdk;
}

public Client withSdk(Sdk sdk) {
this.sdk = sdk;
return this;
}

public Application getApplication() {
return application;
}

public void setApplication(Application application) {
this.application = application;
}

public Client withApplication(Application application) {
this.application = application;
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
        Client guest = (Client) other;
        return (publicIpAddress == guest.publicIpAddress || (  publicIpAddress != null && publicIpAddress.equals(guest.getPublicIpAddress())));
    }

@Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.publicIpAddress);
    
        return hash;
    }

}