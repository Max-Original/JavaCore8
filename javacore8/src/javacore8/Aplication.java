package javacore8;

import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

public class Aplication {


	enum Seasons {

		WINTER, SUMMER, SPRING, AUTUMN;
	}

	enum months {

		JANUARY(31, Seasons.WINTER),
		FEBRUARY(29, Seasons.WINTER),
		MARCH(31, Seasons.SPRING),
		APRIL(30, Seasons.SPRING),
		MAY(31, Seasons.SPRING), 
		JUNE(30, Seasons.SUMMER),
		JULY(31, Seasons.SUMMER), 
		AUGUST(31, Seasons.SUMMER),
		SEPTEMBER(30, Seasons.AUTUMN),
		OCTOBER(31, Seasons.AUTUMN), 
		NOVEMBER(30, Seasons.AUTUMN),
		DECEMBER(31, Seasons.WINTER);

		Integer days;
		Seasons s;

		months(Integer days, Seasons s) {
			this.days = days;
			this.s = s;
		}
		

		public Integer getDays() {
			return days;
		}

		public Seasons getS() {
			return s;
		}
	}

	static void menu() {

		System.out.println("Натисніть 1 щоб перевірити наявність місяця ");
		System.out.println("Натисніть 2 щоб вивести місяці з одної пори року");
		System.out.println("Натисніть 3 щоб вивести всі місяці які мають таку саму кількість днів");
		System.out.println("Натисніть 4 щоб вивести на екран всі місяці які мають меншу кількість днів");
		System.out.println("Натисніть 5 щоб вивести на екран всі місяці які мають більшу кількість днів");
		System.out.println("Натисніть 6 щоб вивести на екран наступну пору року");
		System.out.println("Натисніть 7 щоб вивести на екран попередню пору року");
		System.out.println("Натисніть 8 щоб вивести на екран всі місяці які мають парну кількість днів");
		System.out.println("Натисніть 9 щоб вивести на екран всі місяці які мають непарну кількість днів");
		System.out.println("Натисніть 10 щоб вивести на екран чи введений з консолі місяць має парну кількість днів");

	}

	public static void main(String[] args) throws WrongInputConsoleParametersException {

		months[] mas = months.values();
		Seasons[] mass = Seasons.values();

		Scanner sc = new Scanner(System.in);

		while (true) {
			menu();
			switch (sc.next()) {
			case "1": {
				System.out.println("Enter month");
				sc = new Scanner(System.in);
				String months = sc.next().toUpperCase();

				boolean flag = checkForMonth(mas, months);
				if (!flag) {
					System.out.println("Month doesnt exit");
				}
				 
				 
				break;
			}
			case "2": {
				System.out.println("Enter Season");
				sc = new Scanner(System.in);
				String Season = sc.next().toUpperCase();

				boolean flag = sameSeason(mas, Season);

				if (!flag) {
					System.out.println("Season doesnt exist");
				}
				break;
			}

			case "3": {
				System.out.println("Enter number of Days");
				sc = new Scanner(System.in);
				int num = sc.nextInt();

				boolean flag = sameDaysInMonth(mas, num);
				if (!flag) {
					System.out.println("month with " + num + " days doesnt exist");
				}
				break;

			}
			case "4": {
				System.out.println("Enter number of Days");
				sc = new Scanner(System.in);
				int num = sc.nextInt();
				
				if (num > 33) {
					throw new WrongInputConsoleParametersException("month with "+ num +  " days doesnt exist");
				}
				
				boolean flag = lessDays(mas, num);
				
				  if (!flag) { System.out.println("month with " + num + " days doesnt exist");
				  } break;
				 
			}
			case "5": {
				System.out.println("Enter number of Days");
				sc = new Scanner(System.in);
				int num = sc.nextInt();
				
				if (num < 28) {
					throw new WrongInputConsoleParametersException("month with "+ num +  " days doesnt exist");
				}
				
				boolean flag = moreDays(mas, num);
				if (!flag) {
					System.out.println("month with " + num + " days doesnt exist");
				}
				break;
			}
			case "6": {
				System.out.println("Enter Season");
				sc = new Scanner(System.in);
				String seasons = sc.next().toUpperCase();

				boolean flag = nextSeason(mass, seasons);
				if (flag) {
					Seasons se2 = Seasons.valueOf(seasons);
					int ordinal = se2.ordinal();

					if (se2.ordinal() == (mass.length - 1)) {
						ordinal = 0;
						System.out.println(mass[ordinal]);
					} else {
						System.out.println(mass[ordinal + 1]);
					}
				}
				if (!flag) {
					System.out.println("Season " + seasons + " Doesnt exist");
				}
				break;
			}
			case "7": {
				System.out.println("Enter Season");
				sc = new Scanner(System.in);
				String seasons = sc.next().toUpperCase();

				boolean flag = previousSeason(mass, seasons);
				if (!flag) {
					System.out.println("Season " + seasons + " Doesnt exist");
				}
				break;
			}
			case "8": {
				for (months m : mas) {
					if (m.getDays() % 2 == 0) {
						System.out.println(m);
					}
				}
				break;
			}
			case "9": {
				for (months m : mas) {
					if (m.getDays() % 2 == 1) {
						System.out.println(m);
					}
				}
				break;
			}
			case "10": {
				System.out.println("Enter month");
				sc = new Scanner(System.in);
				String month = sc.next().toUpperCase();

				boolean flag = false;

				for (months m : mas) {
					if (m.name().equals(month)) {
						System.out.println("Місяць існує");
						flag = true;
					}
				}
				if (flag) {

					months mo = months.valueOf(month);

					if (mo.getDays() % 2 == 0) {
						System.out.println("Місяць " + mo + " має парну кількість днів");
					} else {
						System.out.println("Місяць " + mo + " має не парну кількість днів");
					}
				}
				if (!flag) {
					System.out.println("Month " + month + " doesnt exit");
				}
			}
			}
		}
	}

	private static boolean previousSeason(Seasons[] mass, String seasons) {
		boolean flag = false;

		for (Seasons s : mass) {
			if (s.name().equals(seasons)) {
				System.out.println("Season exists");
				flag = true;
			}
		}
		if (flag) {
			Seasons se2 = Seasons.valueOf(seasons);
			int ordinal = se2.ordinal();

			if (se2.ordinal() == (mass.length - 1)) {
				ordinal = 2;
				System.out.println(mass[ordinal]);
			} else if (se2.ordinal() == 0) {
				ordinal = mass.length - 1;
				System.out.println(mass[ordinal]);
			} else {
				System.out.println(mass[ordinal - 1]);
			}
		}
		return flag;
	}

	private static boolean nextSeason(Seasons[] mass, String seasons) {
		boolean flag = false;

		for (Seasons s : mass) {
			if (s.name().equals(seasons)) {
				System.out.println("Season exists");
				flag = true;
			}
		}
		return flag;
	}

	private static boolean moreDays(months[] mas, int num) {
		boolean flag = false;

		for (months m : mas) {
			if (m.getDays() > num) {
				flag = true;
				System.out.println(m);
			}
		}
		return flag;
	}
	
	private static boolean lessDays(months[] mas, int num) {
		boolean flag = false;
		
		for (months m : mas) {
			if (m.getDays() < num) {
				flag = true;
				System.out.println(m);
			}
		}
		return flag;
	}

	private static boolean sameDaysInMonth(months[] mas, int num) {
		boolean flag = false;

		for (months m : mas) {
			if (m.getDays() == num) {
				flag = true;
				System.out.println(m);
			}
		}
		return flag;
	}
	

	private static boolean sameSeason(months[] mas, String Season) {
		boolean flag = false;

		for (months m : mas) {
			if (m.getS().name().equals(Season)) {
				flag = true;
			}
		}
		if (flag) {
			Seasons seasons = Seasons.valueOf(Season);

			for (months se : mas) {
				if (se.getS().name().equals(Season)) {
					System.out.println(se);
				}
			}
		}
		return flag;
	}

	private static boolean checkForMonth(months[] mas, String months) {
		boolean flag = false;

		for (months m : mas) {
			if (m.name().equals(months)) {
				System.out.println("Month exists");
				flag = true;
			}
		}
		return flag;
	}
}
