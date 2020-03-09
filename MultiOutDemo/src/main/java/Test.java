import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String args[]) throws IOException {
        String[] featureParts = "a_b_c_d".split("_");
        File file = new File("./outname.txt");
        for (String a : featureParts) {
            FileUtils.writeStringToFile(file,a +"\n",true);
        }
        //System.out.println(featureParts[featureParts.length-1]);
    }
}
