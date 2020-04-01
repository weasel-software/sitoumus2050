import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

export const expandAnimation = [
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
    transition('void <=> *', animate('250ms linear')),
  ]),
];

@Component({
  selector: 'app-tip-row',
  templateUrl: './tip-row.component.html',
  styleUrls: ['./tip-row.component.scss'],
  animations: expandAnimation,
})
export class TipRowComponent implements OnInit {
  @Input()
  tip: any;
  @Input()
  expanded: boolean;

  @Output()
  expand = new EventEmitter<any>();
  @Output()
  toggleTip = new EventEmitter<any>();

  public checked: boolean;
  public done: boolean;
  public todo: boolean;

  constructor(public translate: TranslateService) {}

  ngOnInit() {
    this.todo = this.tip.todo;
    this.done = this.tip.done;
  }

  expandTip(id) {
    this.expand.emit(id);
  }

  toggleDone(event) {
    if (event.target.checked) {
      this.done = true;
      this.toggleTodo({ target: { checked: false } });

      this.toggleTip.emit({
        tip: this.tip,
        action: 'done',
        add: true,
      });
    } else {
      this.done = false;

      this.toggleTip.emit({
        tip: this.tip,
        action: 'done',
        remove: true,
      });
    }
  }

  toggleTodo(event) {
    if (event.target.checked) {
      this.todo = true;
      this.toggleDone({ target: { checked: false } });

      this.toggleTip.emit({
        tip: this.tip,
        action: 'todo',
        add: true,
      });
    } else {
      this.todo = false;

      this.toggleTip.emit({
        tip: this.tip,
        action: 'todo',
        remove: true,
      });
    }
  }
}
