<#ftl strip_whitespace=true>
<#-- @ftlvariable name="seoFreemarkerFacade" type="com.coremedia.blueprint.seo.cae.tags.SEOFreemarkerFacade" -->

<#-- GET ROBOTS META TAG FOR PAGE -->
<#function robotsMetaTag page>
    <#return seoFreemarkerFacade.getRobotsMetaTag(page)>
</#function>

<#-- GET ROBOTS OPTIONS FOR PAGE -->
<#function robotsOptions page>
  <#return seoFreemarkerFacade.getRobotsOptions(page)>
</#function>
