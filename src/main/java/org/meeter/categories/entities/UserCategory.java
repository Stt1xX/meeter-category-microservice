package org.meeter.categories.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_category")
public class UserCategory {
    @EmbeddedId
    private UserCategoryId id;

    @MapsId("categoryId")
    @ManyToOne
    @JoinColumn(name = "category_id",  nullable = false)
    private Category category;
}
