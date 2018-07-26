package com.uzabase.rsseater.convert;

import com.sun.xml.internal.stream.events.StartElementEvent;

import javax.xml.stream.events.StartElement;

/**
 * 
 *
 * @author Moath
 */
public class MyStartElement extends StartElementEvent {
    public MyStartElement(String prefix, String uri, String localpart) {
        super(prefix, uri, localpart);
    }


}
