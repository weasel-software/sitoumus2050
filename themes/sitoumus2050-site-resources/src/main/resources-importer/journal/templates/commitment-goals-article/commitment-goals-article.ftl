<#if title.getSiblings()?has_content>
    <div class="row goals">
        <#list title.getSiblings() as cur_title>
            <div class="col-md-3 col-sm-6 col-xs-12 goal">
                <#if cur_title.icon.getData()?? && cur_title.icon.getData() != "">
                    <img src="${cur_title.icon.getData()}" />
                </#if>
                <h2>${cur_title.getData()}</h2>
                <p>${cur_title.description.getData()}</p>
            </div>
        </#list>
    </div>
</#if>