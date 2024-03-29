package utilitaires;

public interface Observateur {
	//Permet à l'observateur de se mettre à jour lorsqu'il est notifié par
	//l'objet Observable qu'il observe :
	public void seMettreAJour(Observable observable);
}
