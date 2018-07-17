package com.despegar.p13n.tests.benchmarks;

import static java.util.concurrent.ThreadLocalRandom.current;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@Fork(warmups = 1, value = 3)
@Warmup(iterations = 2, time = 1)
@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 3, time = 1)
public class MaxBenchMark {

  @Benchmark
  public Optional<Long> withStreams() {
    return elements.stream().max(Long::compareTo);
  }

  @Benchmark
  public void withLoop() {
    Optional<Long> result = Optional.empty();
    for (Long element : elements) {
      if (!result.isPresent() || element.compareTo(result.get()) > 0) {
        result = Optional.of(element);
      }
    }
  }

  private static final List<Long> elements = createElements();

  private static List<Long> createElements() {
    List<Long> result = new ArrayList<>(100);

    for (int i = 0; i < 100; i++) {
      result.add(current().nextLong());
    }
    return Collections.unmodifiableList(result);
  }
}
