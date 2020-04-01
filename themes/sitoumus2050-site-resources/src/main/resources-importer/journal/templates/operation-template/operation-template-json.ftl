{
"id":"${(.vars['reserved-article-id'].data)!}",
"operationTitle":"${operationTitle.getData()!?json_string}",
"meterTypes": [
<#if meterType.getSiblings()?has_content>
    <#list meterType.getSiblings() as cur_meterType>
    	    {
    	        "meterType": "${cur_meterType.getData()!?json_string}",
                "meterValueType": "${cur_meterType.meterValueType.getData()!?json_string}"
    	    }<#if cur_meterType?has_next>,</#if>
    </#list>
</#if>
    ]
}