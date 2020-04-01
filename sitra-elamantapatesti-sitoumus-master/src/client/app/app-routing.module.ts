import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultComponent } from './result/result.component';
import { TestComponent } from './test/test.component';
import { TipComponent } from './tip/tip.component';

const routes: Routes = [
  {
    path: 'testi',
    component: TestComponent,
    data: { language: 'fi_FI' },
  },
  {
    path: 'test',
    component: TestComponent,
    data: { language: 'en_US' },
  },
  {
    path: 'testet',
    component: TestComponent,
    data: { language: 'sv_SE' },
  },
  {
    path: 'en/test',
    component: TestComponent,
    data: { language: 'en_US' },
  },
  {
    path: 'sv/testet',
    component: TestComponent,
    data: { language: 'sv_SE' },
  },
  {
    path: 'tulos/:id',
    component: ResultComponent,
    data: { language: 'fi_FI' },
  },
  {
    path: 'result/:id',
    component: ResultComponent,
    data: { language: 'en_US' },
  },
  {
    path: 'resultat/:id',
    component: ResultComponent,
    data: { language: 'sv_SE' },
  },
  {
    path: 'en/result/:id',
    component: ResultComponent,
    data: { language: 'en_US' },
  },
  {
    path: 'sv/resultat/:id',
    component: ResultComponent,
    data: { language: 'sv_SE' },
  },
  {
    path: '',
    redirectTo: 'testi',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
  constructor() {}
}
