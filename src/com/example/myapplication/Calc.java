package com.example.myapplication;

import java.text.DecimalFormat;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calc extends Activity {

	private static String deleteChar(String str) {
		if (str.length() != 0) {
			if ((str.getBytes()[str.length() - 1] == '.')) {
				flag_dot = 0;
			}
			if ((str.getBytes()[str.length() - 1] == '+')) {
				flag_plus = 0;
			}
			if ((str.getBytes()[str.length() - 1] == '-')) {
				flag_minus = 0;
			}
			if ((str.getBytes()[str.length() - 1] == 'X')) {
				flag_into = 0;
			}
			if ((str.getBytes()[str.length() - 1] == '/')) {
				flag_divide = 0;
			}
			if ((str.getBytes()[str.length() - 1] == '=')) {
				flag_equals = 0;
			}
			return str.substring(0, str.length() - 1);
		} else
			return "";
	}

	private static String getFirstString(String str) {
		if (str.length() != 0) {
			return str.substring(0, temp_len); // temp_len points to last digit
												// just b4 '+'
		} else
			return "";
	}

	private static String getSecondString(String str) {
		if (str.length() != 0) {
			return str.substring(temp_len + 1, str.length());
		} else
			return "";
	}

	private static void calculateAns(/* String str, String str1, String str2 */) {
		if (flag_plus == 2) {
			finalAns = x + y;
		} else if (flag_minus == 2) {
			finalAns = x - y;
		} else if (flag_into == 2) {
			finalAns = x * y;
		} else if (flag_divide == 2) {
			finalAns = x / y;
		}
	}

	private String roundDouble(double value) {
		return df.format(value);
	}

	private void setOperators() {
		flag_plus = 1;
		// flag_minus = 1;
		flag_divide = 1;
		flag_into = 1;
	}

	private void clearOperators() {
		flag_plus = 0;
		flag_minus = 0;
		flag_divide = 0;
		flag_into = 0;
	}

	Button zero, one, two, three, four, five, six, seven, eight, nine;
	Button plus, minus, divide, into, equals, dot, delete;
	int disp;
	static int temp_len, temp_len_equals;
	static int flag_dot = 0, flag_plus = 0, flag_minus = 0, flag_divide = 0,
			flag_into = 0, flag_equals = 1;
	TextView display;
	// static float x, y;
	static double x, y, finalAns;
	String entry = "", entry1 = "", entry2 = "", ans = "";
	DecimalFormat df = new DecimalFormat("#.###");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.calc);

		zero = (Button) findViewById(R.id.zero);
		one = (Button) findViewById(R.id.one);
		two = (Button) findViewById(R.id.two);
		three = (Button) findViewById(R.id.three);
		four = (Button) findViewById(R.id.four);
		five = (Button) findViewById(R.id.five);
		six = (Button) findViewById(R.id.six);
		seven = (Button) findViewById(R.id.seven);
		eight = (Button) findViewById(R.id.eight);
		nine = (Button) findViewById(R.id.nine);

		dot = (Button) findViewById(R.id.dot);
		delete = (Button) findViewById(R.id.delete);
		plus = (Button) findViewById(R.id.plus);
		minus = (Button) findViewById(R.id.minus);
		into = (Button) findViewById(R.id.into);
		divide = (Button) findViewById(R.id.divide);
		equals = (Button) findViewById(R.id.equals);

		display = (TextView) findViewById(R.id.textview);

		plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag_plus == 0) {
					if (entry != "") {
						entry = entry + '+';
						display.setText(entry);
						// flag_plus = 1;
						flag_dot = 0;
						temp_len = entry.length() - 1;
						entry1 = getFirstString(entry);
						x = Double.parseDouble(entry1);
						flag_equals = 1;
						setOperators();
						flag_plus++;
					}
				}
			}
		});

		minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag_minus == 0) {
					entry = entry + '-';
					display.setText(entry);
					flag_minus = 1;
					flag_dot = 0;
					temp_len = entry.length() - 1;
					entry1 = getFirstString(entry);
					x = Double.parseDouble(entry1);
					flag_equals = 1;
					setOperators();
					flag_minus++;
				}
			}
		});

		into.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag_into == 0) {
					if (entry != "") {
						entry = entry + 'x';
						display.setText(entry);
						// flag_into = 1;
						flag_dot = 0;
						temp_len = entry.length() - 1;
						entry1 = getFirstString(entry);
						x = Double.parseDouble(entry1);
						flag_equals = 1;
						setOperators();
						flag_into++;
					}
				}
			}
		});

		divide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag_divide == 0) {
					if (entry != "") {
						entry = entry + '/';
						display.setText(entry);
						// flag_divide = 1;
						flag_dot = 0;
						temp_len = entry.length() - 1;
						entry1 = getFirstString(entry);
						x = Double.parseDouble(entry1);
						flag_equals = 1;
						setOperators();
						flag_divide++;
					}
				}
			}
		});

		equals.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag_equals == 0) {
					temp_len_equals = entry.length() - 1;
					entry2 = getSecondString(entry);
					if (entry1 != "") {
						y = Double.parseDouble(entry2);
						calculateAns();
						ans = roundDouble(finalAns);
						display.setText(ans);
						entry = ans;
						flag_equals = 1;
						flag_dot = 0;
						clearOperators();
					} else {
						display.setText(entry);
					}
				} else
					;
			}
		});

		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag_equals == 1) {
					flag_equals = 0;
					entry = "";
				}
				entry = deleteChar(entry);
				display.setText(entry);
			}
		});

		dot.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (flag_dot == 0) {
					if (entry == "") {
						entry = entry + "0.";
					} else {
						entry = entry + '.';
						display.setText(entry);
						flag_dot = 1;
					}
				}
				flag_equals = 0;
			}
		});

		zero.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '0';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		one.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '1';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		two.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '2';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		three.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '3';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		four.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '4';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		five.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '5';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		six.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '6';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		seven.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '7';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		eight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '8';
				display.setText(entry);
				flag_equals = 0;
			}
		});

		nine.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				entry = entry + '9';
				display.setText(entry);
				flag_equals = 0;
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
}
