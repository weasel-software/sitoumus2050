import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipRowComponent } from './tip-row.component';

describe('TipRowComponent', () => {
  let component: TipRowComponent;
  let fixture: ComponentFixture<TipRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
