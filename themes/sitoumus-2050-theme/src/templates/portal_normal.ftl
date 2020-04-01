<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
    <title>${the_title} - ${company_name}</title>

    <meta content="initial-scale=1.0, width=device-width" name="viewport" />
    <meta http-equiv="x-ua-compatible" content="IE=edge" />    

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js" integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl" crossorigin="anonymous"></script>

    <!-- Google Tag Manager -->
    <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
                new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
            j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
            'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
    })(window,document,'script','dataLayer','GTM-TXR875K');</script>
    <!-- End Google Tag Manager -->

    <!-- Facebook Pixel Code -->
    <script>
    !function(f,b,e,v,n,t,s)
    {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
        n.callMethod.apply(n,arguments):n.queue.push(arguments)};
      if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
      n.queue=[];t=b.createElement(e);t.async=!0;
      t.src=v;s=b.getElementsByTagName(e)[0];
      s.parentNode.insertBefore(t,s)}(window, document,'script',
        'https://connect.facebook.net/en_US/fbevents.js');
    fbq('init', '2251498305124763');
    fbq('track', 'PageView');
    </script>

<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class} commitment2050">
    <!-- Google Tag Manager (noscript) -->
    <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-TXR875K"
                      height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
    <!-- End Google Tag Manager (noscript) -->

    <@liferay_ui["quick-access"] contentId="#main-content" />

    <@liferay_util["include"] page=body_top_include />

    <#if showcontrolmenu>
        <@liferay.control_menu />
    </#if>

    <#if !is_signed_in>
        <div data-component="signup_register"></div>
    </#if>
    <#--<span class="sign-in text-default" role="presentation"> <a href="/c/portal/login?p_l_id=59619" class="sign-in text-default" data-redirect="false">Kirjaudu sisään<span class="icon-monospaced icon-user"></span></a> </span>-->



    <div class="container-fluid" id="wrapper">
        <div class="quicklinks">
            <div class="container outer-container">
                <div class="row">
                    <div class="col-xs-12 col-sm-3">
                      <div class="flex-vertical-align navbar-inner-height">
                        <span class="glyphicon glyphicon-globe"></span>
                        <div data-component="language_selector"></div>
                      </div>
                    </div>
                    <div class="col-xs-12 col-sm-7 pull-right">
                      <div class="flex-vertical-align flex-justify-end navbar-inner-height">
                        <#if is_signed_in>
                            <div class="inline-block" data-habitat-class"inline-block" data-component="navbar_links"></div>
                            <a class="make-commitment" href="/web/sitoumus2050/profiili#/tee-sitoumus">
                                ${languageUtil.get(locale, "sit.profile.createCommitment")}
                            </a>
                        <#else>
                            <a data-redirect="${is_login_redirect_required?c}" href="${sign_in_url}" id="sign-in" rel="nofollow">
                            ${sign_in_text}
                            </a>
                            <div class="inline-block" data-habitat-class"inline-block" data-component="navbar_links"></div>
                            <a class="make-commitment" data-redirect="${is_login_redirect_required?c}" href="${sign_in_url}" id="sign-in" rel="nofollow">
                                ${languageUtil.get(locale, "sit.profile.createCommitment")}
                            </a>
                        </#if>
                      </div>
                    </div>
                </div>
            </div>
        </div>
        <header id="banner" role="banner">
            <div id="heading" class="container outer-container">
                <div class="row">
                    <div class="col-xs-8 col-sm-4 col-md-3 col-lg-2 full-height">
                        <#attempt>
                                <#assign logo = journalArticleLocalService.getArticleByUrlTitle(group_id, "logo")>
                                ${journalArticleLocalService.getArticleContent(logo, logo.getDDMTemplateKey(), "VIEW", locale, themeDisplay)}
                            <#recover>
                            <a href="/koti" class="logotext">SITOUMUS2050</a>
                        </#attempt>
                    </div>
                    <div class="col-xs-4">
                        <#if has_navigation && is_setup_complete>
                            <button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation"  class="navbar-toggler pull-right" data-target="#navigationCollapse" data-toggle="collapse" type="button">
                                <span class="glyphicon glyphicon-menu-hamburger"></span>
                            </button>
                        </#if>
                    </div>
                    <div class="col-xs-12 col-sm-8 col-md-9 col-lg-10 full-height pull-right">
                        <#if has_navigation && is_setup_complete>

                            <div class="navbar navbar-top navigation-bar-secondary">
                                <div class="container user-personal-bar">
                                    <#include "${full_templates_path}/navigation.ftl" />
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </header>

        <section id="content" class="main-content-area">

            <#if currentFriendlyUrl?contains("/koti") && !currentFriendlyUrl?contains("/koti?")>
                <div id="infopopup" class="infopopup collapse in width" aria-expanded="true">
                    <#attempt>
                    <#assign infoPopup = journalArticleLocalService.getArticleByUrlTitle(group_id, "info-popup")>
                        ${journalArticleLocalService.getArticleContent(infoPopup, infoPopup.getDDMTemplateKey(), "VIEW", locale, themeDisplay)}
                    <#recover>
                    </#attempt>
                </div>
            </#if>

            <#if selectable>
                <@liferay_util["include"] page=content_include />
            <#else>
            ${portletDisplay.recycle()}

            ${portletDisplay.setTitle(the_title)}

                <@liferay_theme["wrap-portlet"] page="portlet.ftl">
                    <@liferay_util["include"] page=content_include />
                </@>
            </#if>
        </section>

<#assign isFrontPage = the_title?matches("Sitoumus2050") || the_title?matches("Commitment2050")>
        <footer id="footer" role="contentinfo" <#if isFrontPage>class="frontpage"</#if> >
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <#attempt>
                            <#assign footerLeft = journalArticleLocalService.getArticleByUrlTitle(group_id, "footer-left")>
                                ${journalArticleLocalService.getArticleContent(footerLeft, footerLeft.getDDMTemplateKey(), "VIEW", locale, themeDisplay)}
                            <#recover>
                        </#attempt>
                    </div>
                    <div class="col-md-3">
                        <#attempt>
                            <#assign footerCenter = journalArticleLocalService.getArticleByUrlTitle(group_id, "footer-center")>
                                ${journalArticleLocalService.getArticleContent(footerCenter, footerCenter.getDDMTemplateKey(), "VIEW", locale, themeDisplay)}
                            <#recover>
                        </#attempt>
                    </div>
                    <div class="col-md-offset-1 col-md-4">
                        <div class="row">
                            <div class="inline-block" data-habitat-class"inline-block" data-component="some_share_container"></div>
                        </div>
                        <div class="row">
                            <#attempt>
                                <#assign footerRight = journalArticleLocalService.getArticleByUrlTitle(group_id, "footer-right")>
                                    ${journalArticleLocalService.getArticleContent(footerRight, footerRight.getDDMTemplateKey(), "VIEW", locale, themeDisplay)}
                                <#recover>
                            </#attempt>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>

    <div data-component="error_container">

    </div>


    <@liferay_util["include"] page=body_bottom_include />

    <@liferay_util["include"] page=bottom_include />

    <!-- inject:js -->
    <!-- endinject -->
    <#assign domain = theme_display.getPortalDomain()>
</body>

</html>
