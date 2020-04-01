import { Location } from '@angular/common';
import { Component, Injector, Input, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  @Input() language = 'fi_FI';
  public inIframe = false;

  constructor(
    private location: Location,
    private router: Router,
    private injector: Injector,
    private titleService: Title,
    public translate: TranslateService
  ) {
    this.translate.setDefaultLang('fi_FI');

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.setTitle();
    });

    // Detect if language is English and change
    // translate.currentLang to affect the whole site
    router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        const subdomain = document.location.hostname.split('.')[0];
        if (subdomain === 'elamantapatesti') {
          this.translate.use('fi_FI');
        } else if (subdomain === 'lifestyletest') {
          this.translate.use('en_US');
        } else if (subdomain === 'livsstilstest') {
          this.translate.use('sv_SE');
        } else if (this.language) {
          this.translate.use(this.language);
        } else if (!this.translate.currentLang) {
          this.translate.use('fi_FI');
        }

        const pathArgument = event.url.split('/')[1];
        if (pathArgument === 'en_US') {
          this.translate.use('en_US');
        } else if (pathArgument === 'sv_SE') {
          this.translate.use('sv_SE');
        }
      }
      if (event instanceof NavigationEnd) {
        if (this.router.routerState.root.firstChild) {
          this.router.routerState.root.firstChild.data.subscribe(data => {
            if (data.language) {
              this.translate.use(data.language);
            }
          });
        }
      }
    });
  }

  ngOnInit() {
    this.inIframe = false;
  }

  setTitle() {
    this.translate.get('META.TITLE').subscribe(translation => {
      this.titleService.setTitle(translation);
    });
  }
}
