package hse.seminar10.mock.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataSearchRequest {

  private String id;

  private Date updatedBefore;

  private int length;
}
