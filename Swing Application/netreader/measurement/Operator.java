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

public class Operator {


private Data data;

private Network___ network;

private Subscriber subscriber;

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public Operator withData(Data data) {
this.data = data;
return this;
}

public Network___ getNetwork() {
return network;
}

public void setNetwork(Network___ network) {
this.network = network;
}

public Operator withNetwork(Network___ network) {
this.network = network;
return this;
}

public Subscriber getSubscriber() {
return subscriber;
}

public void setSubscriber(Subscriber subscriber) {
this.subscriber = subscriber;
}

public Operator withSubscriber(Subscriber subscriber) {
this.subscriber = subscriber;
return this;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}
