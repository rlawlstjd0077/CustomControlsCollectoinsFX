package ui.screen.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 스케줄 정보와 같이 시작/종료가 있는 정보 구조체.
 */
public class ScheduleDateTime {
  private ZonedDateTime start;
  private ZonedDateTime end;

  /**
   * ScheduleDateTime 생성자료 시작/종료 시각을 설정.
   *
   * @param start 시작 시각.
   * @param end   종료 시각.
   */
  @JsonCreator
  public ScheduleDateTime(@JsonProperty("start") ZonedDateTime start,
                          @JsonProperty("end") ZonedDateTime end) {
    this.start = start;
    this.end = end;
  }

  public ZonedDateTime getStart() {
    return start;
  }

  public ZonedDateTime getEnd() {
    return end;
  }

  @JsonIgnore
  public double getDurationSec() {
    return start.until(end, ChronoUnit.SECONDS);
  }
}
