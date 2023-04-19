package blackboard_pattern.knowledgesource;

import blackboard_pattern.blackboard.BlackBoard;
import blackboard_pattern.blackboard.BlackBoardState;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.List;

public class PrivacyProtector extends KnowledgeSource {
	private static final String ENCRYPTION_KEY = "SXGWOwgzldpLZPDkoHell0ifWrlduJYvjhtysecureFrOmN$A.ACE";
	
	public PrivacyProtector(BlackBoard blackBoard) {
		super(blackBoard);
	}
	
	public boolean executeCondition() {
		
		return blackBoard.getState() == BlackBoardState.TOPIC_SENTENCE_SET && isEncryptionRequired();
		
	}

	public void executeAction() {
			updateBlackBoard();
		
	}

	protected void updateBlackBoard() {

			blackBoard.setEncryptedSentence(encrypt(blackBoard.getSynonymSentence()));
			blackBoard.setState(BlackBoardState.ENCRYPTED_SENTENCE_SET);
	}

	private boolean isEncryptionRequired() {

        List<TaggedWord> taggedValues = this.blackBoard.getTaggedValues();
        if (taggedValues != null && !taggedValues.isEmpty()) {
            for (TaggedWord word : taggedValues) {
                if ("excellence".equals(word.getTag()) || "antarctic animal".equals(word.getTag())) {
                    return true;
                }
            }
        }
		return false;
	}

	private String encrypt(String plainText) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(ENCRYPTION_KEY);
		return textEncryptor.encrypt(plainText);
	}
}
