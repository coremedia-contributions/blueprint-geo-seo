package com.coremedia.blueprint.seo;

import com.coremedia.blueprint.base.settings.SettingsService;
import com.coremedia.blueprint.cae.web.taglib.BlueprintFreemarkerFacade;
import com.coremedia.blueprint.seo.cae.tags.SEOFreemarkerFacade;
import com.coremedia.blueprint.seo.structureddata.JsonLdSerializer;
import com.coremedia.blueprint.seo.structureddata.StructuredDataPageHeadViewHookEventListener;
import com.coremedia.blueprint.seo.structureddata.StructuredDataSerializer;
import com.coremedia.blueprint.seo.structureddata.model.ContentBeanToJsonLdModelMapper;
import com.coremedia.objectserver.beans.ContentBeanFactory;
import com.coremedia.objectserver.web.links.LinkFormatter;
import com.coremedia.springframework.customizer.Customize;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.freemarker.autoconfigure.FreeMarkerAutoConfiguration;
import org.springframework.boot.freemarker.autoconfigure.FreeMarkerVariablesCustomizer;
import org.springframework.boot.jackson2.autoconfigure.Jackson2AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import freemarker.template.Configuration;

@AutoConfiguration(after = {FreeMarkerAutoConfiguration.class, Jackson2AutoConfiguration.class})
@Import(Jackson2AutoConfiguration.class)
public class SEOCaeAutoConfiguration {

  @Bean
  public StructuredDataPageHeadViewHookEventListener seoStructuredDataPageHeadViewHookEventListener(
    StructuredDataSerializer seoStructuredDataSerializer,
    ContentBeanToJsonLdModelMapper seoContentBeanToJsonLdModelMapper) {
    return new StructuredDataPageHeadViewHookEventListener(seoStructuredDataSerializer, seoContentBeanToJsonLdModelMapper);
  }

  @Customize(value = "viewRepositories", mode = Customize.Mode.PREPEND)
  @Bean(autowireCandidate = false)
  public String addSEOViewRepositoryName() {
    return "seo";
  }

  @Bean
  SEOFreemarkerFacade seoFreemarkerFacade(SettingsService settingsService) {
    return new SEOFreemarkerFacade(settingsService);
  }

  @Bean
  FreeMarkerVariablesCustomizer seoFreemarkerSharedVariablesCustomizer(SEOFreemarkerFacade seoFreemarkerFacade) {
    return variables -> variables.put("seoFreemarkerFacade", seoFreemarkerFacade);
  }

  /**
   * Add an auto-import for the SEO namespace in Freemarker templates.
   * @param configuration Freemarker configuration
   * @return
   */
  @Bean
  public Object seoFreemarkerAutoImport(Configuration configuration) {
    configuration.addAutoImport("seo", "/lib/coremedia.com/blueprint/seo.ftl");
    return new Object(); // side-effect bean
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
  public StructuredDataSerializer seoStructuredDataSerializer(@NonNull ObjectMapper mapper) {
    return new JsonLdSerializer(mapper);
  }

}
