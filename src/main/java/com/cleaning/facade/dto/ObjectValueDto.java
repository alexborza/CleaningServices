package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ObjectValueDto<T> {
    private final T value;
}
