package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń dane osoby        \n" +
			"3 - Modyfikuj dane osoby   \n" +
			"4 - Wczytaj dane z pliku   \n" +
			"5 - Zapisz dane do pliku   \n" +
			"0 - Zakończ program        \n";	
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";

	private static final String KOLEKCJE =
			" Właściwości jakiej kolekcji pokazać? \n"+
					"1 - HashSet \n" +
					"2 - TreeSet \n" +
					"3 - ArrayList \n" +
					"4 - LinkedList \n" +
					"5 - HashMap \n" +
					"6 - TreeMap \n" +
					"0 - Powrót do menu głównego \n";
	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		//application.runMainLoop();
		application.loopCollections();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;
	
	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					currentPerson = Person.readFromFile(file_name);
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
				}
					break;
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
				}

					break;
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) { 
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}
	public void loopCollections() {
		try {
			Person a = new Person("Jarek", "Ogarek");
			a.setBirthYear(1997);
			a.setJob("Gość");

			Person b = new Person("Jacek", "Placek");
			b.setBirthYear(1939);
			b.setJob("Student");

			Person c = new Person("Jurek", "Ogórek");
			c.setBirthYear(2000);
			c.setJob("Student");

			Person d = new Person("Jan", "Dzban");
			d.setBirthYear(1969);
			d.setJob("Dyrektor");

			Person e = new Person("Jurek", "Ogórek");
			e.setBirthYear(1987);
			e.setJob("Student");

			Person f = new Person("Jarek", "Ogarek");
			f.setBirthYear(1997);
			f.setJob("Gość");


			PersonZadanie ax = new PersonZadanie("Ben", "Masny");
			ax.setBirthYear(1994);
			ax.setJob("Dyrektor");

			PersonZadanie bx = new PersonZadanie("Rydz", "Rudy");
			bx.setBirthYear(1999);
			bx.setJob("Kierownik");

			PersonZadanie cx = new PersonZadanie("Jurek", "Ogórek");
			cx.setBirthYear(1987);
			cx.setJob("Student");

			PersonZadanie dx = new PersonZadanie("Jarek", "Ogarek");
			dx.setBirthYear(1945);
			dx.setJob("Nauczyciel");

			PersonZadanie ex = new PersonZadanie("Miłosz", "Czesław");
			ex.setBirthYear(1921);
			ex.setJob("-------");

			PersonZadanie fx = new PersonZadanie("Ben", "Masny");
			fx.setBirthYear(1994);
			fx.setJob("Kierownik");


		System.out.println("Program ilustrujący działanie różnych typów kolekcji:");
		while (true) {
			switch (UI.enterInt(KOLEKCJE)) {
				case 1: //Sprawdzanie dodawania/usuwania/wielokrotnego dodawania
						//obiektów Person i PersonZadanie do HashSet
					UI.printInfoMessage("HashSet:");
					Set<Person> hs = new HashSet<>(); //tworzenie hs z obiektami Person
					Set<PersonZadanie> hsx = new HashSet<>(); //tworzenie hsx z obiektami PersonZadanie
					System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
					hs.add(a);
					hs.add(b);
					hs.add(f);

					hsx.add(ax);
					hsx.add(bx);
					hsx.add(fx);
					for (Person i : hs) { //wyświetlanie kolejnych obiektów Person z hs
						showPerson(i);
					}
					UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych a do stosu:");
					hs.add(a);
					hs.add(a);
					hs.add(b);
					hs.add(e);
					hsx.add(ax);
					hsx.add(bx);
					hsx.add(ex);
					UI.printInfoMessage("Stos obiektów klasy bez nadpisania metod equals i hashcode:\n");
					for (Person i : hs) { //wyświetlanie kolejnych obiektów Person z hs
						showPerson(i);
					}
					UI.printInfoMessage("Stos obiektów klasy z nadpisaniem metod equals i hashcode:\n");
					for (Person i : hsx) { //wyświetlanie kolejnych obiektów PersonZadanie z hsx
						showPerson(i);
					}
					UI.printInfoMessage("Usuwanie:");
					hs.remove(a);
					for (Person i : hs) { //wyświetlanie obiektów pozostałych po usunięciu
						showPerson(i);
					}
					break;
				case 2: //TreeSet
					UI.printInfoMessage("TreeSet:");
					Set<Person> ts = new TreeSet<>(new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							if(o1.getFirstName().equals(o2.getFirstName())
							&& o1.getLastName().equals(o2.getLastName())
							&& o1.getBirthYear()== o2.getBirthYear())
							return 0;
							else return 1;
						}
					});
					Set<PersonZadanie> tsx = new TreeSet<>(new Comparator<PersonZadanie>() {
						@Override
						public int compare(PersonZadanie o1, PersonZadanie o2) {
							if(o1.getFirstName().equals(o2.getFirstName())
									&& o1.getLastName().equals(o2.getLastName())
									&& o1.getBirthYear()== o2.getBirthYear())
								return 0;
							else return 1;
						}
					});
					System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
					ts.add(a);
					ts.add(b);
					ts.add(f);

					tsx.add(ax);
					tsx.add(bx);
					tsx.add(fx);
					for (Person i : ts) {
						showPerson(i);
					}
					UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych a do stosu:");
					ts.add(a);
					ts.add(a);
					ts.add(b);
					ts.add(e);
					tsx.add(ax);
					tsx.add(bx);
					tsx.add(ex);
					UI.printInfoMessage("Stos obiektów klasy bez nadpisania metod equals i hashcode:\n");
					for (Person i : ts) {
						showPerson(i);
					}
					UI.printInfoMessage("Stos obiektów klasy z nadpisaniem metod equals i hashcode:\n");
					for (Person i : tsx) {
						showPerson(i);
					}
					UI.printInfoMessage("Usuwanie:");
					ts.remove(a);
					for (Person i : ts) {
						showPerson(i);
					}
					break;
				case 3: //ArrayList
					UI.printInfoMessage("ArrayList:");
					List<Person> al = new ArrayList<>();
					List<PersonZadanie> alx = new ArrayList<>();
					System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
					al.add(a);
					al.add(b);
					al.add(f);

					alx.add(ax);
					alx.add(bx);
					alx.add(fx);
					for (Person i : al) {
						showPerson(i);
					}
					UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
					al.add(a);
					al.add(a);
					al.add(b);
					al.add(e);
					alx.add(ax);
					alx.add(bx);
					alx.add(ex);
					UI.printInfoMessage("Lista obiektów klasy bez nadpisania metod equals i hashcode:\n");
					for (Person i : al) {
						showPerson(i);
					}
					UI.printInfoMessage("Lista obiektów klasy z nadpisaniem metod equals i hashcode:\n");
					for (Person i : alx) {
						showPerson(i);
					}
					UI.printInfoMessage("Usuwanie:");
					al.remove(a);
					for (Person i : al) {
						showPerson(i);
					}
					break;
				case 4: //LinkedList
					UI.printInfoMessage("LinkedList:");
					List<Person> ll = new LinkedList<>();
					List<PersonZadanie> llx = new LinkedList<>();
					System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
					ll.add(a);
					ll.add(b);
					ll.add(f);

					llx.add(ax);
					llx.add(bx);
					llx.add(fx);
					for (Person i : ll) {
						showPerson(i);
					}
					UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
					ll.add(a);
					ll.add(a);
					ll.add(b);
					ll.add(e);
					llx.add(ax);
					llx.add(bx);
					llx.add(ex);
					UI.printInfoMessage("Lista obiektów klasy bez nadpisania metod equals i hashcode:\n");
					for (Person i : ll) {
						showPerson(i);
					}
					UI.printInfoMessage("Lista obiektów klasy z nadpisaniem metod equals i hashcode:\n");
					for (Person i : llx) {
						showPerson(i);
					}
					UI.printInfoMessage("Usuwanie:");
					ll.remove(a);
					List<Person> remove = new LinkedList<>();
					remove.add(a);
					remove.add(b);
					ll.removeAll(remove);
					for (Person i : ll) {
						showPerson(i);
					}
					break;
				case 5: //HashMap
					UI.printInfoMessage("HashMap:");
					Map<Integer, Person> hm = new HashMap<>();
					System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
					hm.put(1, a);
					hm.put(2, b);
					hm.put(1, f); //dodanie elementu na klucz zajęty
					for (Person i : hm.values()) {
						showPerson(i);
					}
					UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
					hm.put(3, a);
					hm.put(4, b);
					hm.put(5, a);
					hm.put(6, e);

					UI.printInfoMessage("Lista obiektów:\n");
					for (Person i : hm.values()) {
						showPerson(i);
					}
					UI.printInfoMessage("Usuwanie:");
					hm.remove(1);
					hm.remove(2);
					for (Person i : hm.values()) {
						showPerson(i);
					}
					break;
				case 6: //TreeMap
					UI.printInfoMessage("TreeMap:");
					Map<Integer, Person> tm = new TreeMap<>();
					System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
					tm.put(1, a);
					tm.put(2, b);
					tm.put(3, b);
					tm.put(1, f); //dodanie elementu na klucz zajęty
					for (Person i : tm.values()) {
						showPerson(i);
					}
					UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
					tm.put(3, a);
					tm.put(4, b);
					tm.put(5, a);
					tm.put(6, e);

					UI.printInfoMessage("Lista obiektów:\n");
					for (Person i : tm.values()) {
						showPerson(i);
					}
					UI.printInfoMessage("Usuwanie:");
					tm.remove(1);
					tm.remove(2);
					for (Person i : tm.values()) {
						showPerson(i);
					}
					break;
				case 0:
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
			}
		}
		}
		catch (PersonException personException) {
			personException.printStackTrace();
		}
	}



	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			//sb.append("Aktualna osoba: \n")
			  sb.append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	
	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
}  // koniec klasy PersonConsoleApp
