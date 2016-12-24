

public class Converter {
	private char[] hangeulChars;
	
	public void inputNextWord(int[][] jamo){
		hangeulChars = new char[jamo.length];
		
		for(int i = 0; i < jamo.length; i++) {
			int hangeulBlock = (jamo[i][0] * 588) + (jamo[i][1] * 28) + jamo[i][2] + 44032;
			hangeulChars[i] = (char)hangeulBlock;
		}
	}
	
	public String getConvertedWord(){
		return new String(hangeulChars);
	}
}
