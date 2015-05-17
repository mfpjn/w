package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.State;
import at.mfpjn.workflow.model.StateTranslation;

import java.util.List;

public interface StateTranslationDao {

	List<StateTranslation> getStates(String language);

	StateTranslation getState(int Id);

	StateTranslation getStateByLanguageAndId(String language, State state_fk);

}
