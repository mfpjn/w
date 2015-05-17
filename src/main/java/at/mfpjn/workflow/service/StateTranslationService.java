package at.mfpjn.workflow.service;

import at.mfpjn.workflow.dao.StateTranslationDao;
import at.mfpjn.workflow.model.State;
import at.mfpjn.workflow.model.StateTranslation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stateTranslationService")
public class StateTranslationService {

	@Autowired
	private StateTranslationDao stateTranslationDao;

	public List<StateTranslation> getStates(String language) {
		return stateTranslationDao.getStates(language);
	}

	public StateTranslation getState(int stateId) {
		return stateTranslationDao.getState(stateId);
	}

	public StateTranslation getStateByLanguageAndId(String language, State state_fk) {
		return stateTranslationDao.getStateByLanguageAndId(language, state_fk);
	}
}
