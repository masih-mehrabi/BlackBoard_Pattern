package blackboard_pattern.knowledgesource;

import blackboard_pattern.blackboard.BlackBoard;


public abstract class KnowledgeSource {

	protected BlackBoard blackBoard;

	protected KnowledgeSource(BlackBoard blackBoard) {
		this.blackBoard = blackBoard;
	}

	protected abstract void updateBlackBoard();

	public abstract boolean executeCondition();

	public abstract void executeAction();
}
