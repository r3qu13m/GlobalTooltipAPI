package r3qu13m.api;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IGlobalTooltipHandler {
	/**
	 * add Information to Global Tooltip
	 * @param par1ItemStack currentItem
	 * @param par2List Information List
	 */
	public void addInformation(ItemStack par1ItemStack, List par2List);
}
