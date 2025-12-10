package com.coremedia.blueprint.seo.structureddata;

import com.coremedia.blueprint.common.contentbeans.Page;
import com.coremedia.blueprint.seo.structureddata.model.ContentBeanToJsonLdModelMapper;
import com.coremedia.objectserver.beans.ContentBean;
import com.coremedia.objectserver.view.RenderNode;
import com.coremedia.objectserver.view.events.ViewHookEvent;
import com.coremedia.objectserver.view.events.ViewHookEventListener;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Optional;

import static com.coremedia.blueprint.base.cae.web.taglib.ViewHookEventNames.VIEW_HOOK_HEAD;

/**
 * {@link ViewHookEventListener} used to add structured data markup (JSON-LD) to the page head.
 */
public class StructuredDataPageHeadViewHookEventListener implements ViewHookEventListener<Page> {

  private final StructuredDataSerializer serializer;
  private final ContentBeanToJsonLdModelMapper contentBeanToJsonLdModelMapper;

  public StructuredDataPageHeadViewHookEventListener(@NonNull StructuredDataSerializer serializer, @NonNull ContentBeanToJsonLdModelMapper contentBeanToJsonLdModelMapper) {
    this.serializer = serializer;
    this.contentBeanToJsonLdModelMapper = contentBeanToJsonLdModelMapper;
  }

  @Override
  public RenderNode onViewHook(ViewHookEvent<Page> event) {
    if (VIEW_HOOK_HEAD.equals(event.getId())) {
      Page page = event.getBean();

      Optional<Object> model = Optional.of(page)
        .map(Page::getContent)
        .filter(ContentBean.class::isInstance)
        .map(ContentBean.class::cast)
        .map((bean) -> contentBeanToJsonLdModelMapper.map(bean, page));

      if (model.isPresent()) {
          String data = serializer.serialize(model.get());
          return new RenderNode(new StructuredDataPageHead(data), VIEW_HOOK_HEAD);
      }
    }
    return null;
  }

  @Override
  public int getOrder() {
    return DEFAULT_ORDER;
  }
}
