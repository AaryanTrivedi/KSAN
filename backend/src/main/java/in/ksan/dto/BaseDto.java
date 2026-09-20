package in.ksan.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BaseDto (Long id,LocalDate creationDate,LocalDateTime updationTimeStamp){}
