<div class="bg-image-wrapper" <#if bgImage.getData()??>style="background-image: url('${bgImage.getData()}');"</#if>>
    <div class="container">
        <div class="row no-gutters">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-xs-center">
                <div class="carousel-text-container">
                    <h1>${title.getData()}</h1>

                    <p>${content.getData()}</p>
                    <p>
                        <#attempt>
                            <#if CustomLink.getData()?has_content>
                                <a href="${CustomLink.getData()}">${linkLabel.getData()}</a>
                            <#else>
                                <a href="${linkToPage.getFriendlyUrl()}">${linkLabel.getData()}</a>
                            </#if>
                        <#recover>
                            <a href="${linkToPage.getFriendlyUrl()}">${linkLabel.getData()}</a>
                        </#attempt>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
