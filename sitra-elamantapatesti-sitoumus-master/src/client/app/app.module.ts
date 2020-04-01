import { Location } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injector, NgModule } from '@angular/core';
import { createCustomElement } from '@angular/elements';
import { FormsModule } from '@angular/forms';
import {
  BrowserModule,
  BrowserTransferStateModule,
} from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { ClickOutsideModule } from 'ng-click-outside';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import { ChartComponent } from './chart/chart.component';
import { FilterPipe } from './pipes/filter.pipe';
import { RoundUpPipe } from './pipes/round-up.pipe';
import { SortByCategoryPipe } from './pipes/sort-by-category.pipe';
import { SortByIndexPipe } from './pipes/sort-by-index.pipe';
import { SortPipe } from './pipes/sort.pipe';
import { ThousandSeparatorPipe } from './pipes/thousand-separator.pipe';
import { QuestionComponent } from './question/question.component';
import { ResultComponent } from './result/result.component';
import { CategoryService } from './services/category.service';
import { ResultService } from './services/result.service';
import { TestService } from './services/test.service';
import { TestComponent } from './test/test.component';
import { TipRowComponent } from './tip-row/tip-row.component';
import { TipComponent } from './tip/tip.component';
import { environment } from '../environments/environment';
import { APP_BASE_HREF } from '@angular/common';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, environment.assetsUrl);
}

@NgModule({
  declarations: [
    AppComponent,
    ResultComponent,
    QuestionComponent,
    CategoryComponent,
    TestComponent,
    ChartComponent,
    RoundUpPipe,
    FilterPipe,
    TipComponent,
    SortPipe,
    SortByIndexPipe,
    SortByCategoryPipe,
    ThousandSeparatorPipe,
    TipRowComponent,
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'sitra-elamantapatesti' }),
    BrowserTransferStateModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ClickOutsideModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
  ],
  exports: [
    AppComponent,
    ResultComponent,
    QuestionComponent,
    CategoryComponent,
    TestComponent,
  ],
  providers: [
    CategoryService,
    ResultService,
    TestService,
    { provide: APP_BASE_HREF, useValue: '/elamantapatesti' },
  ],
  entryComponents: [AppComponent],
})
export class AppModule {
  constructor(
    private injector: Injector,
    private router: Router,
    private location: Location
  ) {
    const customElement = createCustomElement(AppComponent, { injector });
    customElements.define('sitra-elamantapatesti', customElement);

    this.location.subscribe(data => {
      this.router.navigateByUrl(data.url);
    });

    // using this router outlet is loaded normaly on init
    this.router.navigateByUrl(this.location.path(true));
  }
  ngDoBootstrap() {}
}
