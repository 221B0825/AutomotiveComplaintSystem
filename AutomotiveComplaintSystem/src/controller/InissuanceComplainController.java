package controller;

import view.TUI;

public class InissuanceComplainController {

	private TUI TUI;

	public InissuanceComplainController() {
		// TODO Auto-generated constructor stub
	}

	public InissuanceComplainController(TUI TUI) {
		this.TUI = TUI;
	}

	public void carRegistrationReIssuance() {
		TUI.printSelectCarStatus();
	}

}
