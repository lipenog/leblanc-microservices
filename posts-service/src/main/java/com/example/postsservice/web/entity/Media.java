package com.example.postsservice.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media")
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter @Setter
public class Media {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Posts posts;
    @Column(name = "media_type")
    private String mediaType;
    @Column(name = "media_path")
    private String mediaPath;
}
