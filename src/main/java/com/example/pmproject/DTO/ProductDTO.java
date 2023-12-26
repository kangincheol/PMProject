package com.example.pmproject.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long productId;

    @NotEmpty(message = "상품 이름은 필수 입력 사항입니다.")
    private String name;

    @NotEmpty(message = "상품 설명은 필수 입력 사항입니다.")
    private String content;

    @NotNull(message = "상품 가격은 필수 입력 사항입니다.")
    private Integer price;

    private String img;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
