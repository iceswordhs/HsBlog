export function runtime () {
  // 初始时间，日/月/年 时:分:秒
  var X = new Date('11/18/2016 8:32:00')
  var Y = new Date()
  var T = (Y.getTime() - X.getTime())
  var M = 24 * 60 * 60 * 1000
  var a = T / M
  var A = Math.floor(a)
  var b = (a - A) * 24
  var B = Math.floor(b)
  var c = (b - B) * 60
  var C = Math.floor((b - B) * 60)
  var D = Math.floor((c - C) * 60)
  // 信息写入到DIV中
  return '本站勉强运行: ' + A + '天' + B + '小时' + C + '分' + D + '秒'
}
