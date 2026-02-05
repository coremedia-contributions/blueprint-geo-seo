package com.coremedia.blueprint.seo.cae.tags;

import com.coremedia.blueprint.base.settings.SettingsService;
import com.coremedia.blueprint.common.contentbeans.Page;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SEOFreemarkerFacadeTest {

  private SEOFreemarkerFacade testling;

  @Mock
  private SettingsService settingsService;

  @Mock
  private Page page;

  @Before
  public void setUp() {
    testling = new SEOFreemarkerFacade(settingsService);
  }

  @Test
  public void testGetRobotsMetaTagWithEmptyOptions() {
    String expected = "<meta name=\"robots\" content=\"index, follow\" />";
    when(settingsService.nestedSetting(Arrays.asList("seo", "robots"), Map.class, page)).thenReturn(Collections.emptyMap());
    assertEquals(expected, testling.getRobotsMetaTag(page));
  }

  @Test
  public void testGetRobotsMetaTagWithBaseOptions() {
    String expected = "<meta name=\"robots\" content=\"noindex, nofollow\" />";
    Map<String, Object> optionsMap = Map.of(
            "indexing", "noindex",
            "following", "nofollow"
    );
    when(settingsService.nestedSetting(Arrays.asList("seo", "robots"), Map.class, page)).thenReturn(optionsMap);
    assertEquals(expected, testling.getRobotsMetaTag(page));
  }

  @Test
  public void testGetRobotsMetaTagWithExtendedOptions() {
    String expected = "<meta name=\"robots\" content=\"noindex, follow, noarchive, nosnippet, notranslate\" />";
    Map<String, Object> optionsMap = Map.of(
            "indexing", "noindex",
            "following", "follow",
            "misc", List.of("noarchive", "nosnippet", "notranslate")
    );
    when(settingsService.nestedSetting(Arrays.asList("seo", "robots"), Map.class, page)).thenReturn(optionsMap);
    assertEquals(expected, testling.getRobotsMetaTag(page));
  }

}
