package blackboard_pattern;

import blackboard_pattern.control.Control;
import blackboard_pattern.knowledgesource.PrivacyProtector;
import blackboard_pattern.knowledgesource.SynonymIdentifier;
import blackboard_pattern.blackboard.BlackBoard;
import blackboard_pattern.knowledgesource.WordTagger;

public class Main {
	public static void main(String[] args) {
		BlackBoard blackBoard = new BlackBoard();

		blackBoard.addKnowledgeSource(new SynonymIdentifier(blackBoard));
		blackBoard.addKnowledgeSource(new WordTagger(blackBoard));
		blackBoard.addKnowledgeSource(new PrivacyProtector(blackBoard));
		
		Control control = new Control(blackBoard);
		control.start();
	}
}
