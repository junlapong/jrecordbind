package org.jrecordbind;

import java.lang.reflect.Method;

class EnumPropertyHelper {

  private final RecordDefinition.Property property;

  public EnumPropertyHelper(RecordDefinition.Property property) {
    this.property = property;
  }

  public boolean isEnum() {
    Class<?> typeClass;
    try {
      typeClass = getTypeClass();
    } catch (Exception e) {
      return false;
    }

    return Enum.class.isAssignableFrom(typeClass);
  }

  public Class<?> getTypeClass() {
    return Utils.loadClass(property.getType());
  }

  @SuppressWarnings("unchecked")
  public Enum<?>[] getValues() {
    try {
      Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) getTypeClass();
      Method method = enumClass.getMethod("values");
      return (Enum<?>[]) method.invoke(enumClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String[] getStringValues() {
    Enum<?>[] values = getValues();
    String[] stringValues = new String[values.length];
    try {
      Method valueMethod = values[0].getClass().getMethod("value");
      for (int i = 0; i < values.length; i++) {
        stringValues[i] = (String) valueMethod.invoke(values[i]);
      }
      return stringValues;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
