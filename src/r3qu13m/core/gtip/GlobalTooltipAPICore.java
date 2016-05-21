package r3qu13m.core.gtip;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class GlobalTooltipAPICore implements IFMLLoadingPlugin {

	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"r3qu13m.core.gtip.GlobalTooltipAPITransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "r3qu13m.core.gtip.GlobalTooltipAPIModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

}
