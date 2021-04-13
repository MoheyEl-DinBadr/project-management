package com.mohey.pma.dao;

import com.mohey.pma.dto.ChartData;
import com.mohey.pma.dto.ProjectTimelineDto;
import com.mohey.pma.entities.Employee;
import com.mohey.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 15, 2021
 */
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(stage) as value FROM project GROUP BY stage")
    List<ChartData> getProjectStatus();

    @Query(nativeQuery = true, value = "SELECT name, start_date as startDate, end_date as endDate FROM project")
    List<ProjectTimelineDto> getProjectsTimeline();

}
