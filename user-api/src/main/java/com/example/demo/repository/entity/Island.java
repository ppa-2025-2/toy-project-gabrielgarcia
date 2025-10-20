package com.example.demo.repository.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// one-to-one (um-para-um)
// one-to-many or many-to-one
// relationship owner (proprietário do relacionamento)

// many-to-many
// exigir uma tabela associativa

@Entity
@Table(name = "islands")
public class Island extends BaseEntity {

    public enum Disposition {
        PAIR(2), // 0
        TRIANGLE(3), // 1
        SQUARE(4), // 2
        RECTANGLE(6), // 3
        CIRCULAR(8); // 4

        private final int placements;

        public int getPlacements() {
            return placements;
        }

        Disposition(int placements) {
            this.placements = placements;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Disposition disposition;

    @OneToMany(mappedBy = "island", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Workstation> workstations = new HashSet<>();

    public void removeWorkstations(Predicate<Workstation> predicate) {
        this.workstations.removeIf(predicate);
    }

    public Set<Workstation> getWorkstations() {
        return workstations;
    }

    public void setWorkstations(Set<Workstation> workstations) {
        this.workstations = workstations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Disposition getDisposition() {
        return disposition;
    }

    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
    }

    public void alocarWorkstation(@NonNull User user) {
        // primeira workstation livre e seta o usuário
        this.getWorkstations().stream()
                .filter(ws -> ws.getUser() == null)
                .findFirst()
                .ifPresent(ws -> ws.setUser(user));
    }

    @Override
    public String toString() {
        return "Island [id=" + id
                + ", description=" + description
                + ", disposition=" + disposition
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + "]";
    }

    public Optional<Workstation> firstAvailableWorkstation() {
        return this.workstations.stream()
                .filter(w -> w.getUser() == null)
                .findFirst();
    }

    public void assignUserToTheFirstWorkstationAvailable(User user) {
        firstAvailableWorkstation()
                .ifPresent(w -> w.setUser(user));
    }

    public static Island encontrarIslandParaAlocacao(@NonNull List<Island> islands) {
        // busca ilhas começando por uma ws livre, depois duas, ...
        Island freeIsland = islands.get(0);
        for (int slots = 1; slots < Island.Disposition.CIRCULAR.getPlacements(); slots++) {
            final int positions = slots;
            var possibleIsland = islands.stream()
                    .filter(i -> i.getWorkstations().stream()
                            .map(Workstation::getUser)
                            .filter(Objects::nonNull)
                            .count() == positions)
                    .findFirst();
            if (possibleIsland.isPresent()) {
                freeIsland = possibleIsland.get();
                break;
            }
        }
        return freeIsland;
    }

}
