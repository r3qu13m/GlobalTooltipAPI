package r3qu13m.core.gtip;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;

public class GlobalTooltipAPIModContainer extends DummyModContainer {
	public GlobalTooltipAPIModContainer() {
		super(new ModMetadata());

		ModMetadata meta = getMetadata();

		meta.modId = "GlobalTooltipAPI";
		meta.name = "Global Tooltip API";
		meta.version = "1.0.0";
		meta.authorList = Arrays.asList("r3qu13m");
		meta.description = "A Global Tooltip API";
		meta.url = "";
		meta.credits = "";
		this.setEnabledState(true);
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}
