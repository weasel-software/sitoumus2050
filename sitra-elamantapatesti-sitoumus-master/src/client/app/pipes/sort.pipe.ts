import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sort',
})
export class SortPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    return value.sort((x, y) => {
      if (
        x.environmentEffect.en_US === 'small' &&
        y.environmentEffect.en_US === 'medium'
      ) {
        return 1;
      }
      if (
        x.environmentEffect.en_US === 'small' &&
        y.environmentEffect.en_US === 'large'
      ) {
        return -1;
      }
      if (
        x.environmentEffect.en_US === 'medium' &&
        y.environmentEffect.en_US === 'small'
      ) {
        return -1;
      }
      if (
        x.environmentEffect.en_US === 'medium' &&
        y.environmentEffect.en_US === 'large'
      ) {
        return 1;
      }
      if (
        x.environmentEffect.en_US === 'large' &&
        y.environmentEffect.en_US === 'small'
      ) {
        return -1;
      }
      if (
        x.environmentEffect.en_US === 'large' &&
        y.environmentEffect.en_US === 'medium'
      ) {
        return -1;
      }
      return 0;
    });
  }
}
