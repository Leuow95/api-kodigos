package com.kodigos_challenge.api.entities;

import java.util.List;

public record OrderRequestDto(
        String description,
        List<String> checklist,
        String photoData
) {
}
