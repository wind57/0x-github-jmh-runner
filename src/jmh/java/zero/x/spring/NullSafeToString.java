package zero.x.spring;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10, time = 10)
@Measurement(iterations = 10, time = 10)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class NullSafeToString {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
            .include(NullSafeToString.class.getSimpleName())
            .build();

        new Runner(opt).run();

    }

    @Param(value = {"-1", "0", "10", "100", "1000"})
    int size;

    @Fork(3)
    @Benchmark
    public String nullSafeToString() {

        byte[] array;
        if (size == -1) {
            array = null;
        } else {
            array = new byte[size];
            Arrays.fill(array, (byte) 1);
        }

        if (array == null) {
            return "null";
        }
        int length = array.length;
        if (length == 0) {
            return "{}";
        }
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");
        for (short s : array) {
            stringJoiner.add(String.valueOf(s));
        }
        return stringJoiner.toString();
    }

    @Fork(3)
    @Benchmark
    public String refactored() {

        byte[] array;
        if (size == -1) {
            array = null;
        } else {
            array = new byte[size];
            Arrays.fill(array, (byte) 1);
        }

        if (array == null) {
            return "null";
        }

        String inter = Arrays.toString(array);
        return "{" + inter.substring(1, inter.length() - 1) + "}";

    }

}

