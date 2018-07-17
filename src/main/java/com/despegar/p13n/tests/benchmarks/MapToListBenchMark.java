package com.despegar.p13n.tests.benchmarks;

import static com.despegar.p13n.tests.model.EnumElement.values;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import com.despegar.p13n.tests.model.Element;
import com.despegar.p13n.tests.model.EnumElement;

@Fork(warmups = 1, value = 3)
@Warmup(iterations = 2, time = 1)
@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 3, time = 1)
public class MapToListBenchMark {

  @Benchmark
  public List<String> withStreams() {
    return elements.stream().map(Object::toString).collect(toList());
  }

  @Benchmark
  public List<String> withLoop() {
    List<String> result = new ArrayList<>(elements.size());
    for (Element element : elements) {
      result.add(element.toString());
    }
    return result;
  }

  private static final List<Element> elements = createElements();

  private static List<Element> createElements() {
    List<Element> result = new ArrayList<>(100);

    for (int i = 0; i < 100; i++) {
      EnumElement enumElement = values()[current().nextInt(values().length)];
      result.add(new Element(enumElement, "string-" + enumElement));
    }
    return Collections.unmodifiableList(result);
  }
}
