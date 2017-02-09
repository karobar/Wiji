package tjp.wiji.event;
   
/**
 * An EventProcessable Object can handle events.
 * 
 * @author      Travis Pressler (travisp471@gmail.com)
 * @version     %I%, %G%
 */
public interface EventProcessable
{
    public void handleEvent(GameEvent e);
}