package conveniencecore.api.item;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IBehaviourProvider {
	public void getBehaviours(ItemStack stack,World world,List<Long> behaviours);
	public void getBehaviours(ItemStack stack,List<Long> behaviours);
}