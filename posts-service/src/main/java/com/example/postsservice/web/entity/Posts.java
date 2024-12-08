package com.example.postsservice.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Posts {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Media> mediaSet;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    @PrePersist
    private void prePersist(){
        this.publishedAt = LocalDateTime.now(ZoneId.of("UTC"));
        this.mediaSet.forEach(m -> m.setPosts(this));
    }
}
