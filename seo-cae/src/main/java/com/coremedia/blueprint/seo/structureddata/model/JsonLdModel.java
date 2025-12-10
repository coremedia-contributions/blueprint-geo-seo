package com.coremedia.blueprint.seo.structureddata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class JsonLdModel {

  @JsonProperty("@context")
  private final String context = "https://schema.org";

  @JsonProperty("@type")
  private final String type;

  protected JsonLdModel(String type) {
    this.type = type;
  }

}
