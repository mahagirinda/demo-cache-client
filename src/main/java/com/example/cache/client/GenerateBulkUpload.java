package com.example.cache.client;

import java.text.DecimalFormat;
import java.util.Random;

public class GenerateBulkUpload {
    public static void main(String[] args) {

        int i;
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("0000");
        String ExternalRefPrefix = "MHUPLOADDEMO_COLW_013_";

        for (i=1; i<= 100; i++) {
            if (i%3 == 0) {
                System.out.println(ExternalRefPrefix.concat(df.format(i))
                        + "|AO001|AO001000000410|AO001000000208||LOCAL|TLKM|1000|20190422|UPLOAD_BULK_TEST_MAHA");
            } else if (i%3 == 1) {
                System.out.println(ExternalRefPrefix.concat(df.format(i))
                        + "|DX001|DX001000000419|DX001000000217||LOCAL|TLKM|1000|20190422|UPLOAD_BULK_TEST_MAHA");
            } else {
                int random1 = random.nextInt(900) + 100;
                int random2 = random.nextInt(900) + 100;
                int random3 = random.nextInt(900) + 100;
                System.out.println(ExternalRefPrefix.concat(df.format(i)) + "|DX"+random1+"|DX001000000"+random2
                        +"|DX001000000"+random3+"||LOCAL|TLKM|1000|20190422|UPLOAD_BULK_TEST_MAHA");
            }
        }

    }
}
