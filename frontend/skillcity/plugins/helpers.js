export default function ({app, $axios, store, env}, inject) {

  const screenType = {
    'iphone5': 320,
    'iphone6': 375,
    'custom0': 500,
    'ipad': 1024,
    'laptop': 1440,
    'desktop': 1680,
    'desktop2': 1920,
    'desktop3': 2560
  }

  function getScreenTypeFunc() {
    for (screen in screenType) {
      if (window.innerWidth > screenType[screen]) {
      } else {
        return screen
      }
    }
  }

  inject('getScreenType', getScreenTypeFunc);

  function calcCurrentScreenType() {
    var currentScreenType = getScreenTypeFunc()
    switch (currentScreenType) {
      case 'iphone5':
        return 1;
      case 'iphone6':
        return 1;
      case 'custom0':
        return 1;
      case 'ipad':
        return 2;
      case 'laptop':
        return 3;
      case 'desktop':
        return 4;
      case 'desktop2':
        return 4;
      case 'desktop3':
        return 4;
    }
  }

  inject('pagesPerPagination', calcCurrentScreenType)
}
