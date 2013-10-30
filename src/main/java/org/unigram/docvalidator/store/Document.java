/**
 * DocumentValidator
 * Copyright (c) 2013-, Takahiko Ito, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package org.unigram.docvalidator.store;

import java.util.Iterator;
import java.util.Vector;

/**
 * Document class represent input document, which consists
 * of more than one files.
 */
public final class Document implements Block {

  public Iterator<FileContent> getFiles() {
    return files.iterator();
  }

  public void appendFile(FileContent file) {
    files.add(file);
  }

  public Document() {
    super();
    files = new Vector<FileContent>();
  }

  public FileContent getLastSection() {
    return files.lastElement();
  }

  public int getFileNumber() {
    return files.size();
  }

  public int getBlockID() {
    return BlockTypes.DOCUMENT;
  }

  public int extractSummary() {
    // extract summary information from files
    // extract total summary
    return 0;
  }

  private Vector<FileContent> files;
}
