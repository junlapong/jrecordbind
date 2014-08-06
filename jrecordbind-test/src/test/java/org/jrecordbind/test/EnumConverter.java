package org.jrecordbind.test;

import org.jrecordbind.Converter;

public class EnumConverter implements Converter {

  public Object convert(String value) {
    return MyEnum.valueOf(value.trim());
  }

  public String toString(Object value) {
    return ((MyEnum) value).name();
  }

}
