package com.testcode.yjp.last.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainerSearchDto {
    private String search; // PTController의 search()
    private String head; // PTController의 search()

    private Long member_id; // PTController의 trainerApply()
    private Long trainer_id; // PTController의 trainerApply()

}
