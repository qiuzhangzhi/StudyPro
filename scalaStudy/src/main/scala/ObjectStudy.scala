object ObjectStudy {

  def main(args: Array[String]): Unit = {
    val cat = new Cat
    cat.name = "小白"
    printf("小猫信息：%s,%d", cat.name,cat.age)
  }
}

class Cat {
  //默认是private的
  var name:String = "" //必须付一个默认值
  var age:Int = _ //默认值0
}
