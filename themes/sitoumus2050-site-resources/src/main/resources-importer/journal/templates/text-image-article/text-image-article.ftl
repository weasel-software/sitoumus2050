<div class="row title-info-row no-gutters">
    <div class="col-md-7"><h2>${Title.getData()} <small>${Subtitle.getData()}</small></h2></div>
    <div class="col-md-5"><a href="${LinkUrl.getData()}">${Linktext.getData()}</a></div>
</div>

<div class="row text-image-article">
    <div class="col-md-7">${Content.getData()}</div>
    <div class="col-md-5">
        <#if Image.getData()?? && Image.getData() != "">
            <img src="${Image.getData()}" />
        </#if>
    </div>
</div>
