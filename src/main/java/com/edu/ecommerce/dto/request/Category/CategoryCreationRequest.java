package com.edu.ecommerce.dto.request.Category;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreationRequest {

    @Size(min = 3, max = 20, message = "CATEGORY_NAME_INVALID")
    String name;

    @Size(min = 3, max = 100, message = "CATEGORY_DESCRIPTION_INVALID")
    String description;

    boolean status;
}
