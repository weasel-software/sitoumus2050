//AUI().ready(
//    'liferay-sign-in-modal',
//    function(A) {
//      var signIn = A.one('.sign-in > a');
//
//      if (signIn && signIn.getData('redirect') !== 'true') {
//        signIn.plug(Liferay.SignInModal);
//      }
//    }
//);


AUI().ready(
        'liferay-sign-in-modal',
        'event-outside',
        'transition',
        function(A) {
            var signIn = A.one('#sign-in');

            if (signIn) {
                signIn.plug(Liferay.SignInModal);
            }

            var loginField = document.getElementById("_com_liferay_login_web_portlet_LoginPortlet_login");
            if (loginField)
                loginField.setAttribute("placeholder",  Liferay.Language.get('sit.liferay.placeholder.email'));

            var passwordField =  document.getElementById("_com_liferay_login_web_portlet_LoginPortlet_password");
            if (passwordField)
                passwordField.setAttribute("placeholder",  Liferay.Language.get('sit.liferay.placeholder.password'));
					//add facebooks current official login button-look
			    $('.taglib-icon-list a:first').html("<img id=facebook-login-button border=0 alt=facebook-login src=https://www.sitoumus2050.fi/documents/20143/31403/facebook-login.png/ddf3319a-cdc5-342c-23f8-c0dcc060f983?t=1569233624454 height=100>");

					//Add title to password change
					var title_new_password = "<h2>" + Liferay.Language.get('sit.liferay.newPassword') + "</h2>";
					$( title_new_password ).insertBefore( "#portlet_new-password .portlet-content" );
      }
);

Liferay.Portlet.ready(

    /*
     This function gets loaded after each and every portlet on the page.

     portletId: the current portlet's id
     node: the Alloy Node object of the current portlet
     */

    function(portletId, node) {
    }
);

Liferay.on(
    'allPortletsReady',

    /*
     This function gets loaded when everything, including the portlets, is on
     the page.
     */

    function() {
        //Js redirections
        var currentUrl = Liferay.ThemeDisplay.getLayoutURL();
        if ( currentUrl.includes("/elamantapasitoumus-kirjaudu") && ( Liferay.ThemeDisplay.isSignedIn() && "Tuki Soikea" !== (Liferay.ThemeDisplay.getUserName() ))) {
            window.location.href = "/profiili#/tee-elamantapa-sitoumus";
        }

        // load react-ui, try to make sure scripts aren't included twice
        var el = document.getElementById("react_id");
        if (el) {
            el.parentNode.removeChild(el);
        }

        if (window.location.href.indexOf("localhost") !== -1 || window.location.href.indexOf("localtunnel.me") !== -1) {
            var js = document.createElement("script");
            js.type = "text/javascript";
            js.id = "react_id"
            js.src = "http://localhost:3000/static/js/bundle.js";
            document.body.appendChild(js);
        } else {
            loadAssetManifestJSON(function(response) {

                /* Webpack produced js bundles must be loaded in a speficic order. */
                var assetManifest = JSON.parse(response);
                var js = document.createElement("script");
                js.type = "text/javascript";
                js.id = "runtime_js";
                js.src = "/o/sitoumus-2050-theme/react-ui/build/" + assetManifest['runtime.js'];
                js.onload = function() {
                    var vendors = document.createElement("script");
                    vendors.type = "text/javascript";
                    vendors.id = "vendors_js";
                    vendors.src = "/o/sitoumus-2050-theme/react-ui/build/" + assetManifest['vendors.js'];
                    vendors.onload = function() {
                        var js_0 = document.createElement("script");
                        js_0.type = "text/javascript";
                        js_0.id = "js_0";
                        js_0.src = "/o/sitoumus-2050-theme/react-ui/build/" + assetManifest['0.js'];
                        js_0.onload = function() {
                            var js_1 = document.createElement("script");
                            js_1.type = "text/javascript";
                            js_1.id = "js_1";
                            js_1.src = "/o/sitoumus-2050-theme/react-ui/build/" + assetManifest['1.js'];
                            document.body.appendChild(js_1);
                        }
                        document.body.appendChild(js_0);
                    }
                    document.body.appendChild(vendors);
                }
                document.body.appendChild(js);

                var css = document.createElement("link")
                css.rel = "stylesheet";
                css.type = "text/css";
                css.href="/o/sitoumus-2050-theme/react-ui/build/" + assetManifest['1.css'];
                document.body.appendChild(css);
            });
        }
    }
);

function loadAssetManifestJSON(callback) {

  var xreq = new XMLHttpRequest();
  xreq.overrideMimeType("application/json");
  xreq.open('GET', '/o/sitoumus-2050-theme/react-ui/build/asset-manifest.json', true);
  xreq.onreadystatechange = function () {
    if (xreq.readyState == 4 && xreq.status == 200) {
      callback(xreq.responseText);
    }
  };
  xreq.send(null);
}
