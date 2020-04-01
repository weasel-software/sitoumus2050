import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { environment } from '../../environments/environment';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-tip',
  templateUrl: './tip.component.html',
  styleUrls: ['./tip.component.scss'],
  animations: [
    trigger('expand', [
      state(
        '*',
        style({
          height: '*',
          opacity: '1',
        })
      ),
      state(
        'void',
        style({
          height: '0',
          opacity: '0',
        })
      ),
      transition('void <=> *', animate('150ms linear')),
    ]),
  ],
})
export class TipComponent implements OnInit {
  @Input()
  co2e: number;
  @Input()
  tips: Array<any>;
  @Output()
  categoriesWithTips = new EventEmitter<any>();

  public categories = [];
  public expandedTip = null;
  public expandedCategory: any;

  public tipsTodo = [];
  public tipsDone = [];

  constructor(
    public translate: TranslateService,
    private categoryService: CategoryService,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories;

      this.categories.map(category => {
        category.tips = [];
        category.todo = [];
        category.done = [];
      });

      this.http.get(environment.apiUrl + '/api/tips').subscribe((res: any) => {
        this.tips = this.tips.map(tip => {
          return res.filter(t => t.testId).find(t => {
            return JSON.parse(t.testId) === tip.wpId;
          });
        });

        this.tips
          .filter(tip => tip)
          .map(tip => {
            tip.decrease = JSON.parse(tip.decrease.replace(/,/, '.'));
            tip.decrease = tip.decrease * (this.co2e / 10300);
            tip.decrease = Math.round(tip.decrease * 10) / 10;
            tip.decrease = tip.decrease * 10;
            return tip;
          })
          .sort((x, y) => {
            if (x.decrease > y.decrease) {
              return -1;
            }
            if (x.decrease < y.decrease) {
              return 1;
            }
            if (x.decrease === y.decrease) {
              return 0;
            }
          })
          .map(tip => {
            if (tip.category === 'Asuminen') {
              this.categories[0].tips.push(tip);
            }
            if (tip.category === 'Liikkuminen') {
              this.categories[1].tips.push(tip);
            }
            if (tip.category === 'Ruoka') {
              this.categories[2].tips.push(tip);
            }
            if (tip.category === 'Tuotteet ja palvelut') {
              this.categories[3].tips.push(tip);
            }
          });

        this.expandTip(this.categories[0].tips[0]);
      });
    });
  }

  expandCategory(category) {
    this.expandedCategory =
      this.expandedCategory === category ? undefined : category;
    // Wait for collapse animation to end before scrolling
    setTimeout(() => {
      document
        .getElementById(category._id)
        .scrollIntoView({ behavior: 'smooth', block: 'start' });
    }, 150);
  }

  expandTip(tip) {
    this.expandedTip = this.expandedTip === tip ? null : tip;
  }

  toggleTip(event, category) {
    const i = category.tips.indexOf(event.tip);

    if (event.add) {
      if (event.action === 'todo') {
        category.tips[i].todo = true;
        category.todo.push(event.tip);
      }
      if (event.action === 'done') {
        category.tips[i].done = true;
        category.done.push(event.tip);
      }
    } else if (event.remove) {
      if (event.action === 'todo') {
        category.tips[i].todo = false;
        const index = category.todo.indexOf(event.tip);
        if (index !== -1) {
          category.todo.splice(index, 1);
        }
      }
      if (event.action === 'done') {
        category.tips[i].done = false;
        const index = category.done.indexOf(event.tip);
        if (index !== -1) {
          category.done.splice(index, 1);
        }
      }
    }
    this.categoriesWithTips.emit(this.categories);
  }

  categoryTodoReduction(category) {
    let decrease = 0;
    category.todo.map(tip => {
      decrease += tip.decrease;
    });
    return decrease / 10;
  }
}
