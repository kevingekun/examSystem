package com.wondersgroup.kaoshi.service;

import java.util.List;

import com.wondersgroup.kaoshi.bo.Cz70;
import com.wondersgroup.kaoshi.bo.Tjobsubject;

public interface ProfessionService {
	public List<Cz70> findByName(String name);
	public List<Tjobsubject> findByjobnameAndDj(String jobname, String dj);
	public boolean findByjobname(String jobname);
}
