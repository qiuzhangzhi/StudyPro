package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SocketTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf();
    conf.setMaster("local")
    conf.setAppName("SocketTest")
    val ssc = new StreamingContext(conf, Seconds(1))
    val lins = ssc.socketTextStream("localhost", 9999)
    val errorLines = lins.filter(_.contains("error"))
    errorLines.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
