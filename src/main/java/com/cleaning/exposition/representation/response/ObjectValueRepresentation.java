package com.cleaning.exposition.representation.response;

import lombok.*;

@AllArgsConstructor
@Getter
public class ObjectValueRepresentation<T> {
    private final T value;
}
