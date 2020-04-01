<div class="row no-gutters hidden-print lift">
    <div class="col-xs-12">
        <div class="image-link" style="background-image: url('${image.getData()}');">
            <#if imageText?has_content>
                <div class="imagetext">
                    ${imageText.getData()}
                </div>
            </#if>
        </div>
    </div>
    <div class="col-xs-12">
        <div class="textcontent">
            <h2>${title.getData()}</h2>
            <p>${text.getData()}</p>
            <#attempt>
                <#if LinkTargetBlank?has_content>
                    <a href="${linkUrl.getData()}"
                        <#if LinkTargetBlank?has_content && getterUtil.getBoolean(LinkTargetBlank.getData())>target="_blank"</#if>
                    >
                        ${linkText.getData()}
                    </a>
                <#else>
                    <a href="${linkUrl.getData()}">${linkText.getData()}</a>
                </#if>
            <#recover>
            </#attempt>
        </div>
    </div>
</div>

