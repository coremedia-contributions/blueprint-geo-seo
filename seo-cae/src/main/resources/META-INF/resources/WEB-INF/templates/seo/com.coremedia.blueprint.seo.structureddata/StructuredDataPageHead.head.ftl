<#-- @ftlvariable name="self" type="com.coremedia.blueprint.seo.structureddata.StructuredDataPageHead" -->
<#if self.structuredData?has_content>
<script type="application/ld+json">
${self.structuredData?no_esc}
</script>
</#if>
