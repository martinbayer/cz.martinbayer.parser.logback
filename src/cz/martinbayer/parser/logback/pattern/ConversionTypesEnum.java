package cz.martinbayer.parser.logback.pattern;

import cz.martinbayer.parser.logback.pattern.logic.DateTimePattern;
import cz.martinbayer.parser.logback.pattern.logic.ExceptionPattern;
import cz.martinbayer.parser.logback.pattern.logic.ExtendedExceptionPattern;
import cz.martinbayer.parser.logback.pattern.logic.FilePattern;
import cz.martinbayer.parser.logback.pattern.logic.LevelPattern;
import cz.martinbayer.parser.logback.pattern.logic.LinePattern;
import cz.martinbayer.parser.logback.pattern.logic.MessagePattern;
import cz.martinbayer.parser.logback.pattern.logic.NewLinePattern;
import cz.martinbayer.parser.logback.pattern.logic.ThreadPattern;

public enum ConversionTypesEnum {

	DATE_TIME_TYPE(ConversionWordsEnum.DATE_TIME_OF_EVENT,
			new DateTimePattern()),
	LEVEL_TYPE(ConversionWordsEnum.LEVEL_OF_EVENT, new LevelPattern()),
	EXCEPTION_TYPE(ConversionWordsEnum.EXCEPTION, new ExceptionPattern()),
	EXTENDED_EXCEPTION_TYPE(ConversionWordsEnum.EXTENDED_EXCEPTION,
			new ExtendedExceptionPattern()),
	FILE_TYPE(ConversionWordsEnum.FILE_OF_REQUEST, new FilePattern()),
	LINE_TYPE(ConversionWordsEnum.LINE_OF_REQUEST, new LinePattern()),
	NEW_LINE_TYPE(ConversionWordsEnum.NEW_LINE, new NewLinePattern()),
	THREAD_TYPE(ConversionWordsEnum.THREAD_NAME, new ThreadPattern()),
	MESSAGE_TYPE(ConversionWordsEnum.MESSAGE, new MessagePattern());

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
