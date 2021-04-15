package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Category model.
 * @author M.Sokil
 */
@Builder
@Data
@Entity
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"})
@Table(name="categories")
public class Category {
    @Id
    @Column(name="category_id")
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}


