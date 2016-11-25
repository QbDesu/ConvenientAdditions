package convenientadditions.entity.launchingArrow;

import convenientadditions.api.ExtendedExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityLaunchingArrow extends EntityArrow {
    public static final DataParameter<Byte> VARIANT = EntityDataManager.<Byte>createKey(EntityLaunchingArrow.class, DataSerializers.BYTE);

    public EnumLaunchingArrowVariant variant = EnumLaunchingArrowVariant.slime;

    public EntityLaunchingArrow(World worldIn) {
        super(worldIn);
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
    }

    public EntityLaunchingArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
    }

    public EntityLaunchingArrow(World worldIn, EntityLivingBase shooter, EnumLaunchingArrowVariant variant) {
        super(worldIn, shooter);
        this.setVariant(variant);
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    public void onUpdate() {
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
        super.onUpdate();

        if (this.inGround && !worldObj.isRemote) {
            if (worldObj.getClosestPlayerToEntity(this, 1.5D) != null) {
                ExtendedExplosion.newExplosion(EnumLaunchingArrowVariant.getExtendedExplosionFromVariant(getVariant(), this));
                this.setDead();
            }
        }
    }

    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        ExtendedExplosion.newExplosion(EnumLaunchingArrowVariant.getExtendedExplosionFromVariant(getVariant(), this));
        this.setDead();
    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setByte("variant", (byte) this.variant.ordinal());
    }

    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        if (tagCompund.hasKey("variant"))
            setVariant(EnumLaunchingArrowVariant.values()[tagCompund.getByte("variant")]);
        else
            setVariant(EnumLaunchingArrowVariant.values()[0]);
    }

    @Override
    protected void entityInit() {
        this.getDataManager().register(VARIANT, (byte) 2);
        super.entityInit();
    }

    public EnumLaunchingArrowVariant getVariant() {
        if (worldObj.isRemote) {
            Byte b = (Byte) this.getDataManager().get(VARIANT);
            if (b != null && b < EnumLaunchingArrowVariant.values().length)
                return EnumLaunchingArrowVariant.values()[b];
            else
                return EnumLaunchingArrowVariant.slime;
        } else {
            return this.variant;
        }
    }

    public void setVariant(EnumLaunchingArrowVariant variant) {
        this.variant = variant;
        this.getDataManager().set(VARIANT, (byte) variant.ordinal());
        this.getDataManager().setDirty(VARIANT);
    }

    public enum EnumLaunchingArrowVariant {
        creeper(3f, true, true, .75f, .9f),
        blast(3f, false, true, .4f, 1.1f),
        slime(3f, false, false, 0f, 1.6f);

        public float strength = 3f;
        public boolean destroyBlocks = true;
        public boolean doDamage = true;
        public float damageMultiplier = 1f;
        public float knockbackMultiplier = 1f;

        EnumLaunchingArrowVariant(float strength, boolean destroyBlocks, boolean doDamage, float damageMultiplier, float knockbackMultiplier) {
            this.strength = strength;
            this.destroyBlocks = destroyBlocks;
            this.doDamage = doDamage;
            this.damageMultiplier = damageMultiplier;
            this.knockbackMultiplier = knockbackMultiplier;
        }

        public static ExtendedExplosion getExtendedExplosionFromVariant(EnumLaunchingArrowVariant variant, Entity entity) {
            return new ExtendedExplosion(entity.worldObj, entity, entity.posX, entity.posY, entity.posZ, variant.strength, false, true).setDamageMultiplier(variant.damageMultiplier).setDamaging(variant.doDamage).setGrieving(variant.destroyBlocks).setKnockbackMultiplier(variant.knockbackMultiplier);
        }
    }
}
