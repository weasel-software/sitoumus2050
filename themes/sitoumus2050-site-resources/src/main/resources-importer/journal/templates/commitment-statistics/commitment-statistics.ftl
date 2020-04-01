<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign commitmentservice = serviceLocator.findService("com.soikea.commitment2050.service.CommitmentService") />


<#assign commitments = commitmentservice.countPublishedCommitmentsByCategory(33553) />
<#assign greendeals = commitmentservice.countPublishedCommitmentsByCategory(33554) />
<#assign nutritions = commitmentservice.countPublishedCommitmentsByCategory(33555) />
<#assign tests = commitmentservice.getDoneLifestyleTestCount() />

<div class="container">
    <div class="row">
        <div class="col-xs-12 commitment-statistics">
            <ul>
                <li>
                    <span>${commitments}</span> ${commitment.getData()}
                </li>
                <li>
                    <span>${greendeals}</span> ${greendeal.getData()}
                </li>
                <li>
                    <span>${nutritions}</span> ${nutrition.getData()}
                </li>
                <#if tests gt 0 >
                    <li>
                        <span>${tests}</span> ${lifestyletest.getData()}
                    </li>
                </#if>
                <li>
                    <a href="${linkUrl.getData()}"> ${linkText.getData()}</a>
                </li>
            </ul>
        </div>
    </div>
</div>