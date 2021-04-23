package benchmark;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Random;

public class TimeseriesDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	long runId;
	String model;
	String scenario;
	int version;
	String region;
	String variable;
	String unit;
	Integer meta;
	String subannual;
	Integer time;
	int year;
	Double value;


    TimeseriesDTO() {
        Random rand = new Random();
        runId = rand.nextLong();
        
        model = randomString();
        scenario = randomString();
        version = rand.nextInt();

        region = randomString();
        variable = randomString();
        unit = randomString();
        meta = rand.nextInt();
        subannual = randomString();
        time = rand.nextInt();
        year = rand.nextInt();
        value = rand.nextDouble();
    }

    String randomString() {
        Random rand = new Random();
        byte[] array = new byte[Math.abs(rand.nextInt()) % 30];
        rand.nextBytes(array);
        String string = new String(array, Charset.forName("UTF-8"));
        return string;
    }
}
