<div class="panel panel-default">
    <div class="panel-body">
        <div class="row">
            <div class="col-xs-10">
                <h2>${Title.getData()}</h2>
            </div>
            <div class="col-xs-2">
                <div id="infoPopupClose" class="close-button pull-right" data-toggle="collapse" data-target="#infopopup">
                    <div class="close"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-11">
                ${content.getData()}
                <p>
                    <a href="${linkUrl.getData()}">${linkText.getData()}</a>
                </p>
            </div>
        </div>
    </div>
</div>

