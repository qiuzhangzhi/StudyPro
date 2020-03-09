import com.getui.axe.v3.codis.common.JodisFactory;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WriteCodis {
    public static void main(String args[]) throws Exception{
        //Jedis jedis = JodisFactory.getCodisPool("192.168.18.32:2182", "/zk/codis/db_codis-test-ceshi/proxy", 100).getResource();
        Jedis jedis = JodisFactory.getCodisPool("192.168.10.36:2181", "/zk/codis/db_codis-demo/proxy", 100).getResource();
        jedis.set("c6nxM5Rbz7A1tDkMoT5TN11","{\"appid\":\"c6nxM5Rbz7A1tDkMoT5TN11\",\"planlist\":[{\"eventA\":\"eventId001\",\"eventAAttri\":[{\"attrName\":\"$app_version\",\"symbol\":\"eq\",\"type\":\"string\",\"value\":[\"12.2.2\"]},{\"attrName\":\"$channelId\",\"symbol\":\"contain\",\"type\":\"string\",\"value\":[\"360\"]}],\"eventB\":\"eventId002\",\"eventBAttri\":[{\"attrName\":\"sex\",\"symbol\":\"neq\",\"type\":\"string\",\"value\":[\"女\"]},{\"attrName\":\"搜索水果\",\"symbol\":\"neq\",\"type\":\"string\",\"value\":[\"苹果\"]}],\"eventType\":\"1\",\"planEndTime\":1585922414000,\"planId\":\"IOP_0128_6757cad538234ebf961b422b225555a5\",\"planStartTime\":1583244014000,\"planType\":\"4\",\"targetAttri\":[{\"attrName\":\"$os\",\"symbol\":\"eq\",\"type\":\"string\",\"value\":[\"\"]},{\"attrName\":\"$screen_height\",\"symbol\":\"notNull\",\"type\":\"number\"},{\"attrName\":\"$province\",\"symbol\":\"notContain\",\"type\":\"string\",\"value\":[\"\"]}],\"targetEvent\":\"eventId001\",\"targetInterval\":15,\"targetIntervalType\":\"0\",\"timeOut\":false,\"trigerInterval\":2,\"trigerIntervalType\":\"0\"}]}");

                //jedis.set("c6nxM5Rbz7A1tDkMoT5TN2","{\"appid\":\"c6nxM5Rbz7A1tDkMoT5TN2\",\"planlist\":[{\"eventA\":\"购买书包\",\"eventAAttri\":[{\"attrName\":\"年龄\",\"symbol\":\"gt\",\"type\":\"string\",\"value\":[\"12\"]}],\"eventB\":\"购买书包\",\"eventBAttri\":[{\"attrName\":\"年龄\",\"symbol\":\"lt\",\"type\":\"string\",\"value\":[\"60\"]}],\"eventType\":\"0\",\"planEndTime\":1580313600000,\"planId\":\"IOP_0116_6757cad538234ebf961b422b228403a5\",\"planStartTime\":1579104000000,\"planType\":\"3\",\"targetAttri\":[{\"attrName\":\"年龄\",\"symbol\":\"lt\",\"type\":\"string\",\"value\":[\"60\"]}],\"targetEvent\":\"购买书包\",\"targetInterval\":5,\"targetIntervalType\":\"0\",\"timeOut\":false,\"trigerInterval\":2,\"trigerIntervalType\":\"1\"},{\"eventA\":\"购买商品\",\"eventAAttri\":[{\"attrName\":\"商品品类\",\"symbol\":\"eq\",\"type\":\"string\",\"value\":[\"食品\"]}],\"eventType\":\"0\",\"planEndTime\":1579190400000,\"planId\":\"IOP_0116_191001b2dd634338af2ce4cc1243eaf6\",\"planStartTime\":1579104000000,\"planType\":\"4\",\"targetAttri\":[],\"targetEvent\":\"\",\"targetInterval\":15,\"targetIntervalType\":\"0\",\"timeOut\":false,\"trigerInterval\":0}]}");
//        //jedis.set("c6nxM5Rbz7A1tDkMoT5TN1","{\"appid\":\"c6nxM5Rbz7A1tDkMoT5TN1\",\"planlist\":[{\"eventA\":\"加入购物车\",\"eventAAttri\":[{\"attrName\":\"商品种类\",\"symbol\":\"neq\",\"type\":\"string\",\"value\":[\"速食\"]}],\"eventB\":\"购买食品\",\"eventBAttri\":[{\"attrName\":\"食品种类\",\"symbol\":\"contain\",\"type\":\"string\",\"value\":[\"有机\"]}],\"eventType\":\"0\",\"planEndTime\":1580313600000,\"planId\":\"IOP_0116_6757cad538234ebf961b422b228403a7\",\"planStartTime\":1579104000000,\"planType\":\"3\",\"targetAttri\":[{\"attrName\":\"食品种类\",\"symbol\":\"eq\",\"type\":\"string\",\"value\":[\"有机\"]}],\"targetEvent\":\"购买食品\",\"targetInterval\":3,\"targetIntervalType\":\"0\",\"timeOut\":false,\"trigerInterval\":2,\"trigerIntervalType\":\"1\"},{\"eventA\":\"购买商品\",\"eventAAttri\":[{\"attrName\":\"商品品类\",\"symbol\":\"eq\",\"type\":\"string\",\"value\":[\"食品\"]}],\"eventType\":\"0\",\"planEndTime\":1579190400000,\"planId\":\"IOP_0116_191001b2dd634338af2ce4cc1243eaf8\",\"planStartTime\":1579104000000,\"planType\":\"4\",\"targetAttri\":[],\"targetEvent\":\"\",\"targetInterval\":15,\"targetIntervalType\":\"0\",\"timeOut\":false,\"trigerInterval\":0}]}");
//
//        //jedis.set("bloom_count_IOP_0111_43a4db386883421789b5c161689c1bbc","1000");
//         // System.out.println("hhhhh");
         System.out.println(jedis.get("c6nxM5Rbz7A1tDkMoT5TN11"));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(sdf.parse("2019-02-02 12:12:13"));
//        System.out.println("day:" + calendar.get(Calendar.DAY_OF_MONTH));
//        System.out.println("hour:" + calendar.get(Calendar.HOUR_OF_DAY));
//        System.out.println("min:" + calendar.get(Calendar.MINUTE));
//
//        System.out.println(isRefreshXD("2019-02-02 13:12:13","2019-02-02 12:12:13"));

    }


    private static boolean isRefreshXD(String startTime, String endTime) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(startTime));
        int startHour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.setTime(sdf.parse(endTime));
        int endHour = calendar.get(Calendar.HOUR_OF_DAY);
        if (startHour != endHour) {
            return true;
        }
        return false;

    }
}
