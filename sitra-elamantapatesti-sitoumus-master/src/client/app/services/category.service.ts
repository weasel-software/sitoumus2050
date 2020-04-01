import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

@Injectable()
export class CategoryService {
  constructor(public http: HttpClient) {}

  getAllCategories(): Observable<any> {
    return this.http.get(environment.apiUrl + '/api/categories');
  }

  getCategoryByName(category): Observable<any> {
    return this.http.get(environment.apiUrl + '/api/category/name/' + category);
  }
}
