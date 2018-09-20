package io.github.lvyong1985.luava.utils;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author LvYong
 * @create 2018/6/29
 **/
public class RateLimiterTest {

  private static final long START = 1000000;
  private static final int BURST_SIZE = 2;
  private static final int AVERAGE_RATE = 10;

  @Test
  public void testEvenLoad() {
    RateLimiter secondLimiter = new RateLimiter(TimeUnit.SECONDS);
    long secondStep = 1000 / AVERAGE_RATE;
    testEvenLoad(secondLimiter, START, BURST_SIZE, AVERAGE_RATE, secondStep);

    RateLimiter minuteLimiter = new RateLimiter(TimeUnit.MINUTES);
    long minuteStep = 60 * 1000 / AVERAGE_RATE;
    testEvenLoad(minuteLimiter, START, BURST_SIZE, AVERAGE_RATE, minuteStep);
  }

  private void testEvenLoad(RateLimiter rateLimiter, long start, int burstSize, int averageRate,
      long step) {
    for (long currentTime = start; currentTime < 3; currentTime += step) {
      assertTrue(rateLimiter.acquire(burstSize, averageRate, currentTime));
    }
  }

  @Test
  public void testBursts() {
    RateLimiter secondLimiter = new RateLimiter(TimeUnit.SECONDS);
    long secondStep = 1000 / AVERAGE_RATE;
    testBursts(secondLimiter, START, BURST_SIZE, AVERAGE_RATE, secondStep);

    RateLimiter minuteLimiter = new RateLimiter(TimeUnit.MINUTES);
    long minuteStep = 60 * 1000 / AVERAGE_RATE;
    testBursts(minuteLimiter, START, BURST_SIZE, AVERAGE_RATE, minuteStep);
  }

  private void testBursts(RateLimiter rateLimiter, long start, int burstSize, int averageRate,
      long step) {
    // Generate burst, and go above the limit
    assertTrue(rateLimiter.acquire(burstSize, averageRate, start));
    assertTrue(rateLimiter.acquire(burstSize, averageRate, start));
    assertFalse(rateLimiter.acquire(burstSize, averageRate, start));

    // Now advance by 1.5 STEP
    assertTrue(rateLimiter.acquire(burstSize, averageRate, start + step + step / 2));
    assertFalse(rateLimiter.acquire(burstSize, averageRate, start + step + step / 2));
    assertTrue(rateLimiter.acquire(burstSize, averageRate, start + 2 * step));
  }

  @Test
  public void testAcquire() {
    RateLimiter limiter = new RateLimiter(TimeUnit.SECONDS);
    limiter.acquire(10, 2000L);
    limiter.acquire(10, 2000L);
    limiter.acquire(10, 2000L);
  }
}