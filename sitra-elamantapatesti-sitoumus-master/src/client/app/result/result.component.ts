import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Result } from '../interfaces/result';
import { ResultService } from '../services/result.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss'],
})
export class ResultComponent implements OnInit {
  public result: Result;
  public id: string;
  public noTodos = false;
  public infoVisible = false;

  // Charts
  public minScale: number = 0;
  public resultChart: Array<any> = [];
  public averageChart: Array<any> = [];
  public averageTotal: number = 0;

  // Tips
  public tips: Array<any> = [];

  public categories = [];
  public doneReduction = 1;
  public todoReduction = 1;
  public totalReduction = 0;

  constructor(
    public resultService: ResultService,
    public route: ActivatedRoute,
    public translate: TranslateService
  ) {
    this.getId();
  }

  ngOnInit() {
    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {});
    this.resultService.getAverages().subscribe((averages: any) => {
      if (averages) {
        this.averageTotal = averages.average;
        this.formatAverageChart(averages);
      }
    });
  }

  getId() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.getResult();
    });
  }

  getResult() {
    this.resultService.getResult(this.id).subscribe(
      result => {
        this.result = result;
        this.formatResultChart();
        this.parseTips();
      },
      error => console.error(error)
    );
  }

  openInfo() {
    this.infoVisible = true;
  }

  closeInfo() {
    this.infoVisible = false;
  }

  formatAverageChart(averages) {
    averages.categories.map(category => {
      this.averageChart.push({
        name: category.name,
        value: Math.ceil(category.average / 100) * 100,
        icon: category.icon,
        color: category.color,
        index: category.index,
      });
    });
    this.averageChart.sort((a, b) => {
      if (a.index < b.index) {
        return -1;
      } else {
        return 1;
      }
    });
    this.updateCurrentMax();
  }

  formatResultChart() {
    const averageChart = [];
    this.result.categoryCO2e.map((categoryCO2e, index) => {
      if (categoryCO2e.category) {
        averageChart.push({
          name: categoryCO2e.category.title,
          value: Math.ceil(categoryCO2e.co2e / 100) * 100,
          icon: categoryCO2e.category.icon,
          color: categoryCO2e.category.color,
        });
      }
    }, this);
    this.resultChart = averageChart;
    this.updateCurrentMax();
  }

  updateCurrentMax() {
    let resultMax = 0;
    let averageMax = 0;
    if (this.result) {
      this.result.categoryCO2e.map(categoryCO2e => {
        resultMax += Math.ceil(categoryCO2e.co2e / 100) * 100;
      }, this);
    }
    if (this.averageChart) {
      this.averageChart.map(data => {
        averageMax += Math.ceil(data.value / 100) * 100;
      });
    }
    if (resultMax > this.minScale || averageMax > this.minScale) {
      this.minScale = resultMax < averageMax ? averageMax : resultMax;
    }
  }

  parseTips() {
    this.result.answers.map(answer => {
      if (answer.answer && answer.answer.tips) {
        answer.answer.tips.map(tip => {
          if (this.tips.findIndex(_tip => _tip._id === tip._id) === -1) {
            this.tips.push(tip);
          }
        });
      }
    }, this);
  }

  calculateReduction(categories) {
    let todo = 0;
    let done = 0;
    categories.map(category => {
      category.todo.map(tip => {
        todo += tip.decrease;
      });
      category.done.map(tip => {
        done += tip.decrease;
      });
    });
    this.todoReduction = 1 - todo / 100 / 10;
    this.doneReduction = 1 - done / 100 / 10;

    this.totalReduction = todo / 10;
    this.categories = categories;
  }

  register() {
    const todo = [];
    const done = [];

    this.categories.map(category => {
      category.todo.map(tip => {
        todo.push({
          id: tip.articleId,
          decrease: tip.decrease / 1000,
        });
      });
      category.done.map(tip => {
        done.push({
          id: tip.articleId,
          decrease: tip.decrease / 1000,
        });
      });
    });

    if (todo.length === 0) {
      this.noTodos = true;
    } else {
      this.noTodos = false;
      const calculations = {
        co2e: Math.ceil(this.result.co2e / 100) * 100,
        co2eAfterDone:
          Math.ceil((this.result.co2e * this.doneReduction) / 100) * 100,
        co2eAfterTodo:
          Math.ceil((this.result.co2e * this.todoReduction) / 100) * 100,
        reduction: this.totalReduction,
      };

      window.localStorage.setItem('calculations', JSON.stringify(calculations));
      window.localStorage.setItem('tipsTodo', JSON.stringify(todo));
      window.localStorage.setItem('tipsDone', JSON.stringify(done));
      window.location.href = environment.registrationUrl;
    }
  }
}
