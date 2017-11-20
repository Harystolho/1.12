package harystolho.uberminer.world;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class UberExplosion extends Explosion {

	private final boolean causesFire;
	/** whether or not this explosion spawns smoke particles */
	private final boolean damagesTerrain;
	private final Random random;
	private final World world;
	private final double x;
	private final double y;
	private final double z;
	private final Entity exploder;
	private final float size;
	/** A list of ChunkPositions of blocks affected by this explosion */
	private final List<BlockPos> affectedBlockPositions;
	/**
	 * Maps players to the knockback vector applied by the explosion, to send to the
	 * client
	 */
	private final Map<EntityPlayer, Vec3d> playerKnockbackMap;
	private final Vec3d position;

	public UberExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming,
			boolean damagesTerrain) {
		super(worldIn, entityIn, x, y, z, size, flaming, damagesTerrain);
		this.random = new Random();
		this.affectedBlockPositions = Lists.<BlockPos>newArrayList();
		this.playerKnockbackMap = Maps.<EntityPlayer, Vec3d>newHashMap();
		this.world = worldIn;
		this.exploder = entityIn;
		this.size = size;
		this.x = x;
		this.y = y;
		this.z = z;
		this.causesFire = flaming;
		this.damagesTerrain = damagesTerrain;
		this.position = new Vec3d(this.x, this.y, this.z);
	}

	@Override
	public void doExplosionA() {
		Set<BlockPos> set = Sets.<BlockPos>newHashSet();

		for (float x = -this.size; x < this.size + 1; x++) {
			for (float y = -this.size; y < this.size + 1; y++) {
				for (float z = -this.size; z < this.size + 1; z++) {
					BlockPos blockpos = new BlockPos(this.x + x, this.y + y, this.z + z);
					IBlockState iblockstate = this.world.getBlockState(blockpos);
					if (isOre(iblockstate.getBlock())) {
						set.add(blockpos);
					}
				}
			}
		}
		this.affectedBlockPositions.addAll(set);
	}

	@Override
	public void doExplosionB(boolean spawnParticles) {
		this.world.playSound((EntityPlayer) null, this.x, this.y, this.z, SoundEvents.ENTITY_GENERIC_EXPLODE,
				SoundCategory.BLOCKS, 4.0F,
				(1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);

		if (this.size >= 2.0F && this.damagesTerrain) {
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
		} else {
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
		}

		if (this.damagesTerrain) {
			for (BlockPos blockpos : this.affectedBlockPositions) {
				IBlockState iblockstate = this.world.getBlockState(blockpos);
				Block block = iblockstate.getBlock();

				System.out.println(block);

				if (iblockstate.getMaterial() != Material.AIR) {
					block.dropBlockAsItemWithChance(this.world, blockpos, this.world.getBlockState(blockpos), 0.85F, 0);
				}
				block.onBlockExploded(this.world, blockpos, this);
			}
		}
	}

	public boolean isOre(Block block) {
		if (block.getUnlocalizedName().toString().toLowerCase().contains("ore")
				|| block.getLocalizedName().toString().toLowerCase().contains("ore")
				|| block.getLocalizedName().toString().toLowerCase().contains("ic2")) {
			System.out.println(block.getLocalizedName());
			return true;
		}

		return false;
	}

}
