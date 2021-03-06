package benchmark;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Warmup;

import benchmark.TimeseriesDTO;

@State(Scope.Benchmark)
//@Measurement(iterations = 1000000, timeUnit = TimeUnit.SECONDS)
//@Fork(3)
//@BenchmarkMode(Mode.AverageTime)
//--
//@Measurement(iterations = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1) 		// Warmup Iteration = n1
@Measurement(iterations = 3) 	// Iteration = n2
public class SerializationBenchmark {
  
  TimeseriesDTO tsd;
  ObjectOutputStream out;
  
  @Setup(Level.Iteration)
  public void setUp() throws Exception {
	  //OutputStream stream = new OutputStream.nullOutputStream(); // since java 11
	  OutputStream stream = new OutputStream() {
		  @Override
		  public void write(int b) {}
	  };
	  out = new ObjectOutputStream(stream);
	  tsd = new TimeseriesDTO();
  }
  
  @TearDown
  public void tearDown() throws Exception {
      out.close();
  }

  @Benchmark
  public void serializeTimeSeriesDTO() throws IOException {
	  out.writeObject(tsd);
  }
}
