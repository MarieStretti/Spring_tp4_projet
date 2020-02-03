/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.Spring_tp4_projet.model;

import org.springframework.data.repository.CrudRepository;
import eu.ensg.Spring_tp4_projet.model.Participant;

public interface ParticipantRepository extends CrudRepository <Participant, Integer> {
    
}
