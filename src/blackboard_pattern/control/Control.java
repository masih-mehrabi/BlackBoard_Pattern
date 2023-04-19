package blackboard_pattern.control;

import java.util.ArrayList;
import java.util.List;

import blackboard_pattern.blackboard.BlackBoard;
import blackboard_pattern.knowledgesource.KnowledgeSource;


import static blackboard_pattern.blackboard.BlackBoardState.*;

public class Control {

	private final BlackBoard blackBoard;
	private final InputSource inputSource;
	


	public Control(final BlackBoard blackBoard) {
		this.blackBoard = blackBoard;
		this.inputSource = new InputSource();
	}

	public void start() {
		for (String sentence : inputSource.getSentences()) {
			this.blackBoard.clean();
			this.blackBoard.setInputSentence(sentence);
			this.blackBoard.setState(INPUT_SENTENCE_SET);
			applyKnowledgeSources();
			System.out.println("\nOriginal sentence: " + sentence);
			System.out.println("Synonym sentence: " + this.blackBoard.getSynonymSentence());
			System.out.println("Encrypted sentence: " + this.blackBoard.getEncryptedSentence() + "\n");
		}
	}

	public void applyKnowledgeSources() {
		while (blackBoard.getState() != DONE) {
			List<KnowledgeSource> candidateKnowledgeSources = new ArrayList<>();
			for (KnowledgeSource knowledgeSource : this.blackBoard.getKnowledgeSources()) {
				if (knowledgeSource.executeCondition()) {
					candidateKnowledgeSources.add(knowledgeSource);
				}
			}
			
			if (!candidateKnowledgeSources.isEmpty()) {
				for (KnowledgeSource knowledgeSource: candidateKnowledgeSources) {
					knowledgeSource.executeAction();
				}
			} else {
				blackBoard.setState(DONE);
			}
		}
	}
}
