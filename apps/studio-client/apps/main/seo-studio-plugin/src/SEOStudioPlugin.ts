import SEOForm from "@coremedia-blueprint/studio-client.main.blueprint-forms/forms/containers/SEOForm";
import StudioPlugin from "@coremedia/studio-client.main.editor-components/configuration/StudioPlugin";
import Config from "@jangaroo/runtime/Config";
import ConfigUtils from "@jangaroo/runtime/ConfigUtils";
import AddSEORobotFieldsPlugin from "./plugins/AddSEORobotFieldsPlugin";

interface SEOStudioPluginConfig extends Config<StudioPlugin> {
}

class SEOStudioPlugin extends StudioPlugin {
  declare Config: SEOStudioPluginConfig;

  static readonly xtype: string = "com.coremedia.blueprint.seo.studio.config.seoStudioPlugin";

  constructor(config: Config<SEOStudioPlugin> = null) {
    super(ConfigUtils.apply(Config(SEOStudioPlugin, {

      rules: [
        Config(SEOForm, {
          plugins: [
            Config(AddSEORobotFieldsPlugin),
          ],
        }),
      ],

    }), config));
  }
}

export default SEOStudioPlugin;
