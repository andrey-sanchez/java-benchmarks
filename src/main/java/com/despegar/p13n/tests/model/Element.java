package com.despegar.p13n.tests.model;

public class Element {
  private final EnumElement enumElement;
  private final String someString;

  public Element(EnumElement enumElement, String someString) {
    this.enumElement = enumElement;
    this.someString = someString;
  }

  public EnumElement getEnumElement() {
    return this.enumElement;
  }

  public String getSomeString() {
    return this.someString;
  }
}
