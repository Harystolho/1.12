package harystolho.uberminer.init;

import java.util.ArrayList;
import java.util.List;

import harystolho.uberminer.items.BowUber;
import harystolho.uberminer.items.RangeModifier;
import harystolho.uberminer.items.SpeedModifier;
import harystolho.uberminer.items.UberPowderTier1;
import harystolho.uberminer.items.UberPowderTier2;
import net.minecraft.item.Item;

public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	
	public static final Item TOOL_UBER = new BowUber("bow_uber");
	
	public static final Item MODIFIER_SPEED = new SpeedModifier("speed_modifier");
	public static final Item MODIFIER_RANGE = new RangeModifier("range_modifier");
	
	public static final Item POWDER_UBER = new UberPowderTier1("uber_powder_1");
	public static final Item POWDER_UBER_2 = new UberPowderTier2("uber_powder_2");
		
	//public static final Item UberModifier = new ItemUberTool("modifier_uber");
	
}
