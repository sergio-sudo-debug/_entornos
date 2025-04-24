package RuizSergioRA67;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Persona atraccion[] = new Persona[4];
		LinkedList<Persona> cola = new LinkedList<Persona>();
		HashMap<Integer,Integer> PersonasMontadas = new HashMap<Integer,Integer>();
		int opc, edad, año, mes, dia, altura = 0, tipo,numtipo,numeropersonas=0;
		int numero = 0;
		boolean estadoAtraccion = false;
		String codigoPromocional = "ABC12";
		LocalDate fecha_nacimiento;
		LocalDate fecha = LocalDate.now();
		do {
			System.out.println(
					"1. Nueva persona en la cola\n2. Ordenar cola de menor a mayor altura\n3. Montar en la atracción \n4. Poner en marcha la atracción\n5. Parar la atracción en marcha\n6. Mostrar información de las personas en cola y en la atracción");
			opc = sc.nextInt();
			switch (opc) {
			case 1:
				System.out.println("Introduzca su año de nacimiento");
				año = sc.nextInt();
				System.out.println("Introduzca su mes de nacimiento");
				mes = sc.nextInt();
				System.out.println("Introduzca su dia de nacimiento");
				dia = sc.nextInt();
				fecha_nacimiento = LocalDate.of(año, mes, dia);
				long años = ChronoUnit.YEARS.between(fecha_nacimiento, fecha);
				edad = (int) años;
				do {
					Random r = new Random();
					altura = r.nextInt(210);
				} while (altura < 70);
				do {
					Random r = new Random();
					tipo = r.nextInt(2) + 1;
				} while (tipo > 3);
				if (tipo == 1) {
					cola.addLast(new resto(edad, altura));
					PersonasMontadas.put(tipo, numeropersonas++);
				}
				if (tipo == 2) {
					cola.addLast(new bono(edad, altura));
					PersonasMontadas.put(tipo, numeropersonas++);
				}
				if (tipo == 3) {
					cola.addLast(new CodigoPromocional(edad, altura, codigoPromocional));
					PersonasMontadas.put(tipo, numeropersonas++);

				}
				break;
			case 2:
				Collections.sort(cola);
				for (Persona Per : cola) {
					System.out.println(Per);
				}
				break;
			case 3:
				if (cola.size() < 4) {
					System.out.println("No hay suficientes personas en la cola");
				} else {
					do {
						try {
							int pos = verificarAltura(altura, cola);
							atraccion[numero] = cola.get(numero);
							System.out.println("Se ha montado en la atracción " + atraccion[numero]);
							System.out.println("El precio de la atracción es de " + cola.get(numero).pagar() + " euros");
							numero++;

						} catch (SergioException e) {
							System.out.println(e.getMessage());
						}
						numero++;

					} while (numero < 4);
				}
				break;
			case 4:
				if (atraccion.length < 4) {
					System.out.println("No hay suficientes personas para empezar la atracción");
				}
				if (estadoAtraccion == true) {
					System.out.println("La atracción ya esta encendida");
				} else {
					estadoAtraccion = true;
					System.out.println("Se pone en marcha la atracción");
				}
				break;
			case 5:
				if (estadoAtraccion == false) {
					System.out.println("La atracción ya esta parada");
				}
				estadoAtraccion = false;
				System.out.println("Atracción parada");
				break;
			case 6:
				for(int i=0;i<atraccion.length;i++) {
					System.out.println("Persona en la atracción= " + atraccion[i]);
				}
				for(Persona i : cola) {
					System.out.println("Persona en la cola= " + i);
					}
				System.out.println(Persona.totalrecaudado);
				for(Integer i : PersonasMontadas.keySet()) {
				System.out.println("Del tipo " +i+ "se han montado: " +PersonasMontadas.get(i));
				}
				if(estadoAtraccion==false)
				System.out.println("La atracción esta parada");
				else
					System.out.println("La atracción esta en movimiento");
				break;
			}
		} while (opc != 7);
	}

	public static int verificarAltura(int altura, LinkedList<Persona> cola) throws SergioException {
		int numero = 0;
		if (cola.get(numero).getAltura() > 100 && cola.get(numero).getAltura() < 190) {
			return 1;

		}

		throw new SergioException("no se puede montar");
	}
}
