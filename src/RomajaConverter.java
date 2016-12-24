import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RomajaConverter {

	public String convert(String input) {
		Pattern regex = Pattern.compile("[!?,.~]+$");
		Transliterator transliterator = new Transliterator();
		Converter converter = new Converter();
		
		String output = "";
		
		String[] words = input.split(" ");
		for(int i = 0; i < words.length; i++){
			String word = words[i];
			String punctuation = "";
			
			Matcher matcher = regex.matcher(word);
			if(matcher.find()){
				punctuation = matcher.group();
				word = matcher.replaceFirst("");
			}

			System.out.printf("Current word: %s\n", word);
			String[] syllables = word.split("-");
			
			int[][] wordValues = new int[syllables.length][3];
			for(int c = 0; c < wordValues.length; c++){
				String syllable = syllables[c];
				wordValues[c] = transliterator.getJamoMap(syllable);
			}
			
			converter.inputNextWord(wordValues);
			String currentWord = converter.getConvertedWord();
			
			if(i == words.length - 1){
				output = output + currentWord + punctuation;
				break;
			}
			output = output + currentWord + punctuation + " ";
		}
		
		return output;
	}
}
