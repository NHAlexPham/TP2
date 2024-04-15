package controleur.MonObserver;

import java.util.ArrayList;
import java.util.ListIterator;

public abstract class Observable {
    //Liste des objets qui observent cet Observable :
    private ArrayList<Observateur> observateurs = new ArrayList<>();
    //Ajoute un observateur à la liste, s'il n'y est pas déjà :
    public boolean ajouterObservateur(Observateur observateur) {
        boolean ajoutEffectue;
        if (this.observateurs.contains(observateur))
            ajoutEffectue = false;
        else {
            this.observateurs.add(observateur);
            ajoutEffectue = true;
        }
        if (ajoutEffectue)
            observateur.seMettreAJour(this);
        return ajoutEffectue;
    }
    //Demande à tous les observateurs de se mettre à jour :
    public void notifierObservateurs() {
        ListIterator<Observateur> iterateur = this.observateurs.listIterator();
        while (iterateur.hasNext())
            iterateur.next().seMettreAJour(this);
    }
}
