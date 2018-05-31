package manglar.soporte.services;

import java.util.concurrent.atomic.AtomicLong;

public class LongIdGenerator implements IdGenerator {

  private AtomicLong atomicLong = new AtomicLong();

  @Override
  public long nextId() {
    return atomicLong.incrementAndGet();
  }
}
