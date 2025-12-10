## Structured Data (experimental)
The extension uses a view hook to add structured data (JSON-LD) to the page head.

Please note, that structured data rendering is currently experimental and only supported for the following:

- Pages
- Article detail pages
- Person detail pages

---

The following sections describe the data that is rendered for the specific content.

## Page (CMChannel) 
Markup added on non-detail pages:

Example:
```html

<script type="application/ld+json">
{
  "@context": "https://schema.org",
  "@type": "WebPage",
  "name": "ChefCorp Home",
  "url": "/blueprint/servlet/corporate",
  "description": "Chef Corp. is an excellent portal page for professionals that enables chefs with the best tools to prepare perfect meals."
}
</script>
```

## Article (CMArticle)

Markup added on article detail pages:

Example:
```html
<script type="application/ld+json">
{
  "@context": "https://schema.org",
  "@type": "Article",
  "headline": "Fun is your Driver",
  "datePublished": "2025-02-07T11:17:12Z",
  "dateModified": "2025-02-07T11:17:12Z",
  "image": [
    "http://localhost:40980/blueprint/servlet/resource/image/2958/portrait_ratio1x1/768/768/969fc8ac53ff10a85b932261982ffd23/0562A64AAF6CA3758DCECF5258C57CA0/chef-having-fun-picture.jpg",
    "http://localhost:40980/blueprint/servlet/resource/image/2958/landscape_ratio4x3/768/576/98de9f34b47783d797191b24353ddf99/2C5A0A24392D599EFB13EC6E27BCE4AE/chef-having-fun-picture.jpg",
    "http://localhost:40980/blueprint/servlet/resource/image/2958/landscape_ratio16x9/944/531/1681db96ea5f3f5678a6df605217f448/4C8E580C46CF05D75DC863065C5B7F85/chef-having-fun-picture.jpg"
  ],
  "author": [
    {
      "@context": "https://schema.org",
      "@type": "Person",
      "name": "Charlotte May",
      "url": "/blueprint/servlet/corporate/details/charlotte-may-3248"
    }
  ]
}
</script>
```

## Person (CMPerson)
Markup added on person detail pages:

Example:
```html
<script type="application/ld+json">
{
  "@context": "https://schema.org",
  "@type": "Person",
  "name": "Charlotte May",
  "url": "/blueprint/servlet/corporate/details/charlotte-may-3248"
}
</script>
```
