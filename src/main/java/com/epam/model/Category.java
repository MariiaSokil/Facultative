package com.epam.model;

import com.epam.validator.BasicInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Category model.
 * @author M.Sokil
 */
@Data @Accessors(chain = true)
@Entity
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"})
@Table(name="categories")
public class Category {
    @Id
    @Column(name="category_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotBlank(message = "Name is mandatory",groups = BasicInfo.class)
    @Size(max = 30, groups = BasicInfo.class)
    @NotNull(groups = BasicInfo.class)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}


