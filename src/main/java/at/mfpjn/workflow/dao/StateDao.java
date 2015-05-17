package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.State;

import java.util.List;


public interface StateDao {
	
List<State> getStates();
	
	State getState(int Id);
	
	State getStateByName(String name);

}
