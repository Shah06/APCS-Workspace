
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChallengeBugTester
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ChallengeBug alice = new ChallengeBug();
        ChallengeBug jim = new ChallengeBug();
        ChallengeBug bob = new ChallengeBug();
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.add(new Location(5, 4), jim);
        world.show();
    }
}