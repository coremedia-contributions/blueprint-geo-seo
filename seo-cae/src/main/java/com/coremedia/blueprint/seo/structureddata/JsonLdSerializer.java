package com.coremedia.blueprint.seo.structureddata;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class JsonLdSerializer implements StructuredDataSerializer {

  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private ObjectMapper mapper;

  public JsonLdSerializer(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Nullable
  public String serialize(@Nullable Object object) {
    String result = null;
    try {
      if (object != null) {
        result = mapper.writeValueAsString(object);
      }
    } catch (JacksonException e) {
      LOG.error("unable to serialize object: {}", e.getMessage());
    }
    return result;
  }

}
