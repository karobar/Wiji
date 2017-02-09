package tjp.wiji.event;

import java.util.LinkedList;

/**
 * Implements a general placeholder list for all events
 * individual handling of most key/mouse events should happen in their 
 * respective screens.
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public class EventProcessor {
    private LinkedList eventList;
    //private EventProcessable handler;
    
    public EventProcessor(EventProcessable handler) {
        eventList = new LinkedList();
        //this.handler = handler;
    }
    
    //char or int from keyTyped(char) keyUp(keycode) keyUp(keycode)
    public void addEvent(GameEvent event) {
        synchronized(eventList) {
            eventList.add(event);
        }
    }
  
    public void processEventList(EventProcessable handler) {
        GameEvent currEvent;
        while(eventList.size() > 0) {
            synchronized(eventList) {
                currEvent = (GameEvent) eventList.removeFirst();
            }
            handler.handleEvent(currEvent);
        }
    }
}