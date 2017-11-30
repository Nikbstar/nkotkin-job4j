package ru.nik66;

/**
 * First Java application.
 * @author Kotkin Nikolay
 * @since 29-11-2017
 * @version 1.0
 */
public class Calculate {

	/**
	 * Method for tripling entered string.
	 * @param value - entered string
	 * @return triple string
	 */
	public String echo(String value) {
		return String.format("%s %s %s", value, value, value);
	}

	/**
	 * Mine method for starting the aplication.
	 * @param args - array of strings
	 */
	public static void main(String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("Hello"));
	}

}

