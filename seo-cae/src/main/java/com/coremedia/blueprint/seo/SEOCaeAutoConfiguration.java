package com.coremedia.blueprint.seo;

import com.coremedia.blueprint.base.settings.SettingsService;
import com.coremedia.blueprint.cae.web.taglib.BlueprintFreemarkerFacade;
import com.coremedia.blueprint.seo.structureddata.JsonLdSerializer;
import com.coremedia.blueprint.seo.structureddata.StructuredDataPageHeadViewHookEventListener;
import com.coremedia.blueprint.seo.structureddata.StructuredDataSerializer;
import com.coremedia.blueprint.seo.structureddata.model.ContentBeanToJsonLdModelMapper;
import com.coremedia.objectserver.beans.ContentBeanFactory;
import com.coremedia.objectserver.web.links.LinkFormatter;
import com.coremedia.springframework.customizer.Customize;
import com.coremedia.springframework.xml.ResourceAwareXmlBeanDefinitionReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@AutoConfiguration
@ImportResource(value = {
        "classpath:/framework/spring/blueprint-freemarker-views.xml"
}, reader = ResourceAwareXmlBeanDefinitionReader.class)
public class SEOCaeAutoConfiguration {

  @Bean
  public StructuredDataPageHeadViewHookEventListener seoStructuredDataPageHeadViewHookEventListener(
          SettingsService settingsService,
          StructuredDataSerializer seoStructuredDataSerializer,
          ContentBeanToJsonLdModelMapper seoContentBeanToJsonLdModelMapper) {
    return new StructuredDataPageHeadViewHookEventListener(settingsService, seoStructuredDataSerializer, seoContentBeanToJsonLdModelMapper);
  }

  @Customize(value = "viewRepositories", mode = Customize.Mode.PREPEND)
  @Bean(autowireCandidate = false)
  public String addSEOViewRepositoryName() {
    return "seo";
  }

  @Bean
  public ContentBeanToJsonLdModelMapper seoContentBeanToJsonLdModelMapper(
          ContentBeanFactory contentBeanFactory,
          SettingsService settingsService,
          LinkFormatter linkFormatter,
          BlueprintFreemarkerFacade blueprintFreemarkerFacade) {
    return new ContentBeanToJsonLdModelMapper(contentBeanFactory, settingsService, linkFormatter, blueprintFreemarkerFacade);
  }

  @Bean
  public StructuredDataSerializer seoStructuredDataSerializer(ObjectMapper mapper) {
    return new JsonLdSerializer(mapper);
  }

}
