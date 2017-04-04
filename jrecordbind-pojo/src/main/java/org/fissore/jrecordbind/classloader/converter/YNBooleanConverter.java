package org.fissore.jrecordbind.classloader.converter;

import org.fissore.jrecordbind.Converter;

public class YNBooleanConverter implements Converter {

  public Object convert(String value) {
    if (value == null || value.length() == 0) {
      return Boolean.FALSE;
    }
    return Boolean.valueOf(value);
  }

  public String toString(Object value) {
    boolean b = ((Boolean) value).booleanValue();
    return b ? "Y" : "N";
  }

}
