package in.ksan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public record SellProductRequestDto(
  int price, // to be added when user sells
  int stockToSell //the amount user wants to sell, cannot be less that total stock:
){}
