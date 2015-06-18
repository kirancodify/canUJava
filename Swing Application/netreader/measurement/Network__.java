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

public class Network__ {


private String time;

private String cellId;

private long technology;

private String type;

private String areaCode;

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public Network__ withTime(String time) {
this.time = time;
return this;
}

public String getCellId() {
return cellId;
}

public void setCellId(String cellId) {
this.cellId = cellId;
}

public Network__ withCellId(String cellId) {
this.cellId = cellId;
return this;
}

public long getTechnology() {
return technology;
}

public void setTechnology(long technology) {
this.technology = technology;
}

public Network__ withTechnology(long technology) {
this.technology = technology;
return this;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Network__ withType(String type) {
this.type = type;
return this;
}

public String getAreaCode() {
return areaCode;
}

public void setAreaCode(String areaCode) {
this.areaCode = areaCode;
}

public Network__ withAreaCode(String areaCode) {
this.areaCode = areaCode;
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
        Network__ guest = (Network__ ) other;
        return (areaCode.equals(guest.areaCode) || (  areaCode != null && areaCode.equals(guest.getAreaCode()))) && (cellId == guest.cellId || (  cellId != null && cellId.equals(guest.getCellId())));
        
    }

@Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.areaCode);
        hash = 67 * hash + Objects.hashCode(this.cellId);
        
        return hash;
    }

}