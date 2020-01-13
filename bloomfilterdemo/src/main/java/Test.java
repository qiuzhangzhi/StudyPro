import com.getui.axe.v3.codis.common.JodisFactory;
import com.google.common.hash.Funnels;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于guava分布式布隆过滤器
 */
public class Test {
    public static void main(String[] args) throws Throwable{
        BloomFilterHelper bloomFilterHelper =  new BloomFilterHelper<CharSequence>(Funnels.stringFunnel(), 1000, 0.1);

        //JedisCluster cluster = null;
        Jedis jedis = JodisFactory.getCodisPool("192.168.10.36:2181", "/zk/codis/db_codis-demo/proxy", 100).getResource();

        RedisBloomFilter redisBloomFilter = new RedisBloomFilter( jedis);

        int j = 0;
        for (int i = 0; i < 100; i++) {
            redisBloomFilter.addByBloomFilter(bloomFilterHelper, "bloom", i+"");
        }
        for (int i = 0; i < 1000; i++) {
            boolean result = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, "bloom", i+"");
            if (!result) {
                j++;
            }
        }
//        System.out.println("漏掉了" + j + "个");
        //1577852730000
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(new Date(Long.parseLong("1577852730000"))));
    //    System.out.println(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000);
    }
}

