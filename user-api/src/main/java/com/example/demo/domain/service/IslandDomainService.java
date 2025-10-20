package com.example.demo.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.example.demo.repository.entity.Island;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.entity.Workstation;

public class IslandDomainService {

    public Island alocarWorkstation(@NonNull List<Island> islands, @NonNull User user) {
        // island 0 square 2/4
        // island 1 triangular 2/3
        // island 3 rectangular 1/6

        // busca ilhas começando por uma ws livre, depois duas, ...
        Island freeIsland = islands.getFirst();
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

        // primeira workstation livre e seta o usuário
        freeIsland.getWorkstations().stream()
                .filter(ws -> ws.getUser() == null)
                .findFirst()
                .ifPresent(ws -> ws.setUser(user));

        return freeIsland;
    }
}