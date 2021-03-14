package com.mohey.pma.dao;

import com.mohey.pma.entities.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
