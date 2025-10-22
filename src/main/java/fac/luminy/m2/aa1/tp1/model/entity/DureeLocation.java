package fac.luminy.m2.aa1.tp1.model.entity;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public record DureeLocation(LocalDate dateDebut, LocalTime dateFin) {
}
