package convenientadditions.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import convenientadditions.api.network.PacketExtendedExplosion;
import convenientadditions.init.ModNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ExtendedExplosion extends Explosion {
	public boolean damaging=true;
	public boolean grieving=true;
	public float knockbackMultiplier=1f;
	public float damageMultiplier=1f;

    /** whether or not the explosion sets fire to blocks around it */
    private final boolean isFlaming;
    /** whether or not this explosion spawns smoke particles */
    private final boolean isSmoking;
    private final Random explosionRNG;
    private final World worldObj;
    private final double explosionX;
    private final double explosionY;
    private final double explosionZ;
    private final Entity exploder;
    private final float explosionSize;
    private final List<BlockPos> affectedBlockPositions;
    private final Map<EntityPlayer, Vec3d> playerKnockbackMap;
    private final Vec3d position;

    @SideOnly(Side.CLIENT)
    public ExtendedExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions)
    {
        this(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
    }

    @SideOnly(Side.CLIENT)
    public ExtendedExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking, List<BlockPos> affectedPositions)
    {
        this(worldIn, entityIn, x, y, z, size, flaming, smoking);
        this.affectedBlockPositions.addAll(affectedPositions);
    }

    public ExtendedExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking)
    {
    	super(worldIn, entityIn, x, y, z, size, flaming, smoking);
        this.explosionRNG = worldIn.rand;
        this.affectedBlockPositions = Lists.<BlockPos>newArrayList();
        this.playerKnockbackMap = Maps.<EntityPlayer, Vec3d>newHashMap();
        this.worldObj = worldIn;
        this.exploder = entityIn;
        this.explosionSize = size;
        this.explosionX = x;
        this.explosionY = y;
        this.explosionZ = z;
        this.isFlaming = flaming;
        this.isSmoking = smoking;
        this.position = new Vec3d(explosionX, explosionY, explosionZ);
    }
	
	//setters
	
	public ExtendedExplosion setDamaging(boolean b){
		damaging=b;
		return this;
	}
	
	public ExtendedExplosion setGrieving(boolean b){
		grieving=b;
		return this;
	}
	
	public ExtendedExplosion setKnockbackMultiplier(float f){
		knockbackMultiplier=f;
		return this;
	}
	
	public ExtendedExplosion setDamageMultiplier(float f){
		damageMultiplier=f;
		return this;
	}
	
	public World getWorld(){
		return this.worldObj;
	}

	//overrides

    /**
     * Does the first part of the explosion (destroy blocks)
     */
	@SuppressWarnings("unused")
	@Override
    public void doExplosionA()
    {
		if(grieving){
	        Set<BlockPos> set = Sets.<BlockPos>newHashSet();
	        int i = 16;
	
	        for (int j = 0; j < 16; ++j)
	        {
	            for (int k = 0; k < 16; ++k)
	            {
	                for (int l = 0; l < 16; ++l)
	                {
	                    if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15)
	                    {
	                        double d0 = (double)((float)j / 15.0F * 2.0F - 1.0F);
	                        double d1 = (double)((float)k / 15.0F * 2.0F - 1.0F);
	                        double d2 = (double)((float)l / 15.0F * 2.0F - 1.0F);
	                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	                        d0 = d0 / d3;
	                        d1 = d1 / d3;
	                        d2 = d2 / d3;
	                        float f = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
	                        double d4 = this.explosionX;
	                        double d6 = this.explosionY;
	                        double d8 = this.explosionZ;
	
	                        for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F)
	                        {
	                            BlockPos blockpos = new BlockPos(d4, d6, d8);
	                            IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
	
	                            if (iblockstate.getMaterial() != Material.AIR)
	                            {
	                                float f2 = this.exploder != null ? this.exploder.getExplosionResistance(this, this.worldObj, blockpos, iblockstate) : iblockstate.getBlock().getExplosionResistance(worldObj, blockpos, (Entity)null, this);
	                                f -= (f2 + 0.3F) * 0.3F;
	                            }
	
	                            if (f > 0.0F && (this.exploder == null || this.exploder.verifyExplosion(this, this.worldObj, blockpos, iblockstate, f)))
	                            {
	                                set.add(blockpos);
	                            }
	
	                            d4 += d0 * 0.30000001192092896D;
	                            d6 += d1 * 0.30000001192092896D;
	                            d8 += d2 * 0.30000001192092896D;
	                        }
	                    }
	                }
	            }
	        }
	
	        this.affectedBlockPositions.addAll(set);
		}
		
        float f3 = this.explosionSize * 2.0F;
        int k1 = MathHelper.floor(this.explosionX - (double)f3 - 1.0D);
        int l1 = MathHelper.floor(this.explosionX + (double)f3 + 1.0D);
        int i2 = MathHelper.floor(this.explosionY - (double)f3 - 1.0D);
        int i1 = MathHelper.floor(this.explosionY + (double)f3 + 1.0D);
        int j2 = MathHelper.floor(this.explosionZ - (double)f3 - 1.0D);
        int j1 = MathHelper.floor(this.explosionZ + (double)f3 + 1.0D);
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f3);
        Vec3d vec3d = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);

        for (int k2 = 0; k2 < list.size(); ++k2)
        {
            Entity entity = list.get(k2);

            if (!entity.isImmuneToExplosions())
            {
                double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)f3;

                if (d12 <= 1.0D)
                {
                    double d5 = entity.posX - this.explosionX;
                    double d7 = entity.posY + (double)entity.getEyeHeight() - this.explosionY;
                    double d9 = entity.posZ - this.explosionZ;
                    double d13 = (double)MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);

                    if (d13 != 0.0D)
                    {
                        d5 = d5 / d13;
                        d7 = d7 / d13;
                        d9 = d9 / d13;
                        double d14 = (double)this.worldObj.getBlockDensity(vec3d, entity.getEntityBoundingBox());
                        double d10 = (1.0D - d12) * d14;
                        if(damaging)
                        	entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), (float)((int)((d10 * d10 + d10) / 2.0D * 7.0D * (double)f3 + 1.0D)*damageMultiplier));
                        double d11 = 1.0D;

                        if (entity instanceof EntityLivingBase)
                        {
                            d11 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase)entity, d10);
                        }

                        entity.motionX += d5 * d11;
                        entity.motionY += d7 * d11;
                        entity.motionZ += d9 * d11;

                        if (entity instanceof EntityPlayer)
                        {
                            EntityPlayer entityplayer = (EntityPlayer)entity;

                            if (!entityplayer.isSpectator() && (!entityplayer.isCreative() || !entityplayer.capabilities.isFlying))
                            {
                                this.playerKnockbackMap.put(entityplayer, new Vec3d(d5 * d10, d7 * d10, d9 * d10).scale(knockbackMultiplier));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Does the second part of the explosion (sound, particles, drop spawn)
     */
    public void doExplosionB(boolean spawnParticles)
    {
        this.worldObj.playSound(null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

        if (this.explosionSize >= 2.0F && this.isSmoking)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
        }
        else
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
        }

        if (this.isSmoking)
        {
            for (BlockPos blockpos : this.affectedBlockPositions)
            {
                IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
                Block block = iblockstate.getBlock();

                if (spawnParticles)
                {
                    double d0 = (double)((float)blockpos.getX() + this.worldObj.rand.nextFloat());
                    double d1 = (double)((float)blockpos.getY() + this.worldObj.rand.nextFloat());
                    double d2 = (double)((float)blockpos.getZ() + this.worldObj.rand.nextFloat());
                    double d3 = d0 - this.explosionX;
                    double d4 = d1 - this.explosionY;
                    double d5 = d2 - this.explosionZ;
                    double d6 = (double)MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 = d3 / d6;
                    d4 = d4 / d6;
                    d5 = d5 / d6;
                    double d7 = 0.5D / (d6 / (double)this.explosionSize + 0.1D);
                    d7 = d7 * (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
                    d3 = d3 * d7;
                    d4 = d4 * d7;
                    d5 = d5 * d7;
                    this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.explosionX) / 2.0D, (d1 + this.explosionY) / 2.0D, (d2 + this.explosionZ) / 2.0D, d3, d4, d5, new int[0]);
                    this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
                }

                if (iblockstate.getMaterial() != Material.AIR)
                {
                    if (block.canDropFromExplosion(this))
                    {
                        block.dropBlockAsItemWithChance(this.worldObj, blockpos, this.worldObj.getBlockState(blockpos), 1.0F / this.explosionSize, 0);
                    }

                    block.onBlockExploded(this.worldObj, blockpos, this);
                }
            }
        }

        if (this.isFlaming)
        {
            for (BlockPos blockpos1 : this.affectedBlockPositions)
            {
                if (this.worldObj.getBlockState(blockpos1).getMaterial() == Material.AIR && this.worldObj.getBlockState(blockpos1.down()).isFullBlock() && this.explosionRNG.nextInt(3) == 0)
                {
                    this.worldObj.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
                }
            }
        }
    }

    public Map<EntityPlayer, Vec3d> getPlayerKnockbackMap()
    {
        return this.playerKnockbackMap;
    }

    /**
     * Returns either the entity that placed the explosive block, the entity that caused the explosion or null.
     */
    public EntityLivingBase getExplosivePlacedBy()
    {
        return this.exploder == null ? null : (this.exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : (this.exploder instanceof EntityLivingBase ? (EntityLivingBase)this.exploder : null));
    }

    public void clearAffectedBlockPositions()
    {
        this.affectedBlockPositions.clear();
    }

    public List<BlockPos> getAffectedBlockPositions()
    {
        return this.affectedBlockPositions;
    }

    public Vec3d getPosition(){ return this.position; }


    public static Explosion newExplosion(ExtendedExplosion e)
    {
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(e.getWorld(), e)) return e;
        e.doExplosionA();
        e.doExplosionB(false);

        if (!e.isSmoking)
        {
            e.clearAffectedBlockPositions();
        }

        for (EntityPlayer entityplayer : e.getWorld().playerEntities)
        {
            if (entityplayer instanceof EntityPlayerMP && entityplayer.getDistanceSq(e.explosionX, e.explosionY, e.explosionZ) < 4096.0D)
            {
                ModNetworking.INSTANCE.sendTo(new PacketExtendedExplosion(e.explosionX, e.explosionY, e.explosionZ, e.explosionSize, e.getAffectedBlockPositions(), (Vec3d)e.getPlayerKnockbackMap().get(entityplayer)),(EntityPlayerMP)entityplayer);
            }
        }
        
        return e;
    }
}