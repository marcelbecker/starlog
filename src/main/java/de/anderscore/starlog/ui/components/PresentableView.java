package de.anderscore.starlog.ui.components;

/**
 * Created by MBecker on 20.01.2017.
 */
public interface PresentableView<PRESENTER> {

    void addPresenter(PRESENTER presenter);

    void removePresenter(PRESENTER presenter);

}
