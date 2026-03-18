package com.coremedia.blueprint.seo.structureddata;

import com.coremedia.blueprint.base.settings.SettingsService;
import com.coremedia.blueprint.common.contentbeans.Page;
import com.coremedia.blueprint.seo.structureddata.model.ContentBeanToJsonLdModelMapper;
import com.coremedia.objectserver.beans.ContentBean;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

public class StructuredDataProvider {

  public static final String SERVICE_KEY = "seo";
  public static final String ENABLED = "renderStructuredDataPageHead";

  private final Page page;
  private final SettingsService settingsService;
  private final StructuredDataSerializer serializer;
  private final ContentBeanToJsonLdModelMapper contentBeanToJsonLdModelMapper;

  public StructuredDataProvider(@NonNull Page page,
                                @NonNull SettingsService settingsService,
                                @NonNull StructuredDataSerializer serializer,
                                @NonNull ContentBeanToJsonLdModelMapper contentBeanToJsonLdModelMapper) {
    this.page = page;
    this.settingsService = settingsService;
    this.serializer = serializer;
    this.contentBeanToJsonLdModelMapper = contentBeanToJsonLdModelMapper;
  }

  public Optional<StructuredDataPageHead> getStructuredDataPageHead() {
    Optional<StructuredDataPageHead> optional = Optional.empty();

    Optional<Object> model = Optional.of(page)
            .map(Page::getContent)
            .filter(ContentBean.class::isInstance)
            .map(ContentBean.class::cast)
            .map((bean) -> contentBeanToJsonLdModelMapper.map(bean, page));

    if (model.isPresent()) {
      String data = serializer.serialize(model.get());
      optional = Optional.of(new StructuredDataPageHead(data));
    }

    return optional;
  }

  public boolean isEnabled() {
    Map<String, Object> settingsMap = settingsService.settingAsMap(SERVICE_KEY, String.class, Object.class, page);
    return settingsMap.containsKey(ENABLED) && Boolean.TRUE.equals(settingsMap.get(ENABLED));
  }

}
