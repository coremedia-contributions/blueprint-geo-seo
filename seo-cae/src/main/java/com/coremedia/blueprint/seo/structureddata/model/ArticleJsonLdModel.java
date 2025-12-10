package com.coremedia.blueprint.seo.structureddata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Article (Article, NewsArticle, BlogPosting) structured data representation.
 */
public class ArticleJsonLdModel extends JsonLdModel {

  @JsonProperty("headline")
  private String headline;

  @JsonProperty("datePublished")
  private OffsetDateTime datePublished;

  @JsonProperty("dateModified")
  private OffsetDateTime dateModified;

  @JsonProperty("image")
  private List<String> imageUrls;

  @JsonProperty("author")
  private List<PersonJsonLdModel> authors;

  public ArticleJsonLdModel() {
    super("Article");
  }

  public String getHeadline() {
    return headline;
  }

  public void setHeadline(String headline) {
    this.headline = headline;
  }

  public OffsetDateTime getDatePublished() {
    return datePublished;
  }

  public void setDatePublished(OffsetDateTime datePublished) {
    this.datePublished = datePublished;
  }

  public OffsetDateTime getDateModified() {
    return dateModified;
  }

  public void setDateModified(OffsetDateTime dateModified) {
    this.dateModified = dateModified;
  }

  public List<String> getImageUrls() {
    return imageUrls;
  }

  public void setImageUrls(List<String> imageUrls) {
    this.imageUrls = imageUrls;
  }

  public List<PersonJsonLdModel> getAuthors() {
    return authors;
  }

  public void setAuthors(List<PersonJsonLdModel> authors) {
    this.authors = authors;
  }

}
