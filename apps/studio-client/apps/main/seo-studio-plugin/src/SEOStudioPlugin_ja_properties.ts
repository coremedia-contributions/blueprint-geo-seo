import ResourceBundleUtil from "@jangaroo/runtime/l10n/ResourceBundleUtil";
import SEOStudioPlugin_properties from "./SEOStudioPlugin_properties";

/**
 * Overrides of ResourceBundle "SEOStudioPlugin" for Locale "ja".
 * @see SEOStudioPlugin_properties#INSTANCE
 */
ResourceBundleUtil.override(SEOStudioPlugin_properties, {
  SEORobotsFields_title: "ロボット",
  SEORobotsFields_indexing_label: "インデックス",
  SEORobotsFields_following_label: "フォロイング",
  SEORobotsFields_misc_label: "雑多な",
  SEORobotsFields_index_label: "index",
  SEORobotsFields_index_tooltip: "ロボットがページをインデックスすることを許可（デフォルト",
  SEORobotsFields_noindex_label: "noindex",
  SEORobotsFields_noindex_tooltip: "ロボットにページのインデックスを作成しないように依頼します。",
  SEORobotsFields_follow_label: "follow",
  SEORobotsFields_follow_tooltip: "ロボットがページのリンクをたどることを許可します（デフォルト）。",
  SEORobotsFields_nofollow_label: "nofollow",
  SEORobotsFields_nofollow_tooltip: "ページ上のリンクをフォローしないようにロボットに要求します。",
  SEORobotsFields_noarchive_label: "noarchive",
  SEORobotsFields_noarchive_tooltip: "ページのコンテンツをキャッシュしないように検索エンジンに要求します。(Google, Yahoo, Bingで使用)",
  SEORobotsFields_nosnippet_label: "nosnippet",
  SEORobotsFields_nosnippet_tooltip: "検索エンジンの検索結果にページの説明を表示しないようにします。(Google, Bingで使用)",
  SEORobotsFields_noimageindex_label: "noimageindex",
  SEORobotsFields_noimageindex_tooltip: "このページを、インデックスされた画像の参照ページとして表示しないようにします。(Googleが使用します)",
  SEORobotsFields_notranslate_label: "notranslate",
  SEORobotsFields_notranslate_tooltip: "このページの翻訳を検索結果に表示しないようにします。(Googleが使用しています)",
});
