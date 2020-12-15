package zero.x.spring;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
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
public class SystemGetEnvVsPath {

	public static void main(String[] args) throws Exception {
		Options opt = new OptionsBuilder()
				.include(SystemGetEnvVsPath.class.getSimpleName())
				.build();

		new Runner(opt).run();

	}

	@Fork(3)
	@Benchmark
	public String getEnv(){
		return System.getenv("HOSTNAME");
	}

	@Fork(3)
	@Benchmark
	public boolean paths(){
		return Paths.get("/var/test").toFile().exists()
				&& Paths.get("/var/test2").toFile().exists();
	}

}
