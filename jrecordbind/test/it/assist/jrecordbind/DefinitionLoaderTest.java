package it.assist.jrecordbind;

import it.assist.jrecordbind.RecordDefinition.Property;

import java.io.InputStreamReader;

import junit.framework.TestCase;

public class DefinitionLoaderTest extends TestCase {

  public void testDelimiter() throws Exception {
    DefinitionLoader definitionLoader = new DefinitionLoader();
    definitionLoader.load(new InputStreamReader(DefinitionLoaderTest.class.getResourceAsStream("/delimiter.def.xsd")));
    RecordDefinition definition = definitionLoader.getDefinition();

    assertEquals("|", definition.getDelimiter());
    assertEquals(30, definition.getLength());
  }

  public void testHierarchical() throws Exception {
    DefinitionLoader definitionLoader = new DefinitionLoader();
    definitionLoader
        .load(new InputStreamReader(DefinitionLoaderTest.class.getResourceAsStream("/hierarchical.def.xsd")));
    RecordDefinition definition = definitionLoader.getDefinition();

    assertEquals("it.assist_si.schemas.jrb.hierarchical.MasterRecord", definition.getClassName());
    assertEquals("|", definition.getDelimiter());
    assertEquals(36, definition.getLength());

    assertEquals(4, definition.getProperties().size());

    Property property = definition.getProperties().get(0);
    assertEquals("recordId", property.getName());
    assertEquals("String", property.getType());
    assertEquals(3, property.getLength());
    assertEquals("000", property.getFixedValue());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = definition.getProperties().get(1);
    assertEquals("name", property.getName());
    assertEquals("String", property.getType());
    assertEquals(10, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = definition.getProperties().get(2);
    assertEquals("surname", property.getName());
    assertEquals("String", property.getType());
    assertEquals(10, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = definition.getProperties().get(3);
    assertEquals("taxCode", property.getName());
    assertEquals("String", property.getType());
    assertEquals(10, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    assertEquals(2, definition.getSubRecords().size());

    RecordDefinition subDefinition = definition.getSubRecords().get(0);

    assertEquals("it.assist_si.schemas.jrb.hierarchical.RowRecord", subDefinition.getClassName());
    assertEquals("|", subDefinition.getDelimiter());
    assertEquals(0, subDefinition.getMinOccurs());
    assertEquals(-1, subDefinition.getMaxOccurs());
    assertEquals("rows", subDefinition.getName());

    assertEquals(3, subDefinition.getProperties().size());

    property = subDefinition.getProperties().get(0);
    assertEquals("recordId", property.getName());
    assertEquals("String", property.getType());
    assertEquals(3, property.getLength());
    assertEquals("A00", property.getFixedValue());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = subDefinition.getProperties().get(1);
    assertEquals("name", property.getName());
    assertEquals("String", property.getType());
    assertEquals(10, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = subDefinition.getProperties().get(2);
    assertEquals("surname", property.getName());
    assertEquals("String", property.getType());
    assertEquals(10, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    subDefinition = definition.getSubRecords().get(1);
    assertEquals(1, subDefinition.getMinOccurs());
    assertEquals(1, subDefinition.getMaxOccurs());

    assertEquals("it.assist_si.schemas.jrb.hierarchical.ChildRecord", subDefinition.getClassName());
    assertEquals("|", subDefinition.getDelimiter());
    assertEquals("child", subDefinition.getName());

    assertEquals(2, subDefinition.getProperties().size());

    property = subDefinition.getProperties().get(0);
    assertEquals("recordId", property.getName());
    assertEquals("String", property.getType());
    assertEquals(3, property.getLength());
    assertEquals("B01", property.getFixedValue());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = subDefinition.getProperties().get(1);
    assertEquals("when", property.getName());
    assertEquals("java.util.Date", property.getType());
    assertEquals(8, property.getLength());
    assertEquals("it.assist.jrecordbind.test.SimpleRecordDateConverter", property.getConverter());
  }

  public void testMultiRow() throws Exception {
    DefinitionLoader definitionLoader = new DefinitionLoader();
    definitionLoader.load(new InputStreamReader(DefinitionLoaderTest.class.getResourceAsStream("/multi-row.def.xsd")));
    RecordDefinition definition = definitionLoader.getDefinition();
    assertEquals(2, definition.getRows());

    assertEquals("it.assist_si.schemas.jrb.multi_row.MultiRowRecord", definition.getClassName());
    assertEquals("", definition.getDelimiter());
    assertEquals(69, definition.getLength());

    assertEquals(8, definition.getProperties().size());

    Property property = definition.getProperties().get(0);
    assertEquals("name", property.getName());
    assertEquals("String", property.getType());
    assertEquals(20, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());
    assertEquals(0, property.getRow());

    property = definition.getProperties().get(1);
    assertEquals("surname", property.getName());
    assertEquals("String", property.getType());
    assertEquals(20, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());
    assertEquals(0, property.getRow());

    property = definition.getProperties().get(2);
    assertEquals("taxCode", property.getName());
    assertEquals("String", property.getType());
    assertEquals(16, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());
    assertEquals(0, property.getRow());

    property = definition.getProperties().get(3);
    assertEquals("birthday", property.getName());
    assertEquals("java.util.Date", property.getType());
    assertEquals(8, property.getLength());
    assertEquals("it.assist.jrecordbind.test.SimpleRecordDateConverter", property.getConverter());
    assertEquals(0, property.getRow());

    property = definition.getProperties().get(4);
    assertEquals("oneInteger", property.getName());
    assertEquals("Integer", property.getType());
    assertEquals(2, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.IntegerConverter", property.getConverter());
    assertEquals(0, property.getRow());

    property = definition.getProperties().get(5);
    assertEquals("oneFloat", property.getName());
    assertEquals("Float", property.getType());
    assertEquals(3, property.getLength());
    assertEquals("it.assist.jrecordbind.test.SimpleRecordFloatConverter", property.getConverter());
    assertEquals(0, property.getRow());

    property = definition.getProperties().get(6);
    assertEquals("fatherName", property.getName());
    assertEquals("String", property.getType());
    assertEquals(20, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());
    assertEquals(1, property.getRow());

    property = definition.getProperties().get(7);
    assertEquals("motherName", property.getName());
    assertEquals("String", property.getType());
    assertEquals(20, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());
    assertEquals(1, property.getRow());
  }

  public void testSimple() throws Exception {
    DefinitionLoader definitionLoader = new DefinitionLoader();
    definitionLoader.load(new InputStreamReader(DefinitionLoaderTest.class.getResourceAsStream("/simple.def.xsd")));
    RecordDefinition definition = definitionLoader.getDefinition();

    assertEquals("it.assist_si.schemas.jrb.simple.SimpleRecord", definition.getClassName());
    assertEquals("", definition.getDelimiter());
    assertEquals(100, definition.getLength());

    assertEquals(6, definition.getProperties().size());

    Property property = definition.getProperties().get(0);
    assertEquals("name", property.getName());
    assertEquals("String", property.getType());
    assertEquals(20, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = definition.getProperties().get(1);
    assertEquals("surname", property.getName());
    assertEquals("String", property.getType());
    assertEquals(20, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = definition.getProperties().get(2);
    assertEquals("taxCode", property.getName());
    assertEquals("String", property.getType());
    assertEquals(16, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.StringConverter", property.getConverter());

    property = definition.getProperties().get(3);
    assertEquals("birthday", property.getName());
    assertEquals("java.util.Date", property.getType());
    assertEquals(8, property.getLength());
    assertEquals("it.assist.jrecordbind.test.SimpleRecordDateConverter", property.getConverter());

    property = definition.getProperties().get(4);
    assertEquals("oneInteger", property.getName());
    assertEquals("Integer", property.getType());
    assertEquals(2, property.getLength());
    assertEquals("it.assist.jrecordbind.converters.IntegerConverter", property.getConverter());

    property = definition.getProperties().get(5);
    assertEquals("oneFloat", property.getName());
    assertEquals("Float", property.getType());
    assertEquals(3, property.getLength());
    assertEquals("it.assist.jrecordbind.test.SimpleRecordFloatConverter", property.getConverter());
  }

}
