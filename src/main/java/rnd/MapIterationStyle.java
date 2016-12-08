package rnd;

import java.util.Map;
import org.openjdk.jmh.infra.Blackhole;

public enum MapIterationStyle {
  INTERNAL {
    @Override
    public void apply(Map<?, ?> map, Blackhole sink) {
      map.forEach((key, value) -> {
        sink.consume(key);
        sink.consume(value);
      });
    }
  },
  ENTRY_SET_INTERNAL {
    @Override
    public void apply(Map<?, ?> map, Blackhole sink) {
      map.entrySet().forEach(entry -> {
        sink.consume(entry.getKey());
        sink.consume(entry.getValue());
      });
    }
  },
  ENTRY_SET_EXTERNAL {
    @Override
    public void apply(Map<?, ?> map, Blackhole sink) {
      for (Map.Entry<?, ?> entry : map.entrySet()) {
        sink.consume(entry.getKey());
        sink.consume(entry.getValue());
      }
    }
  },
  KEY_SET_INTERNAL {
    @Override
    public void apply(Map<?, ?> map, Blackhole sink) {
      map.keySet().forEach(key -> {
        sink.consume(key);
        sink.consume(map.get(key));
      });
    }
  },
  KEY_SET_EXTERNAL {
    @Override
    public void apply(Map<?, ?> map, Blackhole sink) {
      for (Object key : map.keySet()) {
        sink.consume(key);
        sink.consume(map.get(key));
      }
    }
  };

  public abstract void apply(Map<?, ?> map, Blackhole sink);
}
