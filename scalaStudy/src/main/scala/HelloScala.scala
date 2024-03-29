import scala.io._ //_  包的所有内容 ctr + b 查看包快捷键
import util.control.Breaks._
object HelloScala {

  def main(args: Array[String]): Unit = {
    var n:Int = 10
    println(s"hello scala ${n}")
    println(n.isInstanceOf[Int])
    val m = 2

    println(n.toString)
    println(100.toString)
    //AnyVal AnyRef
    //
    // sayHello
    //隐式转换
    var num = 1.2 //默认为double
    var num1 = 1.8f
    num = num1
    //num1 = num

    //Unit 只有一个实例 () -> void

    //需要加L默认是int
    //var e = 456464565465465465
    //var f1:Float = 1.1 // double -> float错误
    //float精度小数点后7位

    var char1:Char = 97
    println(char1)

    //当把一个计算结果赋值给一个变量，则编译器会进行类型转换及判断（即会看范围和类型）
    //当把一个字面量赋值给一个变量，则编译器只进行范围判断
    //var c3:Char = 97 +1
    var c4:Char = 98

    //null只能赋值AnyRef
    var dog:Dog = null

    "hello".take(1)
    println("hello".takeRight(1))



//   // var num:Int = 1.2.toInt
//    //关键字要用反引号标示
//
//    //睡觉学生项目经理
//
//    //scala不支持三目运算符 用if-else实现
//    val numi = if (2 > 3) 1 else  5
//
//    print(numi)
//
//   // val name = StdIn.readLine()
//
//    //没有switch使用模式匹配
//
//    for(i <- 1 to 3) {
//      println("hello")
//    }
//
//    var list = List(20, "he")
//    for (i <- list if i!=20) //循环守卫 没有break和continue
//      println(i)
//
//    //嵌套循环
//    for (i<- 1 to 3 ; j <- 1 to 3) {
//      println(i+"," + j)
//    }
//
    val res = for (i <- 1 to 10)
      yield {
        if (i%2==0) {
          i
        } else {
          "不是偶数"
        }
      }

    println(res)
//
//
//    //嵌套循环
//    for (i<- Range(1,10,2)) {
//      println(i)
//      if(i==2) {
//        break()
//
//      }
//
//      //breakable() breakable{} //可以接收函数的的函数
//    }

    //有while循环 但不建议使用

    //纯函数
  }

  def sayHello: Nothing = {
    throw new Exception("error")
  }

  class Dog {

    def sayHi(): Unit = {
      println("ww........")
    }
  }

  trait AAA { // JAVA 里的interface 和 abstract

  }

}
