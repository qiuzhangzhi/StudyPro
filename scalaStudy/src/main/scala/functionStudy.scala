object functionStudy {

  def main(args: Array[String]): Unit = {
    val dog = new Dog
    println(dog.sum(70, 80))
    val f1 = dog.sum _
    println(f1(20, 80))

    val f3 = (num1:Int, num2:Int) => {
      num1 + num2
    }
    println(f3(10, 80))

    lazy val s = f3(10,90)
  }

  //[: 类型 =]

  //函数默认值 带名参数

  //所有语法都可以嵌套

  //形参默认是val 因此不能再函数中进行修改

  //递归无法自动推断参数类型

  //懒加载


}

class Dog {
  def sum(num1:Int, num2:Int): Int = {

    def sayOk(): Unit = {
      println("sayOk")
    }
    sayOk()
    num1 + num2
  }

  //可变参数， 可变参数只能放在形参的最后
  def sum(args:Int *):Unit = {

  }

  def f1="ei"

  //过程 返回值是Unit


}
