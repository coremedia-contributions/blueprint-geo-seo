import ContentPropertyNames from "@coremedia/studio-client.cap-rest-client/content/ContentPropertyNames";
import Struct from "@coremedia/studio-client.cap-rest-client/struct/Struct";
import SubBean from "@coremedia/studio-client.client-core/data/impl/SubBean";
import Bean from "@coremedia/studio-client.client-core/data/Bean";
import PropertyChangeEvent from "@coremedia/studio-client.client-core/data/PropertyChangeEvent";
import ValueExpression from "@coremedia/studio-client.client-core/data/ValueExpression";
import beanFactory from "@coremedia/studio-client.client-core/data/beanFactory";
import ArrayUtils from "@coremedia/studio-client.client-core/util/ArrayUtils";
import PropertyFieldGroup from "@coremedia/studio-client.main.editor-components/sdk/premular/PropertyFieldGroup";
import { bind, is } from "@jangaroo/runtime";
import Config from "@jangaroo/runtime/Config";
import trace from "@jangaroo/runtime/trace";

interface SEORobotsFieldsBaseConfig extends Config<PropertyFieldGroup> {
}

class SEORobotsFieldsBase extends PropertyFieldGroup {
  declare Config: SEORobotsFieldsBaseConfig;

  static readonly #STRUCT_BASE_PATH: string = "localSettings.seo.robots";

  static readonly INDEXING: string = "indexing";

  static readonly INDEX: string = "index";

  static readonly NO_INDEX: string = "noindex";

  static readonly NO_IMAGE_INDEX: string = "noimageindex";

  static readonly FOLLOWING: string = "following";

  static readonly FOLLOW: string = "follow";

  static readonly NO_FOLLOW: string = "nofollow";

  static readonly MISC: string = "misc";

  static readonly NO_ARCHIVE: string = "noarchive";

  static readonly NO_SNIPPET: string = "nosnippet";

  static readonly NO_TRANSLATE: string = "notranslate";

  #model: Bean = null;

  static readonly #VALID_INDEXING_OPTIONS: Array<any> = [SEORobotsFieldsBase.INDEX, SEORobotsFieldsBase.NO_INDEX, SEORobotsFieldsBase.NO_IMAGE_INDEX];

  static readonly #VALID_FOLLOWING_OPTIONS: Array<any> = [SEORobotsFieldsBase.FOLLOW, SEORobotsFieldsBase.NO_FOLLOW];

  static readonly #VALID_MISC_OPTIONS: Array<any> = [SEORobotsFieldsBase.NO_ARCHIVE, SEORobotsFieldsBase.NO_SNIPPET, SEORobotsFieldsBase.NO_TRANSLATE];

  constructor(config: Config<SEORobotsFieldsBase> = null) {
    super(config);
  }

  protected getModel(): Bean {
    if (!this.#model) {
      this.#model = beanFactory._.createLocalBean();
      this.#model.set(SEORobotsFieldsBase.INDEXING, { "indexing": SEORobotsFieldsBase.INDEX });
      this.#model.set(SEORobotsFieldsBase.FOLLOWING, SEORobotsFieldsBase.FOLLOW);
      this.#model.set(SEORobotsFieldsBase.MISC, { "misc": [] });
      this.#model.addValueChangeListener(bind(this, this.#handleModelChange));
    }
    return this.#model;
  }

  protected getStructValueExpression(): ValueExpression {
    return this.bindTo.extendBy(ContentPropertyNames.PROPERTIES, SEORobotsFieldsBase.#STRUCT_BASE_PATH);
  }

  #handleModelChange(event: PropertyChangeEvent): void {
    try {

      const indexingOption = this.getModel().get(SEORobotsFieldsBase.INDEXING);
      if (indexingOption) {
        this.getStructValueExpression().extendBy(SEORobotsFieldsBase.INDEXING).setValue(indexingOption);
      }

      const followingOption: string = this.getModel().get(SEORobotsFieldsBase.FOLLOWING);
      if (followingOption) {
        this.getStructValueExpression().extendBy(SEORobotsFieldsBase.FOLLOWING).setValue(followingOption);
      }

      const miscOptions: any = this.getModel().get(SEORobotsFieldsBase.MISC);
      const robots_misc = ArrayUtils.asArray(miscOptions[SEORobotsFieldsBase.MISC]).concat();
      if (robots_misc.length > 0) {
        this.getStructValueExpression().extendBy(SEORobotsFieldsBase.MISC).setValue(ArrayUtils.asArray(miscOptions[SEORobotsFieldsBase.MISC]).concat());
      } else {
        const s: Struct = this.getStructValueExpression().getValue();
        s.getType().removeProperty(SEORobotsFieldsBase.MISC);
      }

    } catch (e) {
      if (is(e, Error)) {
        trace("[WARN] SEORobotsFields - Unable to store values. " + e);
      } else throw e;
    }

  }

  protected override onDestroy(): void {
    this.getModel() && this.getModel().removeValueChangeListener(bind(this, this.#handleModelChange));
    this.getStructValueExpression() && this.getStructValueExpression().removeChangeListener(bind(this, this.#loadStructValues));
    super.onDestroy();
  }

  protected override afterRender(): void {
    super.afterRender();

    // Load the stored values from the struct property
    this.#loadStructValues();

    // Listen to changes on the bound content (might occur e.g. when the content bound to the form is changes)
    this.bindTo.addChangeListener(bind(this, this.#loadStructValues));
  }

  #loadStructValues(): void {
    this.#initModel();

    // Load initial values
    this.getStructValueExpression().loadValue((data: SubBean): void => {
      try {
        this.getModel().removeValueChangeListener(bind(this, this.#handleModelChange));

        const indexingOption: string = data.get(SEORobotsFieldsBase.INDEXING);
        if (SEORobotsFieldsBase.#VALID_INDEXING_OPTIONS.indexOf(indexingOption) >= 0) {
          this.getModel().set(SEORobotsFieldsBase.INDEXING, indexingOption);
        }

        const followingOption: string = data.get(SEORobotsFieldsBase.FOLLOWING);
        if (SEORobotsFieldsBase.#VALID_FOLLOWING_OPTIONS.indexOf(followingOption) >= 0) {
          this.getModel().set(SEORobotsFieldsBase.FOLLOWING, followingOption);
        }

        const miscOptions = ArrayUtils.asArray(data.get(SEORobotsFieldsBase.MISC));
        this.getModel().set(SEORobotsFieldsBase.MISC, { "misc": miscOptions });

        this.getModel().addValueChangeListener(bind(this, this.#handleModelChange));

      } catch (e) {
        if (is(e, Error)) {
          trace("[WARN] SEORobotsFields - Unable to load initial values from struct.");
        } else throw e;
      }
    });
  }

  #initModel(): void {
    this.getModel().removeValueChangeListener(bind(this, this.#handleModelChange));
    this.getModel().set(SEORobotsFieldsBase.INDEXING, SEORobotsFieldsBase.INDEX);
    this.getModel().set(SEORobotsFieldsBase.FOLLOWING, SEORobotsFieldsBase.FOLLOW);
    this.getModel().set(SEORobotsFieldsBase.MISC, { "misc": [] });
    this.getModel().addValueChangeListener(bind(this, this.#handleModelChange));
  }

}

export default SEORobotsFieldsBase;
