import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val distFile = sc.textFile("data.txt");
    val count = distFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((a,b)=>a+b)
    count.saveAsTextFile("dataout.txt")
  }
}
