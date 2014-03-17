package cz.martinbayer.parser.logback.pattern;

import cz.martinbayer.parser.logback.pattern.logic.DateTimePattern;
import cz.martinbayer.parser.logback.pattern.logic.LevelPattern;

public enum ConversionTypesEnum {

	DATE_TIME_TYPE(ConversionWordsEnum.DATE_TIME_OF_EVENT,
			new DateTimePattern()), LEVEL_TYPE(
			ConversionWordsEnum.LEVEL_OF_EVENT, new LevelPattern());

	private ConversionWordsEnum words;
	private TypedPattern typedPattern;

	ConversionTypesEnum(ConversionWordsEnum words, TypedPattern typedPattern) {
		this.words = words;
		this.typedPattern = typedPattern;
	}

	public ConversionWordsEnum getWords() {
		return words;
	}

	public TypedPattern getTypedPattern() {
		return typedPattern;
	}
}
