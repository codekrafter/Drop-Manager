package net.codekrafter.plugins.dm;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class DropManager extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler(ignoreCancelled=true)
	public void onBB(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		int xp = e.getExpToDrop();
		Collection<ItemStack> drops = b.getDrops(p.getItemInHand());
		for (ItemStack is : drops) {
			p.getInventory().addItem(is);
		}
		b.setType(Material.AIR);
		p.giveExp(xp);
	}
}
