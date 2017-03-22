package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Mission;
import entities.Skill;


@Remote
public interface MissionServiceEJBRemote {
	public void AddMission(Mission m);
	public void UpdateMission(Mission m);
	public void DeleteMission(Mission m);
	public Mission findMissionId(int id);

	public Mission findMissionByName(String name);
	public List<Mission> findMission(int id);
	public List<Skill>findAllSkills();
	public void addSkill(Skill s);
	
	
	

}
