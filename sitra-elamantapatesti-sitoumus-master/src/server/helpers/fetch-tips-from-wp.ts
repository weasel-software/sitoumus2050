import * as dotenv from 'dotenv';
import * as request from 'request';
import Tip from '../models/tip';

dotenv.load({ path: '.env' });

const wpUrl =
  process.env.WP_URL ||
  'http://sitra.fi/wp/wp-admin/admin-ajax.php?action=get_all_posters_by_post_type&post_type=cases&show_taxonomies=case_types&tax_query_taxonomy=case_types&tax_query_term_ids=5255';
// "https://www.sitra.fi/wp/wp-admin/admin-ajax.php?action=get_all_posters_by_post_type&post_type=cases&show_taxonomies=case_types,environment_effect&tax_query_taxonomy=case_types&tax_query_term_ids=5255"

export async function fetchTips(): Promise<any> {
  return new Promise((resolve, reject) => {
    request(wpUrl, (error, response, body) => {
      if (error) {
        console.error(error);
        reject();
        return;
      }
      const tips = JSON.parse(body).posts.map(rawTip => {
        if (
          rawTip.fi &&
          rawTip.en &&
          rawTip.fi.ID &&
          rawTip.fi.portrait_image &&
          rawTip.fi.title &&
          rawTip.en.title &&
          rawTip.sv.title &&
          rawTip.fi.vinjetti &&
          rawTip.en.vinjetti &&
          rawTip.sv.vinjetti &&
          rawTip.fi.url &&
          rawTip.en.url &&
          rawTip.sv.url &&
          rawTip.fi.environment_effect &&
          rawTip.en.environment_effect &&
          rawTip.sv.environment_effect
        ) {
          return {
            wpId: rawTip.fi.ID,
            images: rawTip.fi.portrait_image.urls.full,
            title: {
              fi_FI: rawTip.fi.title,
              en_US: rawTip.en.title,
              sv_SE: rawTip.sv.title,
            },
            tag: {
              fi_FI: rawTip.fi.vinjetti.label,
              en_US: rawTip.en.vinjetti.label,
              sv_SE: rawTip.sv.vinjetti.label,
            },
            url: {
              fi_FI: rawTip.fi.url,
              en_US: rawTip.en.url,
              sv_SE: rawTip.sv.url,
            },
            environmentEffect: {
              fi_FI: rawTip.fi.environment_effect,
              en_US: rawTip.en.environment_effect,
              sv_SE: rawTip.sv.environment_effect,
            },
          };
        } else {
          throw new Error('Parameters not matching. Tips not fetched.');
        }
      });
      resolve(tips);
    });
  });
}
