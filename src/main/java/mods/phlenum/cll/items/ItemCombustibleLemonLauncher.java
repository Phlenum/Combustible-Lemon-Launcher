package mods.phlenum.cll.items;

import net.minecraft.item.Item;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * 
 * @author Phil Julian
 * @date 05 Jan 2016
 */

public class ItemCombustibleLemonLauncher extends Item {

	public ItemCombustibleLemonLauncher(Properties properties) {
		super(properties);
	}
	private static final String NBTKEY_LEMONTYPE = "lemontype";
	private static final String LOCALIZED_SWITCHED_TYPE = "msg.itemcombustiblelemonlauncher.switchedtype";
/*
	public ItemCombustibleLemonLauncher(String unloc) {
		super();
		setCreativeTab(CommonProxy.tabCLL);
		setUnlocalizedName(unloc);
		setMaxStackSize(1);
		setRegistryName(unloc);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	private static void setLemonType(ItemStack cll, LemonType type) {
		cll.getTagCompound().setByte(NBTKEY_LEMONTYPE, (byte) type.ordinal());
	}

	private static boolean createNBTTagIfNeeded(ItemStack itemstack) {
		if (!itemstack.hasTagCompound()) {
			itemstack.setTagCompound(new NBTTagCompound());
			setLemonType(itemstack, LemonType.LEMONTYPE_NORMAL);
			return true;
		}
		return false;
	}

	private static LemonType getLemonType(ItemStack cll) {
		return LemonType.values()[cll.getTagCompound().getByte(NBTKEY_LEMONTYPE)];
	}

	private static void toggleLemonType(EntityPlayer player, ItemStack cll) {
		switch (getLemonType(cll)) {
		case LEMONTYPE_NORMAL:
			setLemonType(cll, LemonType.LEMONTYPE_EXPLOSION);
			break;
		case LEMONTYPE_EXPLOSION:
			setLemonType(cll, LemonType.LEMONTYPE_NORMAL);
			break;
		}
		if (player.world.isRemote) {
			String msg = I18n.format(LOCALIZED_SWITCHED_TYPE, getLemonType(cll).itemReference.getDisplayName());
			player.sendMessage(new TextComponentString(msg));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand){
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		createNBTTagIfNeeded(itemStackIn);
		if (playerIn.isSneaking()) {
			toggleLemonType(playerIn, itemStackIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}
		if(!playerIn.world.isRemote) {
			LemonType currentType = getLemonType(itemStackIn);
			if (!playerIn.capabilities.isCreativeMode) {
				if (!currentType.playerHasItem(playerIn)) {
					CLLSoundPacket outofammoSound = new CLLSoundPacket(playerIn.getPosition(), (char)CLLSounds.CLL_OUT_OF_AMMO.ordinal(), 0.3F, itemRand.nextFloat());
					CombustibleLemonLauncher.proxy.packetCLL_sendToAll(outofammoSound, (EntityPlayerMP)playerIn);
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
				}
				currentType.consumeItem(playerIn);
			}
			CLLPacketLauncherProcess packetLauncherProcess = new CLLPacketLauncherProcess(currentType);
			CombustibleLemonLauncher.proxy.packetCLL_sendToPlayer(packetLauncherProcess, (EntityPlayerMP) playerIn);
			EntityLemon lemonEnt = new EntityLemon(worldIn, playerIn, currentType);
			lemonEnt.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntity(lemonEnt);
			CLLSoundPacket fireSound = new CLLSoundPacket(playerIn.getPosition(), (char)CLLSounds.CLL_FIRE.ordinal(), 0.3F, itemRand.nextFloat());
			CombustibleLemonLauncher.proxy.packetCLL_sendToAll(fireSound, (EntityPlayerMP)playerIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
*/
}
