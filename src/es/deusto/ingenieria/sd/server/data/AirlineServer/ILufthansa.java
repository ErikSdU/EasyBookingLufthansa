package es.deusto.ingenieria.sd.server.data.AirlineServer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.server.data.dto.FlightInfoDTO;
import es.deusto.ingenieria.sd.server.data.dto.PassengerDTO;
import es.deusto.ingenieria.sd.server.data.dto.UserDTO;

public interface ILufthansa extends Remote{
	public List<FlightInfoDTO> getFlight(String airportDepCode, String airportArrivalCode, String departureDate, int numPeople) throws RemoteException;
	public boolean notifyAirline(FlightInfoDTO flight, UserDTO user, List<PassengerDTO> listPassengers) throws RemoteException;
}