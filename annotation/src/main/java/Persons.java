//@Retention(RetentionPolicy.SOURCE)
////@Retention(RetentionPolicy.CLASS)
////@Retention(RetentionPolicy.RUNTIME)
////RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
////RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
////RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
//
//@Documented
////@Target()
////类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，它张贴的地方就非常具体了，比如只能张贴到方法上、类上、方法参数上等等。@Target 有下面的取值
//@Inherited
////Inherited 是继承的意思，但是它并不是说注解本身可以继承，而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
public @interface Persons {

    Person[] value();
}



//@interface Persons {
//    Person[]  value();
//}
//
//@Repeatable(Persons.class)
//@interface Person{
//    String role default "";
//}
//
//@Person(role="artist")
//@Person(role="coder")
//@Person(role="PM")
//public class SuperMan{
//
//}