<div class="row hidden-print">
    <div class="col-xs-12">
        <div class="image-link" style="background-image: url('${bgImage.getData()}');">
            <#if link?has_content>
                <#attempt>
                    <a href="${link.getData()}"
                        <#if LinkTargetBlank?has_content && getterUtil.getBoolean(LinkTargetBlank.getData())>target="_blank"</#if>>
                        ${linkText.getData()}
                    </a>
                <#recover>
                    <a href="${link.getData()}">
                        ${linkText.getData()}
                    </a>
                </#attempt>

            </#if>
        </div>
    </div>
</div>
