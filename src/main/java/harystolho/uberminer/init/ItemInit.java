package harystolho.uberminer.init;

import java.util.ArrayList;
import java.util.List;

import harystolho.uberminer.objects.items.BowUber;
import harystolho.uberminer.objects.items.RangeModifier;
import harystolho.uberminer.objects.items.SpeedModifier;
import net.minecraft.item.Item;

public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	
	public static final Item TOOL_UBER = new BowUber("bow_uber");
	
	public static final Item MODIFIER_SPEED = new SpeedModifier("speed_modifier");
	public static final Item MODIFIER_RANGE = new RangeModifier("range_modifier");
		
	//public static final Item UberModifier = new ItemUberTool("modifier_uber");
	
}
