package helper_Three;

import java.util.function.Supplier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class helperThree {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Words word = new Words();
		Valid valid = new Valid();
		Random rand = new Random();

		String validAr[] = new String[12972];
		String wordle[] = new String[2315];
		int att = 5;

		for (int i = 0; i < wordle.length; i++) {

			String str3 = word.getWord(i);
			String str4 = str3.substring(0, str3.length() - 1);
			wordle[i] = str4;
		}
		for (int i = 0; i < validAr.length; i++) {

			String str3 = valid.getWord(i);
			String str4 = str3.substring(0, str3.length() - 1);
			validAr[i] = str4;

		}
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < validAr.length; i++) {
			String str4 = validAr[i];
			stringList.add(str4);
		}

//		System.out.println(stringList);

		String str = new String();

		int low = 1;
		int high = 2315;
		int result = rand.nextInt(high - low) + low;
		str = wordle[result];

		 System.out.println("The word is " + str);
		for (int i = 0; i < 6; i++) { 
			String guess;
			String tryGuess;
			guess = null;
			guess = sc.nextLine();
			if (isValid(validAr, guess)) {
				compare(guess, str);
			tryGuess=brain(wordle, guess, str, stringList);
			System.out.println(tryGuess);

			} else
				i--;
		}

	}

	public static String brain(String[] wordle, String guess, String str, List<String> stringList) {
		boolean flag = false;
		List<Integer> list = new ArrayList<Integer>();
String tryThis=new String();
		Scanner sc = new Scanner(System.in);

		if (isValid(wordle, guess)) {

			for (int y = 0; y < guess.length(); y++) {

				int one = 1;
				int two = 2;
				int zero = 0;
				char str1 = guess.charAt(y);
				int index = y;
				int strIndex = y;
				/*
				 * // checks if the letter appears in the word
				 */
				if (guess.charAt(index) == str.charAt(strIndex)) {

					list.add(two);
					flag = true;
				} else if (str.indexOf(str1) != -1) {

					list.add(one);
					flag = true;
				}

				else

					list.add(zero);
				flag = true;
			}
		}
		System.out.print(list);
		if (flag == true) {

			tryThis=filter(wordle, stringList, guess);
			list.removeAll(list);
		}
		return tryThis;

	}

	public static String filter(String[] wordle, List<String> stringList, String guess) {
		boolean match;
		boolean match2;
		Scanner sc = new Scanner(System.in);
		String str2 = sc.nextLine();
		char ch2 = '2';
		char ch1 = '1';
		char ch0 = '0';
		

		for (int j = 0; j < str2.length(); j++) {
			if (str2.charAt(j) == ch2) {

				for (int i = 0; i < wordle.length; i++) {
					String str4 = wordle[i];

					if (str4.charAt(j) != guess.charAt(j)) {

						stringList.remove(str4);
					}
				}
			}
			

			else if (str2.charAt(j) == ch1) {

				char ch4 = guess.charAt(j);

				for (int i = 0; i < wordle.length; i++) {
					String str6 = wordle[i];

					if (str6.indexOf(ch4) != -1) {

						match = true;

						if (match == false) {

						}
					} else {
						stringList.remove(str6);
					}
				}
			} 
			//new code starts here
			else if (str2.charAt(j) == ch1) {

				for (int i = 0; i < wordle.length; i++) {
					String str8 = wordle[i];

					if (str8.charAt(j) != guess.charAt(j)) {

						stringList.remove(str8);
					}
				}
			}
			// new code ends here
			
			else if (str2.charAt(j) == ch0) { // remove any strings that contain this letter at 0.

				char ch5 = guess.charAt(j);
				for (int p = 0; p < wordle.length; p++) {
					String str7 = wordle[p];

					//if (ch5 == str7.charAt(j)) {
						if(str7.indexOf(ch5) != -1){
						// System.out.println(str7.charAt(j));
						match2 = true;
						if (match2 == false) {

						} else {
							stringList.remove(str7);
						}
					}
				}
			}
		}
		stringList.remove(guess);
		String str9 = new String();
//		System.out.print(stringList);
		for (int i = 0; i < 1; i++) {
			str9=stringList.get(i);
			System.out.println("Try" + " " + stringList.get(i));
		}
		return str9;

	}

	public static boolean isValid(String[] validArray, String input) {
		boolean check = false;

		for (int i = 0; i < validArray.length; i++) {
			if (input.contains(validArray[i])) {
				check = true;
				return check;
			}

		}
		System.out.println("word not valid");
		return check;
	}

	public static boolean compare(String input, String str) {

		int y = str.length() - 1;
		if (input.equals(str)) {
			System.out.println("congrats the word is " + str);
			System.exit(0);
		}
		return false;
	}
}

class Words {

	public String input[];

	public Words() {
		input = load("C:\\Users\\joshu\\OneDrive\\Desktop\\wordleWords.txt");
	}

	public int getSize() {
		return input.length;
	}

	public String getWord(int n) {
		return input[n];
	}

	public String[] load(String file) {
		File aFile = new File(file);
		StringBuffer contents = new StringBuffer();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(aFile));
			String line = null;
			int i = 0;
			while ((line = input.readLine()) != null) {
				contents.append(line);
				i++;
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Can't find the file - are you sure the file is in this location: " + file);
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Input output exception while processing file");
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				System.out.println("Input output exception while processing file");
				ex.printStackTrace();
			}
		}
		String[] array = contents.toString().split("\n");
		for (String s : array) {
			s.trim();
		}
		return array;
	}
}

class Valid {

	public String input[];

	public Valid() {
		input = load("C:\\Users\\joshu\\OneDrive\\Desktop\\wordleAllowable.txt");
	}

	public int getSize() {
		return input.length;
	}

	public String getWord(int n) {
		return input[n];
	}

	public String[] load(String file) {
		File aFile = new File(file);
		StringBuffer contents = new StringBuffer();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(aFile));
			String line = null;
			int i = 0;
			while ((line = input.readLine()) != null) {
				contents.append(line);
				i++;
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Can't find the file - are you sure the file is in this location: " + file);
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Input output exception while processing file");
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				System.out.println("Input output exception while processing file");
				ex.printStackTrace();
			}
		}
		String[] array = contents.toString().split("\n");
		for (String s : array) {
			s.trim();
		}
		return array;
	}
}
