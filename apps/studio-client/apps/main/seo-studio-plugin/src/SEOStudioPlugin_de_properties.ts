import ResourceBundleUtil from "@jangaroo/runtime/l10n/ResourceBundleUtil";
import SEOStudioPlugin_properties from "./SEOStudioPlugin_properties";

/**
 * Overrides of ResourceBundle "SEOStudioPlugin" for Locale "de".
 * @see SEOStudioPlugin_properties#INSTANCE
 */
ResourceBundleUtil.override(SEOStudioPlugin_properties, {
  SEORobotsFields_title: "Robots",
  SEORobotsFields_indexing_label: "Indexierung",
  SEORobotsFields_following_label: "Link-Verfolgung",
  SEORobotsFields_misc_label: "Weitere Optionen",
  SEORobotsFields_index_label: "index",
  SEORobotsFields_index_tooltip: "Erlaubt dem Roboter, die Seite zu indizieren (Standard)",
  SEORobotsFields_noindex_label: "noindex",
  SEORobotsFields_noindex_tooltip: "Fordert den Roboter auf, die Seite nicht zu indizieren.",
  SEORobotsFields_follow_label: "follow",
  SEORobotsFields_follow_tooltip: "Ermöglicht es dem Roboter, den Links auf der Seite zu folgen (Standard).",
  SEORobotsFields_nofollow_label: "nofollow",
  SEORobotsFields_nofollow_tooltip: "Fordert den Roboter auf, den Links auf der Seite nicht zu folgen.",
  SEORobotsFields_noarchive_label: "noarchive",
  SEORobotsFields_noarchive_tooltip: "Fordert die Suchmaschine auf, den Inhalt der Seite nicht zu cachen. (wird von Google, Yahoo, Bing verwendet)",
  SEORobotsFields_nosnippet_label: "nosnippet",
  SEORobotsFields_nosnippet_tooltip: "Keine Beschreibung der Seite in den Suchmaschinenergebnissen anzeigen. (wird von Google und Bing verwendet)",
  SEORobotsFields_noimageindex_label: "noimageindex",
  SEORobotsFields_noimageindex_tooltip: "Diese Seite nicht als verweisende Seite eines indizierten Bildes anzeigen. (wird von Google verwendet)",
  SEORobotsFields_notranslate_label: "notranslate",
  SEORobotsFields_notranslate_tooltip: "Keine Übersetzung dieser Seite in den Suchergebnissen anzeigen. (wird von Google verwendet)",
});
