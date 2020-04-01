// @flow

import React from "react";
import { Helmet } from "react-helmet";
import {
  FacebookShareButton,
  LinkedinShareButton,
  TwitterShareButton,
  WhatsappShareButton,
  EmailShareButton,
  FacebookIcon,
  TwitterIcon,
  LinkedinIcon,
  WhatsappIcon,
  EmailIcon
} from "react-share";

const SomeShareContainer = ({
  facebook,
  twitter,
  linkedin
}: {
  facebook?: {
    url: string,
    appId?: string,
    quote?: string,
    title?: string,
    image?: string,
    description?: string
  },
  twitter?: {
    url: string,
    title?: string
  },
  linkedin?: {
    url: string,
    title?: string
  }
}) => {
  const shareUrl = window.location.href;
  const title = window.document.title;
  return (
    <div className="someshare_container">
      <div className="someshare">
        <FacebookShareButton
          url={facebook ? facebook.url : shareUrl}
          quote={facebook && facebook.quote ? facebook.quote : null}
          className="share-button"
        >
          <FacebookIcon size={32} round />
          {facebook && (
            <Helmet>
              <meta property="og:url" content={shareUrl} />
              <meta property="og:type" content="website" />
              facebook.appId && (<meta
                property="fb:app_id"
                content={facebook.appId}
              />) facebook.title && (<meta
                property="og:title"
                content={facebook.title}
              />) facebook.image && (<meta
                property="og:image"
                content={facebook.image}
              />) facebook.description && (<meta
                property="og:description"
                content={facebook.description}
              />)
            </Helmet>
          )}
        </FacebookShareButton>
      </div>

      <div className="someshare">
        <TwitterShareButton
          url={twitter ? twitter.url : shareUrl}
          title={twitter && twitter.title ? twitter.title : title}
          className="share-button"
        >
          <TwitterIcon size={32} round />
        </TwitterShareButton>
      </div>

      <div className="someshare">
        <LinkedinShareButton
          url={linkedin ? linkedin.url : shareUrl}
          title={linkedin && linkedin.title ? linkedin.title : title}
          windowWidth={750}
          windowHeight={600}
          className="share-button"
        >
          <LinkedinIcon size={32} round />
        </LinkedinShareButton>
      </div>

      <div className="someshare">
        <WhatsappShareButton
          url={shareUrl}
          title={title}
          separator=":: "
          className="someshare__share-button"
        >
          <WhatsappIcon size={32} round />
        </WhatsappShareButton>
      </div>

      <div className="someshare">
        <EmailShareButton
          url={shareUrl}
          subject={title}
          body={shareUrl}
          className="share-button"
        >
          <EmailIcon size={32} round />
        </EmailShareButton>
      </div>
    </div>
  );
};

export default SomeShareContainer;
