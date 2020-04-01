<#assign startDate_Data = getterUtil.getString(reportStartDate.getData())>

<#assign endDate_Data = getterUtil.getString(reportEndDate.getData())>

{
    "id":"${(.vars['reserved-article-id'].data)!}",
    "reportTitle": "${reportTitle.getData()!?json_string}",
    "commitmentArticleId": "${commitmentArticleId.getData()!?json_string}",
    "operationTitle": "${operationTitle.getData()!?json_string}",
    "commitmentOperationRefId": "${commitmentOperationRefId.getData()!?json_string}",

    "reportStartDate": "${startDate_Data!}",
    "reportEndDate": "${endDate_Data!}",
    "meters": [
        <#if meterType.getSiblings()?has_content>
            <#list meterType.getSiblings() as cur_meterType>
            {
                "meterType": "${cur_meterType.getData()!?json_string}",
                "commitmentOperationMeterRefId": "${cur_meterType.commitmentOperationMeterRefId.getData()!?json_string}",
                "meterValueType":" ${cur_meterType.meterValueType.getData()!?json_string}",
                "startingLevel": "${cur_meterType.startingLevel.getData()!?json_string}"
                "targetLevel": "${cur_meterType.targetLevel.getData()!?json_string}"
                "currentLevel": "${cur_meterType.currentLevel.getData()!?json_string}"
            }<#if cur_meterType?has_next>,</#if>
            </#list>
        </#if>
    ],
    "progress": "${progress.getData()!json_string}",
    "reportText": "${reportText.getData()!json_string}"
}
