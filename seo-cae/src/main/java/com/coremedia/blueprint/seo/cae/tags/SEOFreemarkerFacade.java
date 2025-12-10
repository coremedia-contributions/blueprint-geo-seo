package com.coremedia.blueprint.seo.cae.tags;

import com.coremedia.blueprint.base.settings.SettingsService;
import com.coremedia.blueprint.common.contentbeans.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SEOFreemarkerFacade {

  private static final Logger LOG = LoggerFactory.getLogger(SEOFreemarkerFacade.class);

  private static final String DEFAULT_ROBOTS_TAG = "<meta name=\"robots\" content=\"index, follow\" />";
  private static final String ROBOTS_TAG_TPL = "<meta name=\"robots\" content=\"%s\" />";

  private SettingsService settingsService;

  @Autowired
  public void setSettingsService(SettingsService settingsService) {
    this.settingsService = settingsService;
  }

  public List<String> getRobotsOptions(Page page) {
    if (page == null) {
      throw new IllegalArgumentException("Error retrieving SEO settings: page must not be null.");
    }

    List<String> result = new ArrayList<>();

    try {

      Map<String, Object> robotSettings = settingsService.nestedSetting(Arrays.asList("seo", "robots"), Map.class, page);

      if (robotSettings != null) {
        String indexingOtions = (String) robotSettings.get("indexing");
        if (StringUtils.isNotBlank(indexingOtions)) {
          result.add(indexingOtions);
        }

        String followingOptions = (String) robotSettings.get("following");
        if (StringUtils.isNotBlank(followingOptions)) {
          result.add(followingOptions);
        }

        List<String> miscOpts = (List<String>) robotSettings.get("misc");
        if (miscOpts != null && !miscOpts.isEmpty()) {
          result.addAll(miscOpts);
        }

      }

    } catch (Exception e) {
      LOG.warn("Could not retrieve SEO robots options.");
    }

    return result;
  }

  public String getRobotsMetaTag(Page page) {
    String value = DEFAULT_ROBOTS_TAG;
    List<String> robotsOptions = getRobotsOptions(page);
    if (CollectionUtils.isNotEmpty(robotsOptions)) {
      value = String.format(ROBOTS_TAG_TPL, String.join(", ", robotsOptions));
    }
    return value;
  }


}
