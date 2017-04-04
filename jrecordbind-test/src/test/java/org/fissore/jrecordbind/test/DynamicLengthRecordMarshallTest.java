/*
 * JRecordBind, fixed-length file (un)marshaller
 * Copyright 2009, Assist s.r.l., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.fissore.jrecordbind.test;

import static org.junit.Assert.*;
import org.fissore.jrecordbind.Marshaller;
import org.jrecordbind.schemas.jrb.dynamiclength.DynamicRecord;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class DynamicLengthRecordMarshallTest {

  private Marshaller<DynamicRecord> marshaller;
  private DynamicRecord record;
  private StringWriter stringWriter;

  @Test
  public void marshallALot() throws Exception {
    for (int i = 0; i < 100000; i++) {
      marshaller.marshall(record, stringWriter);
    }

    assertEquals(5100000, stringWriter.toString().length());
  }

  @Test
  public void marshallMore() throws Exception {
    marshaller.marshall(record, stringWriter);
    marshaller.marshall(record, stringWriter);

    assertEquals("A NAME|A SURNAME|ABCDEF88L99H123B|19790518|81|197|\n"
        + "A NAME|A SURNAME|ABCDEF88L99H123B|19790518|81|197|\n", stringWriter.toString());
  }

  @Test
  public void marshallOne() throws Exception {
    marshaller.marshall(record, stringWriter);

    assertEquals("A NAME|A SURNAME|ABCDEF88L99H123B|19790518|81|197|\n", stringWriter.toString());
  }

  @Test
  public void marshallOneNoLengthLimit() throws Exception {
    record.setName("1234567890123456789012345");
    marshaller.marshall(record, stringWriter);

    assertEquals("1234567890123456789012345|A SURNAME|ABCDEF88L99H123B|19790518|81|197|\n", stringWriter.toString());
  }

  @Before
  public void setUp() throws Exception {
    record = new DynamicRecord();
    record.setName("A NAME");
    record.setSurname("A SURNAME");
    record.setTaxCode("ABCDEF88L99H123B");

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1979);
    calendar.set(Calendar.MONTH, 4);
    calendar.set(Calendar.DAY_OF_MONTH, 18);
    record.setBirthday(calendar);

    record.setOneInteger(81);
    record.setOneFloat(1.97f);

    marshaller = new Marshaller<DynamicRecord>(new InputStreamReader(DynamicLengthRecordMarshallTest.class
        .getResourceAsStream("/dynamicLength.def.xsd")));

    stringWriter = new StringWriter();
  }
}
