import ValueExpressionFactory from "@coremedia/studio-client.client-core/data/ValueExpressionFactory";
import BoundRadioGroup from "@coremedia/studio-client.ext.ui-components/components/BoundRadioGroup";
import ConfigBasedValueExpression from "@coremedia/studio-client.ext.ui-components/data/ConfigBasedValueExpression";
import BindPropertyPlugin from "@coremedia/studio-client.ext.ui-components/plugins/BindPropertyPlugin";
import BindDisablePlugin from "@coremedia/studio-client.main.editor-components/sdk/premular/fields/plugins/BindDisablePlugin";
import CheckboxGroup from "@jangaroo/ext-ts/form/CheckboxGroup";
import Checkbox from "@jangaroo/ext-ts/form/field/Checkbox";
import Radio from "@jangaroo/ext-ts/form/field/Radio";
import Config from "@jangaroo/runtime/Config";
import ConfigUtils from "@jangaroo/runtime/ConfigUtils";
import SEOStudioPlugin_properties from "../SEOStudioPlugin_properties";
import SEORobotsFieldsBase from "./SEORobotsFieldsBase";

interface SEORobotsFieldsConfig extends Config<SEORobotsFieldsBase> {
}

class SEORobotsFields extends SEORobotsFieldsBase {
  declare Config: SEORobotsFieldsConfig;

  static SEO_ROBOTS_INDEXING_OPTIONS_ITEM_ID: string = "indexingOptions";

  static SEO_ROBOTS_FOLLOW_OPTIONS_ITEM_ID: string = "followOptions";

  static SEO_ROBOTS_MISC_OPTIONS_ITEM_ID: string = "miscOptions";

  constructor(config: Config<SEORobotsFields> = null) {
    super((()=> ConfigUtils.apply(Config(SEORobotsFields, {
      title: SEOStudioPlugin_properties.SEORobotsFields_title,
      itemId: "metaRobots",
      collapsed: true,
      layout: "hbox",

      items: [

        /* INDEXING Options */
        Config(BoundRadioGroup, {
          itemId: SEORobotsFields.SEO_ROBOTS_INDEXING_OPTIONS_ITEM_ID,
          defaultValue: SEORobotsFieldsBase.INDEX,
          fieldLabel: SEOStudioPlugin_properties.SEORobotsFields_indexing_label,
          columns: 1,
          flex: 1,
          bindTo: ValueExpressionFactory.create(SEORobotsFieldsBase.INDEXING, this.getModel()),
          ...ConfigUtils.append({
            plugins: [
              Config(BindDisablePlugin, {
                bindTo: config.bindTo,
                forceReadOnlyValueExpression: config.forceReadOnlyValueExpression,
              }),
            ],
          }),
          items: [
            Config(Radio, {
              inputValue: SEORobotsFieldsBase.INDEX,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_index_label,
            }),
            Config(Radio, {
              inputValue: SEORobotsFieldsBase.NO_INDEX,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_noindex_label,
            }),
            Config(Radio, {
              inputValue: SEORobotsFieldsBase.NO_IMAGE_INDEX,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_noimageindex_label,
            }),
          ],
        }),

        /* FOLLOW Options */
        Config(BoundRadioGroup, {
          itemId: SEORobotsFields.SEO_ROBOTS_FOLLOW_OPTIONS_ITEM_ID,
          defaultValue: SEORobotsFieldsBase.FOLLOW,
          fieldLabel: SEOStudioPlugin_properties.SEORobotsFields_following_label,
          columns: 1,
          flex: 1,
          bindTo: ValueExpressionFactory.create(SEORobotsFieldsBase.FOLLOWING, this.getModel()),
          ...ConfigUtils.append({
            plugins: [
              Config(BindDisablePlugin, {
                bindTo: config.bindTo,
                forceReadOnlyValueExpression: config.forceReadOnlyValueExpression,
              }),
            ],
          }),
          items: [
            Config(Radio, {
              inputValue: SEORobotsFieldsBase.FOLLOW,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_follow_label,
            }),
            Config(Radio, {
              inputValue: SEORobotsFieldsBase.NO_FOLLOW,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_nofollow_label,
            }),
          ],
        }),

        /* MISC Options */
        Config(CheckboxGroup, {
          fieldLabel: SEOStudioPlugin_properties.SEORobotsFields_misc_label,
          columns: 1,
          flex: 1,
          plugins: [
            Config(BindPropertyPlugin, {
              bidirectional: true,
              componentEvent: "change",
              bindTo: new ConfigBasedValueExpression({
                expression: SEORobotsFieldsBase.MISC,
                context: this.getModel(),
              }),
            }),
            Config(BindDisablePlugin, {
              bindTo: config.bindTo,
              forceReadOnlyValueExpression: config.forceReadOnlyValueExpression,
            }),
          ],
          items: [
            Config(Checkbox, {
              name: SEORobotsFieldsBase.MISC,
              inputValue: SEORobotsFieldsBase.NO_ARCHIVE,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_noarchive_label,
            }),
            Config(Checkbox, {
              name: SEORobotsFieldsBase.MISC,
              inputValue: SEORobotsFieldsBase.NO_SNIPPET,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_nosnippet_label,
            }),
            Config(Checkbox, {
              name: SEORobotsFieldsBase.MISC,
              inputValue: SEORobotsFieldsBase.NO_TRANSLATE,
              boxLabel: SEOStudioPlugin_properties.SEORobotsFields_notranslate_label,
            }),
          ],
        }),
      ],

    }), config))());
  }
}

export default SEORobotsFields;
