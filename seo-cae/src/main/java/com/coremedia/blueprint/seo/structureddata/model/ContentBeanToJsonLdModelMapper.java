package com.coremedia.blueprint.seo.structureddata.model;

import com.coremedia.blueprint.base.settings.SettingsService;
import com.coremedia.blueprint.cae.web.WebEnvironment;
import com.coremedia.blueprint.cae.web.taglib.BlueprintFreemarkerFacade;
import com.coremedia.blueprint.common.contentbeans.CMArticle;
import com.coremedia.blueprint.common.contentbeans.CMPerson;
import com.coremedia.blueprint.common.contentbeans.CMPicture;
import com.coremedia.blueprint.common.contentbeans.Page;
import com.coremedia.cap.content.Content;
import com.coremedia.cap.content.ContentType;
import com.coremedia.objectserver.beans.ContentBean;
import com.coremedia.objectserver.beans.ContentBeanFactory;
import com.coremedia.objectserver.web.links.LinkFormatter;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContentBeanToJsonLdModelMapper {

  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  // default image aspect ratios to use form image urls
  // TODO: Move to settings to configure via content
  private static final List<String> IMAGE_ASPECT_RATIOS = List.of("portrait_ratio1x1", "landscape_ratio4x3", "landscape_ratio16x9");

  private final ContentBeanFactory contentBeanFactory;
  private final SettingsService settingsService;
  private final LinkFormatter linkFormatter;
  private final BlueprintFreemarkerFacade blueprintFreemarkerFacade;

  public ContentBeanToJsonLdModelMapper(ContentBeanFactory contentBeanFactory,
                                        SettingsService settingsService,
                                        LinkFormatter linkFormatter,
                                        BlueprintFreemarkerFacade blueprintFreemarkerFacade) {
    this.contentBeanFactory = contentBeanFactory;
    this.settingsService = settingsService;
    this.linkFormatter = linkFormatter;
    this.blueprintFreemarkerFacade = blueprintFreemarkerFacade;
  }

  /**
   * Map the given {@link ContentBean} to a {@link JsonLdModel}.
   *
   * @param contentBean content bean to map
   * @param containingPage page containing the content bean used as context
   *
   * @return
   */
  @Nullable
  public JsonLdModel map(ContentBean contentBean, Page containingPage) {
    JsonLdModel model = null;

    try {
      Optional<Content> content = Optional.ofNullable(contentBean).map(ContentBean::getContent);
      ContentType contentType = content.map(Content::getType).orElse(null);


      if (content.isPresent() && contentType != null) {
        if (contentType.isSubtypeOf("CMArticle")) {
          model = createArticleJsonLdModel(contentBeanFactory.createBeanFor(content.get(), CMArticle.class), containingPage);
        } else if (contentType.isSubtypeOf("CMPerson")) {
          model = createPersonJsonLdModel(contentBeanFactory.createBeanFor(content.get(), CMPerson.class), containingPage);
        }
      }

    } catch (Exception e) {
      LOG.error("Error while trying to map content bean to JSON-LD model", e);
    }

    return model;
  }

  protected ArticleJsonLdModel createArticleJsonLdModel(@Nullable CMArticle article, @NonNull Page containingPage) {
    if (article == null) {
      return null;
    }

    ArticleJsonLdModel m = new ArticleJsonLdModel();
    m.setHeadline(article.getTitle());
    m.setDateModified(convertToOffsetDateTime(article.getExternallyDisplayedDate()));
    m.setDatePublished(convertToOffsetDateTime(article.getExternallyDisplayedDate()));

    if (article.getPicture() != null) {
      m.setImageUrls(imageUrlsForPicture(article.getPicture(),  containingPage));
    }

    // add authors
    Optional.of(article.getAuthors().stream().map(author -> createPersonJsonLdModel(author, containingPage)).toList()).ifPresent(authors -> m.setAuthors(authors));

    return m;
  }

  protected PersonJsonLdModel createPersonJsonLdModel(@Nullable CMPerson person, @NonNull Page containingPage) {
    if (person == null) {
      return null;
    }

    PersonJsonLdModel m = new PersonJsonLdModel();
    m.setName(person.getDisplayName());
    m.setUrl(linkFormatter.formatLink(person, null, WebEnvironment.getCurrentRequest(), WebEnvironment.getCurrentResponse(), false));
    return m;
  }


  // --- Helpers ---

  private List<String> imageUrlsForPicture(CMPicture picture, Page containingPage) {
    List<String> imageUrls = new ArrayList<>();
    for (String aspectRatio : IMAGE_ASPECT_RATIOS) {
      imageUrls.add(getImageLink(picture, containingPage, aspectRatio));
    }
    return imageUrls;
  }

  /**
   * Converts the given {@link Calendar} to a {@link OffsetDateTime} in UTC time-zone.
   *
   * @param calendar calendar to convert
   * @return
   */
  protected OffsetDateTime convertToOffsetDateTime(Calendar calendar) {
    return calendar.toInstant().atOffset(ZoneOffset.UTC);
  }

  /**
   * Get the biggest image link for the given picture with the given aspect ratio.
   *
   * @param picture     picture to build link for
   * @param page        page context for responsive image settings resolving
   * @param aspectRatio aspect ratio name as configured in responsive image settings
   * @return
   */
  protected String getImageLink(CMPicture picture, Page page, String aspectRatio) {
    return blueprintFreemarkerFacade.getLinkForBiggestImageWithRatio(picture, page, aspectRatio, true);
  }

}
