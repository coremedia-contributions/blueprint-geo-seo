
/**
 * Interface values for ResourceBundle "SEOStudioPlugin".
 * @see SEOStudioPlugin_properties#INSTANCE
 */
interface SEOStudioPlugin_properties {

  SEORobotsFields_title: string;
  SEORobotsFields_indexing_label: string;
  SEORobotsFields_following_label: string;
  SEORobotsFields_misc_label: string;
  SEORobotsFields_index_label: string;
  SEORobotsFields_index_tooltip: string;
  SEORobotsFields_noindex_label: string;
  SEORobotsFields_noindex_tooltip: string;
  SEORobotsFields_follow_label: string;
  SEORobotsFields_follow_tooltip: string;
  SEORobotsFields_nofollow_label: string;
  SEORobotsFields_nofollow_tooltip: string;
  SEORobotsFields_noarchive_label: string;
  SEORobotsFields_noarchive_tooltip: string;
  SEORobotsFields_nosnippet_label: string;
  SEORobotsFields_nosnippet_tooltip: string;
  SEORobotsFields_noimageindex_label: string;
  SEORobotsFields_noimageindex_tooltip: string;
  SEORobotsFields_notranslate_label: string;
  SEORobotsFields_notranslate_tooltip: string;
}

/**
 * Singleton for the current user Locale's instance of ResourceBundle "SEOStudioPlugin".
 * @see SEOStudioPlugin_properties
 */
const SEOStudioPlugin_properties: SEOStudioPlugin_properties = {
  SEORobotsFields_title: "Robots",
  SEORobotsFields_indexing_label: "Indexing",
  SEORobotsFields_following_label: "Following",
  SEORobotsFields_misc_label: "Misc",
  SEORobotsFields_index_label: "index",
  SEORobotsFields_index_tooltip: "Allows the robot to index the page (default)",
  SEORobotsFields_noindex_label: "noindex",
  SEORobotsFields_noindex_tooltip: "Requests the robot to not index the page.",
  SEORobotsFields_follow_label: "follow",
  SEORobotsFields_follow_tooltip: "Allows the robot to follow the links on the page (default).",
  SEORobotsFields_nofollow_label: "nofollow",
  SEORobotsFields_nofollow_tooltip: "Requests the robot to not follow the links on the page.",
  SEORobotsFields_noarchive_label: "noarchive",
  SEORobotsFields_noarchive_tooltip: "Requests the search engine not to cache the page content. (Used by Google, Yahoo, Bing)",
  SEORobotsFields_nosnippet_label: "nosnippet",
  SEORobotsFields_nosnippet_tooltip: "Prevents displaying any description of the page in search engine results. (Used by Google, Bing)",
  SEORobotsFields_noimageindex_label: "noimageindex",
  SEORobotsFields_noimageindex_tooltip: "Requests this page not to appear as the referring page of an indexed image. (Used by Google)",
  SEORobotsFields_notranslate_label: "notranslate",
  SEORobotsFields_notranslate_tooltip: "Do not offer translation of this page in search results. (Used by Google)",
};

export default SEOStudioPlugin_properties;
