const { jangarooConfig } = require("@jangaroo/core");

module.exports = jangarooConfig({
  type: "code",
  sencha: {
    name: "com.coremedia.blueprint__seo-studio-plugin",
    namespace: "com.coremedia.blueprint.seo.studio",
    studioPlugins: [
      {
        mainClass: "com.coremedia.blueprint.seo.studio.SEOStudioPlugin",
        name: "SEO",
      },
    ],
  },
});
