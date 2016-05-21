package r3qu13m.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

public class GlobalTooltip {
	private static List<IGlobalTooltipHandler> handlerList = new ArrayList<>();

	public static void addList(ItemStack par1ItemStack, List par2List) {
		for(IGlobalTooltipHandler handler : handlerList) {
			handler.addInformation(par1ItemStack, par2List);
		}
	}

	public static void registerTooltipHandler(IGlobalTooltipHandler par1Handler) {
		handlerList.add(par1Handler);
	}
}
