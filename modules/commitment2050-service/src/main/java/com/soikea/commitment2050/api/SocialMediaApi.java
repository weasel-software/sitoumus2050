package com.soikea.commitment2050.api;

import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.soikea.commitment2050.model.Commitment;
import com.soikea.commitment2050.service.CommitmentService;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("/share-commitment")
@Component(immediate = true, service = Application.class)
public class SocialMediaApi extends Application {
  public Set<Object> getSingletons() {
    return Collections.<Object>singleton(this);
  }

  private static Logger _logger = LoggerFactory
      .getLogger(com.soikea.commitment2050.api.CommitmentApi.class.getName());

  @Reference
  CommitmentService commitmentService;

  @GET
  @Path("/{id}")
  public Response getMetaTags(@PathParam("id") String id, @Context HttpServletRequest request, @Context HttpHeaders hh) {

    List<String> list = hh.getRequestHeader("User-Agent");
    String userAgent = list.isEmpty() ? "" : list.get(0);

    _logger.info("userAgent = "+userAgent);

    try {
      String[] schemeAndRest = request.getRequestURL().toString().split("://");
      String host = schemeAndRest[1].split("/")[0];
      String url = schemeAndRest[0] + "://" + host + "/selaa-sitoumuksia#//details/"+id;
      if(userAgent.indexOf("facebookexternalhit") >= 0 || userAgent.indexOf("LinkedInBot") >= 0 || userAgent.indexOf("Twitterbot") >= 0) {
        Commitment commitment = commitmentService
            .getCommitment(id + "", WorkflowConstants.STATUS_APPROVED, false);

        String imageUrl = "https://sitoumus2050.fi/documents/20143/42371/eltest-some.jpg";
        if(commitment.getOrganizationName() != null && !commitment.getOrganizationName().isEmpty()) {
          imageUrl = "https://www.sitoumus2050.fi/documents/20143/43923/Sitoumus2050_leima+400x400.png";
        }

        String content = new StringBuilder()
            .append("<!DOCTYPE html>")
            .append("<html>")
            .append("<head>")
            .append("<meta property=\"og:type\" content=\"website\">")
            .append("<meta property=\"og:site_name\" content=\"Sitoumus2050\">")
            .append("<meta property=\"og:title\" content=\"").append(commitment.getTitle_fi_FI()).append("\">")
            .append("<meta property=\"og:description\" content=\"Sitoumus2050.fi on verkkopalvelu, jossa organisaatiot, yritykset ja yksityiset henkil&ouml;t voivat antaa kest&auml;v&auml;n kehityksen toimenpidesitoumuksia ja ideoita.\">")
            .append("<meta property=\"og:url\" content=\"").append(request.getRequestURL().toString()).append("\">")
            .append("<meta property=\"og:image\" content=\""+imageUrl+"\">")
            .append("</head>")
            .append("</html>")
            .toString();
        return Response.ok()
            .header("Content-Type", "text/html")
            .entity(content)
            .build();
      } else {
        return Response.temporaryRedirect(new URI(url)).build();
      }
    } catch (Exception e) {
      _logger.error("getMetaTags failed", e);
      return Response.serverError().build();
    }
  }
}
