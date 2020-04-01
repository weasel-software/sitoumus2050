<div class="row no-gutters person">
    <div class="col-md-4">
        <#if image.getData()?? && image.getData() != "">
            <img src="${image.getData()}" />
        </#if>
    </div>
    <div class="col-md-8">
        <h2>${names.getData()}</h2>
        <h3>${title.getData()}</h3>
        <p>${content.getData()}</p>
        <div class="row">
            <div class="col-md-6 organization">
                ${organization.getData()}
            </div>
            <div class="col-md-6 link-to-commitment">
                <a class="pull-right" href="${linkUrl.getData()}">
                    ${linkText.getData()}
                </a>
            </div>
        </div>
    </div>
</div>
