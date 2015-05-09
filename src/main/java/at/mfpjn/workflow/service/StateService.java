package at.mfpjn.workflow.service;

import at.mfpjn.workflow.dao.StateDao;
import at.mfpjn.workflow.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stateService")
public class StateService {

	@Autowired
	private StateDao stateDao;

	public List<State> getStates() {
		return stateDao.getStates();
	}

	public State getState(int stateId) {
		return stateDao.getState(stateId);
	}
}
