<div class="row title-info-row no-gutters">
    <div class="col-md-7"><h2>${title.getData()} <small>${subTitle.getData()}</small></h2></div>
    <div class="col-md-5">
        <#attempt>
                <a href="${linkUrl.getData()}"
                    <#if LinkTargetBlank?has_content && getterUtil.getBoolean(LinkTargetBlank.getData())>target="_blank"</#if>>${linktext.getData()}</a>
            <#recover>
                    <a href="${linkUrl.getData()}">${linktext.getData()}</a>
        </#attempt>
    </div>
</div>