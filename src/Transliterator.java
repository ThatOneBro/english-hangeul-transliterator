

public class Transliterator {
	private String[] initialJamo = {"g", "kk", "n", "d", "tt", "r", "m", "b", "pp", "s", "ss", "-", "j", "jj", "ch", "k", "t", "p", "h"};
	private String[] medialJamo = {"a", "ae", "ya", "yae", "eo", "e", "yeo", "ye", "o", "wa", "wae", "oe", "yo", "u", "weo", "we", "wi", "yu", "eu", "ui", "i"};
	private String[] finalJamo = { "-", "g", "kk", "gs", "n", "nj", "nh", "d", "l", "lk", "lm", "lb", "ls", "lt", "lp", "lh", "m", "b", "bs", "s", "ss", "ng", "j", "ch", "k", "t", "p", "h"};
	
	public int[] getJamoMap(String str){
		int[] jamoMap = new int[3];
		
		//An array to fill with the lengths of the medials
		int[] matches = new int[medialJamo.length];
		for(int i = 0; i < medialJamo.length; i++){
			String thisJamo = medialJamo[i];
			if(str.indexOf(thisJamo) != -1)
				matches[i] = thisJamo.length();
		}
		
		int largest = 0;
		int index = 0;
		for(int i = 0; i < matches.length; i++){
			if(matches[i] > largest){
				largest = matches[i];
				index = i;
			}
		}
		String medial = medialJamo[index];
		jamoMap[1] = index;
		if(str.equals(medial)){
			jamoMap[0] = 11;
			jamoMap[2] = 0;
			return jamoMap;
		}
		String[] iniAndFin = str.split(medial);
		if(iniAndFin[0].equals("")){
			jamoMap[0] = 11;
		}
		else {
			String initial = iniAndFin[0];
			for(int i = 0; i < initialJamo.length; i++){
				String current = initialJamo[i];
				if(initial.equals(current)){
					jamoMap[0] = i;
					break;
				}
			}
		}
		if(iniAndFin.length == 1){
			jamoMap[2] = 0;
		} else {
			String fin = iniAndFin[1];
			for(int i = 0; i < finalJamo.length; i++){
				String current = finalJamo[i];
				if(fin.equals(current)){
					jamoMap[2] = i;
					break;
				}
			}
		}
		
		return jamoMap;
	}
}
