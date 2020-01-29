package es.deusto.ingenieria.sd.server.data.AirlineServer;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.deusto.ingenieria.sd.server.data.dto.FlightInfoDTO;
import es.deusto.ingenieria.sd.server.data.dto.PassengerDTO;
import es.deusto.ingenieria.sd.server.data.dto.ReservationDTO;
import es.deusto.ingenieria.sd.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.server.data.dto.*;

public class Lufthansa extends UnicastRemoteObject implements ILufthansa{

	private static final long serialVersionUID = 1L;
	
	private List<FlightInfoDTO> flights = Arrays.asList(new FlightInfoDTO("1", "01012020", "01012020", 180 , 32, "Bilbao", "Milan", "Lufthansa"), 
			new FlightInfoDTO("2", "01012020", "01012020", 150, 89, "Bilbao", "Milan", "Lufthansa"),
			new FlightInfoDTO("3", "07012020", "07012020", 130, 57, "Bilbao", "Milan", "Lufthansa"),
			new FlightInfoDTO("4", "01022020", "01022020", 200, 36, "Milan", "Estocolmo", "Lufthansa"),
			new FlightInfoDTO("5", "11012020", "12012020", 200, 45, "Bilbao", "Estocolmo", "Lufthansa"));
	private List<UserDTO> users = Arrays.asList(new UserDTO("kevin@gmail.com", "3333" ,"kevin", "kevvin", "bilbao"),
			new UserDTO("erik@gmail.com", "1111", "erik", "erik","bilbao"),
			new UserDTO("viola@gmail.com", "0000", "viola", "viola","milan"), new UserDTO("ruben@gmail.com", "1234", "ruben", "ruben","bilbao"));
	private List<ReservationDTO> reser = Arrays.asList();

	protected Lufthansa() throws RemoteException {
		super();
	}
	
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]"); //IP, port and the name Server
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {		
			ILufthansa objServer = new Lufthansa();
			Naming.rebind(name, objServer);//To include in RMIregistry
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}


	@Override
	public List<FlightInfoDTO> getFlight(String airportDep, String airportArrivalCode, String departureDate, int numPeople) {
		// TODO Auto-generated method stub
		System.out.println("dato8");
		List<FlightInfoDTO> retFlights = new ArrayList<FlightInfoDTO>();
		for(int i = 0; 0 < flights.size(); i++){
			if(flights.get(i).getAirportDepart().equals(airportDep)){
				if(flights.get(i).getAirportArriv().equals(airportArrivalCode)){
					if(flights.get(i).getTimeDeparture().equals(departureDate)){
						retFlights.add(flights.get(i));
					}
				}
			}
		}
		return retFlights;
	}


	public boolean notifyAirline(FlightInfoDTO flight, UserDTO user, List<PassengerDTO> listPassengers)
			throws RemoteException {
		
			for (int i = 0; i < flights.size(); i++) {
				if(flights.get(i).equals(flight)){
					for(int j = 0; j<users.size(); j++ ){
						if(users.get(j).equals(user)){
							int numberSeat = listPassengers.size() + 1;
							String id = "" + ((int)(Math.random()*99999999+10000000));
							reser.add(new ReservationDTO(id, numberSeat, user, flight));
						}
					}
				}
			}
		
			return false;
	}

	
}
