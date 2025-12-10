package com.coremedia.blueprint.seo.structureddata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonJsonLdModel extends JsonLdModel {

  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;

  public PersonJsonLdModel() {
    super("Person");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
