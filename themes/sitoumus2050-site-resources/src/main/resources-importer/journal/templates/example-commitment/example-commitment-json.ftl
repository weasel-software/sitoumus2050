{
    "id":"${(.vars['reserved-article-id'].data)!}",
    "content":"${(content.data!)?json_string}",
<#if linkText?has_content && linkUrl?has_content>
    "linkText":"${(linkText.data!)?json_string}",
    "linkUrl":"${(linkUrl.data!)?json_string}"
</#if>
}