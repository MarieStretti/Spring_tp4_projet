package eu.ensg.Spring_tp4_projet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "evenement" )
public class Evenement {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    
    @Column(name="num_event")
    private int numevent;
    
    @Column(name="intitule", nullable=false)
    private String intitule;
    
    @Column(name="theme", nullable=false)
    private String theme;
    
    @Column(name="date_debut")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datedebut;
    
    @Column(name="duree")
    private int duree;
    
    @Column(name="nb_part_max")
    private int nbpartmax;
    
    @Column(name="description")
    private String description;
    
    @Column(name="organisateur")
    private String organisateur;
    
    @Column(name="type_event")
    private String typeevent;
    
    @OneToMany(mappedBy="event")
    public List<Participant> participants = new ArrayList<>();

    
    public int getNumevent() {
        return numevent;
    }

    public void setNumevent(int numevent) {
        this.numevent = numevent;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNbpartmax() {
        return nbpartmax;
    }

    public void setNbpartmax(int nbpartmax) {
        this.nbpartmax = nbpartmax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getTypeevent() {
        return typeevent;
    }

    public void setTypeevent(String typeevent) {
        this.typeevent = typeevent;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
    
    public void addParticipant(Participant participant) {
    	this.participants.add(participant);
    }
    
}
