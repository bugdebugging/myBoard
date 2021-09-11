package com.anny.board.boardme.domain.category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tbl_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    @Builder(builderMethodName = "of")
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
