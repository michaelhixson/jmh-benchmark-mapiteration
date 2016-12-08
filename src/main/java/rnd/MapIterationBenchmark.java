package rnd;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(1)
public class MapIterationBenchmark {
  @Param private MapImplementation mapImplementation;
  @Param private MapIterationStyle mapIterationStyle;
  private Map<?, ?> map;

  @Setup
  public void setup() {
    map = mapImplementation.get();
  }

  @Benchmark
  public void run(Blackhole sink) {
    mapIterationStyle.apply(map, sink);
  }

  public static void main(String[] args) throws Exception {
    Options options = new OptionsBuilder()
        .include(MapIterationBenchmark.class.getName())
        .build();
    new Runner(options).run();
  }
}
