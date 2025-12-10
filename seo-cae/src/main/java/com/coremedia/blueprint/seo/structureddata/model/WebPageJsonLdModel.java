package com.coremedia.blueprint.seo.structureddata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebPageJsonLdModel extends JsonLdModel {

  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;

  @JsonProperty("description")
  private String description;

  public WebPageJsonLdModel() {
    super("WebPage");
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
