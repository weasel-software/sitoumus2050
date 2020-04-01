import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sortByCategory',
})
export class SortByCategoryPipe implements PipeTransform {
  transform(tips: any, worstCategory?: any): any {
    const small = [];
    const medium = [];
    const large = [];

    for (const tip of tips) {
      if (tip.environmentEffect.en_US === 'small') {
        small.push(tip);
      } else if (tip.environmentEffect.en_US === 'medium') {
        medium.push(tip);
      } else if (tip.environmentEffect.en_US === 'large') {
        large.push(tip);
      }
    }

    if (worstCategory) {
      large.sort((a: any, b: any) => {
        for (const tag of worstCategory.tags.en_US) {
          if (a.tag.en_US.toUpperCase() === tag.toUpperCase()) {
            if (b.tag.en_US.toUpperCase() === tag.toUpperCase()) {
              return 0;
            }
            if (b.tag.en_US.toUpperCase() !== tag.toUpperCase()) {
              return -1;
            }
            return 1;
          } else {
            return 1;
          }
        }
      });
    }

    let _tips = [];
    _tips = _tips.concat(large);
    _tips = _tips.concat(medium);
    _tips = _tips.concat(small);

    return _tips;
  }
}
