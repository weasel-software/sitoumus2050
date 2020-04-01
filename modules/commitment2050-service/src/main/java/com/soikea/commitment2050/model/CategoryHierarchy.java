package com.soikea.commitment2050.model;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CategoryHierarchy extends Category {

  private List<CategoryHierarchy> children;
  private Map<Locale, String> titleMap;
  private Map<String, String> properties;

  public Map<Locale, String> getTitleMap() {
    return titleMap;
  }

  public void setTitleMap(Map<Locale, String> titleMap) {
    this.titleMap = titleMap;
  }

  public List<CategoryHierarchy> getChildren() {
    return children;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setChildren(List<CategoryHierarchy> children) {
    this.children = children;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }
}
