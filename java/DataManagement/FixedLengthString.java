package DataManagement;
import java.io.*;

public class FixedLengthString {
	/** Odczytywanie stałej liczby znaków z DataInput */
	public static String readFixedLengthString(int size, DataInput in) throws IOException {
		char[] chars = new char[size]; // Zadeklaruj tablicę znaków
		// Odczytaj ustaloną liczbę znaków do tablicy
		for (int i = 0; i < size; i++)
			chars[i] = in.readChar();
		return new String(chars);
	}

	/** Napisz stałą liczbę znaków do DataOutputstream*/
	public static void writeFixedLengthString(String string, int size, DataOutput out) throws IOException {
		char[] chars = new char[size];
		string.getChars(0, Math.min(string.length(), size), chars, 0);
		for (int i = string.length(); i < size; i++)
			chars[i] = ' ';

		out.writeChars(new String(chars));
	}
}