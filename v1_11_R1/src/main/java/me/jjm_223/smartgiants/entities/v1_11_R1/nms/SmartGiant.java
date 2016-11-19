package me.jjm_223.smartgiants.entities.v1_11_R1.nms;

import me.jjm_223.smartgiants.api.entity.ISmartGiant;
import me.jjm_223.smartgiants.api.util.Configuration;
import net.minecraft.server.v1_11_R1.*;

public class SmartGiant extends EntityGiantZombie implements ISmartGiant
{
    public SmartGiant(World world)
    {
        super(world);

        double health = Configuration.getInstance().maxHealth();

        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(health);
        setHealth((float) health);

        width = 1;

        if (this instanceof SmartGiantHostile)
        {
            return;
        }

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTempt(this, .9F, Items.APPLE, false));
        this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, .9F));
        this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 16.0F));
        this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public boolean isHostile()
    {
        return this instanceof SmartGiantHostile;
    }
}