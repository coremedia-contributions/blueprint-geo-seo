package com.coremedia.blueprint.seo.structureddata;

import edu.umd.cs.findbugs.annotations.Nullable;

public interface StructuredDataSerializer {

  /**
   * Serialize the given object by providing a {@link String} representation.
   *
   * @param object input object
   * @return
   */
  @Nullable
  String serialize(@Nullable Object object);

}
