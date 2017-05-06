package model;

import javafx.application.Platform;
import javafx.concurrent.Task;
import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */

/**
 * A szimuláció időzítéséért felelős. Meghatározott időközönként értesíti a szimulációban részt vevő objektumokat.
 */
public class SimulationTimer {
    /**
     * The Subscribers.
     */
    List<Notifiable> subscribers;
    /**
     * Az esemenysor
     */
    List<String> events;
    private boolean running = false;
    private View view;
    private Simulation simTask;
    private Thread th;

    /**
     * Instantiates a new Simulation timer.
     */
    public SimulationTimer() {
        subscribers = new ArrayList<>();
        events = new ArrayList<>();
        view = null;
        simTask = new Simulation();
    }

    public void setView(View view) {
        this.view = view;
    }

    /**
     * Start.
     */
    public void start() {
        if (running) return;

        simTask = new Simulation();
        th = new Thread(simTask);
        th.setDaemon(true);
        th.start();
        running = true;
    }

    /**
     * Stop.
     */
    public void stop() {
        if (!running || simTask == null) return;
        simTask.cancel();
        try {
            th.join();
        } catch (InterruptedException ignored) {
        }
        running = false;
    }

    /**
     * Lefuttat egy szimulációs lépést.
     */
    public synchronized void step(int stepNum) {
        for (String event : events) {
            for (Notifiable sub : subscribers) {
                sub.update(event);
            }
        }

        events.clear();
        for (int i = 0; i < stepNum; i++) {
            for (Notifiable sub : subscribers) {
                sub.update(null);
            }
        }

        if (view != null) {
            Platform.runLater(() -> view.Update());
        }
    }

    /**
     * Add subscriber.
     * <p>
     * Az ütemezni kívánt objektumokhoz adja a paraméterül kapott Notifable interfészt megvalósító interfészt.
     * </p>
     *
     * @param sub the subscriber
     */
    public void addSubscriber(Notifiable sub) {
        subscribers.add(sub);
    }

    /**
     * Delete subscriptions.
     */
    public void deleteSubscriptions() {
        subscribers.clear();
    }

    /**
     * @param event
     */
    public void addEvent(String event) {
        events.add(event);
    }

    class Simulation extends Task<Void> {

        @Override
        protected Void call() throws Exception {
            while (!isCancelled()) {
//                System.out.print(".");
                try {
                    step(1);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                Thread.sleep(50);
            }
            return null;
        }
    }

}
