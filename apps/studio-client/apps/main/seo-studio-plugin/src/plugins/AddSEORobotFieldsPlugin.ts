import AddItemsPlugin from "@coremedia/studio-client.ext.ui-components/plugins/AddItemsPlugin";
import PropertyFieldGroup from "@coremedia/studio-client.main.editor-components/sdk/premular/PropertyFieldGroup";
import { as } from "@jangaroo/runtime";
import Config from "@jangaroo/runtime/Config";
import ConfigUtils from "@jangaroo/runtime/ConfigUtils";
import SEORobotsFields from "../fields/SEORobotsFields";

interface AddSEORobotFieldsPluginConfig extends Config<AddItemsPlugin> {
}

class AddSEORobotFieldsPlugin extends AddItemsPlugin {
  declare Config: AddSEORobotFieldsPluginConfig;

  #parentCmp: PropertyFieldGroup = null;

  // called by generated constructor code
  #__initialize__(config: Config<AddSEORobotFieldsPlugin>): void {
    this.#parentCmp = as(config.cmp, PropertyFieldGroup);
  }

  constructor(config: Config<AddSEORobotFieldsPlugin> = null) {
    super((()=>{
      this.#__initialize__(config);
      return ConfigUtils.apply(Config(AddSEORobotFieldsPlugin, {

        items: [
          Config(SEORobotsFields, {
            bindTo: this.#parentCmp.bindTo,
            forceReadOnlyValueExpression: this.#parentCmp.forceReadOnlyValueExpression,
          }),
        ],

      }), config);
    })());
  }
}

export default AddSEORobotFieldsPlugin;
