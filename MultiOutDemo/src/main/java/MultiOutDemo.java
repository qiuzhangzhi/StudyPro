import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.hadoop.util.Progressable;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.json4s.jackson.Json;
import scala.Tuple2;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MultiOutDemo {

    public static Broadcast<String> preFeaureName;

    public static void main(String args[]) {


        SparkConf conf = new SparkConf().setMaster("local").setAppName("multioutdemo");
        JavaSparkContext sc = new JavaSparkContext(conf);

        String[] preFeatureNames = new String[]{};

        for (String preFeature : preFeatureNames) {
            JavaRDD<String> rdd = sc.textFile("./" + preFeature);
            // System.out.println(rdd.take(1));
            JavaPairRDD<String, String> pairRDD = rdd.mapToPair(new PairFunction<String, String, String>() {
                public Tuple2<String, String> call(String s) throws Exception {
                    return new Tuple2<String, String>(s.split("\\|")[0], s.split("\\|")[1]);
                }
            });

            String preFeaureNameS = preFeature;
            System.out.println("current feature:" + preFeaureNameS);
            preFeaureName = sc.broadcast(preFeaureNameS);
            preFeaureName.unpersist();
            preFeaureName = sc.broadcast(preFeaureNameS);
            pairRDD.saveAsHadoopFile("./out/"+preFeaureNameS, String.class, String.class, RDDMultipleTextOutputFormat.class);
        }

    }

    public static class RDDMultipleTextOutputFormat<K,V> extends MultipleTextOutputFormat<K, V> {


//        @Override
//        protected String generateFileNameForKeyValue(K key, V value, String name) {
//            String[] keys = key.toString().split("_");
//            if (keys.length > 1) {
//                return keys[1]+"/part-"+keys[0];
//            } else {
//                return keys[0];
//            }
//
//        }

        /***
         * 一行拆分成多条
         * @param key gid
         * @param value jasonArray
         * @return Map<String, String> key 文件名称 value文件内容
         */
        protected Map<String, String> generateFileNamesForKeyValue(K key, V value, String name) {
            Map<String, String> resultMap = new HashMap<String, String>();
            List<Map> mapValue = JSONObject.parseArray((String) value, Map.class);

            //原来的特征名称
            String preFeatureName = preFeaureName.getValue();
            //固定前缀
            String newFeaturePrefix = "app_";

            String[] featureParts = preFeatureName.split("_");
            File file = new File("./outname.txt");
            System.out.println(preFeatureName);
            try {
                FileUtils.writeStringToFile(file,preFeatureName + "\n",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Map object : mapValue) {

                Map<String, String> mapObject = ((Map<String, String>)object);
                String cate = mapObject.get("category_code");
                for (Map.Entry<String, String> vals : mapObject.entrySet()){
                    if("category_code".equals(vals.getKey())) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(newFeaturePrefix);
                    String part2 = featureParts[1];
                    sb.append(featureParts[0]).append("_")
                            .append(featureParts[1]).append("_");

                    if (part2.contains(vals.getKey())) {
                        sb.append(cate)
                                .append("_")
                                .append(featureParts[featureParts.length - 1]);
                    } else {
                        sb.append(vals.getKey())
                                .append("_")
                                .append(cate)
                                .append("_")
                                .append(featureParts[featureParts.length - 1]);
                    }

                    StringBuilder sbValue = new StringBuilder();
                    sbValue.append(key)
                            .append("|")
                            .append(String.valueOf(vals.getValue()));

                    sb.append("/" + name);
                    resultMap.put(sb.toString(), sbValue.toString());
                    System.out.println(sb.toString() + "," + sbValue.toString());
                    try {
                        FileUtils.writeStringToFile(file,sb.toString() + "\n",true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            return resultMap;
        }

        @Override
        public RecordWriter<K, V> getRecordWriter(final FileSystem fs, final JobConf job, String name, final Progressable arg3) throws IOException {


            //System.out.println(job.get("mapreduce.output.fileoutputformat.outputdir")+"=======");
//             System.out.println(job.getWorkingDirectory()+"==========");
//
//            System.out.println(job.get("mapreduce.map.input.file")+"==========");
//
//            for (String path : job.getLocalDirs()) {
//                System.out.println(path);
//            }
           // generateFileNameForKeyValue();
            final String myName = this.generateLeafFileName(name);
            return new RecordWriter<K, V>() {
                TreeMap<String, RecordWriter<K, V>> recordWriters = new TreeMap();

                public void write(K key, V value) throws IOException {



                    Map<String, String> filesMap = generateFileNamesForKeyValue(key, value, myName);
                    for (Map.Entry<String, String> entry : filesMap.entrySet()) {
                        String keyBasedPath = entry.getKey();
                        String finalPath = RDDMultipleTextOutputFormat.this.getInputFileBasedOutputFileName(job, keyBasedPath);
                        V actualValue = (V)entry.getValue();
                        RecordWriter<K, V> rw = (RecordWriter)this.recordWriters.get(finalPath);
                        if (rw == null) {
                            rw = RDDMultipleTextOutputFormat.this.getBaseRecordWriter(fs, job, finalPath, arg3);
                            this.recordWriters.put(finalPath, rw);
                        }
                        rw.write(null, actualValue);
                    }


//                    String keyBasedPath = RDDMultipleTextOutputFormat.this.generateFileNameForKeyValue(key, value, myName);
//                    String finalPath = RDDMultipleTextOutputFormat.this.getInputFileBasedOutputFileName(job, keyBasedPath);
//                    K actualKey = RDDMultipleTextOutputFormat.this.generateActualKey(key, value);
//                    V actualValue = RDDMultipleTextOutputFormat.this.generateActualValue(key, value);
//                    RecordWriter<K, V> rw = (RecordWriter)this.recordWriters.get(finalPath);
//                    if (rw == null) {
//                        rw = RDDMultipleTextOutputFormat.this.getBaseRecordWriter(fs, job, finalPath, arg3);
//                        this.recordWriters.put(finalPath, rw);
//                    }
//                    rw.write(null, actualValue);


                }

                public void close(Reporter reporter) throws IOException {
                    Iterator keys = this.recordWriters.keySet().iterator();

                    while(keys.hasNext()) {
                        RecordWriter<K, V> rw = (RecordWriter)this.recordWriters.get(keys.next());
                        rw.close(reporter);
                    }

                    this.recordWriters.clear();
                }
            };
        }

    }

}
