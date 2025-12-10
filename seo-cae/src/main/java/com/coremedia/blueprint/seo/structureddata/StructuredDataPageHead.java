package com.coremedia.blueprint.seo.structureddata;

/**
 * POJO used for Freemarker template resolving.
 */
public class StructuredDataPageHead {

  private String structuredData;

  public StructuredDataPageHead(String structuredData) {
    this.structuredData = structuredData;
  }

  public String getStructuredData() {
    return structuredData;
  }

  public void setStructuredData(String structuredData) {
    this.structuredData = structuredData;
  }
}
