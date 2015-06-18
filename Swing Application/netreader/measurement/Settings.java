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

public class Settings {


private Background background;

public Background getBackground() {
return background;
}

public void setBackground(Background background) {
this.background = background;
}

public Settings withBackground(Background background) {
this.background = background;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
