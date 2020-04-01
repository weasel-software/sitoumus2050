export class Category {
  public _id: string;
  public index: number;
  public title: { en_US: string; fi_FI: string; sv_SE: string };
  public endInfoPositive: { en_US: string; fi_FI: string; sv_SE: string };
  public endInfoNegative: { en_US: string; fi_FI: string; sv_SE: string };
  public endInfoCommon: { en_US: string; fi_FI: string; sv_SE: string };
  public averageCo2e: number;
  public color: string;
  public icon: string;
  public questions: Array<any>;
  public slug: { en_US: string; fi_FI: string; sv_SE: string };
  public resultInfo: {
    image: string;
    title: { en_US: string; fi_FI: string; sv_SE: string };
    altTitle: { en_US: string; fi_FI: string; sv_SE: string };
    positiveDescription: { en_US: string; fi_FI: string; sv_SE: string };
    negativeDescription: { en_US: string; fi_FI: string; sv_SE: string };
  };

  constructor(category) {
    this._id = category._id;
    this.index = category.index;
    this.title = category.title;
    this.endInfoPositive = category.endInfoPositive;
    this.endInfoNegative = category.endInfoNegative;
    this.endInfoCommon = category.endInfoCommon;
    this.averageCo2e = category.averageCo2e;
    this.color = category.color;
    this.icon = category.icon;
    this.questions = category.questions;
    this.slug = category.slug;
    this.resultInfo = category.resultInfo;
  }
}
