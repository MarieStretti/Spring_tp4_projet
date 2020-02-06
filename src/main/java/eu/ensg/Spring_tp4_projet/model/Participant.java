/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.Spring_tp4_projet.model;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "participant" )
public class Participant {
    
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    
    @Column(name="num_pers")
    private int numpers;
    
    @NotNull
    @Column(name="nom", nullable=false)
    private String nom;
    
    @NotNull
    @Column(name="prenom", nullable=false)
    private String prenom;
    
    @NotNull
    @Column(name="email", nullable=false)
    private String email;
    
    @Column(name="date_naiss")
    private LocalDate datenaiss;
    
    @Column(name="organisation")
    private String organisation;
    
    @Column(name="observations")
    private String observations;
    
    @ManyToOne
    public Evenement event;
    
    public Participant(){
        
    }

    public Participant(String nom, String prenom, String email, LocalDate date_naiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.datenaiss = date_naiss;
    }
    
    public int getNumpers() {
        return numpers;
    }

    public void setNumpers(int numpers) {
        this.numpers = numpers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(LocalDate datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public String toString(){
        return this.nom +" "+ this.prenom;
    }
    
}
