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

package org.fissore.jrecordbind;

import org.xml.sax.InputSource;

abstract class AbstractUnMarshaller {

  protected final ConvertersCache converters;
  protected final RecordDefinition definition;
  protected final Cache<Padder> padders;
  protected final PropertyUtils propertyUtils;

  public AbstractUnMarshaller(InputSource input) {
    this(AbstractUnMarshaller.class.getClassLoader(), input);
  }

  public AbstractUnMarshaller(ClassLoader classloader, InputSource input) {
    this.definition = new DefinitionLoader(classloader, input).load().getDefinition();
    this.converters = new ConvertersCache(classloader, definition);
    this.padders = new PaddersCache(classloader, definition);
    this.propertyUtils = new PropertyUtils();
  }

}
