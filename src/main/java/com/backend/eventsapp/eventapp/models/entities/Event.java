package com.backend.eventsapp.eventapp.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.backend.eventsapp.eventapp.enums.EvenType;
import com.backend.eventsapp.eventapp.enums.EventStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    @Enumerated(value = jakarta.persistence.EnumType.STRING)
    private EvenType type;

    @Column(nullable = true)
    private double latitude;

    @Column(nullable = true)
    private double longitude;

    @Column(nullable = true)
    private String url;

    @Column(nullable = false)
    @Enumerated(value = jakarta.persistence.EnumType.STRING)
    private EventStatus status;

    @ManyToMany
    @JoinTable(name = "event_suscriptions", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> suscribers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private EventCategory category;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();
}
