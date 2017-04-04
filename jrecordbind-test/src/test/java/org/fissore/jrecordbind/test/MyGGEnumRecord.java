package org.fissore.jrecordbind.test;

import org.jrecordbind.schemas.jrb.ggenum.GGEnumRecord;

public class MyGGEnumRecord extends GGEnumRecord {

  @Override
  public MyEnum getMyEnum() {
    return (MyEnum) super.getMyEnum();
  }

}
