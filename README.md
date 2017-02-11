# Hangman Game
### For Strikingly Interview Test

fork from ["strikingly/strikingly-interview-test-instructions"][1]

----------

 - 使用了Android原生的StateMachine来管理逻辑，由于Android Framework的dexLoader的加载顺序会导致内核中的对于类被优先加载而失去访问权限，因此对相关引用做了迁移。
 - 使用仿Bootstrap的框架[Bearded-Hen/Android-Bootstrap][2]（MIT license）来做临时GUI方案，以压缩开发时间。
 - 使用okhttp作为http请求库，并使用GSON作为json解析库。
 - 除作为logo的矢量图([iconmoon license][3])外没有使用第三方素材，没有图片

----------

 TODO list
 - Log 追加可维可测性
 - Monkey测试（稳定性）
 - 单元测试用例补充   5%
 - 全用例测试，兼容性测试
 - GUI优化
 

  [1]: https://github.com/strikingly/strikingly-interview-test-instructions
  [2]: https://github.com/Bearded-Hen/Android-Bootstrap
  [3]: https://icomoon.io/faq.html
  