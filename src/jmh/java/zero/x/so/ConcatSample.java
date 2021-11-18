package zero.x.so;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
public class ConcatSample {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(ConcatSample.class.getSimpleName())
                .verbosity(VerboseMode.EXTRA)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    @Fork(1)
    public String one() {
        String result = "";
        for (int i = 0; i < 200000; i++) {
            result = new StringBuilder(result).append(String.valueOf(i)).toString();
        }

        return result;
    }

    @Benchmark
    @Fork(1)
    public String two() {

        String result = "";
        for (int i = 0; i < 200000; i++) {
            result += String.valueOf(i);
        }

        return result;
    }
}
