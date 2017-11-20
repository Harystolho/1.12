package harystolho.uberminer.entity;

import harystolho.uberminer.world.UberExplosion;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityUberPowder2 extends EntityThrowable {


	public EntityUberPowder2(World worldIn) {
		super(worldIn);

	}

	public EntityUberPowder2(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	@SideOnly(Side.CLIENT)
	public EntityUberPowder2(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public static void registerFixesEnderPearl(DataFixer fixer) {
		EntityThrowable.registerFixesThrowable(fixer, "ThrownUberPowder");
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		EntityLivingBase entitylivingbase = this.getThrower();

		if (result.entityHit != null) {

			if (result.entityHit == this.thrower) {
				return;
			}
		}

		if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
			if (!this.world.isRemote) {

				UberExplosion explosion = new UberExplosion(this.getEntityWorld(), this.thrower, this.posX, this.posY,
						this.posZ, 2, false, true);
				this.setDead();
				explosion.doExplosionA();
				explosion.doExplosionB(false);
				return;
			}
			return;
		}

	}

	@Override
	public void onUpdate() {
		EntityLivingBase entitylivingbase = this.getThrower();

		if (entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.isEntityAlive()) {
			this.setDead();
		} else {
			super.onUpdate();
		}
	}

}
