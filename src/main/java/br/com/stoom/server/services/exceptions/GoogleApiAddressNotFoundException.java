package br.com.stoom.server.services.exceptions;

public class GoogleApiAddressNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8920883274209794145L;

	public GoogleApiAddressNotFoundException() {
		super("Erro ao localizar latitude e longitude do endereço informado.");
	}
}
