package com.cleaning.exposition.representation.response.shared;

import lombok.*;

@AllArgsConstructor
@Getter
public class ObjectValueRepresentation<T> {
    private final T value;
}
