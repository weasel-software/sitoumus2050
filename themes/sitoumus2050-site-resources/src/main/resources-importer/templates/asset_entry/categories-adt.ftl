<#--
Application display templates can be used to modify the look of a
specific application.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->

<#if entries?has_content>
<div class="row">
	<#list entries as curVocabulary>
	    <#list curVocabulary.getCategories() as category>
    	    <#assign assetCategoryPropertyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService")>
    	    <#assign imageUrl = assetCategoryPropertyLocalService.getCategoryProperty(category.getCategoryId(), "imageUrl") />
    	    
    	    <div class="col-xs-12 col-md-3">
    	    <a href="/home/-/categories/${category.categoryId}">
    	        <img src="/documents/20143/33869/${imageUrl.value}" />
                ${category.name}
                </a>
            </div>
	    </#list>
	</#list>
</#if>